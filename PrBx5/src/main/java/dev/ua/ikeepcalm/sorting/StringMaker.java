package dev.ua.ikeepcalm.sorting;

import java.util.Random;

public class StringMaker {

    private final int LATIN_CATEGORY = 0;
    private final int CYRILLIC_CATEGORY = 1;
    private final int DIGIT_CATEGORY = 2;

    private final int LATIN_LETTERS_COUNT = 26;
    private final int CYRILLIC_LETTERS_COUNT = 32;
    private final int DIGITS_COUNT = 10;

    private final Random random;

    public StringMaker(Random random) {
        this.random = random;
    }

    public String[] generateRandomArray(int arraySize, int stringSize) {
        String[] array = new String[arraySize];

        for (int i = 0; i < arraySize; i++) {
            array[i] = generateRandomString(stringSize);
        }

        return array;
    }

    public String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int category = random.nextInt(3);

            int codePoint = switch (category) {
                case LATIN_CATEGORY -> random.nextInt(LATIN_LETTERS_COUNT) + (random.nextBoolean() ? 'a' : 'A');
                case CYRILLIC_CATEGORY -> random.nextInt(CYRILLIC_LETTERS_COUNT) + (random.nextBoolean() ? 'а' : 'А');
                case DIGIT_CATEGORY -> random.nextInt(DIGITS_COUNT) + '0';
                default -> throw new IllegalStateException("Unexpected value: " + category);
            };

            sb.append((char) codePoint);
        }

        return sb.toString();
    }

}
