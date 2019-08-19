import java.util.Arrays;

class DP02LongestIncreasingSubsequence {

  private static final int MAX_SIZE = 30;
  private int lisArray[] = new int [MAX_SIZE];

  private getMax (int a, int b) {
    if (a >= b)
      return a;
    return b;
  }
  public _initialize(int n) {
    for (int i = 0; i < n; i++) {
      lisArray[i] = 1;
    }
  }

  public findLongestIncreasingSubsequence (int [] a, int n) {
    // Finds the longest increasing subsequence from index 0 to index n - 1 in array a
    if (n == 1) {
      // Array with single element
      return 1;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (a[j] < a[i])
          lisArray[i] = getMax(findLongestIncreasingSubsequence(a, i), findLongestIncreasingSubsequence(a, j) + 1);
      }
    }
    return lisArray[n - 1];
  }

  public static void main(String[] args) {
    DP02LongestIncreasingSubsequence longestIncreasingSubsequence = new DP02LongestIncreasingSubsequence();
    int a[] = {10, 22, 9, 33, 21, 50, 41, 60};

    System.out.println("Length of longest increasing subsequence in  array "
      + Arrays.toString(a) + " : " + longestIncreasingSubsequence.findLongestIncreasingSubsequence(a, a.length));
  }
}
