package src.HashTable;

public class HashListElement {

    private String data;
    private HashListElement next;

    public HashListElement(String data){
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setNext(HashListElement next) {
        this.next = next;
    }

    public HashListElement getNext() {
        return next;
    }
}
