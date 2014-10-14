
public class PatternStringGeneration {

    // Generating the starting parameters
    public static void generatePatternString() throws Exception {

        double[][] frequencyMatrix = FrequencyMatrixClass.generateFrequencyMatrix();
        double[][] correlationMatrix = PearsonCorrelationCoefficientClass.calculatePearsonCoefficient(frequencyMatrix).getData();

        // counter variable to check for exhaustion of matrix.
        int count = BackTraceClass.countElementsInMatrix(frequencyMatrix);

        while(count != 0) {

            String uniqueWord = BackTraceClass.identifyWordFromFrequencyMatrix(correlationMatrix);
            System.out.println("Pattern String " + uniqueWord);

        }

    }

    public static void main(String[] args) throws Exception {

        generatePatternString();

    }

}
