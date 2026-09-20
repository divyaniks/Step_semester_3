package interfaces_and_abstraction.assignment_problems;

public class CircleShape extends Shape {

    private double radius;

    public CircleShape(int shapeId, double radius) {
        super(shapeId);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}