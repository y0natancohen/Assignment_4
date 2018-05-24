package src.BTree;

public class BTreeNode {

//    private int t;
//    private Boolean isLeaf;
//    private BTreeNode[] sons;
//    private String[] keys;
//    private int numberOfKeys = 0;
    // TODO: change private
    public Boolean isLeaf;
    public BTreeNode[] sons;
    public String[] keys;
    public int numberOfKeys = 0;

    //    private int maxLength;
//    private int minLength;

//    public int getT() {
//        return this.t;
//    }

    public Boolean getIsLeaf() {
        return this.isLeaf;
    }

//    public int getMaxLength() {
//        return this.maxLength;
//    }
//
//    public int getMinLength() {
//        return this.minLength;
//    }

    public int getNumberOfKeys() {
        return this.numberOfKeys;
    }

//    public void setT(int t) {
//        this.t = t;
//    }

    public void setIsLeaf(Boolean leaf) {
        this.isLeaf = leaf;
    }

//    public void setMaxLength(int maxLength) {
//        this.maxLength = maxLength;
//    }
//
//    public void setMinLength(int minLength) {
//        this.minLength = minLength;
//    }

    public void setNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }

    public BTreeNode(int maxLength, Boolean isLeaf){
        this.isLeaf = isLeaf;
//        this.maxLength = this.t * 2 - 1;
//        this.minLength = this.t - 1;
        this.sons = new BTreeNode[maxLength + 1];
        this.keys = new String[maxLength];


    }

    public BTreeNode getNthChild(int n){
        return this.sons[n];
    }

    public void setNthChild(int n , BTreeNode node){
        // System.out.println("setNthChild("+n+", something)");
//        System.out.println("this.sons="+this.sons.toString());
        // System.out.println("this.sons.length="+this.sons.length);

        this.sons[n] = node;
    }

    public String getNthkey(int n){
        return this.keys[n];
    }

    public void setNthkey(int n , String value){
        this.keys[n] = value;
    }

    public String[] getKeys(){
        return this.keys;
    }


    public BTreeNode[] getSons(){
        return this.sons;
    }


        }
