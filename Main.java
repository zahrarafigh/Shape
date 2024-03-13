import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.Scanner;
import javax.swing.*;

public class Main extends JFrame{

    private Shape[] shapes = new Shape[1000];
    private int counter = 0;

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.GREEN);
        for (Shape shape : shapes){
            if (shape != null){
                graphics2D.setColor(shapeColorToColor(shape.getColor()));
                if (shape.getShapeType() == ShapeType.circle){
                    graphics2D.drawOval(shape.getPosition().getX(), shape.getPosition().getY(), shape.getRadius()*2, shape.getRadius()*2);
                }
                if (shape.getShapeType() == ShapeType.ellipse){
                    graphics2D.drawOval(shape.getPosition().getX(), shape.getPosition().getY(), shape.getRadius1()*2, shape.getRadius2()*2);
                }
                if (shape.getShapeType() == ShapeType.rectangle){
                    graphics2D.drawRect(shape.getPosition().getX(), shape.getPosition().getY(), shape.getLength(), shape.getHeight());
                }
                if (shape.getShapeType() == ShapeType.square){
                    graphics2D.drawRect(shape.getPosition().getX(), shape.getPosition().getY(), shape.getLength(), shape.getLength());
                }
            }
        }
//        graphics2D.drawOval(100,100,200,100);
//        graphics2D.drawRect(50,50,100,200);
    }

    public void draw(){
        setTitle("page");
        setSize(720, 480);
        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Main main = new Main();
        File file = new File("data.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(bufferedReader);
            while (scanner.hasNextLine()){
                String shapeString = scanner.nextLine();
                String[] attributes = shapeString.split(" ");
                for (int i = 0; i < attributes.length; i++){
                    String[] parts = attributes[i].split("=");
//                    System.out.print("0: " + parts[0] + ", 1: " + parts[1] + "\n");
                    if (parts[0].equals("type")){
                        if (parts[1].equals("circle")){
                            main.setCircle(attributes);
                        }else if (parts[1] == "ellipse"){
                            main.setEllipse(attributes);
                        }else if (parts[2] == "rectangle"){
                            main.setRectangle(attributes);
                        }else if (parts[3] == "square"){
                            main.setSquare(attributes);
                        }
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println(e);
        }

        Scanner scanner = new Scanner(System.in);
        int temp = main.menu();
        while (temp!=4){
            switch (temp){
                case 1:
                    int shapeIndex = main.chooseShape();
                    main.printShape(main.shapes[shapeIndex]);
                    main.editShape(shapeIndex);
                    break;
                case 2:
                    main.addShape();
                    break;
                case 3:
                    main.draw();
                    break;

            }
            temp = main.menu();
        }
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public int menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n1- List shapes\n2- Add new shape\n3- Show shapes\n4- Exit\n-> Enter the number: ");
        return scanner.nextInt();
    }

    public void addShape(){
        Scanner scanner = new Scanner(System.in);
        int t = 0;
        for (Shape shape : shapes) {
            if (shape != null) {
                t++;
            }
        }
        System.out.print("Which shape do you want to create?\n1- Circle\n2- Ellipse\n3- Rectangle\n4- Square\n-> Enter the number: ");
        int temp = scanner.nextInt();
        while (temp < 1 || temp > 4){
            System.out.print("-> Enter valid number: ");
            temp = scanner.nextInt();
        }
        System.out.print("Name: ");
        String name = scanner.nextLine();
        ShapeColor shapeColor = chooseColor();
        System.out.print("set Position\nX: ");
        int x = scanner.nextInt();
        System.out.print("Y: ");
        int y = scanner.nextInt();
        Position position = new Position(x,y);
        if (temp == 1){
            System.out.print("Radius: ");
            int radius = scanner.nextInt();
            shapes[t] = new Circle(name, ShapeType.circle, shapeColor, position, radius);
        }else if(temp == 2){
            System.out.print("Radius1: ");
            int radius1 = scanner.nextInt();
            System.out.print("Radius2: ");
            int radius2 = scanner.nextInt();
            shapes[t] = new Ellipse(name, ShapeType.ellipse, shapeColor, position, radius1, radius2);
        }else if(temp == 3){
            System.out.print("Length: ");
            int length = scanner.nextInt();
            System.out.print("Height: ");
            int height = scanner.nextInt();
            shapes[t] = new Rectangle(name, ShapeType.rectangle, shapeColor, position, length, height);
        }else if (temp == 4){
            System.out.print("Length: ");
            int length = scanner.nextInt();
            shapes[t] = new Square(name, ShapeType.square, shapeColor, position, length);
        }

    }

    public ShapeColor chooseColor(){
        Scanner scanner = new Scanner(System.in);
        ShapeColor color;
        System.out.print("Which color?\n1- gray\n2- brown\n3- yellow\n4- green\n5- blue\n6- red\n7- white\n8- black\n-> Enter the number: ");
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
        return color;
    }

    public int chooseShape(){
        int t = 1;
        for (Shape shape: shapes){
            if (shape != null) {
                System.out.println(t + "- " + shape.getName());
                t++;
            }
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("input name: ");
            String temp = scanner.nextLine();
            for (int i = 0; i < t - 1; i++) {
                if (temp.equals(shapes[i].getName())) {
                    return i;
                }
            }
            System.out.print("Not found, ");
        }
    }

    public void editShape(int shapeIndex){
        System.out.print("What you going to do?\n1- change color\n2- change size\n3- change position\n4- delete shape\n5- back to menu\n-> Enter the number: ");
        Scanner scanner = new Scanner(System.in);
        int temp = scanner.nextInt();
        while (temp != 5){
            switch (temp) {
                case 1:
                    shapes[shapeIndex].changeColor();
                    break;
                case 2:
                    shapes[shapeIndex].changeSize();
                    break;
                case 3:
                    shapes[shapeIndex].changePosition();
                    break;
                case 4:
                    int t = 0;
                    for (Shape shape : shapes) {
                        if (shape != null) {
                            t++;
                        }
                    }
                    shapes[shapeIndex] = null;
                    for (int i = shapeIndex; i < t - 1; i++) {
                        shapes[i] = shapes[i + 1];
                    }
                    break;
                default:
                    break;
            }
        }
    }


    public void setCircle(String[] attributes){
        String nameS = null, radiusS = null, colorS = null, positionS = null;
        for (int i = 0; i < attributes.length; i++){
            String[] parts = attributes[i].split("=");
            if (parts[0].equals("name")){
                nameS = parts[1];
            }
            if (parts[0].equals("radius")){
                radiusS = parts[1];
            }
            if (parts[0].equals("color")){
                colorS = parts[1];
            }
            if (parts[0].equals("position")){
                positionS = parts[1];
            }
        }
        int radius = Integer.parseInt(radiusS);
        ShapeColor color = setColor(colorS);
        Position position = setPosition(positionS);

        shapes[counter] = new Circle(nameS, ShapeType.circle, color, position, radius);
        counter++;
    }

    public void setEllipse(String[] attributes){
        String nameS = null, radius1S = null, radius2S = null, colorS = null, positionS = null;
        for (int i = 0; i < attributes.length; i++){
            String[] parts = attributes[i].split("=");
            if (parts[0].equals("name")){
                nameS = parts[1];
            }
            if (parts[0].equals("radius1")){
                radius1S = parts[1];
            }
            if (parts[0].equals("radius2")){
                radius2S = parts[1];
            }
            if (parts[0].equals("color")){
                colorS = parts[1];
            }
            if (parts[0].equals("position")){
                positionS = parts[1];
            }
        }
        int radius1 = Integer.parseInt(radius1S);
        int radius2 = Integer.parseInt(radius2S);
        ShapeColor color = setColor(colorS);
        Position position = setPosition(positionS);

        shapes[counter] = new Ellipse(nameS, ShapeType.ellipse, color, position, radius1, radius2);
        counter++;
    }

    public void setRectangle(String[] attributes){
        String nameS = null, lengthS = null, heightS = null, colorS = null, positionS = null;
        for (int i = 0; i < attributes.length; i++){
            String[] parts = attributes[i].split("=");
            if (parts[0].equals("name")){
                nameS = parts[1];
            }
            if (parts[0].equals("length")){
                lengthS = parts[1];
            }
            if (parts[0].equals("height")){
                heightS = parts[1];
            }
            if (parts[0].equals("color")){
                colorS = parts[1];
            }
            if (parts[0].equals("position")){
                positionS = parts[1];
            }
        }
        int length = Integer.parseInt(lengthS);
        int height = Integer.parseInt(heightS);
        ShapeColor color = setColor(colorS);
        Position position = setPosition(positionS);

        shapes[counter] = new Rectangle(nameS, ShapeType.rectangle, color, position, length, height);
        counter++;
    }
    public void setSquare(String[] attributes){
        String nameS = null, lengthS = null, colorS = null, positionS = null;
        for (int i = 0; i < attributes.length; i++){
            String[] parts = attributes[i].split("=");
            if (parts[0].equals("name")){
                nameS = parts[1];
            }
            if (parts[0].equals("length")){
                lengthS = parts[1];
            }
            if (parts[0].equals("color")){
                colorS = parts[1];
            }
            if (parts[0].equals("position")){
                positionS = parts[1];
            }
        }
        int length = Integer.parseInt(lengthS);
        ShapeColor color = setColor(colorS);
        Position position = setPosition(positionS);

        shapes[counter] = new Square(nameS, ShapeType.square, color, position, length);
        counter++;
    }

    public ShapeColor setColor(String color){
        if (color.equals("gray")){
            return ShapeColor.gray;
        }
        if (color.equals("brown")){
            return ShapeColor.brown;
        }
        if (color.equals("yellow")){
            return ShapeColor.yellow;
        }
        if (color.equals("green")){
            return ShapeColor.green;
        }
        if (color.equals("blue")){
            return ShapeColor.blue;
        }
        if (color.equals("red")){
            return ShapeColor.red;
        }
        if (color.equals("white")){
            return ShapeColor.white;
        }
        return ShapeColor.black;
    }
    public Position setPosition(String position){
        String[] parts = position.split("[(,)]");
//        System.out.print(parts[1]);
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        Position position1 = new Position(x,y);
        return position1;
    }

    public void printShape(Shape shape){
        if (shape.getShapeType() == ShapeType.circle){
            System.out.println("Name: " + shape.getName() + ", Type: " + shape.getShapeType().name() + ", Radius: " + shape.getRadius() + ", Color: " + shape.getColor().name() + ", Position: (" + shape.getPosition().getX() + "," + shape.getPosition().getY() + ")");
        }
        if (shape.getShapeType() == ShapeType.ellipse){
            System.out.println("Name: " + shape.getName() + ", Type: " + shape.getShapeType().name() + ", Radius1: " + shape.getRadius1() + ", Radius2: " + shape.getRadius2() + ", Color: " + shape.getColor().name() + ", Position: (" + shape.getPosition().getX() + "," + shape.getPosition().getY() + ")");
        }
        if (shape.getShapeType() == ShapeType.rectangle){
            System.out.println("Name: " + shape.getName() + ", Type: " + shape.getShapeType().name() + ", length: " + shape.getLength() + ", height: " + shape.getHeight() + ", Color: " + shape.getColor().name() + ", Position: (" + shape.getPosition().getX() + "," + shape.getPosition().getY() + ")");
        }
        if (shape.getShapeType() == ShapeType.square){
            System.out.println("Name: " + shape.getName() + ", Type: " + shape.getShapeType().name() + ", length: " + shape.getLength() + ", Color: " + shape.getColor().name() + ", Position: (" + shape.getPosition().getX() + "," + shape.getPosition().getY() + ")");
        }
    }

    public Color shapeColorToColor(ShapeColor shapeColor){
        if (shapeColor == ShapeColor.gray){
            return Color.gray;
        }
        if (shapeColor == ShapeColor.brown){
            return new Color(102,51,0);
        }
        if (shapeColor == ShapeColor.yellow){
            return Color.yellow;
        }
        if (shapeColor == ShapeColor.green){
            return Color.green;
        }
        if (shapeColor == ShapeColor.blue){
            return Color.blue;
        }
        if (shapeColor == ShapeColor.red){
            return Color.red;
        }
        if (shapeColor == ShapeColor.white){
            return Color.white;
        }
        return Color.black;
    }
}
