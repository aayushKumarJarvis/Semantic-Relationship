import java.util.Map;

public class FrequencyMatrixDriver {

    public static void main(String[] args) throws Exception {

        double[][] frequencyMatrix = FrequencyMatrixClass.generateFrequencyMatrix();

        for(int i=0;i<frequencyMatrix.length;i++) {
            for(int j=0;j<frequencyMatrix[0].length;j++) {
                System.out.print(frequencyMatrix[i][j] + " ");
            }
            System.out.println("");
        }

    }
}
