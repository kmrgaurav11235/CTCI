/*
- Given two 32-bit numbers m and n (m < n) and two bit positions i and j (i < j). Write a method to insert m
    into n such that m starts at bit j and ends at bit i. You can assume that bit j through i have enough
    space to fill m.
- e.g. N = 10 000 000 000 (1024), M = 10 011 (19), i = 2, j = 6
    Output = 10 001 001 100 (1100)
- Solution:
    1) Clear bits j through i in n.
    2) Shift m so that it lines up with bits j through i.
    3) Merge m and n.
- For Step 1, we need to create a mask with all 1s except bits j through i. We can do this by creating the left
    and right half seperately and merging them.
*/
public class BM_03_InsertANumberIntoAnother {

    static int insertANumberIntoAnother(int m, int n, int i, int j) {
        // Create a mask with all 1s except bits j through i
        // left part
        int allOnes = ~0; // will be sequence of all 1s
        int left = allOnes << (j + 1); // 1s before position j, then all 0s. e.g. if j = 4, left = ...11 0000

        // right part
        int right = (1 << i) - 1; // 1s after position i. e.g. if i = 2, right = ...00 011

        int nMask = left | right; // all 1s except bits j through i. e.g. ...110 011
        int clearedN = n & nMask; // clear bits j through i in n

        // move m in correct position 
        int shiftedM = m << i;
        
        return clearedN | shiftedM;
    }

    public static void main(String[] args) {
        int m = 19, n = 1024, i = 2, j = 6;

        System.out.println("m = " + m + ". Binary: " + BM_01_BinaryDecimalConversion.decimalToBinary(m));
        System.out.println("n = " + n + ". Binary: " + BM_01_BinaryDecimalConversion.decimalToBinary(n));
        System.out.println("Inserting " + m + " into " + n + " from position " + j + " to " + i + ":" );

        int result = insertANumberIntoAnother(m, n, i, j);

        System.out.println("Result = " + result + ". Binary: " + BM_01_BinaryDecimalConversion.decimalToBinary(result));
    }
}