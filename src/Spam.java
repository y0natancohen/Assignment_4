package src;

public class Spam {
    private String spamWord;
    private int threshold;

    public Spam(String spamWord, int threshold) {
        this.spamWord = spamWord;
        this.threshold = threshold;
    }

    public String getSpamWord() {
        return spamWord;
    }

    public void setSpamWord(String spamWord) {
        this.spamWord = spamWord;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
