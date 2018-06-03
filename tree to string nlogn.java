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