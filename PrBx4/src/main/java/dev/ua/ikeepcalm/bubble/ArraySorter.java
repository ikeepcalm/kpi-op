package dev.ua.ikeepcalm.bubble;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class ArraySorter {

    private final Scanner scanner;
    private final InputUtil inputUtil;

    public ArraySorter(Scanner scanner, InputUtil inputUtil) {
        this.scanner = scanner;
        this.inputUtil = inputUtil;
    }

    public void sortArray() {
        do {
            System.out.print("\033[H\033[2J"); System.out.flush();
            System.out.println("Enter the size of the array: ");
            int arraySize = inputUtil.readSizeFromKeyboard();
            BigDecimal[] arrayContents = inputUtil.readArrayFromKeyboard(arraySize);
            System.out.println("Array before sorting: " + Arrays.toString(arrayContents));
            for (int i = 0; i < arraySize - 1; i++) {
                for (int j = 0; j < arraySize - i - 1; j++) {
                    if (arrayContents[j].compareTo(arrayContents[j + 1]) > 0) {
                        BigDecimal temp = arrayContents[j];
                        arrayContents[j] = arrayContents[j + 1];
                        arrayContents[j + 1] = temp;
                    }
                }
            }
            System.out.println("Array after sorting: " + Arrays.toString(arrayContents));

            System.out.println("Press 'b' to exit this particular Module, any other key to continue calculations");
        } while (!scanner.next().equals("b"));
    }

}
