import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
https://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/

- Given an array of numbers, arrange them in a way that yields the largest value. For example, if 
    the given numbers are {54, 546, 548, 60}, the arrangement 6054854654 gives the largest value. 
    And if the given numbers are {1, 34, 3, 98, 9, 76, 45, 4}, then the arrangement 998764543431 
    gives the largest value.
 - A simple solution that comes to our mind is to sort all numbers in descending order, but simply 
    sorting doesn’t work. For example, 548 is greater than 60, but in output 60 comes before 548. 
- The idea is to use any comparison based sorting algorithm. 
- In the used sorting algorithm, instead of using the default comparison, write a new comparison 
    method and use it to sort numbers. 
- Given two numbers X and Y – we compare two numbers XY (Y appended at the end of X) and YX (X 
    appended at the end of Y). If XY is larger, then X should come before Y in output, else Y 
    should come before. 
*/
public class ASort_04_LargestNumber {

    static String largestNumber(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return "0";
        }
        Collections.sort(list, (x , y) -> {
            String strX = String.valueOf(x);
            String strY = String.valueOf(y);

            String xy = strX + strY;
            String yx = strY + strX;

            // Order is reversed since we need decreasing order
            return yx.compareTo(xy);
        });

        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(54);
        list.add(546);
        list.add(548);
        list.add(60);

        System.out.println("List: " + list);
        System.out.println("Largest number: " + largestNumber(list));
    }
}
