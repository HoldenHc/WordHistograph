package com.hhc.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hhc
 */
public class WordHistograph {

    private static final Map<String, WordCounter> WORDS = new HashMap<>();
    /**
     * skip any non-word characters except '-' and '_'
     */
    private static final String CHAR_TO_SKIP = "[\\W&&[^-_]]";
    private static int longestWord;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner inputFromSystem = new Scanner(System.in);
        String commandString;

        while (true) {
            System.out.println(">>> enter file name or exit:");
            commandString = inputFromSystem.nextLine();
            if (commandString.toLowerCase().equals("exit")) {
                System.exit(0);
            }

            WORDS.clear();
            try {
                readFileToMap(commandString);
            } catch (FileNotFoundException ex) {
                System.out.println(">>> file not found: " + commandString);
                continue;
            }

            printWords();
        }
    }

    private static void readFileToMap(String fileName)
            throws FileNotFoundException {
        File inputFile = new File(fileName);
        Scanner inputFromFile;

        inputFromFile = new Scanner(inputFile);

        while (inputFromFile.hasNext()) {
            String word = inputFromFile.next().replaceAll(CHAR_TO_SKIP, "");
            if (word.length() > longestWord) {
                longestWord = word.length();
            }
            WordCounter wc = WORDS.get(word);
            if (wc == null) {
                WORDS.put(word, new WordCounter(word));
            } else {
                wc.incrementWordCount();
            }
        }
    }

    private static void printWords() {

        StringBuilder outputBuilder = new StringBuilder();

        WordCounter[] wordArray = new WordCounter[WORDS.size()];

        WORDS.values().toArray(wordArray);
        Arrays.parallelSort(wordArray);

        for (WordCounter cc : wordArray) {
            outputBuilder.append(cc.toString(longestWord))
                    .append("\n");
        }

        System.out.println(outputBuilder.toString());
        
        File outputFile = new File("output.txt");
        try (PrintWriter pw = new PrintWriter(outputFile)) {
            pw.append(outputBuilder.toString());
        } catch (FileNotFoundException ex) {
            System.out.println(">>> couldn't save to file: output.txt");
        } 
    }

}
