

public class Spam {
    private String spamWord;
    private double threshold;

    public Spam(String spamWord, double threshold) {
        this.spamWord = spamWord;
        this.threshold = threshold;
    }

    public String getSpamWord() {
        return spamWord;
    }

    public void setSpamWord(String spamWord) {
        this.spamWord = spamWord;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
