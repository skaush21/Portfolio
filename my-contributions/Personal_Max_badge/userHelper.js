/* eslint-disable quotes */
/* eslint-disable no-continue */
/* eslint-disable no-await-in-loop */
/* eslint-disable no-console */
/* eslint-disable consistent-return  */
/* eslint-disable no-unused-vars */
/* eslint-disable no-shadow */
/* eslint-disable prefer-destructuring */
/* eslint-disable no-use-before-define */
/* eslint-disable no-unsafe-optional-chaining */
/* eslint-disable no-restricted-syntax */

const mongoose = require('mongoose');
const moment = require('moment-timezone');
const _ = require('lodash');
const userProfile = require('../models/userProfile');
const timeEntries = require('../models/timeentry');
const badge = require('../models/badge');
const myTeam = require('./helperModels/myTeam');
const dashboardHelper = require('./dashboardhelper')();
const reportHelper = require('./reporthelper')();
const emailSender = require('../utilities/emailSender');
const logger = require('../startup/logger');
const token = require('../models/profileInitialSetupToken');
const BlueSquareEmailAssignment = require('../models/BlueSquareEmailAssignment');
const cache = require('../utilities/nodeCache')();
const timeOffRequest = require('../models/timeOffRequest');
const notificationService = require('../services/notificationService');
const { NEW_USER_BLUE_SQUARE_NOTIFICATION_MESSAGE } = require('../constants/message');
const timeUtils = require('../utilities/timeUtils');
const fs = require('fs');
const cheerio = require('cheerio');
const axios = require('axios');
const sharp = require('sharp');
const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

