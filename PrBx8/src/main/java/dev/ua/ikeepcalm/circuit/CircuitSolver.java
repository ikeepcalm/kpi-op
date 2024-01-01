package dev.ua.ikeepcalm.circuit;

public class CircuitSolver {

    public void solveFirstCircuit(double f1, double f2, double df, double L, double C, double R) {
        System.out.println("\n===============================================");
        System.out.printf(" %-15s |%-15s %n", "Frequency", "Resistance: real + imaginary");
        System.out.println("===============================================");
        for (double f = f1; f <= f2; f += df) {
            System.out.println("===============================================");
            System.out.println("f: " + f);
            System.out.println("R: " + R);
            System.out.println("L: " + L);
            System.out.println("C: " + C);
            System.out.println("sqrt(L * C): " + Math.sqrt(L * C));
            double f0 = 1.0 / (2.0 * Math.PI * Math.sqrt(L * C));
            System.out.println("f0: " + f0);
            double omega = 2.0 * Math.PI * f;
            System.out.println("omega: " + omega);
            double a = L / C;
            System.out.println("L / C: " + a);
            double b = R / (omega * C);
            System.out.println("R / (omega * C): " + b);
            double c = R;
            System.out.println("R: " + c);
            double d = omega * L - 1.0 / (omega * C);
            System.out.println("omega * L - 1.0 / (omega * C): " + d);
            double realPart = (a * c + b * d) / (Math.pow(c, 2) + Math.pow(d, 2));
            System.out.println("(a * c + b * d) / (Math.pow(c, 2) + Math.pow(d, 2)): " + realPart);
            double imaginaryPart = -1.0 * (b * c - a * d) / (Math.pow(c, 2) + Math.pow(d, 2));
            System.out.println("-1.0 * (b * c - a * d) / (Math.pow(c, 2) + Math.pow(d, 2)): " + imaginaryPart);
            System.out.println("===============================================");
        }
    }

    public void solveSecondCircuit(double f1, double f2, double df, double L, double C, double R) {
        System.out.println("\n===============================================");
        System.out.printf(" %-15s |%-15s %n", "Frequency", "Resistance: real + imaginary");
        System.out.println("===============================================");
        double f0 = 1.0 / (2.0 * Math.PI * Math.sqrt(L * C));
        System.out.println("f0: " + f0);
        System.out.println("===============================================");
        for (double f = f1; f <= f2; f += df) {
            double omega = 2.0 * Math.PI * f;
            double a = L / C;
            double b = R / (omega * C);
            double c = R;
            double d = omega * L - 1.0 / (omega * C);
            double realPart = (a * c + b * d) / (Math.pow(c, 2) + Math.pow(d, 2));
            double imaginaryPart = (b * c - a * d) / (Math.pow(c, 2) + Math.pow(d, 2));
            System.out.printf("f│ %-15.2f│ Resistance: %-25s + i%-25s%n", f, formatExponential(realPart), formatExponential(imaginaryPart));
        }
    }

    public void solveThirdCircuit(double f1, double f2, double df, double L, double C, double R1, double R2) {
        System.out.println("\n===============================================");
        System.out.printf(" %-15s |%-15s %n", "Frequency", "Resistance: real + imaginary");
        System.out.println("===============================================");
        double f0 = 1.0 / (2.0 * Math.PI * Math.sqrt(L * C));
        System.out.println("f0: " + f0);
        System.out.println("===============================================");
        for (double f = f1; f <= f2; f += df) {
            double omega = 2 * Math.PI * f;
            double a = R1 * R2;
            double b = R1 * (omega * L - 1.0 / (omega * C));
            double c = R1 + R2;
            double d = omega * L - 1.0 / (omega * C);
            double realPart = (a * c + b * d) / (Math.pow(c, 2) + Math.pow(d, 2));
            double imaginaryPart = (b * c - a * d) / (Math.pow(c, 2) + Math.pow(d, 2));

            System.out.printf("f│ %-15.2f│ Resistance: %-25s + i%-25s%n", f, formatExponential(realPart), formatExponential(imaginaryPart));
        }
    }

    public void solveFourthCircuit(double f1, double f2, double df, double L, double C, double R1, double R2) {
        System.out.println("\n===============================================");
        System.out.printf(" %-15s |%-15s %n", "Frequency", "Resistance: real + imaginary");
        System.out.println("===============================================");
        double f0 = 1.0 / (2.0 * Math.PI * Math.sqrt(L * C));
        System.out.println("f0: " + f0);
        System.out.println("===============================================");
        for (double i = f1; i <= f2; i += df) {
            double omega = 2.0 * Math.PI * i;
            double a = R1 * R2 + L / C;
            double b = omega * L * R1 - R2 / (omega * C);
            double c = R1 + R2;
            double d = omega * L - 1.0 / (omega * C);
            double realPart = (a * c + b * d) / (Math.pow(c, 2) + Math.pow(d, 2));
            double imaginaryPart = (b * c - a * d) / (Math.pow(c, 2) + Math.pow(d, 2));

            System.out.printf("f│ %-15.2f│ Resistance: %-25s + i%-25s%n", i, formatExponential(realPart), formatExponential(imaginaryPart));
        }
    }

    private String formatExponential(double value) {
        return String.format("%.4e", value);
    }
}
