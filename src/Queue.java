package src;

//import src.BTree.BTreeNode;

import java.util.StringJoiner;

public class Queue {

    private QueueNode first;
    private QueueNode last;

    public Queue(){
        this.first = null;
        this.last = null;
    }

    public void enqueue(BTreeNode BNode){
        QueueNode qNode = new QueueNode(BNode);
        _enqueue(qNode);
    }

    public void enqueue(String str){
        QueueNode qNode = new QueueNode(str);
        _enqueue(qNode);
    }


    private void _enqueue(QueueNode qNode){
        if (this.first == null){
            this.first = qNode;
            this.last = qNode;
        }else{
            this.last.setPrev(qNode);
            this.last = qNode;
        }
    }

//    public BTreeNode getFirst(){
//        return this.first.getNode();
//    }

//    public QueueNode getLast() {
//        return last;
//    }

    public BTreeNode dequeueBNode(){
        return _dequeueNode().getNode();
    }

    public String dequeueString(){
        return _dequeueNode().getStr();
    }

    private QueueNode _dequeueNode(){
        QueueNode toReturn = this.first;
        this.first = this.first.getPrev();
        return toReturn;
    }

    public String toString() {
        StringJoiner sj = new StringJoiner(",");
        QueueNode temp = this.first;
        while (temp != null){
            sj.add("(" + temp.getStr() + ")");
            temp = temp.getPrev();
        }
        return sj.toString();
    }

    public String toStringNode() {
        StringJoiner sj = new StringJoiner(",");
        QueueNode temp = this.first;
        while (temp != null){
            sj.add("(" + temp.getNode().toString() + ")");
            temp = temp.getPrev();
        }
        return sj.toString();
    }

    public Boolean isEmpty(){
        return (this.first == null);
    }
}
