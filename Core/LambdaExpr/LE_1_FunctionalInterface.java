/* 
https://www.geeksforgeeks.org/functional-interfaces-java/
https://www.geeksforgeeks.org/lambda-expressions-java-8/

Lambda expressions provide us with an easier method to work with interfaces that have only got
one method. These interfaces can thus, represent a functionality.

Lambda expressions provide below functionalities.
1) Enable to treat functionality as a method argument, or code as data.
2) A function that can be created without belonging to any class.
3) A lambda expression can be passed around as if it was an object and executed on demand.
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
    // For more than one parameters, we would have to keep the parenthesis. For, one parameter we
    // can get rid of it. `x ->` , `(x) ->` and `(x, y) ->` are correct. `x, y -> ` is incorrect.
    System.out.println("Square of " + testNum3 + " is : " + f3.getSquare(testNum3));

    // Lambda Expression: dropping return statement
    LE_1_Interface f4 = x ->  x * x;
    System.out.println("Square of " + testNum4 + " is : " + f4.getSquare(testNum4));
    /*
    When there is a single statement curly brackets are not mandatory and the return type of the 
    anonymous function is the same as that of the body expression.
    When there are more than one statements, then these must be enclosed in curly brackets (a code 
    block) and the return type of the anonymous function is the same as the type of the value 
    returned within the code block, or void if nothing is returned.
    */
  }
}
