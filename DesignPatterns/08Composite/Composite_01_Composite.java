/*
https://www.journaldev.com/1535/composite-design-pattern-in-java
- Composite pattern is one of the Structural design pattern. It is used when we have to represent a part-whole 
    hierarchy. 
- When we need to create a structure in a way that the objects in the structure has to be treated the same way, 
    we can apply composite design pattern.
- Example: A diagram is a structure that consists of Objects such as Circle, Lines, Triangle etc. When we fill 
    the drawing with color (say Red), the same color also gets applied to the Objects in the drawing. Here drawing 
    is made up of different parts and they all have same operations.
- Composite Pattern consists of following objects.
    * Base Component – Base component is the interface for all objects in the composition, client program uses base 
        component to work with the objects in the composition. It can be an interface or an abstract class with some 
        methods common to all the objects.
    * Leaf – Defines the behavior for the elements in the composition. It is the building block for the composition 
        and implements base component. It doesn’t have references to other Components.
    * Composite – It consists of leaf elements and implements the operations in base component.
- java.awt.Container#add(Component) is a great example of Composite pattern in java and used a lot in Swing. 
*/
import java.util.ArrayList;
import java.util.List;

interface Shape {
    public void draw(String color);
}

class Circle implements Shape {

    @Override
    public void draw(String color) {
        System.out.println("Drawing a Circle with color " + color + ".");
    }

}
class Square implements Shape {

    @Override
    public void draw(String color) {
        System.out.println("Drawing a Square with color " + color + ".");
    }

}
class Hexagon implements Shape {

    @Override
    public void draw(String color) {
        System.out.println("Drawing a Hexagon with color " + color + ".");
    }

}

class Drawing implements Shape {

    private List<Shape> shapes = new ArrayList<>();

    @Override
    public void draw(String color) {
        for (Shape shape : shapes) {
            shape.draw(color);
        }
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    public void clear() {
        shapes.clear();
    }
}

class Composite_01_Composite {
    public static void main(String[] args) {
        Circle circle = new Circle();
        Square square1 = new Square();
        Square square2 = new Square();

        Drawing drawing = new Drawing();
        drawing.addShape(circle);
        drawing.addShape(square1);
        drawing.addShape(square2);

        drawing.draw("Red");

        Hexagon hexagon = new Hexagon();
        drawing.removeShape(square1);
        drawing.addShape(hexagon);
        drawing.draw("Green");
    }
}