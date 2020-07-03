/*
https://www.geeksforgeeks.org/median/
https://www.geeksforgeeks.org/program-for-mean-and-median-of-an-unsorted-array/

- Mean of an array = (sum of all elements) / (number of elements)
- Median is the middle value of a set of data. To determine the median value in a sequence of numbers, 
    the numbers must first be arranged in ascending order.
    * If there is an odd amount of numbers, the median value is the number that is in the middle, with 
        the same amount of numbers below and above.
    * If there is an even amount of numbers in the list, the median is the average of the two middle 
        values.
- The naive method of finding Median in a sorted array includes Sorting the array and then finding its
    median , O(n Log n).
- We can find median in O(n) time using the kth-Smallest/kth-Largest method.
- Median, mean and mode create a grouping called measures of central tendency.
*/
public class AOS_01_MeanAndMedian {
    static double findMean(int[] a, int n) {
        int sum = 0;
        for (int num : a) {
            sum += num;
        }
        return (double)sum / n;
    }

    static double findMedianOfSortedArray(int[] a, int n) {
        if (n % 2 == 0) {
            return (double)(a[n / 2 - 1] + a[n / 2]) / 2;
        } else {
            return a[n / 2];
        }
    }

    public static void main(String[] args) {
        int a[] = { 1, 3, 4, 2, 7, 5, 8, 6 }; 
        int n = a.length; 
        System.out.println("Mean and Median in Sorted Array.");  
        System.out.println("Mean = " + findMean(a, n));  
        System.out.println("Median = " + findMedianOfSortedArray(a, n));  
    }
}