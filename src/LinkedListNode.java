

public class LinkedListNode<T> {

    private LinkedListNode next;
    private T data;

    LinkedListNode(T data){
        this.data = data;
    }

    T getData() {
        return data;
    }

    LinkedListNode<T> getNext() {
        return next;
    }

    void setNext(LinkedListNode next) {
        this.next = next;
    }

}
