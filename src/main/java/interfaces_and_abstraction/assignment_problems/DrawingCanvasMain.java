package interfaces_and_abstraction.assignment_problems;

public class DrawingCanvasMain {

    public static void main(String[] args) {

        Shape circle = new CircleShape(101, 5.0);
        Shape square = new SquareShape(102, 4.0);

        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Square Area: " + square.calculateArea());

        System.out.println(circle.scale(1.5));
        System.out.println(square.scale(20));

        System.out.println(
                "Total Shapes: " + Shape.getShapeCount()
        );
    }
}