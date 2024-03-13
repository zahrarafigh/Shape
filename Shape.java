import java.awt.Color;
import java.util.Scanner;

enum ShapeType{
    circle,
    ellipse,
    rectangle,
    square
}
enum ShapeColor{
    black, white, red, blue, green, yellow, brown, gray
}

public class Shape {
    private String name;
    private ShapeType shapeType;
    private ShapeColor color;
    private Position position;

    public Shape(String name, ShapeType shapeType, ShapeColor color, Position position){
        this.name = name;
        this.shapeType = shapeType;
        this.color = color;
        this.position = position;
    }

    public int getRadius() {
        return 0;
    }
    public int getRadius1() {
        return 0;
    }
    public int getRadius2() {
        return 0;
    }
    public int getLength() {
        return 0;
    }
    public int getHeight() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public ShapeColor getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setColor(ShapeColor color) {
        this.color = color;
    }

    public void changeColor(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("1- gray\n2- brown\n3- yellow\n4- green\n5- blue\n6- red\n7- white\n8- black\n-> Enter the number: ");
        int colorIndex = scanner.nextInt();

        while (colorIndex<1 || colorIndex>8){
            System.out.print("-> Enter valid number: ");
            colorIndex = scanner.nextInt();
        }
        switch (colorIndex){
            case 1:
                color = ShapeColor.gray;
            case 2:
                color = ShapeColor.brown;
            case 3:
                color = ShapeColor.yellow;
            case 4:
                color = ShapeColor.green;
            case 5:
                color = ShapeColor.blue;
            case 6:
                color = ShapeColor.red;
            case 7:
                color = ShapeColor.white;
            case 8:
                color = ShapeColor.black;
            default:
                color = ShapeColor.black;
        }
    }

    public void changeSize(){

    }

    public void changePosition(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> Enter new X: ");
        position.setX(scanner.nextInt());
        System.out.print("-> Enter new Y: ");
        position.setY(scanner.nextInt());
    }
}
