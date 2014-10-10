import org.tartarus.snowball.ext.PorterStemmer;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PreprocessDocumentClass {

    private static String OUTPUT_FILE = "/home/aayush/Downloads/stemmedWords.txt";
    private static String OUTPUT_FILE_STOPWORDS = "/home/aayush/Downloads/stopWordsRemoved.txt";

    public static Boolean searchForStopWord(String word, List<String> textForCheck) {

        int indexOfWord = Collections.binarySearch(textForCheck, word);

        if(indexOfWord < 0)
            return false;
        else
            return true;
    }

    public static List<String> readStopWords(String stopWordsFilename) throws Exception {

        FileInputStream fStream = new FileInputStream(stopWordsFilename);

        DataInputStream dataStreamObject = new DataInputStream(fStream);
        BufferedReader objectForBuffer = new BufferedReader(new InputStreamReader(dataStreamObject));

        String strLine;
        String oneLinerString = "";

        while ((strLine = objectForBuffer.readLine()) != null) {
            strLine.trim();
            oneLinerString = oneLinerString + "," + strLine;
        }

        List<String> tokenizedList = Arrays.asList(oneLinerString.split(","));
        fStream.close();

        return tokenizedList;
    }

    public static void removeStopWords(String textFilename, List<String> stopWords)  {

        try {
            FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE_STOPWORDS);
            PrintStream outputFileWriter = new PrintStream(outputStream);

            FileInputStream fStream = new FileInputStream(textFilename);
            DataInputStream dataStreamObject = new DataInputStream(fStream);
            BufferedReader objectForBuffer = new BufferedReader(new InputStreamReader(dataStreamObject));

            String strLine;

            while ((strLine = objectForBuffer.readLine()) != null) {

                boolean flag = false;

                List<String> tokenizedList = Arrays.asList(strLine.split("([^a-zA-z0-9])"));

                for(int i=0;i<tokenizedList.size();i++) {

                    flag = searchForStopWord(tokenizedList.get(i), stopWords);

                    if (!flag)
                        outputFileWriter.print(tokenizedList.get(i)+" ");

                    flag = false;
                }

                outputFileWriter.print("\n");
            }
        }

        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static String stemWordsInDocument(String textFilename)  {

        String resultOutput = "";
        try {
            FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
            PrintStream outputFileWriter = new PrintStream(outputStream);

            FileInputStream fStream = new FileInputStream(textFilename);
            DataInputStream dataStreamObject = new DataInputStream(fStream);
            BufferedReader objectForBuffer = new BufferedReader(new InputStreamReader(dataStreamObject));

            String strLine;
            PorterStemmer stemmer = new PorterStemmer();

            while ((strLine = objectForBuffer.readLine()) != null) {

                List<String> tokenizedList = Arrays.asList(strLine.split("([^a-zA-z0-9])"));

                for(int i=0;i<tokenizedList.size();i++) {

                    stemmer.setCurrent(tokenizedList.get(i));
                    stemmer.stem();
                    outputFileWriter.print(stemmer.getCurrent()+" ");
                    resultOutput = resultOutput + stemmer.getCurrent() + " ";
                }

                outputFileWriter.print("\n");
            }
        }

        catch(Exception e){
            System.err.println(e.getMessage());
        }

        return resultOutput;
    }

}