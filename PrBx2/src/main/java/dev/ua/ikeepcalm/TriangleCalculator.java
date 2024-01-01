package dev.ua.ikeepcalm;

import dev.ua.ikeepcalm.utils.CalculationUtil;
import dev.ua.ikeepcalm.utils.ValidationUtil;

import java.math.BigDecimal;
import java.util.Scanner;

public class TriangleCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ValidationUtil validationUtil = new ValidationUtil(scanner);
        boolean continueApp = true;
        while (continueApp) {
            double aSide = 0, bSide = 0, cSide = 0;
            boolean enoughInputs = false;

            while (!enoughInputs) {
                System.out.print("Enter the A side value: ");
                aSide = validationUtil.readDoubleFromKeyboard();

                System.out.print("Enter the B side value: ");
                bSide = validationUtil.readDoubleFromKeyboard();

                System.out.print("Enter the C side value: ");
                cSide = validationUtil.readDoubleFromKeyboard();

                if (!validationUtil.isTriangle(aSide, bSide, cSide)) {
                    System.out.println("This triangle can not be calculated. Either unsatisfied sides requirements, or too short/long values compared to one of the given!\nEach two sides must be larger than the third one, the difference between sides must not exceed 1000%");
                    continue;
                } else {
                    enoughInputs = true;
                }
            }
            soutResults(aSide, bSide, cSide);
            System.out.println("Press b to exit, any other key to continue calculations");
            if (scanner.next().equals("b")) {
                continueApp = false;
            }
        }
    }

    private static void soutResults(double a, double b, double c) {
        BigDecimal aSide = BigDecimal.valueOf(a);
        BigDecimal bSide = BigDecimal.valueOf(b);
        BigDecimal cSide = BigDecimal.valueOf(c);
        CalculationUtil calculationUtil = new CalculationUtil();
        System.out.println("=====================================");
        System.out.println("Triangle area: " + calculationUtil.calculateArea(aSide, bSide, cSide).stripTrailingZeros().toPlainString());
        System.out.println("-------------------------------------");
        System.out.println("Perimeter: " + calculationUtil.calculatePerimeter(aSide, bSide, cSide).stripTrailingZeros().toPlainString());
        System.out.println("-------------------------------------");
        System.out.println("Height for A side: " + calculationUtil.calculateHeight(aSide, bSide, cSide, 'a').stripTrailingZeros().toPlainString());
        System.out.println("Height for B side: " + calculationUtil.calculateHeight(aSide, bSide, cSide, 'b').stripTrailingZeros().toPlainString());
        System.out.println("Height for C side: " + calculationUtil.calculateHeight(aSide, bSide, cSide, 'c').stripTrailingZeros().toPlainString());
        System.out.println("-------------------------------------");
        System.out.println("Bisector for A side: " + calculationUtil.calculateBisector(aSide, bSide, cSide, 'a').stripTrailingZeros().toPlainString());
        System.out.println("Bisector for B side: " + calculationUtil.calculateBisector(aSide, bSide, cSide, 'b').stripTrailingZeros().toPlainString());
        System.out.println("Bisector for C side: " + calculationUtil.calculateBisector(aSide, bSide, cSide, 'c').stripTrailingZeros().toPlainString());
        System.out.println("-------------------------------------");
        System.out.println("Median for A side: " + calculationUtil.calculateMedian(aSide, bSide, cSide, 'a').stripTrailingZeros().toPlainString());
        System.out.println("Median for B side: " + calculationUtil.calculateMedian(aSide, bSide, cSide, 'b').stripTrailingZeros().toPlainString());
        System.out.println("Median for C side: " + calculationUtil.calculateMedian(aSide, bSide, cSide, 'c').stripTrailingZeros().toPlainString());
        System.out.println("=====================================\n");
    }
}
