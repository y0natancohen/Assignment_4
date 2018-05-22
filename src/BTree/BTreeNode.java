package src.BTree;

import java.util.LinkedList;

public class BTreeNode {

//    private int t;
    private Boolean isLeaf;
    private BTreeNode[] sons;
    private int[] values;
//    private int maxLength;
//    private int minLength;
    private int numberOfKeys = 0;

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
        this.sons = new BTreeNode[maxLength];
        this.values = new int[maxLength + 1];


    }

    public BTreeNode getNthChild(int n){
        return this.sons[n];
    }

    public void setNthChild(int n , BTreeNode node){
        this.sons[n] = node;
    }

    public int getNthkey(int n){
        return this.values[n];
    }

    public void setNthkey(int n , int value){
        this.values[n] = value;
    }

    public int[] getValues(){
        return this.values;
    }


    public BTreeNode[] getSons(){
        return this.sons;
    }


        }
