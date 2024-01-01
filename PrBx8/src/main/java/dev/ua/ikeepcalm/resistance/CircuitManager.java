package dev.ua.ikeepcalm.resistance;

import dev.ikeepcalm.interactify.Interactify;
import dev.ua.ikeepcalm.circuit.CircuitScheme;
import dev.ua.ikeepcalm.circuit.CircuitSolver;

public class CircuitManager {

    private final Interactify interactify;
    private final CircuitSolver circuitSolver;

    private final int minLimit = 1;
    private final int maxLimit = 1000;
    private final String ANSI_GREEN = "\u001B[32m";

    public CircuitManager(Interactify interactify, CircuitSolver circuitSolver) {
        this.circuitSolver = circuitSolver;
        this.interactify = interactify;
    }

    public void delegateCircuit() {
        printCircuitScheme();

        String option = interactify
                .useStringHandler()
                .askForStringFromOptions(
                        "\nChoose the circuit scheme you want to solve (1-2-3-4): ",
                        new String[]{"1", "2", "3", "4"});

        double f1 = interactify
                .useDoubleHandler()
                .askForDoubleInRange("\nEnter the left frequency side (f1) of the circuit (1-1000): ", minLimit, maxLimit);
        double f2 = interactify
                .useDoubleHandler()
                .askForDoubleInRange("\nEnter the right frequency side (f2) of the circuit (1-1000): ", minLimit, maxLimit);

        if (f1 > f2) {
            double temp = f1;
            f1 = f2;
            f2 = temp;
        }

        double df = interactify
                .useDoubleHandler()
                .askForDoubleInRange("\nEnter the frequency step (df) of the circuit (1-1000): ", minLimit, maxLimit);
        double L = interactify
                .useDoubleHandler()
                .askForDoubleInRange("\nEnter the inductance (L) of the circuit (1-1000): ", minLimit, maxLimit);
        double C = interactify
                .useDoubleHandler()
                .askForDoubleInRange("\nEnter the capacitance (C) of the circuit (1-1000): ", minLimit, maxLimit);
        double R = interactify
                .useDoubleHandler()
                .askForDoubleInRange("\nEnter the resistance (R) of the circuit (1-1000): ", minLimit, maxLimit);


        switch (CircuitScheme.getSchemeByOption(option)) {
            case LRC_SCHEME -> {
                circuitSolver.solveFirstCircuit(f1, f2, df, L, C, R);
            }
            case RLC_SCHEME -> {
                circuitSolver.solveSecondCircuit(f1, f2, df, L, C, R);
            }
            case CRRL_SCHEME -> {
                double R2 = interactify
                        .useDoubleHandler()
                        .askForDoubleInRange("\nEnter the resistance (R2) of the circuit (1-1000): ", minLimit, maxLimit);
                circuitSolver.solveThirdCircuit(f1, f2, df, L, C, R, R2);
            }
            case RRCL_SCHEME -> {
                double R2 = interactify
                        .useDoubleHandler()
                        .askForDoubleInRange("\nEnter the resistance (R2) of the circuit (1-1000): ", minLimit, maxLimit);
                circuitSolver.solveFourthCircuit(f1, f2, df, L, C, R, R2);
            }

            default -> throw new IllegalArgumentException("No such option");
        }
    }

    private void printCircuitScheme() {
        String[] lines1 = CircuitScheme.LRC_SCHEME.toString().split("\n");
        String[] lines2 = CircuitScheme.RLC_SCHEME.toString().split("\n");
        String[] lines3 = CircuitScheme.CRRL_SCHEME.toString().split("\n");
        String[] lines4 = CircuitScheme.RRCL_SCHEME.toString().split("\n");
        int maxLength = Math.max(lines1.length, lines2.length);
        maxLength = Math.max(maxLength, lines3.length);
        maxLength = Math.max(maxLength, lines4.length);
        System.out.println(ANSI_GREEN + "Circuit schemes:");
        iterateWithinString(lines1, lines2, maxLength);
        System.out.println();
        iterateWithinString(lines3, lines4, maxLength);
    }

    private void iterateWithinString(String[] lines1, String[] lines2, int maxLength) {
        for (int i = 0; i < maxLength; i++) {
            if (i < lines1.length) {
                System.out.print(lines1[i]);
            } else {
                System.out.print(" ".repeat(lines1[0].length()));
            }
            System.out.print("   ");
            if (i < lines2.length) {
                System.out.print(lines2[i]);
            } else {
                System.out.print(" ".repeat(lines2[0].length()));
            }
            System.out.println();
        }
    }

}
