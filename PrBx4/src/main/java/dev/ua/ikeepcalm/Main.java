package dev.ua.ikeepcalm;

import dev.ua.ikeepcalm.bubble.ArraySorter;
import dev.ua.ikeepcalm.taylor.TaylorExpander;

import java.util.Scanner;

public class Main {

    static final String taylorOption = "1";
    static final String sorterOption = "2";
    static final String exitOption = "b";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        dev.ua.ikeepcalm.taylor.InputUtil taylorInputUtil = new dev.ua.ikeepcalm.taylor.InputUtil(scanner);
        dev.ua.ikeepcalm.bubble.InputUtil bubbleInputUtil = new dev.ua.ikeepcalm.bubble.InputUtil(scanner);
        TaylorExpander taylorExpander = new TaylorExpander(scanner, taylorInputUtil);
        ArraySorter arraySorter = new ArraySorter(scanner, bubbleInputUtil);
        boolean isLaunched = true;
        while (isLaunched) {
            System.out.print("\033[H\033[2J"); System.out.flush();
            System.out.println("Choose the Solver: \n1. Sin / Cos Solver\n2. Bubble Sort Solver\n");
            System.out.println("(Use 'b' if you want to abandon calculations and exit)");
            String chosenSolver = scanner.nextLine();
            switch (chosenSolver) {
                case taylorOption -> taylorExpander.expandFunction();
                case sorterOption -> arraySorter.sortArray();
                case exitOption -> isLaunched = false;
                default -> System.out.println("Couldn't recognize the option. Try again!");
            }
        }
    }
}
