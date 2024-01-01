package dev.ua.ikeepcalm.cubic;

import java.util.Scanner;


public class CubicSolver {


    private final Scanner scanner;
    private final InputUtil inputUtil;

    public CubicSolver(Scanner scanner) {
        this.scanner = scanner;
        this.inputUtil = new InputUtil(scanner);
    }

    public void solveCubic() {
        boolean isLaunched = true;
        while (isLaunched) {
            System.out.println("In order to calculate x³ + ax² + bx + c = 0, enter the data:\n");
            System.out.println("Enter the a value (integer values): ");
            int a = inputUtil.readValueFromKeyboard();
            System.out.println("Enter the b value (integer values): ");
            int b = inputUtil.readValueFromKeyboard();
            System.out.println("Enter the c value (integer values): ");
            int c = inputUtil.readValueFromKeyboard();

            solveCubicEquation(a, b, c);

            System.out.println("Use 'b' to exit this particular Solver, any other key to continue calculations");
            if (scanner.next().equals("b")) {
                isLaunched = false;
            }
        }
    }

    public void solveCubicEquation(double b, double c, double d) {
        double p = c - (b * b) / 3;
        double q = (2.0 * Math.pow(b, 3) / 27.0) - (b * c / 3.0) + d;
        double discriminant = Math.pow(q, 2) / 4 + Math.pow(p, 3) / 27.0;
        if (b == 0 && c == 0 && d == 0){
            System.out.println("Real Root: 0");
            return;
        }
        if (discriminant > 0) {
            double sqrtDiscriminant = Math.sqrt(discriminant);
            double u = Math.cbrt(-q / 2 + sqrtDiscriminant);
            double v = Math.cbrt(-q / 2 - sqrtDiscriminant);
            double realRoot = u + v - (b / 3.0);
            System.out.println("Real Root: " + realRoot);
            double complexFirstPart = -0.5 * (u + v) - (b / 3.0);
            double complexImaginePart = (Math.sqrt(3) / 2.0) * (u - v);
            System.out.println("Complex Root 1: " + complexFirstPart + " + i" + complexImaginePart);
            System.out.println("Complex Root 2: " + complexFirstPart + " - i" + complexImaginePart);
        } else if (discriminant == 0) {
            double root = 3.0 * q / p;
            System.out.println("Real Root 1: " + root);
            System.out.println("Real Root 2: " + root);
            System.out.println("Real Root 3: " + root);
        } else {
            double r = Math.sqrt(Math.pow(-p / 3, 3));
            double theta = Math.acos(-q / (2.0 * r));
            double root1 = 2.0 * Math.cbrt(r) * Math.cos(theta / 3.0) - (b / 3.0);
            double root2 = 2.0 * Math.cbrt(r) * Math.cos((theta + 2 * Math.PI) / 3.0) - (b / 3.0);
            double root3 = 2.0 * Math.cbrt(r) * Math.cos((theta + 4 * Math.PI) / 3.0) - (b / 3.0);
            System.out.println("Real Root 1: " + root1);
            System.out.println("Real Root 2: " + root2);
            System.out.println("Real Root 3: " + root3);
        }
    }
}


