/*
A Factory is a component responsible solely for wholesale (not piecewise) creation of object. They are
needed when Object creation logic becomes too convoluted. This might be because:
1) Constructor names are not descriptive (they are mandated by the type).
2) We cannot overload a constructor with the same set of argument types with different names.
In this case, wholesale creation of object can be outsourced to a different function (Factory Method). 
This function can exist in a separate class (Factory). Factory classes are generally static.
*/
class Point {
    private double x, y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /*
    Now we might want to allow users to initialize Point using polar coordinates too:
    Point (double rho, double theta, double test) {
        x = rho * Math.cos(theta);
        y = rho * Math.sin(theta);
    }
    Unfortunately, this is illegal as we already have a constructor that takes two doubles.
    */
}
// So, let us now design another Point class using an enum:
enum CoordinateSystem {
    CARTESIAN, POLAR;
}
class BadlyDesignedPoint {
    private CoordinateSystem coordinateSystem;
    private double x, y;
    /**
     * 
     * @param coordinateSystem CARTESIAN or POLAR
     * @param a x-coordinate (CARTESIAN) or rho (POLAR)
     * @param b y-coordinate (CARTESIAN) or theta (POLAR)
     */
    BadlyDesignedPoint (CoordinateSystem coordinateSystem, double a, double b) {
        switch (coordinateSystem) {
            case CARTESIAN:
                x = a;
                y = b;
                break;
            case POLAR:
                x = a * Math.cos(b);
                y = a * Math.sin(b);
                break;
            default:
                break;
        }
    }
    /*
    This is an obviously ugly solution with ugly java-doc. We can solve this problem by using
    Factory pattern.
    */
}
class Factories_01_NeedForFactory {
    public static void main(String[] args) {
        
    }
}