package src.HashTable;

public class HashList {

    private HashListElement head;

    public HashList(){
        this.head = null;
    }

    public void insert(String str){
        HashListElement element = new HashListElement(str);
        element.setNext(this.head);
        this.head = element;
    }

    public Boolean search(String str){
        HashListElement curr = this.head;
        while (curr != null){
            if (curr.getData().equals(str)){
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    public int count(String str){
        int counter = 0;
        HashListElement curr = this.head;
        while (curr != null) {
            if (curr.getData().equals(str)) {
                counter++;
            }
            curr = curr.getNext();
        }
        return counter;
    }
}
