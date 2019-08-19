// Fibonacci Sequence
public class DP01Fibonacci {
  private int fibonacci [] = new int [100];
  public int getFibonacci (final int n) {
    if (fibonacci[n] == 0) {
      if (n < 1) {
        return 0;
      }
      else if (n == 1 || n == 2) {
        fibonacci[n] = 1;
      }
      else {
        fibonacci [n] = getFibonacci(n - 1) + getFibonacci(n - 2);
      }
    }
    return fibonacci[n];
  }
  public static void main(String[] args) {
    DP01Fibonacci fibonacci = new DP01Fibonacci();
    System.out.println("Fibonacci 3 = " + fibonacci.getFibonacci(3));
    System.out.println("Fibonacci 4 = " + fibonacci.getFibonacci(4));
    System.out.println("Fibonacci 5 = " + fibonacci.getFibonacci(5));
    System.out.println("Fibonacci 7 = " + fibonacci.getFibonacci(7));
    System.out.println("Fibonacci 12 = " + fibonacci.getFibonacci(12));
    System.out.println("Fibonacci 15 = " + fibonacci.getFibonacci(15));
  }
}
