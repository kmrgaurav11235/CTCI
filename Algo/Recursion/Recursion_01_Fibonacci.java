class Recursion_01_Fibonacci {
    static int fibonacci (int n) {
        if (n <=2 ) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public static void main(String[] args) {
        System.out.println("5th Fibonacci is: " + fibonacci(5));
		System.out.println("6th Fibonacci is: " + fibonacci(6));
		System.out.println("8th Fibonacci is: " + fibonacci(8));
    }
}