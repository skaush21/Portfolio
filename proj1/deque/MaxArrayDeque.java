package deque;

import java.util.Comparator;

/* @source URL Link: https://stackoverflow.com/questions/27866943/ */
/** Implementation of the MaxArrayDeque, gives Max value of the Array Deque.
 * @author summit kaushal
 * @param <T>
 */
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    /** creates a new comparator called temparr. */
    private Comparator<T>  temparr;
    /** creates a MaxArrayDeque with the given Comparator.
     * @param c copys comparator */
    public MaxArrayDeque(Comparator<T> c) {
        temparr = c;
    }
    /**
     * returns the max element in the deque as governed
     * by the previously given Comparator. If the
     * MaxArrayDeque is empty, simply return null
     * @return null or max
     */
    public T max() {
        if (max(temparr) != null) {
            return max(temparr);
        } else {
            return null;
        }
    }
    /**
     * returns the max element in the deque as governed
     * by the parameter Comparator c. If the MaxArrayDeque
     * is empty, simply return null
     * @param c
     * @return null or maxval
     */
    public T max(Comparator<T> c) {

        if (temparr == null) {
            return null;
        }
        T maxval = get(0);

        for (int i = 0; i < size(); i++) {
            T x = get(i);
            if (c.compare(maxval, x) < 0) {
                maxval = x;
            }
        }
        return maxval;
    }

}
