package src.LinkedList;


public class LikedList {
    private LinkedListNode head;
    private LinkedListNode tail;

    public LikedList(){
        this.head = null;
        this.tail = null;
    }

    public void add(int data){
        LinkedListNode node = new LinkedListNode(data);
        add(node);
    }

    public void add(LinkedListNode node){
        LinkedListNode oldHead = this.head;
        this.head = node;
        node.setNext(oldHead);
        oldHead.setPrev(node);

    }

    public Boolean search(int x){
        LinkedListNode tmp = this.head;
        while (tmp != null){
            if (tmp.getData() == x){
                return true;
            }else{
                tmp = tmp.getNext();
            }
        }
    }
}
