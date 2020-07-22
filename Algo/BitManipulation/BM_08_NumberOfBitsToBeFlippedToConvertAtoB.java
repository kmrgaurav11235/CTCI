/*
https://www.geeksforgeeks.org/count-number-of-bits-to-be-flipped-to-convert-a-to-b/
https://www.geeksforgeeks.org/count-set-bits-in-an-integer/

- Given two numbers 'a' and 'b', write a program to count number of bits needed to be flipped to 
    convert 'a' to 'b'.
- Example :
    1) 
    Input : a = 10, b = 20
    Output : 4
    Binary representation of a is 00001010
    Binary representation of b is 00010100

    2)
    Input : a = 7, b = 10
    Output : 3
    Binary representation of a is 00000111
    Binary representation of b is 00001010

- Algorithm:
  1. Calculate XOR of A and B, A ^ B.
  2. Count the set bits in the above. Each '1' in the XOR represents a bit that is different between 
    A and B.

- Counting set bits in an integer
- Simple Method:
    Loop through all bits in an integer, check if a bit is set. If it is, then increment the set bit 
    count. See the method: countSetBitsSimple().
- Brian Kernighan’s Algorithm:
    * As we know from 'BM_07_AUsefulOperation', subtracting 1 from a decimal number flips all the bits 
        after the rightmost set bit(which is 1) including the rightmost set bit.
    * For example :
        10 in binary is 00001010
        9 in binary is 00001001
        8 in binary is 00001000
        7 in binary is 00000111
    * So if we subtract a number by 1 and do bitwise & with itself (n & (n-1)), we unset the rightmost 
        set bit. If we do (n & (n-1)) in a loop and count the number of times loop executes, we get the 
        set bit count.
    * The beauty of this solution is the number of times it loops is equal to the number of set bits in a given integer.
- Algorithm:
    1)  Initialize count = 0
    2)  While n is not zero
        a) Do bitwise '&' with n and (n-1) and assign the value back to n
            n = n & (n-1)
        b) Increment count by 1
    3) return count
 
*/
public class BM_08_NumberOfBitsToBeFlippedToConvertAtoB {
    static int countSetBitsSimple(int num) {
        int count = 0;
        while (num != 0) {
            count += (num & 1);
            num = num >> 1;
        }
        return count;
    }

    static int countSetBitsBetter(int num) {
        int count;
        for (count = 0; num != 0; count++) {
            num = num & (num - 1);
        }
        return count;
    }

    static int bitSwapsRequired(int a, int b) {
        int xor = a ^ b;
        // return countSetBitsSimple(xor);
        return countSetBitsBetter(xor);
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        System.out.println("Bit Swaps Required to convert " + a + " [bin: " 
                + BM_01_BinaryDecimalConversion.decimalToBinary(a) + "] into " + b + " [bin: " 
                + BM_01_BinaryDecimalConversion.decimalToBinary(b) + "] = "
                + bitSwapsRequired(a, b));
    }
}