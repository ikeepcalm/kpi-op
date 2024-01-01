package dev.ua.ikeepcalm.equations;

public class SlaeVerifier {
    private final String ANSI_GREEN = "\u001B[32m";

    public void verifyResults(double[][] matrix, double[] x) {
            int matrixSize = matrix.length;

            System.out.println(ANSI_GREEN + "Result verification via simple substitution:\n");
            for (double[] doubles : matrix) {
                for (int j = 0; j < matrixSize; j++) {
                    System.out.print(" + " + doubles[j] + "*x" + (j + 1));
                }
                System.out.println("= " + doubles[matrixSize]);
            }

            double result;
            for (int i = 0; i < matrixSize; i++) {
                double sum = 0;
                for (int j = 0; j < matrixSize; j++) {
                    sum += matrix[i][j] * x[j];
                }
                result = sum;
                System.out.println(ANSI_GREEN + "\nCalculated result for Equation " + (i + 1) + ": " + result);
            }
    }

}
