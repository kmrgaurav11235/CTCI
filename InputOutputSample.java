import java.util.*;
import java.io.*;

class InputOutputSample {
    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;
        FastReader()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next()
        {
            try
            {
                while(st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(br.readLine());
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return st.nextToken();
        }
        int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
    static int recursiveBinarySearch(int arr[], int low, int high, int key) {
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
	public static void main (String[] args) {
	    FastReader fr = new FastReader();
	    int t = fr.nextInt(); // test cases
	    int index; // index for for-loop of single test case
	    int [] solution = new int[t]; // solution array

	    for(index = 0; index < t; index++)
	    {
	        int n = fr.nextInt();
	        int key = fr.nextInt();
	        int [] a = new int [n];

	        for(int i = 0; i < n; i++) {
	            a[i] = fr.nextInt();
	        } // Get the array

	        solution[index] = recursiveBinarySearch(a, 0, n - 1, key);
	    }

	    for(index = 0; index < t; index++)
	    {
	        System.out.println((solution[index] == -1)?-1:1);
	    }
	}
}
