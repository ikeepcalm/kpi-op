package dev.ua.ikeepcalm.utils;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalculationUtil {

    public BigDecimal calculatePerimeter(BigDecimal a, BigDecimal b, BigDecimal c) {
        return a.add(b).add(c);
    }

    public BigDecimal calculateArea(BigDecimal a, BigDecimal b, BigDecimal c) {
        BigDecimal perimeter = calculatePerimeter(a, b, c);
        BigDecimal s = perimeter.divide(BigDecimal.valueOf(2), MathContext.DECIMAL128);
        BigDecimal area = s.multiply(s.subtract(a)).multiply(s.subtract(b)).multiply(s.subtract(c));
        return BigDecimal.valueOf(Math.sqrt(area.doubleValue()));
    }

    public BigDecimal calculateHeight(BigDecimal a, BigDecimal b, BigDecimal c, char side) {
        BigDecimal area = calculateArea(a, b, c);
        return switch (side) {
            case 'a' -> area.multiply(BigDecimal.valueOf(2)).divide(a, MathContext.DECIMAL128);
            case 'b' -> area.multiply(BigDecimal.valueOf(2)).divide(b, MathContext.DECIMAL128);
            case 'c' -> area.multiply(BigDecimal.valueOf(2)).divide(c, MathContext.DECIMAL128);
            default -> BigDecimal.ZERO;
        };
    }

    public BigDecimal calculateBisector(BigDecimal a, BigDecimal b, BigDecimal c, char side) {
        BigDecimal perimeter = calculatePerimeter(a, b, c);
        return switch (side) {
            case 'a' -> BigDecimal.valueOf(2).multiply(
                    BigDecimal.valueOf(Math.sqrt(b.multiply(c).multiply(perimeter).multiply(perimeter.subtract(a)).doubleValue()))
                            .divide(b.add(c), MathContext.DECIMAL128)
            );
            case 'b' -> BigDecimal.valueOf(2).multiply(
                    BigDecimal.valueOf(Math.sqrt(a.multiply(c).multiply(perimeter).multiply(perimeter.subtract(b)).doubleValue()))
                            .divide(a.add(c), MathContext.DECIMAL128)
            );
            case 'c' -> BigDecimal.valueOf(2).multiply(
                    BigDecimal.valueOf(Math.sqrt(a.multiply(b).multiply(perimeter).multiply(perimeter.subtract(c)).doubleValue()))
                            .divide(a.add(b), MathContext.DECIMAL128)
            );
            default -> BigDecimal.ZERO;
        };
    }

    public BigDecimal calculateMedian(BigDecimal a, BigDecimal b, BigDecimal c, char side) {
        return switch (side) {
            case 'a' -> BigDecimal.valueOf(0.5).multiply(
                    BigDecimal.valueOf(Math.sqrt(2 * b.multiply(b).add(c.multiply(c).multiply(BigDecimal.valueOf(2))).subtract(a.multiply(a)).doubleValue()))
            );
            case 'b' -> BigDecimal.valueOf(0.5).multiply(
                    BigDecimal.valueOf(Math.sqrt(2 * a.multiply(a).add(c.multiply(c).multiply(BigDecimal.valueOf(2))).subtract(b.multiply(b)).doubleValue()))
            );
            case 'c' -> BigDecimal.valueOf(0.5).multiply(
                    BigDecimal.valueOf(Math.sqrt(2 * a.multiply(a).add(b.multiply(b).multiply(BigDecimal.valueOf(2))).subtract(c.multiply(c)).doubleValue()))
            );
            default -> BigDecimal.ZERO;
        };
    }
}
