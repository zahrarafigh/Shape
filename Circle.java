import java.awt.Color;
import java.util.Scanner;

public class Circle extends Shape {
    private int radius;

    public Circle(String name, ShapeType shapeType, ShapeColor color, Position position, int radius) {
        super(name, shapeType, color, position);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void changeSize(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> Enter new radius: ");
        this.radius = scanner.nextInt();

    }
}
