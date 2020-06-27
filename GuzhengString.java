import java.util.HashSet;

public class GuzhengString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .997; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuzhengString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int)Math.round(SR / frequency));
        while(!buffer.isFull()){buffer.enqueue(0.0);}
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        HashSet used = new HashSet();
        while(!buffer.isEmpty()){buffer.dequeue();}
        while(!buffer.isFull()){
            double r = Math.random() - 0.5;
            while(used.contains(r)){
                r = Math.random() - 0.5;
            }
            used.add(r);
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double first = buffer.dequeue();
        double second = buffer.peek();
        double n = DECAY*0.5*(first + second);
        buffer.enqueue(n);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
