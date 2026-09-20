package interfaces_and_abstraction.assignment_problems;

public class SquareShape extends Shape {

    private double side;

    public SquareShape(int shapeId, double side) {
        super(shapeId);
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }
}