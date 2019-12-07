/*
Article: https://www.geeksforgeeks.org/search-insert-and-delete-in-a-sorted-array/
*/
class Arrays_02_SearchInsertDeleteInSortedArray {
    // Search: Recursive binary search
    int recursiveBinarySearch (int [] arr, int low, int high, int key) {
        if (low > high) {
            return -1; // Not Found
        }
        int mid = (low + high) / 2;
        if (arr[mid] == key) {
            return mid;
        } else if (arr[mid] > key) {
            return recursiveBinarySearch(arr, low, mid - 1, key);
        } else {
            return recursiveBinarySearch(arr, mid + 1, high, key);
        }
    }

    //Search: Iterative binary search
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
    
    //Insert and return new length of array
    int insert (int[] arr, int n, int key) {
        if (n >= arr.length) {
            return n; // Insertion not successful. 
        }
        int i;
        for (i = n - 1; (i >= 0) && (arr[i] > key); i--) {
            arr[i + 1] = arr[i];
        }
        arr[i + 1] = key;
        return n + 1;
    }

    // Delete and return new length of array
    int delete (int[] arr, int n, int key) {
        int keyIndex = iterativeBinarySearch(arr, 0, n - 1, key);
        if (keyIndex == -1) {
            return n; // Not Found. Deletion not successful.
        }
        for (int i = keyIndex; i < n - 1; i++) {
            arr[i] = arr [i + 1];
        }
        return n - 1;
    }
    public static void main(String[] args) {
        Arrays_02_SearchInsertDeleteInSortedArray s = 
            new Arrays_02_SearchInsertDeleteInSortedArray();
        
        System.out.println("************ SEARCH ************");
        int arr1[] = { 5, 6, 7, 8, 9, 10 }; 
        int n1, key1; 
        n1 = arr1.length; 
        key1 = 10; 

        System.out.println("Array:");
        for (int i = 0; i < n1; i++) {
            System.out.print(arr1[i] + " "); 
        }

        int position = s.recursiveBinarySearch(arr1, 0, n1 - 1, key1);
        if (position == - 1) {
            System.out.println("\nElement not found"); 
        } else {
            System.out.println("\nElement " + key1 + " found at Position: " + (position + 1));
        } 

        System.out.println("\n************ INSERT ************");
        int arr2[] = new int[20]; 
        arr2[0] = 12; 
        arr2[1] = 16; 
        arr2[2] = 20; 
        arr2[3] = 40; 
        arr2[4] = 50; 
        arr2[5] = 70; 
        int n2 = 6; 
        int key2 = 26; 
  
        System.out.print("Before Insertion: "); 
        for (int i = 0; i < n2; i++) {
            System.out.print(arr2[i] + " "); 
        }
  
        // Inserting key 
        n2 = s.insert(arr2, n2, key2); 
  
        System.out.print("\nAfter Insertion: "); 
        for (int i = 0; i < n2; i++) {
            System.out.print(arr2[i] + " "); 
        }

        System.out.println("\n\n************ DELETE ************");
        int arr3[] = { 10, 20, 30, 40, 50 }; 
  
        int n3 = arr3.length; 
        int key3 = 30; 
  
        System.out.print("Array before deletion:\n"); 
        for (int i = 0; i < n3; i++) {
            System.out.print(arr3[i] + " "); 
        }
  
        n3 = s.delete(arr3, n3, key3); 
  
        System.out.print("\n\nArray after deletion:\n"); 
        for (int i = 0; i < n3; i++) {
            System.out.print(arr3[i] + " "); 
        }
    }
}