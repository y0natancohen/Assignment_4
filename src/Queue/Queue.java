package src.Queue;

import src.BTree.BTreeNode;

public class Queue {

    private QueueNode first;

    public Queue(){
        this.first = null;
    }

    public void enqueue(BTreeNode BNode){
        QueueNode qNode = new QueueNode(BNode);
        if (this.first == null){
            this.first = qNode;
        }else{
            qNode.setPrev(this.first);
            this.first = qNode;
        }
    }

    public BTreeNode getFirst(){
        return this.first.getNode();
    }


    public BTreeNode dequeue(){
        BTreeNode toReturn = this.first.getNode();
        this.first = this.first.getPrev();
        return toReturn;
    }

    public Boolean isEmpty(){
        return (this.first == null);
    }
}
