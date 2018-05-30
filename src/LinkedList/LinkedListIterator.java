package src.LinkedList;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {
    private LinkedListNode<T> current;

    LinkedListIterator(LinkedListNode<T> head) {
        current = head;
    }

    @Override
    public boolean hasNext() {
        return current.getNext() != null;
    }

    @Override
    public T next() {
        LinkedListNode<T> res = current.getNext();
        current = current.getNext();
        return res.getData();
    }
}
