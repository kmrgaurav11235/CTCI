import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/*
https://www.geeksforgeeks.org/leaders-in-an-array/

- Write a program to print all the Leaders in the array. An element is leader if it is greater than all 
    the elements to its right side. And the rightmost element is always a leader. 
- For example int the array {16, 17, 4, 3, 5, 2}, leaders are 17, 5 and 2. 

- Solution: Scan all the elements from right to left in an array and keep track of maximum till now. When 
    maximum changes its value, record it.
*/
public class AS_04_LeadersInArray {
    static List<Integer> getLeaders(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> leaders = new LinkedList<>();

        // The rightmost element is always a leader
        int currentLeader = arr[n - 1];
        leaders.push(currentLeader);

        for (int i =  n - 2; i >= 0; i--) {
            if (arr[i] > currentLeader) {
                currentLeader = arr[i];
                leaders.push(currentLeader);
            }
        }
        return leaders;
    }
    public static void main(String[] args) {
        int arr[] = {16, 17, 4, 3, 5, 2}; 
        System.out.println("Leaders of Array : " + Arrays.toString(arr) 
            + " are: " + getLeaders(arr) + ".");
    }
}