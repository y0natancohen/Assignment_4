
public class HashList {
    private HashListElement head;

    public HashList() {
        this.head = null;
    }

    /**
     * Inserts a new word to hash table
     * if item already exists increase word count
     * @param wordToAdd - the word to add
     */
    public void insert(String wordToAdd) {
        HashListElement elementToInsert = search(wordToAdd);
        if (elementToInsert == null) {
            HashListElement element = new HashListElement(wordToAdd, 1);
            element.setNext(this.head);
            this.head = element;
        } else {
            elementToInsert.setCount(elementToInsert.getCount() + 1);
        }
    }

    public HashListElement search(String str) {
        HashListElement curr = this.head;
        while (curr != null) {
            if (curr.getData().equals(str)) {
                return curr;
            }
            curr = curr.getNext();
        }
        return null;
    }

}
