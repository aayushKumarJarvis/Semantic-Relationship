import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class FrequencyBasedFilterClass {

    // instance variables
    private static Map<String, Integer> words;
    private static final String OUTPUT_FILE = "/home/aayush/Downloads/frequencyBasedRemoval.txt";

    public FrequencyBasedFilterClass(Scanner sc) {

        words = new HashMap<String, Integer>();

        while(sc.hasNext()) {

            String temp = sc.next().toLowerCase();
            if(words.containsKey(temp)) {
                int freq = words.get(temp);
                freq++;
                words.put(temp, freq);
            }
            else {
                words.put(temp, 1);
            }
        }
    }

    public static String showResults(int num) throws Exception {

        FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
        PrintStream outputFileWriter = new PrintStream(outputStream);

        Set<String> keys = words.keySet();
        ArrayList<WordFreqPair> rankedWords = new ArrayList<WordFreqPair>(keys.size());

        for(String key : keys) {
            int freq = words.get(key);
            WordFreqPair pair = new WordFreqPair(key, freq);
            rankedWords.add(pair);
        }

        Collections.sort(rankedWords);

        int numToShow = Math.min(num, rankedWords.size());
        int mostWords = calcMost(rankedWords);

        int sumOfRankedWordsUpper = 0;
        int sumOfRankedWordsLower = 0;
        int upperLimit;
        int lowerLimit;

        // detailed analysis. Use this in future use cases
        for(int i = 0; i < numToShow; i++) {

            int predicted = (int) (1.0 / (i + 1) * mostWords);
            int actual = rankedWords.get(i).freq;
            double error = 100.0 * (predicted - actual) / predicted;

            String result = "rank: " + (i + 1) + " " + rankedWords.get(i)
                    + ", predicted: " + predicted + " error: " + error;
       }

        //upper limit for Ziph's formula on arbitrary basis
        for(int i = 0; i < numToShow/2; i++)
            sumOfRankedWordsUpper = sumOfRankedWordsUpper + rankedWords.get(i).freq;

        upperLimit = sumOfRankedWordsUpper/(numToShow/2);

        //lower limit for Ziph's formula on arbitrary basis
        for(int i = numToShow/2; i < numToShow; i++ )
            sumOfRankedWordsLower = sumOfRankedWordsLower + rankedWords.get(i).freq;

        lowerLimit = sumOfRankedWordsLower/(numToShow/2);

        //extracting the words between these limits

        String combinedOutput = "";

        for(int i = 0; i<numToShow; i++) {

            int frequency = rankedWords.get(i).freq;

            if(frequency<=upperLimit && frequency>=lowerLimit) {

                WordFreqPair keyValuePair = rankedWords.get(i);
                String pair = keyValuePair.toString();

                combinedOutput += pair.split(" ")[0];
                combinedOutput += " ";

                outputFileWriter.print(pair.split(" ")[0]);
                outputFileWriter.print(" ");
            }
        }

        return combinedOutput;
    }

    private static int calcMost(ArrayList<WordFreqPair> rankedWords) {

        int numToUse = Math.min(rankedWords.size(), 250);
        long total = 0;

        for(int i = 0; i < numToUse; i++) {
            total += (i + 1) * rankedWords.get(i).freq;
        }

        return (int) (total / numToUse);
    }

    public int size() {
        return words.size();
    }

    public void showSome(int num) {

        int i = 0;
        for(String key : words.keySet()) {
            System.out.println(key + " " + words.get(key));
            i++;
            if(i == num)
                break;
        }
    }

    private static class WordFreqPair implements Comparable<WordFreqPair> {

        private int freq;
        private String word;

        private WordFreqPair(String w, int f) {
            freq = f;
            word = w;
        }

        public boolean equals(Object other){

            boolean result = other instanceof WordFreqPair;

            if(result) {
                WordFreqPair otherPair = (WordFreqPair) other;
                result = word.equals(otherPair.word);
            }

            return result;
        }

        public String toString() {
            return word + " " + freq;
        }

        public int compareTo(WordFreqPair other) {
            return other.freq - freq;
        }
    }
}