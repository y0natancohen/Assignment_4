package src.HashTable;

public class HashTable {

    private HashList[] arr;

    public HashTable(int size) {
        this.arr = new HashList[size];
    }

    public void insert(String str) {
        int indexToInsert = this.hashFunction(str);
        if (this.arr[indexToInsert] == null) {
            HashList list = new HashList();
            list.insert(str);
            this.arr[indexToInsert] = list;
        } else {
            this.arr[indexToInsert].insert(str);
        }
    }

    public int count(String str) {
        int indexToSearch = this.hashFunction(str);
        HashList listToSearch = this.arr[indexToSearch];
        return listToSearch.count(str);
    }

    public Boolean search(String str) {
        int indexToSearch = this.hashFunction(str);
        HashList listToSearch = this.arr[indexToSearch];
        return listToSearch.search(str);
    }

    private int hashFunction(String str) {
        return (int) this._rollingHash(str);
    }

    private long _rollingHash(String str) {
        int firstIntCharacter = (int) str.charAt(0);
        long master = (long) firstIntCharacter % this.arr.length; // this is An of the polynome

        for (int i = 1; i < str.length(); i++) { // this is An-1 to A1 of the polynome
            int intCharacter = (int) str.charAt(i);
            master = (intCharacter + master * 256) % this.arr.length;
        }
        return master;
    }

    // TODO: change private
    public int hashFunction1(String str) {
        // TODO: figure out a better hash function
        return (int) this._strToNum(str) % this.arr.length;
    }

    private long _strToNum(String str) {
        long master = 0;
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            int intCharacter = (int) character;
            master = master + ((long) (intCharacter * Math.pow(256, i)));

        }
        return master;
    }

}
