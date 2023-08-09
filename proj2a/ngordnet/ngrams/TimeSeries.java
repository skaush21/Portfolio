package ngordnet.ngrams;

import java.util.*;
/** An object for mapping a year number (e.g. 1996) to numerical data. Provides
 *  utility methods useful for data analysis.
 *  @author Josh Hug
 *  @author Summit Kaushal
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     *  inclusive of both end points. */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();

        for (int x : ts.keySet()) {
            // put (Integer key, Double value) // year, data //  ts.get(/* key value */)
            if (startYear <= x && x <= endYear) {
                this.put(x, ts.get(x));
            }
        }
    }

    /** Returns all years for this TimeSeries (in any order). */
    public List<Integer> years() {
        ArrayList y = new ArrayList<Integer>();

        for (int year : this.keySet()) {
            y.add(year);
        }
        return y;
    }

    /** Returns all data for this TimeSeries (in any order).
     *  Must be in the same order as years(). */
    public List<Double> data() {
        ArrayList d = new ArrayList<Double>();

        for (int i : this.keySet()) {
            d.add(get(i));
        }
        return d;
    }

    /** Returns the yearwise sum of this TimeSeries with the given TS. In other words, for
     *  each year, sum the data from this TimeSeries with the data from TS. Should return a
     *  new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries c1 = new TimeSeries();
        Set<Integer> t1 = this.keySet();
        Set<Integer> t2 = ts.keySet();

        for (Integer a1 : t2) {
            double t3 = ts.get(a1).doubleValue();

            if (t1.contains(a1)) {
                t3 += this.get(a1).doubleValue();
            }
            c1.put(a1, new Double(t3));
        }

        for (Integer a1 : t1) {
            double t3 = this.get(a1).doubleValue();

            if (!t2.contains(a1)) {
                c1.put(a1, new Double(t3));
            }
        }
        return c1;
    }

    /** Returns the quotient of the value for each year this TimeSeries divided by the
     *  value for the same year in TS. If TS is missing a year that exists in this TimeSeries,
     *  throw an IllegalArgumentException. If TS has a year that is not in this TimeSeries, ignore it.
     *  Should return a new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries quotient = new TimeSeries();

        for (Integer key : this.keySet()) {
            if (!ts.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            Double value = (this.get(key).doubleValue() / ts.get(key).doubleValue());
            quotient.put(key, value);
        }
        return quotient;
    }

}
