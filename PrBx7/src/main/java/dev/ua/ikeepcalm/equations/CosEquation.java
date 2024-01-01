package dev.ua.ikeepcalm.equations;

public class CosEquation {

    public final String ANSI_RED = "\u001B[31m";

    private double t;

    public double solveWithHalfDivision(double a, double b, double t, double tolerance) {
        this.t = t;
        double x;

        do {
            x = (a + b) / 2.0;

            if (f(a) * f(x) > 0) {
                a = x;
            } else {
                b = x;
            }
        } while (Math.abs(b - a) > tolerance);

        return x;
    }

    public double solveWithTangents(double a, double b, double t, double tolerance) {
        this.t = t;
        double x = f(b);
        double delta;

        do {
            double denominator = df(x);

            if (Math.abs(denominator) < 1e-15) {
                System.out.println(ANSI_RED + """
                
                Denominator is too small or tends to zero.
                Risk of endless loop! Exiting loop...
                The result below may be incorrect!
                """);
                break;
            }

            delta = f(x) / denominator;
            x = x - delta;
        } while (Math.abs(delta) > tolerance);

        return x;
    }
    private double f(double x) {
        return Math.cos(t / x) - 2 * Math.sin(1 / x) + 1 / x;
    }

    private double df(double x) {
        return -t / (x * x) * Math.sin(t / x) + 2 / (x * x) * Math.cos(1 / x) - 1 / (x * x);
    }

}
