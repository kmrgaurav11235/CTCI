/*
*/
class PointWithFactoryMethod {
    private double x, y;

    // We make the constructor private so that users can create objects only using the factory method.
    private PointWithFactoryMethod(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // Now we define static methods with descriptive names that allow users to create Objects.
    // Their names can give users hints about the kind of object they are going to create.
    public static PointWithFactoryMethod newCartesianPoint (double x, double y) {
        return new PointWithFactoryMethod(x, y);
    }
    public static PointWithFactoryMethod newPolarPoint (double rho, double theta) {
        return new PointWithFactoryMethod(rho * Math.cos(theta), rho * Math.sin(theta));
    }
    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }
}
/*
If we have a lot of factory methods, we might want to group them together in a separate class. This is Factory.
To make sure that out Point constructor remains private, we will stick this class inside Point class and make it static
*/
class PointWithFactory {
    private double x, y;
    private PointWithFactory(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }
    public static class Factory {
        public static PointWithFactory newCartesianPoint (double x, double y) {
            return new PointWithFactory(x, y);
        }
        public static PointWithFactory newPolarPoint (double rho, double theta) {
            return new PointWithFactory(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }
}
class Factories_02_Factory {
    public static void main(String[] args) {
        PointWithFactoryMethod a = PointWithFactoryMethod.newCartesianPoint(7, 11);
        PointWithFactoryMethod b = PointWithFactoryMethod.newPolarPoint(10, Math.PI / 6);
        System.out.println("Point A: " + a + "\nPoint B: " + b);

        
        PointWithFactory p = PointWithFactory.Factory.newCartesianPoint(7, 11);
        PointWithFactory q = PointWithFactory.Factory.newPolarPoint(10, Math.PI / 6);
        System.out.println("\nPoint P: " + p + "\nPoint Q: " + q);
    }
}