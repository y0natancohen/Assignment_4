package src;

//import src.Utils;
//import src.Queue.Queue;

import java.util.StringJoiner;

public class BTree {

    private static int t;
    private static int maxLength;
    private static int minLength;
    // private BTreeNode root;
    // TODO: change private
    public BTreeNode root;

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

//     private String getSymbol(BTreeNode first, BTreeNode second){
//        if first.
//     }
    public String toString1(){
        //TODO: not sure when and how to insert # and ^
        _setHasRightCousins(this.root); // O(log(n))
        _setRightMostBrothers(this.root); // O(n)

        StringBuilder sb = new StringBuilder();

        Queue nodeQ = new Queue();
        Queue signQ = new Queue();

        nodeQ.enqueue(this.root);
        signQ.enqueue("#");

        while (!nodeQ.isEmpty()){
            // System.out.println("nodeQ is : " + nodeQ.toStringNode());
            // System.out.println("signQ is : " + signQ.toString());
            BTreeNode first = nodeQ.dequeueBNode();
            String firstSign = signQ.dequeueString();
            sb.append(first.toString());
            // System.out.println("print is now: " + sb.toString());
            sb.append(firstSign);
            // System.out.println("print is now: " + sb.toString());
//            System.out.println("first.getNumberOfKeys(): " + first.getNumberOfKeys());

            if (!first.getIsLeaf()) {
                for (int i = 0; i <= first.getNumberOfKeys(); i++) {
                    BTreeNode child = first.getNthChild(i);
                    // System.out.println("enquing node: " + child.toString());
                    nodeQ.enqueue(child);

                    if (child.isRightMostBrother()) {
                        // System.out.println("enquing str: #");
                        signQ.enqueue("#");
                    }

                    if (child.hasRightCousins()) {
                        // System.out.println("enquing str: ^");
                        signQ.enqueue("^");
                    }

                    if (i < first.getNumberOfKeys()) {
                        // System.out.println("enquing str: |");
                        signQ.enqueue("|");
                    }
                }
            }
//            System.out.println("enquing str: ^");
//            nodeQ.enqueue("^");

//            sb.append(getSymbol(prev, first));
            // System.out.println("");
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
//
//    private int _getNumOfChildKeys(BTreeNode node){
//        int count = 0;
//        for (BTreeNode child: node.getSons()){
//            if (child != null){
//                count = count + child.getNumberOfKeys();
//            }
//        }
//        return count;
//    }


    private Boolean _lastInLevel(BTreeNode node){
        return false;
    }


    public String toString(){
        StringJoiner sj = new StringJoiner("#");

        int h = height(this.root);
        // System.out.println("doing root lvl: " + h);

        sj.add(_nodeToString(this.root));
        for (int lvl = h - 1; lvl >= 0; lvl--){
            // System.out.println("doing lvl: " + lvl);
            sj.add(_levelToString(this.root, lvl));
            // System.out.println("");

        }

        return sj.toString();

    }


    private static String _levelToString(BTreeNode node, int height){
        int curr = height(node);

        // System.out.println("curr" + curr);
        // System.out.println("wanted" + height);

        if (curr == height + 1){
            // add the sons of that level
            // System.out.println("adding the sons at level " + (curr-1));

//        if (height(node) == height + 1){

            StringJoiner sj = new StringJoiner("|");
            for (int childIndex = 0; childIndex < node.getNumberOfKeys() + 1; childIndex ++) {
                BTreeNode childNode = node.getNthChild(childIndex);
                sj.add(_nodeToString(childNode));
            }
            return sj.toString();

        } else{
            // go deeper
            // System.out.println("going from lvl " + curr + " to lvl " + (curr - 1 ));

            StringJoiner sj = new StringJoiner("^");
            for (int childIndex = 0; childIndex < node.getNumberOfKeys() + 1; childIndex ++) {
                BTreeNode childNode = node.getNthChild(childIndex);
                sj.add(_levelToString(childNode, height));
            }
            return sj.toString();
        }
    }

    private static String _nodeToString(BTreeNode node){
        StringJoiner sj = new StringJoiner(",");
        // System.out.println("inside _nodeToString, node.getNumberOfKeys=" + node.getNumberOfKeys());

        for (int keyIndex = 0; keyIndex < node.getNumberOfKeys(); keyIndex++){
            // System.out.println("adding key: " + node.getNthkey(keyIndex));
            sj.add(node.getNthkey(keyIndex));
        }
        return sj.toString();
    }


     private static int height(BTreeNode node){
        // time complexity: O(log(n))
        if (node.getIsLeaf()){
            return 0;
         }
         else{
            return 1 + height(node.getNthChild(0));
            // this works because BTree is a complete tree
            // and the left-most index is always full in a non-leaf-node
        }
     }


    public void createFullTree(String filePath){
        String friends = Utils.readFile(filePath);
        String[] lines = friends.split("\n");
        for (String line: lines){
//            System.out.println("inserting: "+ line);
            this.insert(line);
        }
    }

//    private void insert(String key){
    // TODO: change to private after testing
    public void insert(String key){
        // System.out.println("starting to insert "+ key);
        BTreeNode currRoot = this.root;
        if (currRoot.getNumberOfKeys() == maxLength){
            // System.out.println("root is full, splitting root");
            BTreeNode newRoot = new BTreeNode(maxLength, false);
            this.root = newRoot;
            newRoot.setNthChild(0, currRoot);
            splitChild(newRoot, 0);
            insertNonFull(newRoot, key);
        }
        else{
            insertNonFull(currRoot, key);
        }
        // System.out.println("finished inserting "+ key);
        // System.out.println("");
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
        // System.out.println("inserting " + key + " into a son of middle node- "+ _nodeToString(node));
        while (i >= 0 && Utils.isBefore(key, node.getNthkey(i))){
            i--;
        }
        int nodeToInsertIndex = i + 1;
        BTreeNode nodeToInsert = node.getNthChild(nodeToInsertIndex);
        // System.out.println("inserting into son num "+nodeToInsertIndex);
        if (nodeToInsert.getNumberOfKeys() >= maxLength){
            // System.out.println("son num "+nodeToInsertIndex+" is full, splitting");

            splitChild(node, nodeToInsertIndex);
            if (Utils.isAfter(key, node.getNthkey(i + 1))){
                nodeToInsert = node.getNthChild(nodeToInsertIndex + 1);
            }
        }
        insertNonFull(nodeToInsert, key);
        // System.out.println("new middle node is- "+ _nodeToString(node));
    }

    private static void _insertToLeaf(int i, String key, BTreeNode node){
        // System.out.println("inserting " + key + " into leaf node- "+ _nodeToString(node));
        while (i >= 0 && Utils.isBefore(key, node.getNthkey(i))){
            String currKey = node.getNthkey(i);
            node.setNthkey(i + 1, currKey);
            i--;
        }
        node.setNthkey(i + 1, key);
        node.setNumberOfKeys(node.getNumberOfKeys() + 1);
        // System.out.println("new leaf node is- "+ _nodeToString(node));
    }

    private static void splitChild(BTreeNode parent, int childToSplitIndex){
        // System.out.println("splitting the " + childToSplitIndex + "th child of" + _nodeToString(parent));
        BTreeNode brother = parent.getNthChild(childToSplitIndex);
        BTreeNode newBrother = new BTreeNode(maxLength, brother.getIsLeaf());

        // System.out.println("giving pointers and keys from left to new brother");
        _giveKeysPointersFromLeft(brother, newBrother);
        // System.out.println("new brother is now- "+_nodeToString(newBrother));

        // System.out.println("deleting pointers and keys from old brother");
        _deleteIrrelevantKeysPointers(brother);
        // System.out.println("old brother is now- "+_nodeToString(brother));

        // System.out.println("offseting pointers and keys from parent and inserting newBrother pointer");
        _offsetKeysPointers(newBrother, parent, childToSplitIndex);
        // System.out.println("parent is now- "+_nodeToString(parent));

        // System.out.println("transferring biggesr value from brother to parent");
        _transferBiggestValue(brother, parent, childToSplitIndex);
        // System.out.println("parent is now- "+_nodeToString(parent));
        // System.out.println("brother is now- "+_nodeToString(brother));

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

    private static void _transferBiggestValue(
            BTreeNode brother, BTreeNode parent, int childToSplitIndex){

        // take the biggest value from the old
        // brother, and put it in the newly open slot

        String biggest = brother.getNthkey(t-1);
        brother.setNthkey(t-1, "");
        parent.setNthkey(childToSplitIndex, biggest);

        brother.setNumberOfKeys(brother.getNumberOfKeys() - 1);
        parent.setNumberOfKeys(parent.getNumberOfKeys() + 1);
    }

    //todo:johny

    /**
     * Search a key in the tree
     * @param key - string value to search
     * @return true if found
     */
    public boolean search(String key) {

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





//            for (int i = 0 ; i < node.getNumberOfKeys(); i++){
//            String currKey = node.getNthkey(i);
//            if (key.equals(currKey)){
//                return true;
//            }
//            if (Utils.isAfter(currKey, key)){
//                BTreeNode childToSearch = node.getNthChild(i);
//                if (childToSearch == null){
//                    return false;
//                }else{
//                    return _search(key, childToSearch);
//                }
//            }
//        }
    }
}