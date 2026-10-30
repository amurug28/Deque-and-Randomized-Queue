import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue that supports adding and removing items at either end.
 * @param <Item> the type of items stored in this deque
 */
public class Deque<Item> implements Iterable<Item> {

    /** A node in the doubly linked list. */
    private class Node {
        Item item;
        Node next;
        Node prev;
        public Node(Item i){
            Node next,prev;
            item = i;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    /** Construct an empty deque. */
    public Deque() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /** Return whether the deque is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Add an item to the front; reject null items with IllegalArgumentException. */
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        else {
            Node n = new Node(item);
            n.next = head.next;
            n.prev = head;
            head.next = n;
            n.next.prev = n;
            size++;
        }

    }

    /** Add an item to the back; reject null items with IllegalArgumentException. */
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        else {
            Node n = new Node(item);
            n.next = tail;
            n.prev = tail.prev;
            tail.prev = n;
            n.prev.next = n;
            size++;
        }
    }

    /** Remove and return the front item; throw NoSuchElementException if empty. */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            Item old = head.next.item;
            head.next.next.prev = head;
            head.next = head.next.next;
            size--;
            return old;

        }

    }

    /** Remove and return the back item; throw NoSuchElementException if empty. */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            Item old = tail.prev.item;
            tail.prev.prev.next = tail;
            tail.prev = tail.prev.prev;
            size--;
            return old;
        }
    }

    /** Return an iterator from front to back; next() and remove() follow the specified exceptions. */
    @Override
    public Iterator<Item> iterator() {
        // TODO: Return an iterator that follows next links from head to tail.
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        private Node current = head.next;
        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public Item next() {
            if (hasNext()){
                Item cur = current.item;
                current = current.next;
                return cur;
            }
            else throw new NoSuchElementException("no more items");
        }
    }

    /** Run unit tests for the deque. */
    public static void main(String[] args) {
        // TODO: Add unit tests.
    }
}




