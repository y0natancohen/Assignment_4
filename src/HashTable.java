public class HashTable {

    private HashList[] arr;

    public HashTable(int size) {
        if (size < 1) {
            throw new RuntimeException("hash table size cant be < 1 (got " + size + ")");
        }
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

    public HashListElement search(String str) {
        int indexToSearch = this.hashFunction(str);
        HashList listToSearch = this.arr[indexToSearch];
        return listToSearch != null ? listToSearch.search(str) : null;
    }

    /**
     * calculating (str mod arr.length) using horner's rule.
     *
     * @param str to be hashed
     * @return int hash
     */
    public int hashFunction(String str) {
        int master = 0;
        for (int i = 0; i < str.length() - 1; i++) { // this is An to A2 of the polynome
            int intCharacter = (int) str.charAt(i);
            master = ((intCharacter + master) * 256) % this.arr.length;
        }
        // this is A1 of the polynome
        master = (master + (int) str.charAt(str.length() - 1)) % this.arr.length;
        return master;
    }

}
