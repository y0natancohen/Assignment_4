package src.Messages;

import src.BTree.BTree;
import src.HashTable.HashTable;
import src.InputHandlers.IInputHandler;
import src.InputHandlers.MessageInputHandler;
import src.InputHandlers.SpamInputHandler;
import src.LinkedList.LinkedList;
import src.Spam.Spam;
import src.Spam.Spams;

import java.util.Iterator;

public class Messages implements Iterable<Message> {

    private Message[] messages;

    public Message[] getMessages() {
        return messages;
    }

    public Messages() {

    }

    /**
     * Initiate hash table for each message representing
     * its content
     * @param _size - the size of the hash table to initiate
     */
    public void createHashTables(String _size) {
        int size = Integer.parseInt(_size);
        for (Message message : this) {
            HashTable hashTable = new HashTable(size);
            String[] words = message.getContent().split("\\s+");
            for (String word : words) {
                hashTable.insert(word);
                message.setTable(hashTable);
            }
        }
    }

    private void rawMessagesToArray(LinkedList<Message> rawMessages) {
        messages = new Message[rawMessages.getSize()];
        int i = 0;
        for (Message m : rawMessages) {
            messages[i] = m;
            i++;
        }
    }

    public void generateMessages(String filePath) {
        IInputHandler<LinkedList<Message>> inputHandler = new MessageInputHandler<>();
        rawMessagesToArray(inputHandler.readFile(filePath));
    }

    public Iterator<Message> iterator() {
        return new MessagesIterator();
    }


    /***
     *
     * @param s - path to spam words file
     * @param btree - friendships tree
     * @return the indexes of the spam messages separated by comma
     */
    public String findSpams(String s, BTree btree) {
        IInputHandler<Spams> inputHandler = new SpamInputHandler<>();
        Spams spams = inputHandler.readFile(s);
        /*
        * for each message
        *   1. check if from & to are friends (search bTree)
        *   if not
        *       sum up total word count
        *       2. for each spam word
        *           2.1 find in message content hash
        *           2.2 calc threshold percentage by total word count,
        *               current count in message against spam threshold field
        *           2.3 if threshold reached save message index to returned as "Spamed"
        *   3. return string of "Spamed" messages indexes
        * */

        return null;
    }

    private class MessagesIterator implements Iterator<Message> {
        int index;

        MessagesIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return messages[index] != null;
        }

        @Override
        public Message next() {
            if (!hasNext()) {
                throw new RuntimeException("No Elements in Sequence!");
            }
            Message res = messages[index];
            index++;
            return res;
        }
    }

}