const userHelper = function () {
  // Update format to "MMM-DD-YY" from "YYYY-MMM-DD" (Confirmed with Jae)
  const earnedDateBadge = () => {
    const currentDate = new Date(Date.now());
    return moment(currentDate).tz('America/Los_Angeles').format('MMM-DD-YY');
  };

  const getTeamMembers = function (user) {
    const userId = mongoose.Types.ObjectId(user._id);
    // var teamid = userdetails.teamId;
    return myTeam.findById(userId).select({
      'myTeam._id': 0,
      'myTeam.role': 0,
      'myTeam.fullName': 0,
      _id: 0,
    });
  };

  const getTeamManagementEmail = function (teamId) {
    const parsedTeamId = mongoose.Types.ObjectId(teamId);
    return userProfile
      .find(
        {
          isActive: true,
          teams: {
            $in: [parsedTeamId],
          },
          role: {
            $in: ['Manager', 'Administrator'],
          },
        },
        'email role',
      )
      .exec();
  };

  const getUserName = async function (userId) {
    const userid = mongoose.Types.ObjectId(userId);
    return userProfile.findById(userid, 'firstName lastName');
  };

  const increaseBadgeCount = async function (personId, badgeId) {
    userProfile.updateOne(
      { _id: personId, 'badgeCollection.badge': badgeId },
      {
        $inc: { 'badgeCollection.$.count': 1 },
        $set: { 'badgeCollection.$.lastModified': Date.now().toString() },
        $push: { 'badgeCollection.$.earnedDate': earnedDateBadge() },
      },
      (err) => {
        if (err) {
          console.log(err);
        }
      },
    );
  };

  const addBadge = async function (personId, badgeId, count = 1, featured = false) {
    userProfile.findByIdAndUpdate(
      personId,
      {
        $push: {
          badgeCollection: {
            badge: badgeId,
            count,
            earnedDate: [earnedDateBadge()],
            featured,
            lastModified: Date.now().toString(),
          },
        },
      },
      (err) => {
        if (err) {
          throw new Error(err);
        }
      },
    );
  };

  const removeDupBadge = async function (personId, badgeId) {
    userProfile.findByIdAndUpdate(
      personId,
      {
        $pull: {
          badgeCollection: { badge: mongoose.Types.ObjectId(badgeId) },
        },
      },
      { new: true },
      (err) => {
        if (err) {
          throw new Error(err);
        }
      },
    );
  };

  const changeBadgeCount = async function (personId, badgeId, count) {
    if (count === 0) {
      removeDupBadge(personId, badgeId);
    } else if (count) {
      // Process exisiting earned date to match the new count
      try {
        const userInfo = await userProfile.findById(personId);
        let newEarnedDate = [];
        const recordToUpdate = userInfo.badgeCollection.find(
          (item) => item.badge._id.toString() === badgeId.toString(),
        );
        if (!recordToUpdate) {
          throw new Error(
            `Failed to update badge for ${personId}. Badge not found ${badgeId.toString()}`,
          );
        }
        // If the count is the same, do nothing
        if (recordToUpdate.count === count) {
          return;
        }
        const copyOfEarnedDate = recordToUpdate.earnedDate;
        // Update: We refrain from automatically correcting the mismatch problem as we intend to preserve the original
        // earned date even when a badge is deleted. This approach ensures that a record of badges earned is maintained,
        // preventing oversight of any mismatches caused by bugs.
        if (recordToUpdate.count < count) {
          let dateToAdd = count - recordToUpdate.count;
          // if the EarnedDate count is less than the new count, add a earned date to the end of the collection
          while (dateToAdd > 0) {
            copyOfEarnedDate.push(earnedDateBadge());
            dateToAdd -= 1;
          }
        }
        newEarnedDate = [...copyOfEarnedDate];
        userProfile.updateOne(
          { _id: personId, 'badgeCollection.badge': badgeId },
          {
            $set: {
              'badgeCollection.$.count': count,
              'badgeCollection.$.lastModified': Date.now().toString(),
              'badgeCollection.$.earnedDate': newEarnedDate,
              'badgeCollection.$.hasBadgeDeletionImpact': recordToUpdate.count > count, // badge deletion impact set to true if the new count is less than the old count
            },
          },
          (err) => {
            if (err) {
              throw new Error(err);
            }
          },
        );
      } catch (err) {
        logger.logException(err);
      }
    }
  };

  const getAllWeeksData = async (personId, user) => {
    const userId = mongoose.Types.ObjectId(personId);
    const weeksData = [];
    const currentDate = moment().tz('America/Los_Angeles');
    const startDate = moment(user.createdDate).tz('America/Los_Angeles');
    const numWeeks = Math.ceil(currentDate.diff(startDate, 'days') / 7);

    for (let week = 1; week <= numWeeks; week += 1) {
      const pdtstart = startDate
        .clone()
        .add(week - 1, 'weeks')
        .startOf('week')
        .format('YYYY-MM-DD');
      const pdtend = startDate.clone().add(week, 'weeks').subtract(1, 'days').format('YYYY-MM-DD');
      try {
        const results = await dashboardHelper.laborthisweek(userId, pdtstart, pdtend);
        const { timeSpent_hrs: timeSpent } = results[0];
        weeksData.push(timeSpent);
      } catch (error) {
        console.error(error);
        throw error;
      }
    }
    return weeksData;
  };

  function mergeHours(array1, array2) { 
    const tempHours = [...array1, ...array2];
    return tempHours;
  }

  const updatePersonalMax = async (personId, user) => { // 
    try {
      const weeksData = await getAllWeeksData(personId, user);
      const savedHours = user.savedTangibleHrs; 
      const result = mergeHours(savedHours, weeksData);
      const MaxHrs = Math.max(...result);
      user.personalBestMaxHrs = MaxHrs; 
      await user.save(); 
      
    } catch (error) {
      console.error(error);
    }
  };
  
  // Most recent changes (March 8, 2025)
  // Optimized badge logic and fixed formatting issue.

  // 'Personal Max',
  const checkPersonalMax = async function (personId, user, badgeCollection) {
    let badgeOfType;
    const duplicateBadges = [];
    const currentDate = moment().tz('America/Los_Angeles').format('MMM-DD-YY');

    // verify that "personal max" badge exists in badgeCollection
    for (let i = 0; i < badgeCollection.length; i += 1) {
      if (badgeCollection[i].badge?.type === 'Personal Max') {
        if (!badgeOfType) {
          badgeOfType = badgeCollection[i];
        } else {
          duplicateBadges.push(badgeCollection[i]);
        }
        break;
      }
    }
    // check the badge collection for duplicates
    for (const b of duplicateBadges) {
      await removeDupBadge(personId, b._id);
    }

    if (!badgeOfType) {
      addBadge(personId, mongoose.Types.ObjectId(badgeOfType.badge._id), );
    }
    
    if (
      user.lastWeekTangibleHrs &&
      user.savedTangibleHrs[user.savedTangibleHrs.length-1] > user.lastWeekTangibleHrs &&
      user.lastWeekTangibleHrs >= user.personalBestMaxHrs &&
      !badgeOfType.earnedDate.includes(currentDate)
      ) {
        if (badgeOfType) {
          increaseBadgeCount(personId, mongoose.Types.ObjectId(badgeOfType.badge._id)); 
        } 
    } 
    await updatePersonalMax(personId, user);
  };

  const awardNewBadges = async () => {
    try {
      const users = await userProfile.find({ isActive: true }).populate('badgeCollection.badge');
      for (let i = 0; i < users.length; i += 1) {
        const user = users[i];
        const { _id, badgeCollection } = user;
        const personId = mongoose.Types.ObjectId(_id);

        await checkPersonalMax(personId, user, badgeCollection);

        // remove cache after badge asssignment.
        if (cache.hasCache(`user-${_id}`)) {
          cache.removeCache(`user-${_id}`);
        }
      }
    } catch (err) {
      logger.logException(err);
    }
  };

  const getTangibleHoursReportedThisWeekByUserId = function (personId) {
    const userId = mongoose.Types.ObjectId(personId);

    const pdtstart = moment().tz('America/Los_Angeles').startOf('week').format('YYYY-MM-DD');
    const pdtend = moment().tz('America/Los_Angeles').endOf('week').format('YYYY-MM-DD');

    return timeEntries
      .find(
        {
          personId: userId,
          dateOfWork: { $gte: pdtstart, $lte: pdtend },
          isTangible: true,
        },
        'totalSeconds',
      )
      .then((results) => {
        const totalTangibleWeeklySeconds = results.reduce(
          (acc, { totalSeconds }) => acc + totalSeconds,
          0,
        );
        return (totalTangibleWeeklySeconds / 3600).toFixed(2);
      });
  };


  return {
    changeBadgeCount,
    getUserName,
    getTeamMembers,
    getTeamManagementEmail,
    validateProfilePic,
    assignBlueSquareForTimeNotMet,
    applyMissedHourForCoreTeam,
    deleteBlueSquareAfterYear,
    reActivateUser,
    sendDeactivateEmailBody,
    deActivateUser,
    notifyInfringements,
    getInfringementEmailBody,
    emailWeeklySummariesForAllUsers,
    awardNewBadges,
    checkXHrsForXWeeks,
    getTangibleHoursReportedThisWeekByUserId,
    deleteExpiredTokens,
    deleteOldTimeOffRequests,
    getProfileImagesFromWebsite,
  };
};

module.exports = userHelper;