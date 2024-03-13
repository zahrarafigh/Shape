import java.awt.Color;
import java.util.Scanner;

public class Square extends Shape{
    private int length;

    public Square(String name, ShapeType shapeType, ShapeColor color, Position position, int length) {
        super(name, shapeType, color, position);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void changeSize(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> Enter new length: ");
        this.length = scanner.nextInt();
    }
}
