import java.awt.Color;
import java.util.Scanner;

public class Ellipse extends Shape{
    private int radius1;
    private int radius2;

    public Ellipse(String name, ShapeType shapeType, ShapeColor color, Position position, int radius1, int radius2) {
        super(name, shapeType, color, position);
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    public int getRadius1() {
        return radius1;
    }
    public int getRadius2() {
        return radius2;
    }

    public void changeSize(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> Enter new radius1: ");
        this.radius1 = scanner.nextInt();
        System.out.print("-> Enter new radius2: ");
        this.radius2 = scanner.nextInt();
    }
}