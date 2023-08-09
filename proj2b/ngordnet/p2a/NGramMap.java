
package ngordnet.p2a;

import java.util.Collection;
import java.util.HashMap;
import edu.princeton.cs.algs4.In;
import ngordnet.ngrams.TimeSeries;

/** An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *  @author Josh Hug
 * @author Summit Kaushal
 * @source https://stackoverflow.com/questions/12557950/
 */
public class NGramMap {
    /*
    1st we take in a string
    then we convert it to an int and from there we
    develop system that will access the content within that converted string
     */
    /** TimeSeries words. */
    private HashMap<String, ngordnet.ngrams.TimeSeries> tswords;
    /** the total amount of years. */
    private ngordnet.ngrams.TimeSeries totalforyear;
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     * @param wordsFilename takes in a string of words.
     * @param countsFilename takes in the data of the file in string form.
     * */
    public NGramMap(String wordsFilename, String countsFilename) {
        // access the files
        In readwords = new In(wordsFilename);
        In readcounts = new In(countsFilename);
        tswords = new HashMap<>();
        // while the file is not empty parse through it and get the data
        while (!readwords.isEmpty()) {
            String[] row = readwords.readLine().split("\\t");
            String word = row[0];
            int year = Integer.parseInt(row[1]);
            double appear = Double.parseDouble(row[2]);

            if (!tswords.containsKey(word)) {
                tswords.put(word, new ngordnet.ngrams.TimeSeries());
            }
            tswords.get(word).put(year, appear);

        }
        totalforyear = new ngordnet.ngrams.TimeSeries();
        while (!readcounts.isEmpty()) {
            String[] row = readcounts.readLine().split(",");
            int year = Integer.parseInt(row[0]);
            double total = Double.parseDouble(row[1]);
            totalforyear.put(year, total);
        }
    }

    /** Provides the history of WORD. The returned TimeSeries should be a copy,
     *  not a link to this NGramMap's TimeSeries. In other words, changes made
     *  to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public ngordnet.ngrams.TimeSeries countHistory(String words) {
        if (tswords.containsKey(words)) {
            return tswords.get(words);
        }
        return null;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     *  returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other words,
     *  changes made to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public ngordnet.ngrams.TimeSeries countHistory(String word, int startYear, int endYear) {
        return new ngordnet.ngrams.TimeSeries(countHistory(word), startYear, endYear);
    }

    /** Returns a defensive copy of the total number of words recorded per year in all volumes. */
    public ngordnet.ngrams.TimeSeries totalCountHistory() {
        return totalforyear;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD compared to
     *  all words recorded in that year. */
    public ngordnet.ngrams.TimeSeries weightHistory(String word) {
        ngordnet.ngrams.TimeSeries whole = countHistory(word);
        return whole.dividedBy(totalforyear);
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     *  and ENDYEAR, inclusive of both ends. */
    public ngordnet.ngrams.TimeSeries weightHistory(String word, int startYear, int endYear) {
        return new ngordnet.ngrams.TimeSeries(weightHistory(word), startYear, endYear);
    }

    /** Returns the summed relative frequency per year of all words in WORDS. */
    public ngordnet.ngrams.TimeSeries summedWeightHistory(Collection<String> words) {
        ngordnet.ngrams.TimeSeries x = new ngordnet.ngrams.TimeSeries();

        for (String word : words) {
            if (countHistory(word) != null) {
                if (x.size() == 0) {
                    x = weightHistory(word);
                } else {
                    x = x.plus(weightHistory(word));
                }
            }
        }
        return x;
    }

    /** Provides the summed relative frequency per year of all words in WORDS
     *  between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     *  this time frame, ignore it rather than throwing an exception. */
    public ngordnet.ngrams.TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        return new TimeSeries(summedWeightHistory(words), startYear, endYear);
    }
}
