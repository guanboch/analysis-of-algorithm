// each deque operation requires constant worst time, thus it must be a linklist implementation

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque <Item> implements Iterable<Item>{
    private int totalN;
    private node front, tail;


    private class node {
        node prev;
        node next;
        Item item;
        private node(Item item){
            prev = null;
            next = null;
            this.item = item;
        }

    }

    public Deque(){
        front = new node(null);
        tail = new node(null);
        front.next = tail;
        tail.prev = front;
        totalN = 0;

    }

    // is the deque empty?
    public boolean isEmpty(){
        return totalN<=0;
    }

    // return the number of items on the deque
    public int size(){
        return totalN;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item != null) {
            node newFirst = new node(item);
            newFirst.next = front.next;
            front.next.prev = newFirst;
            front.next = newFirst;
            newFirst.prev = front;
            totalN++;
        }else{
            throw new IllegalArgumentException("element cant be null");
        }
    }

    // add the item to the back
    public void addLast(Item item){
        if(item != null) {
            node newLast = new node(item);
            newLast.prev = tail.prev;
            tail.prev.next = newLast;
            tail.prev = newLast;
            newLast.next = tail;
            totalN++;
        }else{
            throw new IllegalArgumentException("element cant be null");
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (totalN >=1 ){
            node tmp = front.next;
            Item tmpItem = tmp.item;
            front.next = tmp.next;
            tmp.next.prev = front;
            tmp.item = null;
            tmp.prev = null;
            tmp.next = null;
            totalN--;
            return tmpItem;
        }else{
            throw new NoSuchElementException();

        }
    }

    // remove and return the item from the back
    public Item removeLast() throws NoSuchFieldException {
        if (totalN >= 1){
            node tmp = tail.prev;
            Item tmpItem = tmp.item;
            tail.prev = tmp.prev;
            tmp.prev.next = tail;
            tmp.item = null;
            tmp.prev = null;
            tmp.next = null;
            totalN--;
            return tmpItem;
        }else{
            throw new NoSuchElementException();
        }
    }


    @Override
    public Iterator<Item> iterator() {
        return new linklistIterator();
    }

    private class linklistIterator implements Iterator<Item>{

        private node current = front;

        @Override
        public boolean hasNext() {
            return current.next.item!=null;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if(current.next.item == null){
                throw new NoSuchElementException();
            }else {
                Item tmp = current.next.item;
                current = current.next;
                return tmp;
            }
        }

    }

    // unit testing (required)
    public static void main(String[] args) throws NoSuchFieldException {

        Deque<Integer> testDeque = new Deque<Integer>();
        testDeque.addFirst(3);
        testDeque.addFirst(5);
        testDeque.addLast(12);
        testDeque.addLast(0);
        StdOut.println(testDeque.removeFirst());
        StdOut.println(testDeque.removeLast());
        StdOut.println(testDeque.isEmpty());
        StdOut.println(testDeque.size());

        Iterator dequeIterator = testDeque.iterator();
        StdOut.println(dequeIterator.hasNext());

        for(int i : testDeque){
            StdOut.println(i);
        }



    }

}
