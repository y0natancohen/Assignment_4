package src.Messages;

import src.HashTable.HashTable;
import src.Utils;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Messages implements Iterable<Message>, Iterator<Message> {

    private Message head;
    private Message tail;
    private Message current;

    public Messages() {
        // create an empty message because I hate Java
        head = new Message("", "", "");
        current = head;
        tail = head;
    }


    public void createHashTables(String _size){
        int size = Integer.parseInt(_size);
        for (Message message: this){
            HashTable hashTable = new HashTable(size);
            String[] words = message.getContent().split("\\s+");

            for (String word: words){
                hashTable.insert(word);
            }

            for (String word: words){
                int frequency = hashTable.count(word);
                //TODO: nee dto decide how to save this
            }
        }
    }


    // TODO: romove this. only for testing
    public void generateMessages(String[][] arrayOfParsed){
        for(String[] parsed :arrayOfParsed) {
            Message message = new Message(parsed[0], parsed[1], parsed[2]);
            this.add(message);
        }
    }


    public void generateMessages(String filePath){
        String txt = Utils.readFile(filePath);
        String[] rawMessages = txt.split("#");
        for(String rawMessage:rawMessages){
            String[] parsed = _parse(rawMessage);
            Message message = new Message(parsed[0], parsed[1], parsed[2]);
            this.add(message);
        }
    }

    private static String[] _parse(String rawMessage){
        // TODO: all the fucking parsing
        return new String[3];
    }


    // The next three methods implement Iterator.
    public boolean hasNext() {
        return (current.getNext() != null);
    }

    public Message next() {
        if (current.getNext() == null){
            throw new NoSuchElementException();
        }
        current = current.getNext();
        return current;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    // This method implements Iterable.
    public Iterator<Message> iterator() {
        return this;
    }


    public void add(Message message){
        message.setPrev(tail);
        tail.setNext(message);
        tail = message;

    }

    public Message getHead() {
        return head;
    }

    public Message getCurrent() {
        return current;
    }
}

//public class Messages implements Iterable{
//
//
//    // TODO: Java man make this iterator shit work
//    // TODO: i hate it i want to shoot irena revayev and tzachi
//
//    private int size;
//    private int curretnIndex;
//    private Message[] messages;
//
//    public Messages(){
//        this.size = 0;
//        this.curretnIndex = 0;
//        this.messages = new Message[0];
//    }
//
//    @Override
//    public Iterator iterator() {
//
//        return new Iterator() {
//
//            @Override
//            public boolean hasNext() {
//                //return (this.curretnIndex < this.size -1 );
//                // TODO i dont know whats happenning
//                return false;
//            }
//
//            @Override
//            public Object next() {
//                return null;
//            }
//        };
//    }
//
//
//
//
//}
