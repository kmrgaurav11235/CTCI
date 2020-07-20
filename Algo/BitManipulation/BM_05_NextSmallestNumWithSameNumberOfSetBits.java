/*
- Given a positive integer, print the next smallest number that has the same number of 1 bits in 
    their binary representation.
- Approach: Given a number n and two of its bit locations -- i and j. Suppose we flip the bit i from 
    1 to 0 and j from 0 to 1. If i > j, then n will decrease; if i < j, n will increase.

- Next Smallest Number
- Given a number 13948. It has a binary representation of:
    Bit:    1  1  0  1 1 0 0 1 1 1 1 1 0 0 
    Index: 13 12 11 10 9 8 7 6 5 4 3 2 1 0
- Observations:
    1) If we flip a 1 to 0 (say, at index i), we must flip another 0 to 1 (say, at index j).
    2) To get a larger number, j > i.
    3) To make the number bigger, but not unecessarily bigger, we need to flip the rightmost 0 which
        has 1s on the right of it.
- Step 1: Flip the rightmost, non-trailing 0 (say at position p):
    Bit:    1  1  0  1 1 0 1 1 1 1 1 1 0 0 
    Index: 13 12 11 10 9 8 7 6 5 4 3 2 1 0
                           ⬆
    p = 7
* With this change, we have increased the size of n. But we now have one extra 1 and one less 0.
* We can shrink our number to the smallest possible value by rearranging the bits to the right of p.
* 0s should be on the left and 1s on the right. As we do this, we should also replace one of the 1s 
    with a 0.
* An easy way of doing this is to count the number of 1s to the right of p, let us say count1. Then,
    we clear all bits from 0 until p and add (count - 1) 1s.
- Step 2: Clear bits to the right of p:
    Bit:    1  1  0  1 1 0 1 1 1 1 1 1 0 0 
    Index: 13 12 11 10 9 8 7 6 5 4 3 2 1 0
    
    Here p = 7.
    Count of 0s before p, count0 = 2
    Count of 1s before p, count1 = 5

    To clear these bits, we need a mask that is a sequence of 1s followed by p 0s.
    a = 1 << p // all 0s except 1 at position p
    b - a - 1 // all 0s followed by p 1s
    mask = ~b // all 1s followed by p 0s
    n = n & mask // clear rightmost p bits

    Bit:    1  1  0  1 1 0 1 0 0 0 0 0 0 0 
    Index: 13 12 11 10 9 8 7 6 5 4 3 2 1 0

- Step 3: Add (count1 - 1) 1s:
    a = 1 << (count1 - 1) // all 0s with 1 at position (count1 - 1)
    mask = a - 1 // all 0s followed by (count1 - 1) 1s
    n = n | mask // next smallest number

    Bit:    1  1  0  1 1 0 1 0 0 0 1 1 1 1 
    Index: 13 12 11 10 9 8 7 6 5 4 3 2 1 0
*/
public class BM_05_NextSmallestNumWithSameNumberOfSetBits {

    static int nextSmallest(int num) throws Exception {
        if (num <= 0) {
            throw new Exception("Only positive numbers are allowed");
        }
        int count0 = 0, count1 = 0;
        int n = num;

        // Get count of 0s
        while (n > 0 && ((n & 1) == 0)) {
            count0++;
            n = n >> 1;
        }

        // Get count of 1s
        while ((n & 1) == 1) {
            count1++;
            n = n >> 1;
        }

        // If num = 11..1100..00 (31 bits long, one sign bit), no next smallest number exists.
        if (count0 + count1 == 31) {
            throw new Exception("No bigger number is possible.");
        }

        int p = count0 + count1;

        // Step 1: Flip the rightmost, non-trailing 0 at position p
        num = num | (1 << p);
        // Step 2: Clear bits to the right of p
        num = num & ~((1 << p) - 1);
        // Step 3: Add (count1 - 1) 1s
        num = num | ((1 << (count1 - 1)) - 1);

        return num;
    }

    public static void main(String[] args) {
        int num = 13948;
        System.out.println("Number: " + num + ", Binary: " + BM_01_BinaryDecimalConversion.decimalToBinary(num));

        int nextSmallest;
        try {
            nextSmallest = nextSmallest(num);
            System.out.println("Next Smallest Number with the same  number of set bits: " + nextSmallest + ", Binary: "
                    + BM_01_BinaryDecimalConversion.decimalToBinary(nextSmallest));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}