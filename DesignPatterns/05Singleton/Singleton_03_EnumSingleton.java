import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
/*
- Reflection can be used to destroy all the singleton implementation approaches discussed. When you run the below class, 
    you will notice that hashCode of both the instances is not same that destroys the singleton pattern. Reflection is 
    very powerful and used in a lot of frameworks like Spring and Hibernate.
- Enum Singleton: To overcome this situation with Reflection, Joshua Bloch suggests the use of Enum to implement Singleton 
    design pattern as Java ensures that any enum value is instantiated only once in a Java program. Since Java Enum values 
    are globally accessible, so is the singleton. The drawback is that the enum type is somewhat inflexible; for example, 
    it does not allow lazy initialization, it cannot be inherited etc.
*/

class Singleton_03_EnumSingleton {
    public static void main(String[] args) {
        // Using Reflection to destroy Singleton pattern

        // Instance 1
        EagerSingleton eagerSingleton1 = EagerSingleton.getInstance();

        // Instance 2
        EagerSingleton eagerSingleton2;

        Constructor[] constructors = EagerSingleton.class.getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            //Below code will destroy the singleton pattern
            constructor.setAccessible(true);
            try {
                eagerSingleton2 = (EagerSingleton) constructor.newInstance();
                System.out.println(eagerSingleton1.equals(eagerSingleton2));
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public enum EnumSingleton {
        INSTANCE;

        public static void doSomething() {
            // Do something
        }
    }
}