package oop_fundamentals.class_problems;
import java.util.Arrays;
import java.util.Scanner;

public class PatientVitals {
    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {
        this.readings = new double[500];
        this.count = 0;

        if (initialReadings != null) {
            for (double reading : initialReadings) {
                this.recordReading(reading);
            }
        }
    }

    public void recordReading(double reading) {
        if (reading <= 0 || reading > 45.0) {
            return;
        }
        if (count < readings.length) {
            readings[count++] = reading;
        }
    }

    public double getAverage() {
        if (count == 0) return 0.0;
        double sum = 0.0;
        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }
        return sum / count;
    }

    public double[] getAllReadings() {
        return Arrays.copyOf(readings, count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            double[] initial = new double[n];
            for (int i = 0; i < n; i++) {
                initial[i] = scanner.nextDouble();
            }
            PatientVitals v = new PatientVitals(initial);
            System.out.println(Arrays.toString(v.getAllReadings()));
            System.out.println(v.getAverage());
        }
        scanner.close();
    }
}