package dev.ua.ikeepcalm;

import dev.ua.ikeepcalm.calendar.DateSolver;
import dev.ua.ikeepcalm.cubic.CubicSolver;
import dev.ua.ikeepcalm.roots.RootSolver;

import java.util.Scanner;

public class Main {

    public static double MAX_APP_VALUE = 100000000L;
    public static double MIN_APP_VALUE = -100000000L;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateSolver dateSolver = new DateSolver(scanner);
        CubicSolver cubicSolver = new CubicSolver(scanner);
        RootSolver rootSolver = new RootSolver(scanner);
        boolean isLaunched = true;
        while (isLaunched) {
            System.out.println("Choose the Solver: \n1. Root Solver\n2. Week day name Solver\n3. Cubic Equations Solver\n");
            System.out.println("(Use 'b' if you want to abandon calculations and exit)");
            String chosenSolver = scanner.nextLine();
            String rootOption = "1";
            String dateOption = "2";
            String cubicOption = "3";
            String exitOption = "b";
            if (chosenSolver.equals(rootOption)){
                rootSolver.solveRoot();
            } else if (chosenSolver.equals(dateOption)) {
                dateSolver.solveDate();
            } else if (chosenSolver.equals(cubicOption)){
                cubicSolver.solveCubic();
            } else if (chosenSolver.equals(exitOption)) {
                isLaunched = false;
            } else{
                System.out.println("Please, enter the right option in order to continue");
            }
        }
    }
}
