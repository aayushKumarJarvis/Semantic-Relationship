/*
    Class definition for calculating Pearson correlation coefficient
    Formula implementation.
 */

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class PearsonCorrelationCoefficientClass {

    public static RealMatrix calculatePearsonCoefficient(double[][] frequencyMatrix) throws Exception {

        PearsonsCorrelation objectOfCorrelation = new PearsonsCorrelation();

        RealMatrix correlationMatrix = objectOfCorrelation.computeCorrelationMatrix(frequencyMatrix);

        return correlationMatrix;
     }

    public static double getMaximumElement(double[][] matrix) {

        double max = matrix[0][0];
        for (int col = 0; col < matrix.length; col++) {
            for (int row = 0; row < matrix[col].length; row++) {
                if (max < matrix[col][row]) {
                    max = matrix[col][row];
                }
            }
        }
        return max;
    }

    public static double[][] removeSpecifiedRowColumn(double[][] matrix, int rowToBeRemoved, int columnToBeRemoved) {

        double destinationArray[][] = new double[matrix.length-1][matrix[0].length-1];

        int p = 0;
        for( int i = 0; i < matrix.length; ++i)
        {
            if ( i == rowToBeRemoved)
                continue;


            int q = 0;
            for( int j = 0; j < matrix[0].length; ++j)
            {
                if ( j == columnToBeRemoved)
                    continue;

                destinationArray[p][q] = matrix[i][j];
                ++q;
            }

            ++p;
        }

        return destinationArray;
    }

    public static void displayMatrix(double[][] matrix) {

        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println("");
        }
    }
}

