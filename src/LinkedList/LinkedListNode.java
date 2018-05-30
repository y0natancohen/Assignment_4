package src.LinkedList;

public class LinkedListNode<T> {

    private LinkedListNode next;
    private LinkedListNode prev;
    private T data;

    LinkedListNode(T data){
        this.data = data;
    }

    T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    LinkedListNode<T> getNext() {
        return next;
    }

    void setNext(LinkedListNode next) {
        this.next = next;
    }

    public LinkedListNode getPrev() {
        return prev;
    }

    void setPrev(LinkedListNode prev) {
        this.prev = prev;
    }
}
