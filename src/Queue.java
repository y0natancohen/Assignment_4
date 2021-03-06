import java.util.StringJoiner;

public class Queue {

    private QueueNode first;
    private QueueNode last;

    public Queue(){
        this.first = null;
        this.last = null;
    }

    public void enqueue(BTreeNode BNode){
        if (BNode == null){
            throw new RuntimeException("Cant insert null to queue");
        }
        QueueNode qNode = new QueueNode(BNode);
        _enqueue(qNode);
    }

    public void enqueue(String str){
        if (str == null || str.isEmpty()){
            throw new RuntimeException("Cant insert null or empty to queue");
        }
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

    public Boolean isEmpty(){
        return (this.first == null);
    }
}
