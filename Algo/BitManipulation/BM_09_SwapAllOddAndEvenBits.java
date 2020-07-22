/*
https://www.geeksforgeeks.org/swap-all-odd-and-even-bits/

- Given an integer, swap all odd bits with even bits. For example, if the given number is 23 
    (00010111), it should be converted to 43 (00101011). 

Algorithm:
    Let the input number be x
    1) Get all even bits of x by doing bitwise '&' of x with 0xAAAAAAAA. The number 0xAAAAAAAA 
        is a 32 bit number with all even bits set as 1 and all odd bits as 0.
    2) Get all odd bits of x by doing bitwise '&' of x with 0x55555555. The number 0x55555555 
        is a 32 bit number with all odd bits set as 1 and all even bits as 0.
    3) Right shift all even bits.
    4) Left shift all odd bits.
    5) Bitwise '|' new even and odd bits and return.

- The algorithm assumes that input number is stored using 32 bits. For a 64-bit number, the masks 
    need to be changed.
*/
public class BM_09_SwapAllOddAndEvenBits {
    static int swapBits(int num) {
        int evenBits = num & 0xAAAAAAAA;
        int oddBits = num & 0x55555555;

        evenBits >>= 1;
        oddBits <<= 1;
        return evenBits | oddBits;
    }
    public static void main(String[] args) {
        int x = 23; // 00010111
        int result = swapBits(x); // 43 (00101011)
        System.out.println("Swapping all odd bits with even bits of number " + x + " [bin: "
                + BM_01_BinaryDecimalConversion.decimalToBinary(x) + "] gives: " + result + " [bin: "
                + BM_01_BinaryDecimalConversion.decimalToBinary(result) + "]");
    }
}