/*
Liskov Substitution Principle: You should be able to substitue a sub-class for a base class. e.g. If you have
an API that takes a base class, you should be able to stick a sub class in there without breaking anything.
*/
class Rectangle {
    protected int length, breadth;
    Rectangle() {
    }
    Rectangle(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
    }
    int getLength() {
        return length;
    }
    int getBreadth() {
        return breadth;
    }
    void setLength(int length) {
        this.length = length;
    }
    void setBreadth(int breadth) {
        this.breadth = breadth;
    }
    int getArea() {
        return length * breadth;
    }
}
// Now let us use inheritence to define a special type of rectangle where length and breadth are the same: a Square
class Square extends Rectangle {
    Square() {
    }
    Square(int side) {
        length = side;
        breadth = side;
    }
    // Now let's add special overrides for setting width and height
    @Override
    void setLength(int length) {
        // To enforce the square being square, let us set both length and breadth
        this.length = length;
        this.breadth = length;
    }
    @Override
    void setBreadth(int breadth) {
        // To enforce the square being square, let us set both length and breadth
        this.breadth = breadth;
        this.length = breadth;
    }
    // Even though this looks OK, this violates the Liskov Substitution Principle. Let's check the method useIt().
}

class SDP_03_LiskovSubstitutionPrinciple {
    static void useIt(Rectangle r) {
        // Get the breadth of rectangle
        int breadth = r.getBreadth();

        // Set its length to 10
        r.setLength(10);

        // Area for this rectangle should be 10 * breadth
        System.out.println("Expected area of Rectangle " + (10 * breadth) + 
            ", Actual area = " + r.getArea());
        //This looks like it should work, but it will not work for sub-class as we have broken the Liskov Substitution Principle.
    }
    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);

        // Since we should be able to use a Sub-class instead of a Base-class, we should be able to use Square in place of Rectangle.
        Rectangle sq = new Square();
        sq.setBreadth(5);
        useIt(sq); // Doesn't give the expected output

        /*
        The reason for this is that our setter method in Square a bad setter. It makes sense for a rectangle, but for a Square, it is 
        setting both length and breadth without informing anyone. 

        To improve this code, we can do this: 
        Instead of a new Square class, we can just have a boolean method in rectangle class that checks whether the Rectangle 
        is a square. And, if you want explicit construction of a Square and Rectangle, you can use a design Pattern called Factory pattern.
        */
    }
}

class RectangleFactory {
    public static Rectangle newRectangle(int length, int breadth) {
        return new Rectangle(length, breadth);
    }
    public static Rectangle newSquare(int side) {
        return new Rectangle(side, side);
    }
}