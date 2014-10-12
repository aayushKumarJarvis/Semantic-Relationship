
import org.apache.commons.math3.linear.RealMatrix;

public class PearsonCoefficientDriver {

    public static void main(String args[]) throws Exception {

        RealMatrix matrix = PearsonCorrelationCoefficientClass.calculatePearsonCoefficient();

        double[][] displayMatrix = matrix.getData();

        for(int i=0;i<displayMatrix.length;i++) {
            for(int j=0;j<displayMatrix[0].length;j++) {

                System.out.print(displayMatrix[i][j]+ " ");
            }
            System.out.println("");
        }


        double maximumCorrelationCoefficient = PearsonCorrelationCoefficientClass.getMaximumElement(displayMatrix);
        System.out.println("Maximum Coefficient " + maximumCorrelationCoefficient);
        System.out.println("");

        int rowNumber = 0;
        int columnNumber = 0;

        for(int i=0;i<displayMatrix.length;i++) {
            for(int j=0;j<displayMatrix[0].length;j++) {

                if(displayMatrix[i][j] == maximumCorrelationCoefficient) {
                    rowNumber = i;
                    columnNumber = j;
                }
            }
        }

        System.out.println("Removing Row and Column Number "+rowNumber +" "+columnNumber);

        double[][] newMatrix = PearsonCorrelationCoefficientClass.removeSpecifiedRowColumn(displayMatrix,rowNumber,columnNumber);

        System.out.println("Newly generated Matrix is :");

        PearsonCorrelationCoefficientClass.displayMatrix(newMatrix);
    }


}
