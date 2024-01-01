package dev.ua.ikeepcalm.equations;

import dev.ikeepcalm.interactify.Interactify;

public class NlaeSolver {
    private final Interactify interactify;
    private final CosEquation cosEquation;
    private final LnxEquation lnxEquation;

    public NlaeSolver(Interactify interactify,
                      CosEquation cosEquation,
                      LnxEquation lnxEquation) {
        this.interactify = interactify;
        this.cosEquation = cosEquation;
        this.lnxEquation = lnxEquation;
    }

    public void solveNlae() {
        String ANSI_GREEN = "\u001B[32m";
        String equationType = interactify
                .useStringHandler()
                .askForStringFromOptions(ANSI_GREEN + """
                                Please, choose the equation you want to solve:
                                cos) cos(y/x) - 2sin(1/x) + 1/x = 0, when х ∈ [a1; a2]
                                lnx) sin(lnx) - cos(lnx) + y * lnx = 0, when x ∈ [a1; a2]
                                
                                """,
                new String[]{"cos", "lnx"});
        String algorithmType = interactify
                .useStringHandler()
                .askForStringFromOptions(ANSI_GREEN + """
                                
                                Please, choose the solver algorithm you want to use:
                                division) Algorithm of half division
                                tangents) Algorithm of tangents (Newton's)
                                
                                """,
                        new String[]{"division", "tangents"});
        double a1 = interactify
                .useDoubleHandler()
                .askForDoubleInRange(ANSI_GREEN +"\nPlease, enter the left border of the interval (a1) [-10;+10]: ",
                        - 10,
                        + 10);
        double a2 = interactify
                .useDoubleHandler()
                .askForDoubleInRange(ANSI_GREEN +"\nPlease, enter the right border of the interval (a2) [-10;10]: ",
                        - 10,
                        + 10);
        double t = interactify
                .useDoubleHandler()
                .askForDoubleInRange(ANSI_GREEN +"\nPlease, enter the parameter of the equation (t) [-10;10]: ",
                        - 10,
                        + 10);

        double tolerance = interactify
                .useDoubleHandler()
                .askForDoubleWithDecimalLimit(ANSI_GREEN +"\nPlease, enter the tolerance (up to 10 decimal numbers): ",
                        10);
        double result = 0;

        switch (equationType) {
            case "cos" -> result = solveCosEquation(algorithmType, a1, a2, t, tolerance);
            case "lnx" -> result = solveLnxEquation(algorithmType, a1, a2, t, tolerance);
            default -> System.out.println("\nSomething went wrong. Please, try again.");
        }

        System.out.println(ANSI_GREEN +"\nCalculated via " + algorithmType + " method result is: "
                + interactify
                .useDoubleHandler()
                .roundToDecimalPlaces(result, calculateDecimalPlaces(tolerance)));
    }

    private double solveCosEquation(String algorithmType, double a1, double a2, double t, double tolerance) {
        return algorithmType.equals("division")
                ? cosEquation.solveWithHalfDivision(a1, a2, t, tolerance)
                : cosEquation.solveWithTangents(a1, a2, t, tolerance);
    }

    private double solveLnxEquation(String algorithmType, double a1, double a2, double t, double tolerance) {
        return algorithmType.equals("division")
                ? lnxEquation.solveWithHalfDivision(a1, a2, t, tolerance)
                : lnxEquation.solveWithTangents(a1, a2, t, tolerance);
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


}
