package src.LinkedList;


public class LikedList {
    private LinkedListNode head;
    private LinkedListNode tail;

    public LikedList(){
        this.head = null;
        this.tail = null;
    }

    public void add(String data){
        LinkedListNode node = new LinkedListNode(data);
        this.add(node);
    }

    public void add(LinkedListNode node){
        LinkedListNode oldHead = this.head;
        this.head = node;
        node.setNext(oldHead);
        oldHead.setPrev(node);

    }

    public Boolean search(String str){
        LinkedListNode tmp = this.head;
        while (tmp != null){
            if (tmp.getData() == str){
                return true;
            }else{
                tmp = tmp.getNext();
            }
        }
        return false;
    }

    public LinkedListNode getHead() {
        return head;
    }

    public void setHead(LinkedListNode head) {
        this.head = head;
    }
}
