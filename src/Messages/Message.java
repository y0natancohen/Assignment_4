package src.Messages;

import src.HashTable.HashTable;

import java.util.StringJoiner;

public class Message {

    private String to;
    private String from;
    private String content;
    private Message next;
    private Message prev;
    private HashTable hashTable;

    public Message(String from, String to, String content) {
        this.to = to;
        this.from = from;
        this.content = content;
        this.next = null;
    }

    public String toString() {
        StringJoiner sj = new StringJoiner("");
        return sj.add(this.to).add(this.from).add(this.content).toString();
    }

    public Message getNext() {
        return next;
    }

    public Message getPrev() {
        return prev;
    }

    public void setPrev(Message prev) {
        this.prev = prev;
    }

    public String getContent() {
        return content;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public void setNext(Message next) {
        this.next = next;
    }
}
