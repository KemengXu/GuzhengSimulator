import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public int capacity(){
        return this.capacity;
    }
    @Override
    public int fillCount(){
        return this.fillCount;
    }

    @Override
    public void enqueue(T x) {
        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            fillCount += 1;
            if (last == capacity - 1) {
                last = 0;
            } else {
                last += 1;
            }
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T res = rb[first];
        rb[first] = null;
        fillCount -= 1;
        if (first == capacity - 1) {
            first = 0;
        } else {
            first += 1;
        }
        return res;
    }

        /**
         * Return oldest item, but don't remove it. If the buffer is empty, then
         * throw new RuntimeException("Ring buffer underflow").
         */
    @Override
    public T peek () {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }
    @Override
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T>{
        private int count;
        private int pos;
        ArrayRingBufferIterator() {
            count = 0;
            pos = first;
        }
        public boolean hasNext() {
            return count < fillCount();
        }
        public T next() {
            T item = rb[pos];
            pos += 1;
            if (pos == capacity()) {
                pos = 0;
            }
            count += 1;
            return item;
        }
    }
    @Override
    public boolean equals(Object o){
        if (o == null || this == null || o.getClass()!= this.getClass()){return false; }
        ArrayRingBuffer<T> oCopy = (ArrayRingBuffer<T>) o;
        if (oCopy.fillCount != this.fillCount){return false;}
        Iterator<T> oIter = oCopy.iterator();
        Iterator<T> myIter = this.iterator();
        while(myIter.hasNext() && oIter.hasNext()){
            if(myIter.next() != oIter.next()){return false;}
        }
        return true;
    }
}
