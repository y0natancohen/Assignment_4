package src.LinkedList;


import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private LinkedListNode<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
    }

    public void add(T data) {
        LinkedListNode<T> node = new LinkedListNode<>(data);
        this.add(node);
    }

    private void add(LinkedListNode<T> node) {
        LinkedListNode oldHead = this.head;
        this.head = node;
        node.setNext(oldHead);
        oldHead.setPrev(node);
        size++;
    }

    public int getSize() {
        return size;
    }

    public Boolean search(String str) {
        LinkedListNode tmp = this.head;
        while (tmp != null) {
            if (tmp.getData() == str) {
                return true;
            } else {
                tmp = tmp.getNext();
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }
}
