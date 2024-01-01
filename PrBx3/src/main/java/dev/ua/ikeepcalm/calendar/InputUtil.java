package dev.ua.ikeepcalm.calendar;

public class InputUtil {

    protected static boolean isValidDateFormat(String date) {
        return date.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$");
    }

    protected static boolean isValidDate(int day, int month, int year) {
        if (    year <= 0
                || year >= 5000
                || month < 1
                || month > 12
                || day < 1
                || day > 31) {
            System.out.println("DataSolver can only resolve week day for years in range (0-5000), months (1-12), and days (1-31)");
            return false;
        }
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        if (day <= daysInMonth[month-1]){
            return true;
        } else {
            System.out.println("Invalid day given, exceeded maximum number of days in given month");
            return false;
        }
    }

    protected static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0 && year % 100 == 0);
    }
}
