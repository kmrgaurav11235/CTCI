/*
- Given a positive integer, print the previous largest numbers that has the same number of 1 bits in 
    their binary representation. Check the previous problem first.
- Approach: Given a number n and two of its bit locations -- i and j. Suppose we flip the bit i from 
    1 to 0 and j from 0 to 1. If i > j, then n will decrease; if i < j, n will increase.

- Previous Largest Number
- Given a number 10115. It has a binary representation of:
    Bit:    1  0  0  1 1 1 1 0 0 0 0 0 1 1 
    Index: 13 12 11 10 9 8 7 6 5 4 3 2 1 0
- Our approach will be same as the previous problem. We will compute count0 and count1. This time,
    however, count1 is the number of trailing 1s. count0 is the size of block of 0s immediately to
    its left.
- Step 1: Flip the rightmost, non-trailing 1 (say at position p):
    Bit:    1  0  0  1 1 1 0 0 0 0 0 0 1 1 
    Index: 13 12 11 10 9 8 7 6 5 4 3 2 1 0
                           ⬆
    p = 7, count1 = 2, count0 = 5

- Step 2: Clear bits to the right of p:
    Bit:    1  0  0  1 1 1 0 0 0 0 0 0 0 0
    Index: 13 12 11 10 9 8 7 6 5 4 3 2 1 0

- Step 1 and 2 can be merged and we can set all bits from 0 to index p at once:
    a = ~0 // sequnce of 1s
    mask = a << (p + 1) // all 1s followed by (p + 1) 0s
    num = num & mask

- Step 3: Add (count1 + 1) 1s immediately to the right of p:
    Since count1 + count0 = p, the 'mask' will consist of (count1 + 1) 1s followed by (count0 - 1) 0s.
    a = 1 << (count1 + 1) // 0s with 1 at position (count1 + 1)
    b = a - 1 // 0s followed by (count1 + 1) 1s
    mask = b << (count0 - 1) // (count1 + 1) 1s followed by (count0 - 1) 0s
    num = num | mask // previous largest number

    Bit:    1  0  0  1 1 1 0 1 1 1 0 0 0 0
    Index: 13 12 11 10 9 8 7 6 5 4 3 2 1 0
*/
public class BM_06_PrevLargestNumWithSameNumberOfSetBits {
    static int previousLargest(int num) throws Exception {
        if (num <= 0) {
            throw new Exception("Only positive numbers are allowed");
        }
        int count0 = 0, count1 = 0;
        int n = num;

        // Get count of 1s
        while ((n & 1) == 1) {
            count1++;
            n = n >> 1;
        }

        // This number is like: 11..11
        if (n == 0) {
            throw new Exception("No smaller number with same number of set bits found");
        }

        // Get count of 0s
        while (n > 0 && (n & 1) == 0) {
            count0++;
            n = n >> 1;
        }

        int p = count0 + count1;
        // Step 1 and 2: Flip the rightmost, non-trailing 1s
        num = num & ((~0) << (p + 1));
        // Step 3: Add (count1 + 1) 1s immediately to the right of p
        num = num | ((1 << (count1 + 1)) - 1) << (count0 - 1);
        return num;
    }
    public static void main(String[] args) {
        int num = 10115;
        System.out.println("Number: " + num + ", Binary: " + BM_01_BinaryDecimalConversion.decimalToBinary(num));

        int previousLargest;
        try {
            previousLargest = previousLargest(num);
            System.out.println("Previous Largest Number with the same number of set bits: " + previousLargest + ", Binary: "
                    + BM_01_BinaryDecimalConversion.decimalToBinary(previousLargest));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}