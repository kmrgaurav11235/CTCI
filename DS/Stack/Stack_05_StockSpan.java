import java.util.Deque;
import java.util.LinkedList;

/*
https://www.geeksforgeeks.org/the-stock-span-problem/

- The Stock Span Problem: It is a financial problem where we have a series of n daily 
    price quotes for a stock and we need to calculate span of stock’s price for all 
    n days. 
- The span S(i) of the stock’s price on a given day i is defined as the maximum number 
    of consecutive days just before the i-th day, for which the price of the stock on 
    the current day is less than or equal to its price on the i-th day.
- For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, 
    then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}.
- The span is computed as:
    S[i] = i – h(i).
    Where h(i) the closest day preceding i, such that the price is greater than on that 
    day than the price on the day i. If such a day doesn't exists, we define h(i) = -1.
- To implement this logic, we use a stack as an abstract data type to store the days i, 
    h(i), h(h(i)), and so on. When we go from day 1 to n - 1, we pop the days when the 
    price of the stock was less than or equal to price[i] and then push the value of day 
    i back into the stack.
*/
public class Stack_05_StockSpan {
    static int[] calculateSpan(int[] price, int n) {
        int [] span = new int [n];
        Deque<Integer> priceIndexStack = new LinkedList<>();

        span[0] = 1;
        priceIndexStack.push(0);

        for (int i = 1; i < n; i++) {
            while (!priceIndexStack.isEmpty() && price[i] >= price[priceIndexStack.peek()]) {
                priceIndexStack.pop();
            }
            span[i] = (priceIndexStack.isEmpty() ? i + 1 : i - priceIndexStack.peek());
            priceIndexStack.push(i);
        }
        return span;
    }
    public static void main(String[] args) {
        int [] price = { 100, 80, 60, 70, 60, 75, 85 }; 
        int n = price.length; 

        // Fill the span values in array S[] 
        int [] span = calculateSpan(price, n); 
  
        // print the calculated span values 
        System.out.println("Span Values: ");
        for (int i = 0; i < n; i++) {
            System.out.println("Day: " + i + ", Price: " + price[i] + ", Span: " + span[i]);
        }
    }
}