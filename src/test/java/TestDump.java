
public class TestDump {

    public static void main(String[] args) throws Exception {

        double[][] frequencyMatrix = FrequencyMatrixClass.generateFrequencyMatrix();
        double[][] correlationMatrix = PearsonCorrelationCoefficientClass.calculatePearsonCoefficient(frequencyMatrix).getData();

        PearsonCorrelationCoefficientClass.displayMatrix(frequencyMatrix);

        System.out.println("");
        PearsonCorrelationCoefficientClass.displayMatrix(correlationMatrix);

        System.out.println("");

        correlationMatrix = BackTraceClass.removeColumnFromMatrix(correlationMatrix,176,206);

    }
}
