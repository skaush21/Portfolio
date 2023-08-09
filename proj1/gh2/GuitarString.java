package gh2;

import deque.Deque;
import deque.LinkedListDeque;

/** Guitar String.
 * @author Summit Kaushal
 * */
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;
    private static final double DECAY = .996;

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(((double) SR / frequency));
        buffer = new LinkedListDeque<Double>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        Deque<Double> randomBuffer = new LinkedListDeque<>();
        for (int i = 0; i < buffer.size(); i++) {
            randomBuffer.addLast(Math.random() - 0.5);
        }
        buffer = randomBuffer;

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double front = buffer.removeFirst();
        double newDouble = DECAY * ((front + buffer.get(0)) * 0.5);
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
