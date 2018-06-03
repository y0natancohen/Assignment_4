

public class HashListElement {
    private String data;
    private HashListElement next;
    private int count;

    public int getCount() {
        return count;
    }

    public HashListElement(String data, int count) {
        this.data = data;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
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
