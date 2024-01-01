package dev.ua.ikeepcalm.cubic;

import dev.ua.ikeepcalm.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {

    Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readValueFromKeyboard() {
        boolean isValid = false;
        int input = 0;
        while (!isValid) {
            try {
                input = scanner.nextInt();
                if (input < Main.MAX_APP_VALUE && input > Main.MIN_APP_VALUE) {
                    scanner.nextLine();
                    isValid = true;
                } else {
                    System.out.println("Invalid value given. This app can only read coefficient values from " + Main.MIN_APP_VALUE + " up to " + Main.MAX_APP_VALUE + "\nEnter the value again: ");
                    scanner.nextLine();
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid value given. Did you mistype something except for digits?\nEnter the value again: ");
                scanner.nextLine();
            }
        }
        return input;
    }
}
