/*
https://www.journaldev.com/1540/decorator-design-pattern-in-java-example

- Decorator design pattern is used to modify the functionality of an object at runtime. At the same time 
    other instances of the same class will not be affected by this, so individual object gets the modified 
    behavior. Decorator design pattern is one of the structural design pattern and uses abstract classes or 
    interface with composition to implement.
- We use inheritance or composition to extend the behavior of an object but this is done at compile time and 
    its applicable to all the instances of the class. We can’t add any new functionality of remove any existing 
    behavior at runtime – this is when Decorator pattern comes into picture.
-  Here, we want to implement different kinds of cars – we can create interface Car to define the assemble() 
    method and then we can have a Basic car, further more we can extend it to Sports car and Luxury Car. 
- But if we want to get a car at runtime that has both the features of sports car and luxury car, then the 
    implementation gets complex and if further more, we want to specify which features should be added first, 
    it gets even more complex. Now imagine if we have ten different kind of cars, the implementation logic using 
    inheritance and composition will be impossible to manage. To solve this kind of programming situation, we 
    apply decorator pattern in java.
- We need to have following types to implement decorator design pattern.
    * Component Interface – The interface or abstract class defining the methods that will be implemented. In 
        our case Car will be the component interface. 
    * Component Implementation – The basic implementation of the component interface. We can have BasicCar 
        class as our component implementation. 
    * Decorator – Decorator class implements the component interface and it has a HAS-A relationship with the 
        component interface. The component variable should be accessible to the child decorator classes, so we 
        will make this variable protected. 
    * Concrete Decorators – Extending the base decorator functionality and modifying the component behavior 
        accordingly. We can have concrete decorator classes as LuxuryCar and SportsCar. 
- Important Points:
    * Decorator design pattern is helpful in providing runtime modification abilities and hence more flexible. 
        It is easy to maintain and extend when the number of choices are more.
    * The disadvantage of decorator design pattern is that it uses a lot of similar kind of objects (decorators).
    * Decorator pattern is used a lot in Java IO classes, such as FileReader, BufferedReader etc.
*/


interface Car {
    public void assemble();
}
/*
*/
class BasicCar implements Car {

    @Override
    public void assemble() {
        System.out.println("Adding features of a Basic Car.");
    }
}

class CarDecorator implements Car {

    protected Car car;

    CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        car.assemble();
    }

}

class LuxuryCar extends CarDecorator {
    
    LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("Adding features of a Luxury Car.");
    }
}

class SportsCar extends CarDecorator {
    
    SportsCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("Adding features of a Sports Car.");
    }
}

class Decorator_01_Decorator {
    public static void main(String[] args) {

        System.out.println("Assemble a Luxury Car:");
        Car luxuryCar = new LuxuryCar(new BasicCar());
        luxuryCar.assemble();

        System.out.println("***************");
        
        System.out.println("Assemble a Luxury-Sports Car:");
        Car luxurySportsCar = new LuxuryCar(new SportsCar(new BasicCar()));
        luxurySportsCar.assemble();
    }
}