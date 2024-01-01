package dev.ua.ikeepcalm;

import dev.ikeepcalm.interactify.Interactify;
import dev.ua.ikeepcalm.equations.SlaeSolver;
import dev.ua.ikeepcalm.equations.SlaeVerifier;

import java.util.Scanner;

public class Main {
    static final String exitOption = "b";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interactify interactify = new Interactify(scanner);
        SlaeVerifier slaeVerifier = new SlaeVerifier();
        SlaeSolver slaeSolver = new SlaeSolver(interactify, slaeVerifier);
        clearConsole();
        System.out.println(ANSI_WHITE + "Hello there! This app will help you to solve System of Linear Algebraic Equations (SLAE).\n");
        System.out.println(ANSI_WHITE + "To start, follow the step-by-step instructions given below. Any other questions?\n");
        boolean activeSession = false;
        do {
            if (activeSession){
                clearConsole();
                scanner.nextLine();
            } else {
                activeSession = true;
            }

            slaeSolver.solveSlae();
            System.out.println(ANSI_WHITE + "\nEnter " + ANSI_RED + "'b'" + ANSI_WHITE  + " to exit or any other key to continue");
        } while (!scanner.next().equals(exitOption));
    }

     private static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

