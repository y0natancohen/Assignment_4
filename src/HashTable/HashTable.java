package src.HashTable;

public class HashTable {

    private HashList[] arr;

    public HashTable(int size){
        this.arr = new HashList[size];
    }

    public void insert(String str){
        int indexToInsert = this.hashFunction(str);
        if (this.arr[indexToInsert] == null){
            HashList list = new HashList();
            list.insert(str);
            this.arr[indexToInsert] = list;
        }else{
            this.arr[indexToInsert].insert(str);
        }
    }

    public Boolean search(String str){
        int indexToSearch = this.hashFunction(str);
        HashList listToSearch = this.arr[indexToSearch];
        return listToSearch.search(str);
    }

    private int hashFunction(String str){
        // TODO: figure out a better hash function
        return (int) this._strToNum(str) % this.arr.length;
    }

    private long _strToNum(String str){
        long master = 0;
        for (int i=0; i < str.length(); i++){
            char character = str.charAt(i);
            int intCharacter = (int) character;
            master = master + ((long) (intCharacter * Math.pow(10, i)));

        }
        return master;
    }

}
