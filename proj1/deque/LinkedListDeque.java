/** Implementation of a Circular Doubly Linked List Deque.
 * @author Summit Kaushal
 * @source Professor Josh Hugs - Lecture code for SLList and Office hours
 * @source Summit Kaushal - Lab3 assignment.
 * */
package deque;

import java.util.Iterator;

/** @author summit
 * @param <T> is a generic type that we are using for our linked list.
 * */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    /** Class node. */
    private class Node {
        /** Name of a variable. */
        private T item;
        /** Name of node. */
        private Node next;
        /** Name of a node. */
        private Node prev;
        /** Constructs a node.
         * @param i
         * @param n
         * @param x
         * */
        private Node(T i, Node n, Node x) {
            item = i;
            next = n;
            prev = x;
        }
    }

    /** Creates a sentinel node and the size of the list. */
    private Node sentinel;

    /** name of a variable size. */
    private int size;

    /** name of a variable. */
    private int count = 0;

    /** Creates an empty deque, where sentinel is the new node of the list. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** initializes a new iterator.
     * @return new linkedlists Iterator. */
    public Iterator<T> iterator() {
        return new LLiterator();
    }
    /** constructs an iterator. */
    private class LLiterator implements Iterator<T> {
        /** Initializes p as sentinel nodes next. */
        private Node p = sentinel.next;
        /** checks the next index.
         * @return !isEmpty && p != sentinel
         * */
        @Override
        public boolean hasNext() {
            return !isEmpty() && p != sentinel;
        }
        /** gets the next index.
         * @return null, item
         * */
        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T item = p.item;
            p = p.next;
            return item;
        }
    }

    /** Adds an item to front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        Node oldfirst = sentinel.next;
        sentinel.next = new Node(item, sentinel.next, sentinel);
        oldfirst.prev = sentinel.next;
        size += 1;
    }

    /** Adds an item to the back of the deque.
     * @param item
     */
    public void addLast(T item) {
        Node lastNode = sentinel.prev;
        sentinel.prev = new Node(item, sentinel, lastNode);
        lastNode.next = sentinel.prev;
        size += 1;
    }

    /** contains the size of the deque.
     * @return the number of items in the deque */
    public int size() {
        return size;
    }

    /** prints the item in the deque from first to last. */
    public void printDeque() {
        Node temp = sentinel;
        while (temp.next != sentinel) {
            temp = temp.next;
            System.out.print(" " + temp.item);
        }
        System.out.println();
    }

    /** removes item at the front of the deque.
     * @return removed item or null.
     * */
    public T removeFirst() {
        if (sentinel.next.item == null) {
            return null;
        } else {
            T x = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return x;
        }
    }

    /** removes the item at the back of the deque.
     * @return removed item or null.
     * */
    public T removeLast() {
        if (sentinel.prev.item == null) {
            return null;
        } else {
            T y =  sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return y;
        }
    }

    /** Iterates through lists length to get the element at the index.
     * @return p.item, null
     */
    public T get(int index) {
        Node p = sentinel;

        if (index < 0 || index > size || isEmpty()) {
            return null;
        }

        for (int i = 0; i < index + 1; i += 1) {
            p = p.next;
        }
        return p.item;
    }
    /** helper function, does the recursive call.
     * @param index
     * @param p
     * @return p.item and RecursiveHelper(index-1, p.next)
     * */
    private T recursiveHelper(int index, Node p) {

        if (index == 0) {
            return p.item;
        } else {
            return recursiveHelper(index - 1, p.next);
        }
    }
    /** get the index using recursion.
     * @param index
     * @return null, ResursiveHelper(index, p.next)
     * */
    public T getRecursive(int index) {
        Node p = sentinel;
        if (index < 0 || index > size || isEmpty()) {
            return null;
        }
        return recursiveHelper(index, p.next);
    }

    /** check whether two objects are equal.
     * @param o
     * @return false, true
     * */
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        } else if (o == this) {
            return true;
        } else if (o instanceof Deque<?>) {
            Deque newobj = (Deque) o;

            if (this.size != newobj.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {

                if (!(get(i).equals(newobj.get(i)))) {
                    return false;
                }
                return true;

            }
        }
        return false;
    }
}
