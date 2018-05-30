package src.Queue;

import src.BTree.BTreeNode;

public class QueueNode {

    private QueueNode prev;
    private BTreeNode node;

    public QueueNode(BTreeNode node){
        this.node = node;
        this.prev = null;
    }

    public void setNode(BTreeNode node) {
        this.node = node;
    }

    public void setPrev(QueueNode prev) {
        this.prev = prev;
    }

    public QueueNode getPrev() {
        return this.prev;
    }

    public BTreeNode getNode() {
        return this.node;
    }
}
