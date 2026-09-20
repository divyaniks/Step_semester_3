package interfaces_and_abstraction.assignment_problems;

public abstract class Shape {

    private final int shapeId;
    private static int shapeCount = 0;

    public Shape(int shapeId) {
        this.shapeId = shapeId;
        shapeCount++;
    }

    public int getShapeId() {
        return shapeId;
    }

    public static int getShapeCount() {
        return shapeCount;
    }

    public abstract double calculateArea();

    public String scale(double factor) {
        return "Shape " + shapeId + " scaled by " + factor;
    }

    public String scale(int percentage) {
        return "Shape " + shapeId + " scaled by " + percentage + "%";
    }
}