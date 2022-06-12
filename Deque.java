/* *****************************************************************************
 *  Code behind a basic queue
 *  Thanks folks at Princeton for presenting the demonstration and supplying
 *  some of the code leveraged here.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int n;

    private class Node {
        Item item;
        Node next, previous;
    }

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (first.next == null) {
            last = new Node();
            last.item = item;
            last.previous = null;
        }
        else if (last.previous == null) {
            last.previous = first;
            first.next = last;
        }
        else {
            oldfirst.previous = first;
        }
        n++;
    }

    public void addLast(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;
        if (oldlast.previous == null) {
            first.item = item;
            first.next = null;
        }
        else if (first.next == null) {
            first.next = last;
            last.previous = first;
        }
        else {
            oldlast.next = last;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("underflow");
        }
        else if (n == 1) {
            Item item = first.item;
            first = null;
            last = null;
            n--;
            return item;
        }
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("underflow");
        }
        else if (n == 1) {
            Item item = first.item;
            first = null;
            last = null;
            n--;
            return item;
        }
        Item item = last.item;
        last = last.previous;
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        private Node end = last;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {
        Deque<String> queue = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String wordNow = StdIn.readString();
            queue.addFirst(wordNow);
        }

        for (String s : queue) {
            System.out.println(s);
        }
    }
}
