/* 
https://www.geeksforgeeks.org/functional-interfaces-java/
Lambda expressions provide us with an easier method to work with interfaces that have only got
one method. These interfaces can thus, represent a functionality.
*/
@FunctionalInterface
interface LE_1_Interface {
  int getSquare(int num);
  default void sayHello(String str) {
    System.out.println("Hello " + str + ".");
  }
}
public class LE_1_FunctionalInterface {
  public static void main(String[] args) {
    int testNum1 = 5, testNum2 = 7, testNum3 = 9, testNum4 = 12;

    // Anonymous Class
    LE_1_Interface f1 = new LE_1_Interface() {
      @Override
      public int getSquare(int num) {
        return num * num;
      }
    };
    System.out.println("Square of " + testNum1 + " is : " + f1.getSquare(testNum1));

    // Lambda Expression
    LE_1_Interface f2 = (int x) -> {return x * x;};
    System.out.println("Square of " + testNum2 + " is : " + f2.getSquare(testNum2));

    // Lambda Expression: dropping parameter type as it can be inferred
    LE_1_Interface f3 = x -> {return x * x;};
    System.out.println("Square of " + testNum3 + " is : " + f3.getSquare(testNum3));

    // Lambda Expression: dropping return statement
    LE_1_Interface f4 = x ->  x * x;
    System.out.println("Square of " + testNum4 + " is : " + f4.getSquare(testNum4));
  }
}
