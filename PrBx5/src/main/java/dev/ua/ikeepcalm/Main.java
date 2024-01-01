package dev.ua.ikeepcalm;

import dev.ikeepcalm.interactify.Interactify;
import dev.ua.ikeepcalm.sorting.StringMaker;
import dev.ua.ikeepcalm.sorting.StringSorter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final String exitOption = "b";

    public static void main(String[] args) {
        clearConsole();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        StringMaker stringMaker = new StringMaker(random);
        Interactify interactify = new Interactify(scanner);
        StringSorter stringSorter = new StringSorter(interactify, stringMaker);
        System.out.println("Hello there! This app will help you to sort an array of Strings by their ASCII codes.");
        System.out.println("To start, follow the step-by-step instructions given below this block of greeting!\n");
        boolean activeSession = false;
        do {
            if (activeSession){
                clearConsole();
                scanner.nextLine();
            } else {
                activeSession = true;
            } stringSorter.sortArray();
            System.out.println("Enter 'b' to abandon sorts and exit or any other key to continue");
        } while (!scanner.next().equals(exitOption));
    }

     private static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

