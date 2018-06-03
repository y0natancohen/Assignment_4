import java.util.StringJoiner;

public class BTreeNode {

    private Boolean isLeaf;
    private BTreeNode[] sons;
    private String[] keys;
    private int numberOfKeys = 0;
    private boolean hasRightCousins = false;
    private boolean isRightMostBrother = false;

    public void setHasRightCousins(boolean hasRightCousins) {
        this.hasRightCousins = hasRightCousins;
    }

    public boolean hasRightCousins() {
        return hasRightCousins;
    }

    public boolean isRightMostBrother() {
        return isRightMostBrother;
    }

    public void setRightMostBrother(boolean rightMostBrother) {
        isRightMostBrother = rightMostBrother;
    }

    public Boolean getIsLeaf() {
        return this.isLeaf;
    }

    public int getNumberOfKeys() {
        return this.numberOfKeys;
    }

    public void setNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }

    public BTreeNode(int maxLength, Boolean isLeaf){
        this.isLeaf = isLeaf;
        this.sons = new BTreeNode[maxLength + 1];
        this.keys = new String[maxLength];
    }

    public BTreeNode getNthChild(int n){
        return this.sons[n];
    }

    public void setNthChild(int n , BTreeNode node){
        this.sons[n] = node;
    }

    public String getNthkey(int n){
        return this.keys[n];
    }

    public void setNthkey(int n , String value){
        this.keys[n] = value;
    }

    public BTreeNode[] getSons(){
        return this.sons;
    }

    public String toString(){
        StringJoiner sj = new StringJoiner(",");
        for (String key:this.keys){
             if (key != null) {
                 if (!key.equals("")) {
                     sj.add(key);
                 }
             }
        }
        return sj.toString();

    }
}
