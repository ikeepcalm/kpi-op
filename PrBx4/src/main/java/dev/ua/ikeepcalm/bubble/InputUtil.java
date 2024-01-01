package dev.ua.ikeepcalm.bubble;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {

    Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    protected BigDecimal[] readArrayFromKeyboard(int size) {
        BigDecimal[] array = new BigDecimal[size];

        for (int i = 0; i < size; i++) {
            System.out.print("Enter value for index " + i + ": ");
            while (!scanner.hasNextBigDecimal()) {
                System.out.println("Invalid input. Please enter a valid value.");
                scanner.next();
            }
            array[i] = scanner.nextBigDecimal();
        } return array;
    }

    protected int readSizeFromKeyboard() {
        String input;
        int size = 0;
        boolean isValid = false;

        while (!isValid) {
            input = scanner.nextLine();

            while (input.contains(" ")) {
                System.out.println("Invalid input. Does the input contain spaces? Please enter a valid integer");
            }

            try {
                size = Integer.parseInt(input);
                if (size > 0) {
                    isValid = true;
                    scanner.reset();
                } else {
                    System.out.println("Invalid input. Please enter a positive integer greater than zero.");
                    scanner.reset();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer");
                continue;
            }
        } return size;
    }
}
