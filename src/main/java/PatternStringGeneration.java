
public class PatternStringGeneration {


    // Generating the starting parameters
    public static void generatePatternString() throws Exception {

        double[][] frequencyMatrix = FrequencyMatrixClass.generateFrequencyMatrix();
        double[][] correlationMatrix = PearsonCorrelationCoefficientClass.calculatePearsonCoefficient(frequencyMatrix).getData();

        // counter variable to check for exhaustion of matrix.
        int count = BackTraceClass.countElementsInMatrix(frequencyMatrix);

        // *********************WHILE LOOP WILL START HERE ************************* //

        while (count != 0) {

            // Identifying the maximum correlation element
            double maxCorrelation = PearsonCorrelationCoefficientClass.getMaximumElement(correlationMatrix);

            // identifying the columns to be deleted in the latest matrix.

            int rowIndex = 0;
            int columnIndex = 0;

            for (int i = 0; i < correlationMatrix.length; i++) {
                for (int j = 0; j < correlationMatrix[0].length; j++) {
                    if (maxCorrelation == correlationMatrix[i][j]) {
                        rowIndex = i;
                        columnIndex = j;
                    }
                }
            }

            int indexOfWordOne = rowIndex + 1;
            int indexOfWordTwo = columnIndex + 1;

            int uniqueWordsIndexOne = 0;
            int uniqueWordsIndexTwo = 0;
            int wordCounter = 0;

            for (int i = 0; i < frequencyMatrix.length; i++) {
                for (int j = 0; j < frequencyMatrix[0].length; j++) {
                    wordCounter++;
                    if (wordCounter == indexOfWordOne)
                        uniqueWordsIndexOne = j;
                    if (wordCounter == indexOfWordTwo)
                        uniqueWordsIndexTwo = j;
                }
            }


            // Now get the words from the list of unique words in all documents
            String[] uniqueWordsList = FrequencyMatrixClass.generateUniqueWordsFromAllDocuments();

            String wordOne = uniqueWordsList[uniqueWordsIndexOne];
            String wordTwo = uniqueWordsList[uniqueWordsIndexTwo];

            if(wordTwo == wordOne) {
                System.out.print(wordOne + " ");

            }

            //Modifying the two Matrices and countOfElements
            frequencyMatrix = BackTraceClass.removeColumnFromMatrix(frequencyMatrix, indexOfWordOne, indexOfWordTwo);

            correlationMatrix = PearsonCorrelationCoefficientClass.calculatePearsonCoefficient(frequencyMatrix).getData();

            count = BackTraceClass.countElementsInMatrix(frequencyMatrix);

        }


    }

    public static void main(String[] args) throws Exception {

        generatePatternString();

    }

}
