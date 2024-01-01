package dev.ua.ikeepcalm.equations;

import dev.ikeepcalm.interactify.Interactify;

import java.util.Arrays;

public class SlaeSolver {
    private final Interactify interactify;
    private final SlaeVerifier slaeVerifier;
    private final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_RED = "\u001B[31m";
    public SlaeSolver(Interactify interactify, SlaeVerifier slaeVerifier) {
        this.slaeVerifier = slaeVerifier;
        this.interactify = interactify;
    }


    public void solveSlae() {
        double tolerance = interactify
                .useDoubleHandler()
                .askForDoubleWithDecimalLimit(ANSI_GREEN + "Please, enter the tolerance (up to 8 decimal numbers): ", 8);

        double[][] matrix  = interactify
                .useEquationsHandler()
                .askForSlae(ANSI_GREEN + "Please, enter the number of equations (2-6): ", 2, 6);

        double[][] matrixCopy = matrix.clone();
        int matrixSize = matrix.length;
        double[] x = new double[matrixSize];
        double[] xp = new double[matrixSize];
        double delta = Double.MAX_VALUE;

        if (!hasConvergence(matrix, matrixSize)) {
            System.out.println(ANSI_RED + "\nThe matrix is not convergent! This method doesn't support this SLAE. I'm sorry :(");
            return;
        }

        System.out.println("\nSolving the System of Linear Equations (SLAE) using the Iterative Method:\n");
        printSlae(matrix);

        for (int i = 0; i < matrixSize; i++) {
            xp[i] = matrix[i][matrixSize] / matrix[i][i];
        }

        do {
            for (int i = 0; i < matrixSize; i++) {
                double sum = 0;
                for (int j = 0; j < matrixSize; j++) {
                    if (i != j) {
                        sum += matrix[i][j] * xp[j];
                    }
                }
                x[i] = (matrix[i][matrixSize] - sum) / matrix[i][i];
                double abs = Math.abs(x[i] - xp[i]);
                if (abs < delta) {
                    delta = abs;
                }
                xp[i] = x[i];
            }

        } while (delta > tolerance);

        for (int i = 0; i < matrixSize; i++) {
            x[i] = interactify
                    .useDoubleHandler()
                    .roundToDecimalPlaces(x[i], calculateDecimalPlaces(tolerance));
        }

        System.out.println("\nThe result is: " + Arrays.toString(x) + "\n");
        slaeVerifier.verifyResults(matrixCopy, x);
    }

    private void printSlae(double[][] matrix) {
        int matrixSize = matrix.length;
        for (double[] doubles : matrix) {
            for (int j = 0; j < matrixSize + 1; j++) {
                System.out.print(doubles[j] + "\t");
            }
            System.out.println();
        }
    }

    private int calculateDecimalPlaces(double tolerance) {
        int decimalPlaces = 0;
        double multiplier = 1;
        while (tolerance * multiplier < 1) {
            multiplier *= 10;
            decimalPlaces++;
        }
        return decimalPlaces;
    }

    private boolean hasConvergence(double[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += Math.abs(matrix[i][j]);
            }

            sum -= Math.abs(matrix[i][i]);

            if (Math.abs(matrix[i][i]) < sum) {
                return false;
            }
        } return true;
    }

}
