package deque;
import java.util.Iterator;

/** Creates a circular ArrayDeque.
 * @author Summit Kaushal
 * @param <T>
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    /** name of this array. */
    private T[] items;
    /** name of this variable. */
    private int size;
    /** name of this variable. */
    private int nextfront;
    /** name of this variable. */
    private int nextlast;
    /** name of this variable. */
    private static final int INITLENGTH = 8;
    /** variable for changing the size of array. */
    private static final Double RESIZE = 0.75;
    /** variable for doubling the size. */
    private static final int DOUBLESIZE = 2;

    /** Constructs an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[INITLENGTH];
        size = 0;
        nextfront = 0;
        nextlast  = 1;

    }
    /** Creates a copy of the array with x amount of space.
     * @param x takes in a size
     * */
    private void copy(int x) {
        T[] temp1 = (T[]) new Object[x];
        nextlast = size;

        for (int i = 0; i < size; i++) {
            temp1[i] = get(i);
        }
        items = temp1;
        nextfront = temp1.length - 1;

    }

    /** copies and creates more space for the deque.
     * @param x where 'x' is the size. */
    private void resize(int x) {
        copy(x);

        if (size > INITLENGTH) {
            T[] temp2 = (T[]) new Object[(int) (RESIZE * items.length)];
            nextlast = size;

            for (int j = 0; j < size; j++) {
                temp2[j] = get(j);
            }
            items = temp2;
            nextfront = temp2.length - 1;
        }

    }

    /**  downsizes when there are more empty spaces than there are items.
     * @param x where 'x' is the size. */
    private void downsize(int x) {

        if (size > INITLENGTH) {
            T[] temp2 = (T[]) new Object[(int) (RESIZE * items.length + 1)];
            nextlast = size;

            for (int j = 0; j < size; j++) {
                temp2[j] = this.get(j);
            }
            items = temp2;
            nextfront = temp2.length - 1;
        }

    }

    /** increments the front of the array. */
    private void getFirst() {

        if (items[nextfront] != null) {

            if (nextfront == 0) {
                nextfront = items.length - 1;
            }
        }

    }
    /** gets last element in the array. */
    private void getLast() {

        if (items[nextlast] != null) {

            if (nextlast == items.length - 1) {
                nextlast = 0;
            }

            if (items[nextlast] != null) {
                nextlast += 1;

                if (items[nextlast] != null && size != items.length) {
                    nextlast = nextfront;
                }
            }
        }

    }
    /** Adds an item to the front of the deque.
     * @param item
     * */
    public void addFirst(T item) {

        if (size == items.length) {
            resize(size * DOUBLESIZE);
        }
        items[nextfront] = item;
        nextfront -= 1;

        if (nextfront == -1) {
            nextfront = items.length - 1;
        }
        size += 1;

    }

    /** adds an item of type T to the back of the deque.
     * @param item adds to back.
     * */
    public void addLast(T item) {

        if (size == items.length) {
            resize(size * DOUBLESIZE);
        }
        getLast();
        items[nextlast] = item;
        nextlast += 1;

        if (nextlast == items.length) {
            nextlast = 0;
        }
        size += 1;

    }

    /** returns the number of items in the deque.
     * @return size
     * */
    public int size() {
        return size;
    }

    @Override
    /** returns whether the parameter o is equal to the deque.
     * @param o
     * @return true or false.
     * */
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        } else if (o == this) {
            return true;
        } else if (o instanceof Deque<?>) {
            Deque newo = (Deque) o;
            if (this.size != newo.size()) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (!(get(i).equals(newo.get(i)))) {
                    return false;
                }
                return true;

            }
        }
        return false;

    }

    /** prints item in deque from first to last. */
    public void printDeque() {

        for (int i = 0; i < size; i++) {
            System.out.print(" " + get(i));
        }
        System.out.println();

    }

    /** removes element at the front of the array.
     * @return null or item that was removed
     * */
    public T removeFirst() {

        if (size < 0 || size > items.length || isEmpty()) {
            return null;
        }

        if (size > INITLENGTH && size < RESIZE * items.length) {
            downsize(items.length);
        }
        nextfront += 1;

        if (nextfront == items.length) {
            nextfront = 0;
        }
        T x = null;

        if (items[(nextfront) % items.length] != null) {
            x = items[(nextfront) % items.length];
            items[(nextfront) % items.length] = null;
        }
        size -= 1;
        return x;

    }

    /** removes and returns the item at the back of the deque.
     * @return null or removed item.
     * */
    public T removeLast() {
        if (size < 0 || size > items.length || isEmpty()) {
            return null;
        }
        if (size > INITLENGTH && size < RESIZE * items.length) {
            downsize(items.length);
        }
        nextlast -= 1;

        if (nextlast == -1) {
            nextlast = items.length - 1;
        }
        T x = null;

        if (items[(nextlast) % items.length] != null) {
            x = items[(nextlast) % items.length];
            items[(nextlast) % items.length] = null;
        }
        size -= 1;
        return x;

    }

    /** gets the item at the given index.
     *@param index
     *@return item or null
     * */
    public T get(int index) {

        if (index < 0 || index > items.length || isEmpty()) {
            return null;
        }
        int x = (nextfront + index + 1);
        int i = x % items.length;
        return items[i];
    }
    /** constructing the iterator.
     * @return count < size.
     * @return index of count.
     */
    private class ArrayDequeIterator implements Iterator<T> {
        /** initializing a count to get the items next position.
         * @param count
         */
        private int count = 0;
        @Override
        public boolean hasNext() {
            return count < size;
        }
        @Override
        public T next() {

            if (!hasNext()) {
                return null;
            }
            T x = get(count);
            count += 1;
            return x;
        }
    }
    /** returns new iterator. */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();

    }

}
