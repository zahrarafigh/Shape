import java.awt.Color;
import java.util.Scanner;

public class Rectangle extends Shape{
    private int length;
    private int height;

    public Rectangle(String name, ShapeType shapeType, ShapeColor color, Position position, int length, int height) {
        super(name, shapeType, color, position);
        this.length = length;
        this.height= height;
    }

    public int getLength() {
        return length;
    }
    public int getHeight() {
        return height;
    }

    public void changeSize(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> Enter new length: ");
        this.length = scanner.nextInt();
        System.out.print("-> Enter new height: ");
        this.height = scanner.nextInt();
    }
}
