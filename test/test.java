package test;

import src.BTree.BTree;
import src.BTree.BTreeNode;
import src.HashTable.HashTable;
import src.Messages.Message;
import src.Messages.Messages;
import src.Queue.Queue;
import src.Spam.Spam;

public class test {


    public static void main(String[] args){
        System.out.println("---------------------------------------------");
//        testQ();
        System.out.println("---------------------------------------------");
//        testBFS();
        System.out.println("---------------------------------------------");
//        testTree();
        System.out.println("---------------------------------------------");
//        testMessages1();
        System.out.println("---------------------------------------------");
//        testMessages2();
        System.out.println("---------------------------------------------");
//        testPercentage();
        System.out.println("---------------------------------------------");
        testASCII();
        System.out.println("---------------------------------------------");






    }


    public static void testASCII(){
        HashTable table  = new HashTable(50);
        int i = table.hashFunction("Â£300");
        System.out.println("hash of Â£300 is: "+i);
    }
    public static void testPercentage(){
        // TODO: add a table to the message
        Message m = new Message("a", "b", "a b c d a");
        Spam s  = new Spam("a", 5);
        double d = Messages.getPrecentage(m, s);
        test(d, 20.0);
    }

    public static void testQ(){
        Queue q = new Queue();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.dequeueString();
        q.dequeueString();
        q.enqueue("e");
        q.enqueue("f");
        q.enqueue("g");
        q.dequeueString();
        q.dequeueString();
        test("f", q.dequeueString());
    }

//    public static void testMessages1(){
//        Message m1 = new Message("1", "2", "message 1");
//        Message m2 = new Message("2", "1", "message 2");
//        Message m3 = new Message("1", "2", "message 3");
//        Messages messages = new Messages();
//        messages.add(m1);
//        messages.add(m2);
//        messages.add(m3);
//
//        System.out.println("");
//        Message head = messages.getHead();
//        System.out.println(head.getNext());
//        System.out.println(head.getNext().getNext());
//        System.out.println(head.getNext().getNext().getNext());
//        System.out.println("");
//
//        for (Message message:messages) {
//
//            System.out.println(message);
//        }
//    }

//    public static void testMessages2(){
//
//        String[] m1 = new String[3];
//        m1[0] = "a";
//        m1[1] = "b";
//        m1[2] = "message 1";
//
//        String[] m2 = new String[3];
//        m2[0] = "a";
//        m2[1] = "b";
//        m2[2] = "message 2";
//
//        String[] m3 = new String[3];
//        m3[0] = "a";
//        m3[1] = "b";
//        m3[2] = "message 3";
//
//        Messages messages = new Messages();
//        String[][] arrayOfparsed = new String[3][3];
//        arrayOfparsed[0] = m1;
//        arrayOfparsed[1] = m2;
//        arrayOfparsed[2] = m3;
//        messages.generateMessages(arrayOfparsed);
//
//        System.out.println("");
//        Message head = messages.getHead();
//        System.out.println(head.getNext());
//        System.out.println(head.getNext().getNext());
//        System.out.println(head.getNext().getNext().getNext());
//        System.out.println("");
//
//        for (Message message:messages) {
//
//            System.out.println(message);
//        }
//    }

    public static void testTree(){
        for (int t=2; t<5; t++){
            _testTree(t);
        }
    }
    public static void _testTree(int t){

        BTree tree = new BTree(t);

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

        for (int i = 0; i < friends.length; i++){
            tree.insert(friends[i]);
        }
        System.out.println("tree looks like this:");
        System.out.println(tree.toString());


        test(true, tree.search("Spongebob & Patrick"));

        test(true, tree.search("Han_Solo & Chewbacca"));

        test(false ,tree.search("sdfgb4e5ybw4"));

        test(false ,tree.search(""));

        Message m1 = new Message("Garfunkel", "Simon", "kaka1");
        Message m2 = new Message("Simon", "Garfunkel", "kaka2");
        Message m3 = new Message("Simon", "Jerry", "kaka2");
        Message m4 = new Message("Bert", "Bert", "kaka2");

        test(true,m1.isFriendly(tree));
        test(true,m2.isFriendly(tree));
        test(false, m3.isFriendly(tree));
        test(false, m4.isFriendly(tree));


    }



    public static void testBFS(){
        int t = 2;

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

        String found = tree.toString1().toUpperCase();
        String expected = new StringBuilder("Z,W,R|K|G^C|A#M,H|B#D").reverse().toString();
        test(expected, found);


    }


    public static void test(double expected, double found){
        if (found == expected){
            System.out.println("all good");
        }else {
            System.out.println("what came out:    " + found);
            System.out.print("what is expected: " + expected);
            System.out.println();
        }

    }

    public static void test(int expected, int found){
        if (found == expected){
            System.out.println("all good");
        }else {
            System.out.println("what came out:    " + found);
            System.out.print("what is expected: " + expected);
            System.out.println();
        }

    }

    public static void test(String expected, String found){
        if (found.equals(expected)){
            System.out.println("all good");
        }else {
            System.out.println("what came out:    " + found);
            System.out.print("what is expected: " + expected);
            System.out.println();
        }

    }


    public static void test(boolean expected, boolean found){
        if (found == expected){
            System.out.println("all good");
        }else {
            System.out.println("what came out:    " + found);
            System.out.print("what is expected: " + expected);
            System.out.println();
        }

    }

}
