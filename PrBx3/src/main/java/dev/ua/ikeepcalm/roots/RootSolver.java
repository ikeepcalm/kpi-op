package dev.ua.ikeepcalm.roots;

import java.util.Scanner;

public class RootSolver {

    private final Scanner scanner;
    private final InputUtil inputUtil;

    public RootSolver(Scanner scanner) {
        this.scanner = scanner;
        this.inputUtil = new InputUtil(scanner);
    }

    public void solveRoot() {
        do {
            System.out.println("In order to calculate (y = ᵏ√x with accuracy ε) enter the data:\n");
            System.out.println("Enter the k value (integer values): ");
            int k = inputUtil.readKFromKeyboard();
            System.out.println("Enter the x value (up to 4 digits after comma): ");
            double x = inputUtil.readXFromKeyboard(k);
            System.out.println("Enter the ε accuracy (up to 8 digits after comma): ");
            double e = inputUtil.readEFromKeyboard();

            if (e >= 1) {
                e = 1 / Math.pow(10, e);
            }

            double y = calculateRoot(x, k, e);
            String format = "%." + (int) Math.abs(Math.log10(e)) + "f";
            String formattedResult = String.format(format, y);
            System.out.println("Calculated root is: " + formattedResult);

            System.out.println("Press 'b' to exit this particular Solver, any other key to continue calculations");
        } while (!scanner.next().equals("b"));
    }

    public double calculateRoot(double x, int k, double e) {
        double y = 1;
        double d = (1.0 / Math.abs(k)) * ((x / Math.pow(y, Math.abs(k) - 1)) - y);

        while (Math.abs(d) >= e) {
            y += d;
            d = (1.0 / Math.abs(k)) * ((x / Math.pow(y, Math.abs(k) - 1)) - y);
        }
        if (k < 0) {
            return (1 / y);
        } else {
            return y;
        }
    }

}
