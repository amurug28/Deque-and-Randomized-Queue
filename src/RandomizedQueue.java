import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A queue that removes items uniformly at random.
 * @param <Item> the type of items stored in this randomized queue
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    /** Construct an empty randomized queue. */
    public RandomizedQueue() {
        // TODO: Initialize an empty array-backed randomized queue. Resize by doubling when full and halving when quarter full.
        items = (Item[]) new Object[1];
        size = 0;
    }

    /** Return whether the randomized queue is empty. */
    public boolean isEmpty() {
        // TODO: Report whether this array-backed queue contains no items.
        return size == 0;
    }

    /** Return the number of items in the randomized queue. */
    public int size() {
        // TODO: Return this array-backed queue's item count.
        return size;
    }

    /** Add an item; reject null with IllegalArgumentException. */
    public void enqueue(Item item) {
        // TODO: Add the item to the backing array, resizing as needed.
        if (item == null)
            throw new IllegalArgumentException();
        else {
            if (size >= items.length){
                Item[] items1 = (Item[]) new Object[size*2];
                for (int i = 0; i < items1.length; i++){
                    items1[i] = items[i];
                }
                items = items1;
            }
            items[size] = item;
            size++;
        }
    }

    /** Remove and return a uniformly random item; throw NoSuchElementException if empty. */
    public Item dequeue() {
        // TODO: Pick a random index between 0 and size-1; swap the chosen item with the last item, then remove and resize as needed.
        if (isEmpty())
            return null;
        else {
            if (size <= items.length/4){
                Item[] items1 = (Item[]) new Object[size/2];
                for (int i = 0; i < size; i++){
                    items1[i] = items[i];
                }
                items = items1;
            }
            int key = (int) (Math.random() * size);
            Item removed = items[key];
            items[key] = items[size];
            size--;
            return removed;
        }
    }

    /** Return a uniformly random item without removing it; throw NoSuchElementException if empty. */
    public Item sample() {
        // TODO: Pick a random index between 0 and size-1 and return that array item without removing it.
        if (isEmpty())
            throw new NoSuchElementException();
        int key = (int)(Math.random()*size);
        Item sample = items[key];
        return sample;
    }

    /** Return an independent iterator in uniformly random order; define next() and remove() exceptions. */
    @Override
    public Iterator<Item> iterator() {
        // TODO: Return an independent iterator that traverses the array items in random order.
        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item>{
        private Item[] shuffledItems;
        private int current;

        RandomizedQueueIterator(){
            shuffledItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++){
                shuffledItems[i] = items[i];
            }
            for (int j = 0; j < 2*size; j++){
                int one = (int)(Math.random()*size);
                int two = (int)(Math.random()*size);
                Item swap = shuffledItems[one];
                shuffledItems[one] = shuffledItems[two];
                shuffledItems[two] = swap;
            }
        }
        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = shuffledItems[current];
            current++;
            return item;
        }
    }

    /** Run unit tests for the randomized queue. */
    public static void main(String[] args) {
        // TODO: Add unit tests.
    }
}
