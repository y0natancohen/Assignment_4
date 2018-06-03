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
import java.util.StringJoiner;

public class Messages implements Iterable<Message> {

    private Message[] messages;

    public Messages() {

    }

    /**
     * Initiate hash table for each message representing
     * its content
     *
     * @param size - the size of the hash table to initiate
     */
    public void createHashTables(String size) {
        if (!isInt(size)) {
            throw new RuntimeException("size input is not a valid number");
        }
        for (Message message : this) {
            HashTable hashTable = new HashTable(Integer.valueOf(size));
            String[] words = message.getContent().split("\\s+");
            message.setContentLength(words.length);
            for (String word : words) {
                hashTable.insert(word);
            }
            message.setTable(hashTable);
        }
    }

    private boolean isInt(String input) {
        String validInput = "0123456789";
        for (char c : input.toCharArray()) {
            if (!validInput.contains(String.valueOf(c))) return false;
        }
        return true;
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
     * for each message
     * 1. check if from & to are friends (search bTree)
     * if not
     *  sum up total word count
     *  2. for each spam word
     *      2.1 find in message content hash
     *      2.2 calc threshold percentage by total word count,
     *          current count in message against spam threshold field
     *      2.3 if threshold reached save message index to returned as "Spamed"
     * 3. return string of "Spamed" messages indexes
     *
     * @param s - path to spam words file
     * @param btree - friendships tree
     * @return the indexes of the spam messages separated by comma
     */
    public String findSpams(String s, BTree btree) {
        StringJoiner spamedMessagesIndexes = new StringJoiner(",");
        IInputHandler<Spams> inputHandler = new SpamInputHandler<>();
        Spams spams = inputHandler.readFile(s);
        int messageIndex = 0;
        for (Message message : this) {
            if (!message.isFriendly(btree)) {
                for (Spam spam : spams) {
                    double foundPercentage = getPrecentage(message, spam);
                    if (foundPercentage >= spam.getThreshold()) {
                        spamedMessagesIndexes.add(Integer.toString(messageIndex));
                    }
                }
            }
            messageIndex++;
        }
        return spamedMessagesIndexes.toString();
    }

//    private static double getPrecentage(Message message, Spam spam) {
    // TODO change private
    public static double getPrecentage(Message message, Spam spam) {
        int messageTotalWordCount = message.getContentLength();
        int spamCountInMessage = message.getTable().search(spam.getSpamWord()).getCount();
        return ((double) spamCountInMessage / (double) messageTotalWordCount) * 100.0;
    }

    private class MessagesIterator implements Iterator<Message> {
        int index;

        MessagesIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < messages.length;
        }

        @Override
        public Message next() {
            Message res = messages[index];
            index++;
            return res;
        }
    }

}


