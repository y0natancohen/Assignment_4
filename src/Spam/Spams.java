package src.Spam;

import java.util.Iterator;

public class Spams implements Iterable<Spam> {
    private Spam[] spamMessages;

    public Spams(int spamWordsSize) {
        this.spamMessages = new Spam[spamWordsSize];
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
            return spamMessages[index] != null;
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
