import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Main main = new Main();
    main.sort();
  }

  private void sort() {
    List<Shape> shapes = new ArrayList<>(Arrays.asList(
        new Square(3), new Rectangle(2, 3),
        new Triangle(5, 2), new Circle(1)
    ));

    shapes.sort((o1, o2) -> (int) (o1.area() - o2.area()));

    System.out.println(shapes);
  }

  abstract class Shape {
    abstract double area();
  }

  class Square extends Shape {
    private final float side;

    public Square(float side) {
      this.side = side;
    }

    @Override
    double area() {
      return side * side;
    }

    @Override
    public String toString() {
      return "Square area: " + area();
    }
  }

  class Rectangle extends Shape {
    private final float height;
    private final float width;

    public Rectangle(float height, float width) {
      this.height = height;
      this.width = width;
    }

    @Override
    double area() {
      return height * width;
    }

    @Override
    public String toString() {
      return "Rectangle area: " + area();
    }
  }

  class Triangle extends Shape {
    private final float base;
    private final float height;

    public Triangle(float base, float height) {
      this.base = base;
      this.height = height;
    }

    @Override
    double area() {
      return base * height * 0.5;
    }

    @Override
    public String toString() {
      return "Triangle area: " + area();
    }
  }

  class Circle extends Shape {
    private final float radius;

    public Circle(float radius) {
      this.radius = radius;
    }

    @Override
    double area() {
      return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
      return "Circle area: " + area();
    }
  }
}