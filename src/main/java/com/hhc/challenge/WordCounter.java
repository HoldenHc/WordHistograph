
package com.hhc.challenge;

/**
 *
 * @author hhc
 */
public class WordCounter
        implements Comparable<WordCounter> {

    final String word;
    private Integer wordCount;

    public WordCounter(String word) {
        this.word = word;
        wordCount = 1;
    }

    /**
     * ! signs are flipped to sort in descending order
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(WordCounter other) {
        if (this.wordCount < other.wordCount) {
            return 1;
        } else if (this.wordCount > other.wordCount) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString(int width) {
        StringBuilder sb = new StringBuilder();

        for (int i = width - word.length(); i > 0; i--) {
            sb.append(" ");
        }

        sb.append(word)
                .append(" | ");
        for (int i = 0; i < wordCount; i++) {
            sb.append("=");
        }
        sb.append(" (")
                .append(wordCount)
                .append(")");

        return sb.toString();
    }

    public void incrementWordCount() {
        wordCount++;
    }

}
