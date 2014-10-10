import java.util.List;

/*
    Driver Program will remove all stop words from the document and
    will then remove
 */
public class PreprocessDriver {

    private static final String STOP_WORDS_FILE = "/home/aayush/Downloads/MYSTWORD.TXT";
    private static final String INPUT_FILE = "/home/aayush/Downloads/sportsnews.txt";
    private static final String OUTPUT_FILE = "/home/aayush/Downloads/stopWordsRemoved.txt";

    public static void main(String args[]) {

        try {
            List<String> stopWords = PreprocessDocumentClass.readStopWords(STOP_WORDS_FILE);
            PreprocessDocumentClass.removeStopWords(INPUT_FILE,stopWords);

            PreprocessDocumentClass.stemWordsInDocument(OUTPUT_FILE);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
