package dev.ua.ikeepcalm.taylor;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {

    Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    protected double readDxFromKeyboard() {
        boolean isValid = false;
        BigDecimal input = new BigDecimal(0);
        while (!isValid) {
            try {
                input = scanner.nextBigDecimal();
                if (input.compareTo(BigDecimal.valueOf(0)) > 0 && input.compareTo(BigDecimal.valueOf(10)) < 0) {
                    if (input.scale() < 5) {
                        isValid = true;
                    } else {
                        System.out.println("Invalid value given. This app can only read dx values with maximum 8 digits after the decimal point.\nEnter the value again: ");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Invalid value given. This app can only read double values from 0 up to 10.\nEnter the value again: ");
                    scanner.nextLine();
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid value given.\nDid you mistype something except for digits and comma?\nEnter the value again: ");
                scanner.nextLine();
            }
        }
        return input.doubleValue();
    }

    protected double readEpsilonFromKeyboard() {
        boolean isValid = false;
        BigDecimal input = new BigDecimal(0);
        while (!isValid) {
            try {
                input = scanner.nextBigDecimal();
                if (input.compareTo(BigDecimal.valueOf(0)) > 0 && input.compareTo(BigDecimal.valueOf(10)) < 0) {
                    if (input.scale() < 20) {
                        isValid = true;
                    } else {
                        System.out.println("Invalid value given. This app can only read ε values with maximum 8 digits after the decimal point. Enter the value again: ");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Invalid value given. This app can only read double values from 1 up to 10. Enter the value again: ");
                    scanner.nextLine();
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid value given.\nDid you mistype something except for digits? Enter the value again: ");
                scanner.nextLine();
            }
        }
        return input.doubleValue();
    }


    protected int readXFromKeyboard() {
        boolean isValid = false;
        BigDecimal input = new BigDecimal(0);
        while (!isValid) {
            try {
                input = scanner.nextBigDecimal();
                input = input.remainder(BigDecimal.valueOf(360));
                if (input.scale() < 1) {
                    isValid = true;
                } else {
                    System.out.println("Invalid value given. This app can only read integer X values. Enter the value again: ");
                    scanner.nextLine();
                }

            } catch (InputMismatchException exception) {
                System.out.println("Invalid value given. Did you mistype something except for digits? Enter the value again: ");
                scanner.nextLine();
            }
        }
        return input.intValue();
    }


}
