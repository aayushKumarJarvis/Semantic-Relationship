import java.io.File;
import java.util.Scanner;

public class FrequencyBasedFilterDriver {

    public static void main(String[] args) throws Exception {

        File f = new File("/home/aayush/Downloads/stemmedWords.txt");
        Scanner sc;

        sc = new Scanner(f);
        sc.useDelimiter("[^a-zA-Z']+");

        FrequencyBasedFilterClass wi = new FrequencyBasedFilterClass(sc);
        wi.showResults(250);

    }
}
