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

    public void createHashTables(String _size) {
        int size = Integer.parseInt(_size);
        for (Message message : this) {
            HashTable hashTable = new HashTable(size);
            String[] words = message.getContent().split("\\s+");

            for (String word : words) {
                hashTable.insert(word);
            }

            for (String word : words) {
                int frequency = hashTable.count(word);
                //TODO: nee dto decide how to save this
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
     * @return the indexes of the spam messages seperated by comma as a string
     */
    public String findSpams(String s, BTree btree) {
        IInputHandler<Spams> inputHandler = new SpamInputHandler<>();
        Spams spams = inputHandler.readFile(s);
        for(Spam ss : spams)
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


