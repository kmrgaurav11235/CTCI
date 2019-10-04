/*
Write a program to print all binary numbers of a particular length
Lecture: https://www.youtube.com/watch?v=HvGkzDT2ffI
*/
class BackTracking_01_PrintAllBinary {
    static void printBinaryUtil(String prefix, int numOfDigitsLeftToFix) {
        if  (numOfDigitsLeftToFix == 0) {
            // When no digits are left to fix, print the prefix
            System.out.print(prefix + " ");
            return;
        }
        // At each step, we fix one digit (to all possible values - 0 and 1)
        printBinaryUtil(prefix + "0", numOfDigitsLeftToFix - 1);
        printBinaryUtil(prefix + "1", numOfDigitsLeftToFix - 1);
    }

    static void printBinary(int numOfDigits) {
        printBinaryUtil("", numOfDigits);
    }
    public static void main(String[] args) {
        System.out.println("All Binary Numbers of Length = 2:");
        printBinary(2); 

        System.out.println("\n\nAll Binary Numbers of Length = 3:");
        printBinary(3); 

        System.out.println("\n\nAll Binary Numbers of Length = 4:");
        printBinary(4); 
    }
}