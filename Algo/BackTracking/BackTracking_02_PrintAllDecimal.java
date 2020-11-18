/*
Write a program to print all non-negative decimal numbers of a particular length
Lecture: https://www.youtube.com/watch?v=HvGkzDT2ffI
*/
class BackTracking_02_PrintAllDecimal {
    static void printDecimalUtil(String prefix, int numOfDigitsLeftToFix) {
        if  (numOfDigitsLeftToFix == 0) {
            // When no digits are left to fix, print the prefix
            System.out.print(prefix + " ");
            return;
        }
        // At each step, we fix one digit (to all possible values - 0 to 9)
        for (int i = 0; i <= 9; i++) {
            printDecimalUtil(prefix + i, numOfDigitsLeftToFix - 1);
        }
        // Comment: Use int prefix if you want to print '1' instead of '0001' when numOfDigits = 4
    }

    static void printDecimal(int numOfDigits) {
        printDecimalUtil("", numOfDigits);
    }
    public static void main(String[] args) {
        System.out.println("All Decimal Numbers of Length = 1:");
        printDecimal(1); 

        System.out.println("\n\nAll Decimal Numbers of Length = 2:");
        printDecimal(2); 
    }
}