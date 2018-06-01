package src.Messages;

import src.HashTable.HashTable;

public class Message {

    private String to;
    private String from;
    private String content;
    private HashTable table;

    public Message(String from, String to, String content) {
        this.to = to;
        this.from = from;
        this.content = content;
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
}
