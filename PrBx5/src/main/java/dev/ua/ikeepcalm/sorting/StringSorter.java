package dev.ua.ikeepcalm.sorting;

import dev.ikeepcalm.interactify.Interactify;

public class StringSorter {
    private final Interactify interactify;
    private final StringMaker stringMaker;

    public StringSorter(Interactify interactify,
                        StringMaker stringMaker) {
        this.interactify = interactify;
        this.stringMaker = stringMaker;
    }

    public void sortArray() {
        int arraySize = interactify
                .useIntegerHandler()
                .askForIntegerInRange("Enter the number of strings to sort (1-100): ", 1, 100);
        int stringSize = interactify
                .useIntegerHandler()
                .askForIntegerInRange("Enter the number of characters in each String(1-100): ", 1, 100);
        String[] arrayContents;
        if (interactify
                .useStringHandler()
                .askForYesNoConfirmation("Would you like to generate a random array? ")) {
            arrayContents = stringMaker.generateRandomArray(arraySize, stringSize);
        } else {
            arrayContents = interactify
                    .useArrayHandler()
                    .askForStringArray("Enter the array of Strings step-by-step: ", arraySize, stringSize, stringSize);
        }
        System.out.println("Array before sorting: ");
        printArray(arrayContents);
        sortStringArray(arrayContents);
        System.out.println("Array after sorting: ");
        printArray(arrayContents);
    }


    public void sortStringArray(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (compareTwoStrings(array[j], array[i]) < 0) {
                    String temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    private int compareTwoStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int minLength = Math.min(len1, len2);

        for (int i = 0; i < minLength; i++) {
            int difference = str1.charAt(i) - str2.charAt(i);
            if (difference != 0) {
                return difference;
            }
        }
        return len1 - len2;
    }

    private void printArray(String[] array) {
        System.out.println("------------------------");

        for (int i = 0; i < array.length; i++) {
            System.out.println("Index " + i + ": " + array[i]);
        }

        System.out.println("------------------------\n");
    }
}
