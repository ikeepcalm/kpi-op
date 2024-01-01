package dev.ua.ikeepcalm;

import dev.ikeepcalm.interactify.Interactify;
import dev.ua.ikeepcalm.circuit.CircuitSolver;
import dev.ua.ikeepcalm.resistance.CircuitManager;

import java.util.Scanner;

public class Main {
    static final String exitOption = "b";

    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interactify interactify = new Interactify(scanner);
        CircuitSolver circuitSolver = new CircuitSolver();
        CircuitManager circuitManager = new CircuitManager(interactify, circuitSolver);
        clearConsole();
        System.out.println(ANSI_WHITE + "Hello there! This app will help you to calculate complex resistance");
        System.out.println(ANSI_WHITE + "of a given oscillating circuit depending on the current frequency");
        System.out.println(ANSI_WHITE + "To start, follow the step-by-step instructions given below. Any other questions?\n");
        boolean activeSession = false;
        do {
            if (activeSession){
                clearConsole();
                scanner.nextLine();
            } else {
                activeSession = true;
            }
            circuitManager.delegateCircuit();
            System.out.println(ANSI_WHITE + "Press" + ANSI_RED + " 'b'" + ANSI_WHITE + " to exit or any other key to continue");
        } while (!scanner.next().equals(exitOption));
        System.exit(0);
    }

     private static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

