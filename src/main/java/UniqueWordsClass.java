
import java.io.*;
import java.util.*;

/*
    Class describing the definition to find unique words in a processed document.

 */
public class UniqueWordsClass {

    private static final String OUTPUT_FILE = "/home/aayush/Downloads/uniqueWordsCount.txt";
    private static final String INPUT_FILE = "/home/aayush/Downloads/stemmedWords.txt";

    public static void countUniqueWords() {

        try {
            Map<String, Integer> mapForCount = new HashMap<>();

            FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
            PrintStream outputFileWriter = new PrintStream(outputStream);

            FileInputStream fStream = new FileInputStream(INPUT_FILE);
            DataInputStream dataStreamObject = new DataInputStream(fStream);
            BufferedReader objectForBuffer = new BufferedReader(new InputStreamReader(dataStreamObject));

            String oneLinerString;
            ArrayList<String> listOfTokens = new ArrayList<String>();

            while ((oneLinerString = objectForBuffer.readLine()) != null) {

                String[] tokenizedLine = oneLinerString.split(" ");

                for(int i = 0;i<tokenizedLine.length;i++)
                    listOfTokens.add(i,tokenizedLine[i]);
            }

            for(String words : listOfTokens) {
                Integer count = mapForCount.get(words);
                count = (count == null) ? 1 : ++count;
                mapForCount.put(words, count);
            }

            outputFileWriter.print(mapForCount);
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
