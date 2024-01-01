package dev.ua.ikeepcalm.taylor;

import java.util.Locale;
import java.util.Scanner;

public class TaylorExpander {

    private final Scanner scanner;
    private final InputUtil inputUtil;

    public TaylorExpander(Scanner scanner, InputUtil inputUtil) {
        this.scanner = scanner;
        this.inputUtil = inputUtil;
    }

    public void expandFunction() {
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Choose the function to expand: sin or cos");
            String chosenEngine = scanner.next();
            String sin = "sin";
            String cos = "cos";
            scanner.nextLine();

            if (!(chosenEngine.equals(sin) || chosenEngine.equals(cos))) {
                System.out.println("Given value is not valid. Function can only be 'sin' or 'cos'\n" +
                        "Press 'b' to exit this particular Module, any other key to try again");
                continue;
            }

            System.out.println("In order to expand chosen function, enter the input data\n");
            System.out.println("Enter the x1 value (integer values): ");
            int x1 = inputUtil.readXFromKeyboard();
            System.out.println("Enter the x2 value (integer values): ");
            int x2 = inputUtil.readXFromKeyboard();

            if (x1 == x2) {
                System.out.println("Invalid input. After conversion of x1 and x2 to 360 limit, x1 equals x2\n");
                System.out.println("Final result won't be calculated, since there's no space to expand. Try again!\n");
                continue;
            }

            System.out.println("Enter the dx value, the size of single step (double values 1-10, up to 4 decimal digits): ");
            double dx = inputUtil.readDxFromKeyboard();
            System.out.println("Enter the ε accuracy (double values 1-10, up to 8 decimal digits): ");
            double e = inputUtil.readEpsilonFromKeyboard();

            if (x1 > x2) {
                int tempX1 = x1;
                x1 = x2;
                x2 = tempX1;
            }

            if (chosenEngine.equals(cos)) {
                expandCos(x1, x2, dx, e);
            } else {
                expandSin(x1, x2, dx, e);
            }

            System.out.println("Press 'b' to exit this particular Module, any other key to continue calculations");
        } while (!scanner.next().equals("b"));
    }

    private void expandSin(double x1, double x2, double dx, double e) {
        System.out.printf("%-15s%-15s%-20s%-20s%n", "---x---", "---sin(x)---", "---sin(Taylor)---", "---diff---");

        for (double x = x1; x <= x2; x += dx) {
            double radX = Math.toRadians(x);

            System.out.println("radX = " + radX);

            double sinX = Math.sin(radX);
            double sinTaylor = 0;
            double delta;
            int sign = 1;
            int n = 1;

            do {
                delta = sign * (Math.pow(radX, n)) / (factorial(n));
                sinTaylor += delta;
                n += 2;
                sign *= -1;
            } while (Math.abs(delta) > e);

            double difference = sinX - sinTaylor;
            String formatDiff = "%." + (int) Math.abs(Math.log10(e)) + "e";
            String diffFormatted = String.format(formatDiff, difference);
            String formatValues = "%-15.4f%-15.10f%-20." + (int) Math.abs(Math.log10(e)) + "f";
            System.out.printf(formatValues + "%-20s%n", x, sinX, sinTaylor, diffFormatted);
        }
    }



    private void expandCos(double x1, double x2, double dx, double e) {
        System.out.printf("%-15s%-15s%-20s%-20s%n", "---x---", "---cos(x)---", "---cos(Taylor)---", "---diff---");

        for (double x = x1; x <= x2; x += dx) {
            double radX = Math.toRadians(x);
            System.out.println("radX = " + radX);
            double cosX = Math.cos(radX);
            double cosTaylor = 1;
            double delta;
            int sign = -1;
            int n = 2;

            do {
                delta = sign * (Math.pow(radX, n)) / (factorial(n));
                cosTaylor += delta;
                n += 2;
                sign *= -1;
            } while (Math.abs(delta) > e);


            double difference = cosX - cosTaylor;
            String formatDiff = "%." + (int) Math.abs(Math.log10(e)) + "e";
            String diffFormatted = String.format(formatDiff, difference);
            String formatValues = "%-15.4f%-15.10f%-20." + (int) Math.abs(Math.log10(e)) + "f";
            System.out.printf(formatValues + "%-20s%n", x, cosX, cosTaylor, diffFormatted);
        }
    }

    private long factorial(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }


}
