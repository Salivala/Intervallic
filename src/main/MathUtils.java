package main;

public class MathUtils {
    public static double getPercentOf(double f, double s) {
        double rawPercent = f / s;
        rawPercent = rawPercent * 100;
        return  rawPercent;
    }
}