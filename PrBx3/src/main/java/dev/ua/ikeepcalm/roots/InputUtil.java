package dev.ua.ikeepcalm.roots;

import dev.ua.ikeepcalm.Main;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {

    Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public double readEFromKeyboard() {
        boolean isValid = false;
        BigDecimal input = new BigDecimal(0);
        while (!isValid) {
            try {
                input = scanner.nextBigDecimal();
                if (input.compareTo(BigDecimal.valueOf(Main.MIN_APP_VALUE)) > 0
                        && input.compareTo(BigDecimal.valueOf(Main.MAX_APP_VALUE)) < 0) {
                    if (input.scale() < 9) {
                        isValid = true;
                    } else {
                        System.out.println("Invalid value given. This app can only read values with decimal number up to 8 digits\nEnter the value again: ");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Invalid value given. This app can only ε read values from 4.9e-324 up to 1.7976931348623157e+308, excluding 0 \nEnter the value again: ");
                    scanner.nextLine();
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid value given.\nDid you mistype something except for digits and possible single comma?\nEnter the value again: ");
                scanner.nextLine();
            }
        }
        return input.doubleValue();
    }

    public int readKFromKeyboard() {
        boolean isValid = false;
        int input = 0;
        while (!isValid) {
            try {
                input = scanner.nextInt();
                if (input < Main.MAX_APP_VALUE
                        && input > Main.MIN_APP_VALUE
                        && input != 0) {
                    scanner.nextLine();
                    isValid = true;
                } else {
                    System.out.println("Invalid value given. This app can only read k values from 4.9e-324 up to 1.7976931348623157e+308, excluding 0\nEnter the value again: ");
                    scanner.nextLine();
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid value given.\nDid you mistype something except for digits and possible single comma?\nEnter the value again: ");
                scanner.nextLine();
            }
        }
        return input;
    }

    public double readXFromKeyboard(double k) {
        boolean isValid = false;
        BigDecimal input = new BigDecimal(0);
        while (!isValid) {
            try {
                input = scanner.nextBigDecimal();
                if (input.compareTo(BigDecimal.valueOf(Main.MIN_APP_VALUE)) > 0
                        && input.compareTo(BigDecimal.valueOf(Main.MAX_APP_VALUE)) < 0) {
                    if (input.scale() < 5) {
                        if (k > 0) {
                            if (k % 2 == 0) {
                                if (input.compareTo(BigDecimal.valueOf(0)) >= 0) {
                                    isValid = true;
                                    scanner.nextLine();
                                } else {
                                    System.out.println("When the k is even positive number, x can only be > or = 0\nEnter the value again: ");
                                }
                            } else {
                                isValid = true;
                                scanner.nextLine();
                            }
                        } else if (k < 0) {
                            if (k % 2 == 0) {
                                if (input.compareTo(BigDecimal.valueOf(0)) > 0) {
                                    isValid = true;
                                    scanner.nextLine();
                                } else {
                                    System.out.println("When the k is even negative number, x can only be > 0\nEnter the value again: ");
                                }
                            } else {
                                if (input.compareTo(BigDecimal.valueOf(0)) != 0) {
                                    isValid = true;
                                    scanner.nextLine();
                                } else {
                                    System.out.println("When the k is odd negative number, x cannot be1 0\nEnter the value again: ");
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid value given. This app can only read values with decimal number up to 4 digits\nEnter the value again: ");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Invalid value given. This app can only read coefficient values from " + Main.MIN_APP_VALUE + " up to " + Main.MAX_APP_VALUE + "\nEnter the value again: ");
                    scanner.nextLine();
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid value given.\nDid you mistype something except for digits and possible single comma?\nEnter the value again: \nEnter the value again: ");
                scanner.nextLine();
            }
        }
        return input.doubleValue();
    }


}
