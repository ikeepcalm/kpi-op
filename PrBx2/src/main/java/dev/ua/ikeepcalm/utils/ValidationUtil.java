package dev.ua.ikeepcalm.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidationUtil {
    Scanner scanner;

    public ValidationUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public double readDoubleFromKeyboard() {
        boolean isValid = false;
        double input = 0;
        while (!isValid) {
            try {
                input = scanner.nextDouble();
                if (input > 0.1 && input < Double.MAX_VALUE) {
                    scanner.nextLine();
                    isValid = true;
                } else {
                    System.out.println("Given value can not be less or equal to 0.1! Enter the value again: ");
                    scanner.nextLine();
                    continue;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Given value is not valid.\nIt can only be set via digits and single comma. Enter the value again: ");
                scanner.nextLine();
                continue;
            }
        }
        return input;
    }

    public boolean isTriangle(double a, double b, double c) {
        boolean requirements = a + b > c && a + c > b && b + c > a;
        double threshold = 1000.0;
        boolean difference = (a / b) < threshold && (b / a) < threshold  && (c / a) < threshold && (b / c) < threshold && (c / b) < threshold;
        return requirements && difference;
    }

}
