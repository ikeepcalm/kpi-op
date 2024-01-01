package dev.ua.ikeepcalm;

import dev.ikeepcalm.interactify.Interactify;
import dev.ua.ikeepcalm.equations.CosEquation;
import dev.ua.ikeepcalm.equations.LnxEquation;
import dev.ua.ikeepcalm.equations.NlaeSolver;

import java.util.Scanner;

public class Main {
    static final String exitOption = "b";

    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interactify interactify = new Interactify(scanner);
        CosEquation cosEquation = new CosEquation();
        LnxEquation lnxEquation = new LnxEquation();
        NlaeSolver nlaeSolver = new NlaeSolver(interactify, cosEquation, lnxEquation);
        clearConsole();
        System.out.println(ANSI_WHITE + "Hello there! This app will help you to solve Non - Linear Algebraic Equations (NLAE).\n");
        System.out.println(ANSI_WHITE + "To start, follow the step-by-step instructions given below. Any other questions?\n");
        boolean activeSession = false;
        do {
            if (activeSession){
                clearConsole();
                scanner.nextLine();
            } else {
                activeSession = true;
            }

            nlaeSolver.solveNlae();
            System.out.println(ANSI_WHITE + "Press" + ANSI_RED + " 'b'" + ANSI_WHITE + " to exit or any other key to continue");
        } while (!scanner.next().equals(exitOption));
    }

     private static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

