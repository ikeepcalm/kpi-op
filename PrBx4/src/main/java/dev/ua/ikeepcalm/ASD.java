package dev.ua.ikeepcalm;

public class ASD {
    public static void main(String[] args) {
        double result = 0;
        double S = 1;
        int a = 1;
        for (double x = 1; x <= 7; x += 0.25) {

            if (x <= 4) {
                System.out.println("Початок вкладеного циклу:");
                for (int i = 5; i <= 7; i++) {
                    S = S * (i + x)/(i - x);
                    System.out.println("Для і=" + i + ", S=" + S + ", x=" + x + ", i+x=" + (i + x) + ", i-x=" + (i - x) + ", S=" + S);
                } result = S; S = 1;
                a += 1;
            } else {
                double power = x-5;
                System.out.println(power);
                result = Math.pow(x, power) + 10;
                a+=1;
            }
            System.out.println("Для X=" + x + ", Результат=" + result);
            System.out.println(a);
        }


    }
}
