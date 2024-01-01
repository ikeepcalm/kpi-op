package dev.ua.ikeepcalm.calendar;

import java.util.Scanner;

import static dev.ua.ikeepcalm.calendar.InputUtil.isValidDate;
import static dev.ua.ikeepcalm.calendar.InputUtil.isValidDateFormat;

public class DateSolver {

    private final Scanner scanner;
    public DateSolver(Scanner scanner) {
        this.scanner = scanner;
    }

    public void solveDate() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.print("Enter the date following pattern 'dd.MM.yyyy' or 'b' to exit this particular Solver: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("b")) {
                isRunning = false;
            } else if (isValidDateFormat(input)) {
                int year = Integer.parseInt(input.substring(6, 10));
                int month = Integer.parseInt(input.substring(3, 5));
                int day = Integer.parseInt(input.substring(0, 2));

                if (isValidDate(day, month, year)) {
                    String dayOfWeekName = solveWeekDay(day, month, year);
                    System.out.println(input + " – " + dayOfWeekName);
                }

            } else {
                System.out.println("Invalid date format. Please, enter the date in 'dd.MM.yyyy' format.");
            }
        }
    }

    private String solveWeekDay(int day, int month, int year) {

        if (month <= 2) {
            month += 12;
            year -= 1;
        }

        int dayOfWeek = (day + (26 * (month + 1)) / 10 + (year % 100)
                + (year % 100) / 4 + (year / 100) / 4 + 5 * (year / 100)) % 7;

        return switch (dayOfWeek) {
            case 0 -> "Saturday";
            case 1 -> "Sunday";
            case 2 -> "Monday";
            case 3 -> "Tuesday";
            case 4 -> "Wednesday";
            case 5 -> "Thursday";
            case 6 -> "Friday";
            default -> throw new IllegalStateException("Unknown day. Something went wrong, contact the developer");
        };
    }
}