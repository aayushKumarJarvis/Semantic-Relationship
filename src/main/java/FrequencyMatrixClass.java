/*
    Class design for generation of frequency matrix for
    multiple documents.
 */

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FrequencyMatrixClass {

    private static final String targetDirectory = "/home/aayush/Downloads/Docs";
    private static final String STOP_WORDS_FILE = "/home/aayush/Downloads/MYSTWORD.TXT";
    private static final String STEMMED_WORDS_FILE = "/home/aayush/Downloads/stopWordsRemoved.txt";
    private static final String OUTPUT_FILE = "/home/aayush/Downloads/currentOutput.txt";
    private static final String COMBINED_OUTPUT_FILE = "/home/aayush/Downloads/combinedOutputFile.txt";

    public static String[] generateUniqueWordsFromAllDocuments() throws Exception {

        FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
        PrintStream outputFileWriter = new PrintStream(outputStream);

        FileOutputStream outputStream1 = new FileOutputStream(COMBINED_OUTPUT_FILE);
        PrintStream outputFileWriter1 = new PrintStream(outputStream1);
        File directory = new File(targetDirectory);
        File[] filesInDirectory = directory.listFiles();
        String combinedWordsInAllDocs = "";

        for(File file: filesInDirectory) {
            if (file.isFile()) {

                // Removing StopWords and Stemming the Document
                List<String> stopWords = PreprocessDocumentClass.readStopWords(STOP_WORDS_FILE);
                PreprocessDocumentClass.removeStopWords(file.getAbsolutePath(), stopWords);
                String stemmedWords = PreprocessDocumentClass.stemWordsInDocument(STEMMED_WORDS_FILE);

                // Generating Unique Words in the Document
                File fileObject = new File("/home/aayush/Downloads/stemmedWords.txt");
                Scanner scannerObject;
                scannerObject = new Scanner(fileObject);
                scannerObject.useDelimiter("[^a-zA-Z']+");

                FrequencyBasedFilterClass wi = new FrequencyBasedFilterClass(scannerObject);

                String uniqueWords = wi.showResults(250);
                combinedWordsInAllDocs = combinedWordsInAllDocs + uniqueWords + " ";
                outputFileWriter1.print(combinedWordsInAllDocs);
            }
        }
            // Unique Words in combined words list
            File combinedFileObject = new File(COMBINED_OUTPUT_FILE);
            Scanner obj = new Scanner(combinedFileObject);
            obj.useDelimiter("[^a-zA-Z']+");

            FrequencyBasedFilterClass wiNext = new FrequencyBasedFilterClass(obj);

            String combinedUniqueWords = wiNext.showResults(250);

        return combinedUniqueWords.split(" ");
    }

    public static String[] tokenizeDocument(String textFileName) throws IOException {

        BufferedReader inv = new BufferedReader(new InputStreamReader(new FileInputStream(textFileName)));
        String combinedString = "";
        String oneLinerString = "";

        while ((oneLinerString = inv.readLine()) != null)
            combinedString += oneLinerString;

        String tokenizedList[] = combinedString.split(" ");
        return  tokenizedList;
    }

    // Check for count of unique words in every document.

    public static double[][] generateFrequencyMatrix() throws Exception {

        String[] uniqueWordsFromAllDocuments = generateUniqueWordsFromAllDocuments();

        File directory = new File(targetDirectory);
        File[] filesInDirectory = directory.listFiles();

        double[][] frequencyMatrix = new double[filesInDirectory.length][uniqueWordsFromAllDocuments.length];

        int fileCounter = 0;
        int count = 0;

        for(File file: filesInDirectory) {
            if (file.isFile()) {

                String[] tokensOfFile = tokenizeDocument(file.getAbsolutePath());
                count = 0;

                for (int i = 0; i < uniqueWordsFromAllDocuments.length; i++) {

                    for (int j = 0; j < tokensOfFile.length; j++) {
                        if (uniqueWordsFromAllDocuments[i].equalsIgnoreCase(tokensOfFile[j]))
                            count++;
                    }

                    // storing values in matrix
                    frequencyMatrix[fileCounter][i] = count;
                    count = 0;

                }

                fileCounter++;
            }
        }

        return frequencyMatrix;
    }

}
