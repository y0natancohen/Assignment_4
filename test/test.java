package test;

import src.BTree.BTree;
import src.BTree.BTreeNode;

public class test {


    public static void main(String[] args){

        int t = 2;

        String[] friends = new String[11];
        friends[0] = "Han_Solo & Chewbacca";
        friends[1] = "Batman & Robin";
        friends[2] = "Bert & Ernie";
        friends[3] = "Peanut_Butter & Jelly";
        friends[4] = "Tom & Jerry";
        friends[5] = "Scooby_Doo & Shaggy";
        friends[6] = "Miss_Piggy & Kermit";
        friends[7] = "Lennon & McCartney";
        friends[8] = "Chip & Dale";
        friends[9] = "Spongebob & Patrick";
        friends[10] = "Simon & Garfunkel";


        BTreeNode a = new BTreeNode((2*t)-1, true);
        a.setNthkey(0, "a");
        a.setNumberOfKeys(1);

        BTreeNode b = new BTreeNode((2*t)-1, false);
        b.setNthkey(0, "b");
        b.setNumberOfKeys(1);

        BTreeNode c = new BTreeNode((2*t)-1, true);
        c.setNthkey(0, "c");
        c.setNumberOfKeys(1);

        BTreeNode g = new BTreeNode((2*t)-1, true);
        g.setNthkey(0, "g");
        g.setNumberOfKeys(1);

        BTreeNode hm = new BTreeNode((2*t)-1, false);
        hm.setNthkey(0, "h");
        hm.setNthkey(1, "m");
        hm.setNumberOfKeys(2);

        BTreeNode k = new BTreeNode((2*t)-1, true);
        k.setNthkey(0, "k");
        k.setNumberOfKeys(1);

        BTreeNode rwz = new BTreeNode((2*t)-1, true);
        rwz.setNthkey(0, "r");
        rwz.setNthkey(1, "w");
        rwz.setNthkey(2, "z");
        rwz.setNumberOfKeys(3);



        BTree tree  = new BTree(t);
        BTreeNode r = tree.root;
        r.setIsLeaf(false);
        r.setNumberOfKeys(1);
        r.keys[0] = "d";

        r.sons[0] = b;
        r.sons[1] = hm;

        b.sons[0] = a;
        b.sons[1] = c;

        hm.sons[0] = g;
        hm.sons[1] = k;
        hm.sons[2] = rwz;


//        int x = 10;
//        for (int i = 0; i < 10; i++){
//            System.out.println(i);
//        }

        System.out.println("what came out:    " + tree.toString().toUpperCase());

        System.out.print("what is expected: ");
        System.out.println(new StringBuilder("Z,W,R|K|G^C|A#M,H|B#D").reverse().toString());



    }

}
