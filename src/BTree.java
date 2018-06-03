public class BTree {

    private static int t;
    private static int maxLength;
    private static int minLength;
    private BTreeNode root;

    public BTree(int t) {
        BTree.t = t;
        validateT(t);
        maxLength = (2 * t) - 1;
        minLength = t - 1;
        this.root = new BTreeNode(maxLength, true);
    }


    public BTree(String s) {
        int t = Integer.parseInt(s);
        validateT(t);
        BTree.t = t;
        maxLength = (2 * t) - 1;
        minLength = t - 1;
        this.root = new BTreeNode(maxLength, true);
     }

     private void validateT(int t){
        if (t<2){throw new RuntimeException("t value must be >= 2 (got "+t+")"); }
     }

    public String toString(){
        _setHasRightCousins(this.root); // O(log(n))
        _setRightMostBrothers(this.root); // O(n)

        StringBuilder sb = new StringBuilder();

        Queue nodeQ = new Queue();
        Queue signQ = new Queue();

        nodeQ.enqueue(this.root);
        signQ.enqueue("#");

        while (!nodeQ.isEmpty()){
            BTreeNode first = nodeQ.dequeueBNode();
            String firstSign = signQ.dequeueString();
            sb.append(first.toString());
            sb.append(firstSign);

            if (!first.getIsLeaf()) {
                for (int i = 0; i <= first.getNumberOfKeys(); i++) {
                    BTreeNode child = first.getNthChild(i);
                    nodeQ.enqueue(child);

                    if (child.isRightMostBrother()) {
                        signQ.enqueue("#");
                    }

                    if (child.hasRightCousins()) {
                        signQ.enqueue("^");
                    }

                    if (i < first.getNumberOfKeys()) {
                        signQ.enqueue("|");
                    }
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);  // removes the last '#'
        return sb.toString();
    }

    private void _setHasRightCousins(BTreeNode node){
        if (node.getIsLeaf()){
            return;
        }
        BTreeNode lastChild = node.getNthChild(node.getNumberOfKeys());
        lastChild.setHasRightCousins(true);
        for (BTreeNode child: node.getSons()){
            if (child != null){
                _setHasRightCousins(child);
            }
        }
    }

    private void _setRightMostBrothers(BTreeNode node){
        node.setRightMostBrother(true);
        node.setHasRightCousins(false);

        if (!node.getIsLeaf()){
            int lastChildIndex = node.getNumberOfKeys();
            _setRightMostBrothers(node.getNthChild(lastChildIndex));
        }

    }

    void createFullTree(String filePath){
        String friends = Utils.readFile(filePath);
        String[] lines = friends.split("\n");
        for (String line: lines){
            this.insert(line);
        }
    }

    private void insert(String key){
        BTreeNode currRoot = this.root;
        if (currRoot.getNumberOfKeys() == maxLength){
            BTreeNode newRoot = new BTreeNode(maxLength, false);
            this.root = newRoot;
            newRoot.setNthChild(0, currRoot);
            splitChild(newRoot, 0);
            insertNonFull(newRoot, key);
        }
        else{
            insertNonFull(currRoot, key);
        }
    }

    private static void insertNonFull(BTreeNode node, String key){
        int i = node.getNumberOfKeys() - 1;
        if (node.getIsLeaf()){
            _insertToLeaf(i, key, node);
        }
        else{
            _insertToMiddleNode(i, key, node);
        }
    }

    private static void _insertToMiddleNode(int i, String key, BTreeNode node){
        while (i >= 0 && Utils.isBefore(key, node.getNthkey(i))){
            i--;
        }
        int nodeToInsertIndex = i + 1;
        BTreeNode nodeToInsert = node.getNthChild(nodeToInsertIndex);
        if (nodeToInsert.getNumberOfKeys() >= maxLength){

            splitChild(node, nodeToInsertIndex);
            if (Utils.isAfter(key, node.getNthkey(i + 1))){
                nodeToInsert = node.getNthChild(nodeToInsertIndex + 1);
            }
        }
        insertNonFull(nodeToInsert, key);
    }

    private static void _insertToLeaf(int i, String key, BTreeNode node){
        while (i >= 0 && Utils.isBefore(key, node.getNthkey(i))){
            String currKey = node.getNthkey(i);
            node.setNthkey(i + 1, currKey);
            i--;
        }
        node.setNthkey(i + 1, key);
        node.setNumberOfKeys(node.getNumberOfKeys() + 1);
    }

    private static void splitChild(BTreeNode parent, int childToSplitIndex){
        BTreeNode brother = parent.getNthChild(childToSplitIndex);
        BTreeNode newBrother = new BTreeNode(maxLength, brother.getIsLeaf());

        _giveKeysPointersFromLeft(brother, newBrother);

        _deleteIrrelevantKeysPointers(brother);

        _offsetKeysPointers(newBrother, parent, childToSplitIndex);

        _transferBiggestValue(brother, parent, childToSplitIndex);

    }

    private static void _giveKeysPointersFromLeft(BTreeNode brother, BTreeNode newBrother){
        for (int i = 0; i < minLength; i++){
            newBrother.setNthkey(i, brother.getNthkey(i+t));
        }
        newBrother.setNumberOfKeys(minLength);


        if (!brother.getIsLeaf()){
            // giving the new brother t pointers from the left
            for (int i = 0; i < minLength + 1; i++){
                newBrother.setNthChild(i, brother.getNthChild(i+t));
            }
        }
    }

    private static void _deleteIrrelevantKeysPointers(BTreeNode brother){
        // deleting keys and pointers that now belong
        // to the new brother
        for (int i = 0; i < minLength; i++){
            brother.setNthkey(i+t, "");
        }

        for (int i = 0; i < minLength + 1; i++){
            brother.setNthChild(i+t, null);
        }
        brother.setNumberOfKeys(t);
    }

    private static void _offsetKeysPointers(
            BTreeNode newBrother, BTreeNode parent,  int childToSplitIndex){
        // offsetting the pointers of the parent to the
        // right, from childToSplitIndex+1 and onwards
        for (int i = parent.getNumberOfKeys(); i > childToSplitIndex; i--){
            parent.setNthChild(i+1, parent.getNthChild(i));
        }
        // insert the new brother to the newly opened slot
        parent.setNthChild(childToSplitIndex + 1, newBrother);


        // offsetting the keys of the parent to the
        // right, from childToSplitIndex and onwards
        for (int i = parent.getNumberOfKeys()-1; i >= childToSplitIndex; i--){
            parent.setNthkey(i+1, parent.getNthkey(i));
        }
    }

    private static void _transferBiggestValue(BTreeNode brother, BTreeNode parent, int childToSplitIndex){
        // take the biggest value from the old
        // brother, and put it in the newly open slot

        String biggest = brother.getNthkey(t-1);
        brother.setNthkey(t-1, "");
        parent.setNthkey(childToSplitIndex, biggest);

        brother.setNumberOfKeys(brother.getNumberOfKeys() - 1);
        parent.setNumberOfKeys(parent.getNumberOfKeys() + 1);
    }

    /**
     * Search a key in the tree
     * @param key - string value to search
     * @return true if found
     */
     boolean search(String key) {
        return _search(key, this.root);
    }

    private static boolean _search(String key, BTreeNode node){
        int i = 0;
        while (i < node.getNumberOfKeys() && Utils.isAfter(key, node.getNthkey(i))){
            i++;
        }
        if (i < node.getNumberOfKeys() && key.equals(node.getNthkey(i))){
            return true;
        }else if (node.getIsLeaf()){
            return false;
        }else{
            return _search(key, node.getNthChild(i));
        }

    }
}
