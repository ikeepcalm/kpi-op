#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <malloc.h>
#include <ctype.h>


bool isValidArabicNumber(char *num);

bool isValidRomanCharacter(char c);

bool isValidRomanString(char *str);

bool isValidAppOption(char *str);

int determineIntegerByRoman(char c);

int convertRomanToInteger(char *s);

char *convertIntegerToRoman(int num);

int main(void) {
    bool finished = false;
    while (!finished) {
        printf("Hey there! What are we doing today?\n");
        printf("1. Convert Roman numeral to Arabic number\n");
        printf("2. Convert Arabic number to Roman numeral\n");
        printf("3. Exit\n\n");
        char choice[100];

        scanf("%s", choice);
        while (!isValidAppOption(choice)) {
            printf("Couldn't recognize your choice. Consider typing 1, 2 or 3 in order to proceed!\n\n");
            scanf("%s", choice);
        }

        switch (atoi(choice)) {
            case 1:
                printf("Enter the Roman numeral to convert: ");
                char romanStr[100];
                scanf("%s", romanStr);
                while (!isValidRomanString(romanStr)) {
                    printf("Entered Roman string is not valid. Enter a valid Roman numeral: ");
                    scanf("%s", romanStr);
                }
                printf("Converted Arabic number: %d\n\n", convertRomanToInteger(romanStr));
                break;

            case 2:
                printf("Enter an integer to convert to Roman numeral: ");
                char num[100];

                while (scanf("%s", num) != 1 || !isValidArabicNumber(num)) {
                    printf("Invalid input. Please, enter an integer between 1 and 3999: ");
                    while (getchar() != '\n');
                }

                printf("Converted Roman number: %s\n\n", convertIntegerToRoman(atoi(num)));
                break;

            case 3:
                finished = true;
                break;

            default:
                printf("Couldn't recognize your choice. Consider typing 1, 2 or 3 in order to proceed!\n\n");
        }
    }
    return 0;
}


bool isValidAppOption(char *str) {
    for (int i = 0; i < strlen(str); i++) {
        if (!isdigit(str[i])) {
            return false;
        }
    }
}

bool isValidArabicNumber(char *num) {
    for (int i = 0; i < strlen(num); i++) {
        if (!isdigit(num[i])) {
            return false;
        }
    }
    int value = atoi(num);
    if (value < 1 || value > 3999) {
        return false;
    }

    return true;
}

bool isValidRomanCharacter(char c) {
    return (c == 'I' || c == 'V' || c == 'X' || c == 'L' ||
            c == 'C' || c == 'D' || c == 'M');
}

bool isValidRomanString(char *str) {
    int length = strlen(str);
    if (length > 9) {
        return false;
    }
    for (int i = 0; i < length; i++) {
        if (!isValidRomanCharacter(str[i])) {
            return false;
        }
    }
    return true;
}

int determineIntegerByRoman(char c) {
    switch (c) {
        case 'I':
            return 1;
        case 'V':
            return 5;
        case 'X':
            return 10;
        case 'L':
            return 50;
        case 'C':
            return 100;
        case 'D':
            return 500;
        case 'M':
            return 1000;
        default:
            return 0;
    }
}

int convertRomanToInteger(char *s) {
    int i, integerNumber = determineIntegerByRoman(s[0]);
    int length = strlen(s);
    for (i = 1; i < length; i++) {
        int previousNumber = determineIntegerByRoman(s[i - 1]);
        int currentNumber = determineIntegerByRoman(s[i]);
        if (previousNumber < currentNumber) {
            integerNumber = integerNumber - previousNumber + (currentNumber - previousNumber);
        } else {
            integerNumber += currentNumber;
        }
    }
    return integerNumber;
}

char *convertIntegerToRoman(int num) {
    int arabicNumerals[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    char *romanNumerals[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    char *converted = malloc(100);
    converted[0] = '\0';

    for (int i = 0; i < sizeof(romanNumerals) / sizeof(romanNumerals[0]); i++) {
        while (num >= arabicNumerals[i]) {
            strcat(converted, romanNumerals[i]);
            num = num - arabicNumerals[i];
        }
    }
    return converted;
}