// https://www.geeksforgeeks.org/write-a-program-to-reverse-an-array-or-string/
class AI_03_ReverseAnArray {
    static void printArray(int [] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " "); 
        }
        System.out.println("");
    }

    static void reverseArrayIterative(int [] arr, int low, int high) {
        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }

    static void reverseArrayRecursive(int [] arr, int low, int high) {
        if (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            reverseArrayRecursive(arr, low + 1, high - 1);
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6}; 

        System.out.println("Before Reversal, array is: "); 
        printArray(arr, 6); 

        reverseArrayIterative(arr, 0, 5); 
        
        System.out.println("Reversed array is: "); 
        printArray(arr, 6); 

        reverseArrayRecursive(arr, 0, 5); 
        
        System.out.println("After reversing again, array is: "); 
        printArray(arr, 6); 
    }
}