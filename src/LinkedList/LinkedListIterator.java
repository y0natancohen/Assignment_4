package src.LinkedList;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {
    private LinkedListNode<T> current;

    LinkedListIterator(LinkedListNode<T> head) {
        current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        LinkedListNode<T> toReturn = current;
        current = current.getNext();
        return toReturn.getData();
    }
}
