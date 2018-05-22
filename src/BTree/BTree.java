package src.BTree;
import src.Utils;

public class BTree {

    private static int t;
    private static int maxLength;
    private static int minLength;
    private BTreeNode root;

    private BTree(int t) {
        this.root = new BTreeNode(t, true);
        BTree.t = t;
        maxLength = (2 * t) - 1;
        minLength = t - 1;
    }


    public BTree(String s) {
        int t = Integer.parseInt(s);
        new BTree(t);
        // TODO: Java man- can we overload like this?
     }

     public String toString(){
        // TODO: print BFS shit
         return "";
     }


    public void createFullTree(String filePath){
        String friends = Utils.readFile(filePath);
        String[] splitted = friends.split("\n");
        for (int i = 0; i < splitted.length; i++){

            // TODO: can we insert strings instead
            // TODO: of ints to the tree and sort
            // TODO: by Strings somehow?

            int key = Utils.stringToInt(splitted[i]);
            this.insert(key);
        }
    }

    private void insert(int key){
        BTreeNode currRoot = this.root;
        if (currRoot.getNumberOfKeys() == t){
            BTreeNode newRoot = new BTreeNode(maxLength, false);
            this.root = newRoot;
            newRoot.setNthChild(0, currRoot);
            splitChild(newRoot, 1);
            insertNonFull(newRoot, key);
        }
        else{
            insertNonFull(currRoot, key);
        }
    }

    private static void insertNonFull(BTreeNode node, int key){
        int i = node.getNumberOfKeys() - 1;
        if (node.getIsLeaf()){
            _insertToLeaf(i, key, node);
        }
        else{
            _insertToMiddleNode(i, key, node);
        }
    }

    private static void _insertToMiddleNode(int i, int key, BTreeNode node){
        while (i >= 0 && key < node.getNthkey(i)){
            i--;
        }
        int nodeToInsertIndex = i + 1;
        BTreeNode nodeToInsert = node.getNthChild(nodeToInsertIndex);
        if (nodeToInsert.getNumberOfKeys() >= maxLength){
            splitChild(node, nodeToInsertIndex);
            if (key > node.getNthkey(i + 1)){
                nodeToInsert = node.getNthChild(nodeToInsertIndex + 1);
            }
        }
        insertNonFull(nodeToInsert, key);
    }

    private static void _insertToLeaf(int i, int key, BTreeNode node){
        while (i >= 0 && key < node.getNthkey(i)){
            int currValue = node.getNthkey(i);
            node.setNthkey(i + 1, currValue);
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
        // giving the new brother t-1 keys from the left
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
            brother.setNthkey(i+t, 0);
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

    private static void _transferBiggestValue(
            BTreeNode brother, BTreeNode parent, int childToSplitIndex){

        // take the biggest value from the old
        // brother, and put it in the newly open slot

        int biggest = brother.getNthkey(t-1);
        brother.setNthkey(t-1, 0);
        parent.setNthkey(childToSplitIndex, biggest);

        brother.setNumberOfKeys(brother.getNumberOfKeys() - 1);
        parent.setNumberOfKeys(parent.getNumberOfKeys() + 1);
    }
}
