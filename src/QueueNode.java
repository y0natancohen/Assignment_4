
public class QueueNode {

    private QueueNode prev;
    private BTreeNode node;
    private String str;

    public QueueNode(BTreeNode node){
        this.node = node;
        this.str = null;
        this.prev = null;
    }

    public QueueNode(String str){
        this.str = str;
        this.node = null;
        this.prev = null;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
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
