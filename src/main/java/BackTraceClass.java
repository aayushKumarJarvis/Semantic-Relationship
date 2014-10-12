/*
    Algorithmic approach : Matrix formed by Pearson's Coefficient is of n cross n

                           The row and column number of the maximum correlation
                           gives us the corresponding elements of correlation computation.

                           Reverse the process to find the corresponding words from these
                           word indexes.
 */

import org.apache.commons.math3.linear.RealMatrix;

public class BackTraceClass {

    public static double[][] getCurrentCorrelationMatrix() throws Exception {

        RealMatrix matrix = PearsonCorrelationCoefficientClass.calculatePearsonCoefficient();
        return matrix.getData();
    }

    public static String identifyWordFromFrequencyMatrix() throws Exception {

        double[][] currentMatrix = getCurrentCorrelationMatrix();
        int rowIndex = 0;
        int columnIndex = 0;
        double maxCorrelation = PearsonCorrelationCoefficientClass.getMaximumElement(currentMatrix);

        // Get the row and column index of the maximum correlation coefficient
        for(int i=0;i<currentMatrix.length;i++) {
            for(int j=0;j<currentMatrix[0].length;j++) {
                if(maxCorrelation == currentMatrix[i][j]) {
                    rowIndex = i;
                    columnIndex = j;
                }
            }
        }

        int indexOfWordOne = rowIndex + 1;
        int indexOfWordTwo = columnIndex + 1;

        // Column Index in frequency matrix for both obtained indexes gives us the index of words in uniqueWords array
        double frequencyMatrix[][] = FrequencyMatrixClass.generateFrequencyMatrix();
        int count = 0;
        int uniqueWordsIndexOne = 0;
        int uniqueWordsIndexTwo = 0;

        for(int i=0;i<frequencyMatrix.length;i++) {
            for(int j=0;j<frequencyMatrix[0].length;j++) {
                count++;
                if(count == indexOfWordOne)
                    uniqueWordsIndexOne = j;
                if(count == indexOfWordTwo)
                    uniqueWordsIndexTwo = j;
            }
        }

        // Now get the words from the list of unique words in all documents
        String[] uniqueWordsList = FrequencyMatrixClass.generateUniqueWordsFromAllDocuments();

        String wordOne = uniqueWordsList[uniqueWordsIndexOne];
        String wordTwo = uniqueWordsList[uniqueWordsIndexTwo];

        if(wordOne.equalsIgnoreCase(wordTwo))
            return wordOne;
        else
            return wordOne + " " + wordTwo;
    }

    public static int countElementsInMatrix(double[][] matrix) {

        int count = 0;
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                count++;
            }
        }

        return count;
    }
        
}
