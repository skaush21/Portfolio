# New_Personal_Mas_Record_Badge

## Overview
This feature handles the assignment and display of the **Personal Max Record** badge. It also displays the **earned date** of a badge and the **highest recorded hours** achieved.


## Change History
### **Fix 1 (May 14, 2024)**

* When the weekly record is broken, a new **Max Record Badge** appears in the **Badge section** on the user's dashboard.
* The **number displayed** on the badge icon now represents the **highest number of hours achieved**.
* The **Badge History list** now logs each new Max Record Badge with an **earned date** and an **incremented count** for every instance the badge is earned.
**PR Link** [Link to PR #1](https://github.com/OneCommunityGlobal/HGNRest/pull/947)

### **Fix 2 (March 8, 2025)**

* **Fixed** a formatting issue causing **earnedDates** to be inaccurate.
* **Optimized code** to improve clarity and reduce unnecessary function calls.
* **Added** a helper function to merge `Time_Entries` and `user.savedTangibleHrs` datasets efficiently.
* **cleaned** the code by :
    * Removing unnecessary comments left from debugging.
    * Eliminating redudanct **promise queries**
    * Improving variable naming and structure for better readability.
* **Refactored** the `badgeOfType` assignment logic to prevent unnecessary badge collection updates.
* **Revised** and **added commets** to enhance code readability.
**PR Link** [Link to PR #1](https://github.com/OneCommunityGlobal/HGNRest/pull/1193)