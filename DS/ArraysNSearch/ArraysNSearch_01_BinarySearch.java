// Given a sorted array arr[] of N integers and a number K is given. The task is to check if the element K is present in the array or not.
import java.util.Arrays;

class ArraysNSearch_01_BinarySearch {
  public static void main(String[] args) {
    ArraysNSearch_01_BinarySearch binarySearch = new ArraysNSearch_01_BinarySearch();

    int arr1[] = {5, 10, 23, 42, 49, 119};

    System.out.println("Array 1: " + Arrays.toString(arr1));

    int key1 = 119;
    System.out.println("Key 1: " + key1);

    int position1 = binarySearch.recursiveBinarySearch(arr1, 0, arr1.length - 1, key1);
    System.out.println("Key 1: " + key1 + " found at position " + position1 + " using Recursive Binary Search.");

    int position2 = binarySearch.iterativeBinarySearch(arr1, 0, arr1.length - 1, key1);
    System.out.println("Key 1: " + key1 + " found at position " + position2 + " using Iterative Binary Search.");

    int key2 = 51;
    System.out.println("Key 2: " + key2);

    int position3 = binarySearch.recursiveBinarySearch(arr1, 0, arr1.length - 1, key2);
    System.out.println("Key 2: " + key2 + " found at position " + position3 + " using Recursive Binary Search.");

    int position4 = binarySearch.iterativeBinarySearch(arr1, 0, arr1.length - 1, key2);
    System.out.println("Key 2: " + key2 + " found at position " + position4 + " using Iterative Binary Search.");
  }

  int recursiveBinarySearch(int arr[], int low, int high, int key) {
    if (low <= high) {
      int mid = (low + high) / 2;
      if (key == arr[mid]) {
        return mid;
      } else if (key < arr[mid]) {
        return recursiveBinarySearch(arr, low, mid - 1, key);
      } else {
        return recursiveBinarySearch(arr, mid + 1, high, key);
      }
    }
    return -1;
  }

  int iterativeBinarySearch(int arr[], int low, int high, int key) {
    while (low <= high) {
      int mid = (low + high) / 2;
      if (key == arr[mid]) {
        return mid;
      } else if (key < arr[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }
}
