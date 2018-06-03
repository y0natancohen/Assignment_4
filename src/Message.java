package src;

//import src.BTree.BTree;
//import src.HashTable.HashTable;

public class Message {

    private String to;
    private String from;
    private String content;
    private HashTable table;
    private int contentLength;

    public Message(String from, String to, String content) {
        this.to = to;
        this.from = from;
        this.content = content;
        this.table = null;
        this.contentLength = 0;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public int getContentLength() {
        return contentLength;
    }

    public HashTable getTable() {
        return table;
    }

    public void setTable(HashTable table) {
        this.table = table;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean isFriendly(BTree btree){
        String option1 = this.from + " & " + this.to;
        String option2 = this.to + " & " + this.from;
        return (btree.search(option1) || btree.search(option2));
    }

//    public String toString() {
//        return from + " & " + to;
//    }
}
