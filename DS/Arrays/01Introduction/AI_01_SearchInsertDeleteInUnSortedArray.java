/*
Article: https://www.geeksforgeeks.org/search-insert-and-delete-in-an-unsorted-array/
*/
class AI_01_SearchInsertDeleteInUnSortedArray {
    // Search  and return index of key
    int search (int [] arr, int n, int key) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1; // Key not found.
    }

    //Insert and return new length of array
    int insert (int[] arr, int n, int key) {
        if (n >= arr.length) {
            return n; // Insertion not successful.
        } else {
            arr[n] = key;
            return n + 1;
        }
    }

    // Delete and return new length of array
    int delete (int[] arr, int n, int key) {
        int keyIndex = search(arr, n, key);
        if (keyIndex == -1) {
            return n; // Not Found. Deletion not successful.
        } else {
            for (int i = keyIndex; i < n - 1; i++) {
                arr[i] = arr [i + 1];
            }
            return n - 1;
        }
    }
    public static void main(String[] args) {
        AI_01_SearchInsertDeleteInUnSortedArray s
            = new AI_01_SearchInsertDeleteInUnSortedArray();
        System.out.println("************ SEARCH ************");
        int arr1[] = {12, 34, 10, 6, 40}; 
        int n1 = arr1.length; 
       
        // Using a last element as search element 
        int key1 = 40; 
        int position = s.search(arr1, n1, key1); 

        System.out.println("Array:");
        for (int i = 0; i < n1; i++) {
            System.out.print(arr1[i] + " "); 
        }
        if (position == - 1) {
            System.out.println("\nElement not found"); 
        } else {
            System.out.println("\nElement " + key1 + " found at Position: " + (position + 1));
        }
        
        System.out.println("\n************ INSERT ************");
        int[] arr2 = new int[20];  
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
        int arr3[] = {10, 50, 30, 40, 20}; 
       
        int n3 = arr3.length; 
        int key3 = 30; 
       
        System.out.println("Array before deletion: "); 
        for (int i = 0; i < n3; i++) {
            System.out.print(arr3[i] + " "); 
        }
       
        n3 = s.delete(arr3, n3, key3); 
       
        System.out.println("\nArray after deletion: "); 
        for (int i = 0; i < n3; i++) {
            System.out.print(arr3[i] + " "); 
        }
    }
}