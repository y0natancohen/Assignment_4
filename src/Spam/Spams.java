package src.Spam;

import src.LinkedList.LinkedList;

import java.util.Iterator;

public class Spams implements Iterable<Spam> {
    private Spam[] spamMessages;

    public Spams(LinkedList<Spam> list) {
        spamMessages = new Spam[list.getSize()];
        int i = 0;
        for (Spam s : list) {
            spamMessages[i] = s;
            i++;
        }
    }

    public Spam[] getSpamMessages() {
        return spamMessages;
    }

    @Override
    public Iterator<Spam> iterator() {
        return new SpamWordsIterator();
    }

    private class SpamWordsIterator implements Iterator<Spam> {
        int index;

        SpamWordsIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index<spamMessages.length;
        }

        @Override
        public Spam next() {
            if (!hasNext()) {
                throw new RuntimeException("No Elements in Sequence!");
            }
            Spam res = spamMessages[index];
            index++;
            return res;
        }
    }
}
