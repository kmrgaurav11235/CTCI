/*
https://www.geeksforgeeks.org/program-binary-decimal-conversion/
https://www.geeksforgeeks.org/binary-representation-of-a-given-number/?ref=lbp

Binary To Decimal Conversion
- The idea is to extract the digits of given binary number starting from right most digit and keep it in a 
    variable 'decimal'. At the time of extracting digits from the binary number, multiply the digit with the 
    proper base (Power of 2) and add it to the variable 'decimal'. At the end, the variable 'decimal' will 
    store the required decimal number.
- For Example:
    If the binary number is 111.
    decimal = 1*(2^2) + 1*(2^1) + 1*(2^0) = 7

Decimal to Binary Conversion
- Algorithm:
    1. Store the remainder when the number is divided by 2 in an array.
    2. Divide the number by 2
    3. Repeat the above two steps until the number is greater than zero.
    4. Print the array in reverse order now.

*/
public class BM_01_BinaryDecimalConversion {
    static int binaryToDecimal(int binaryNum) {
        int decimal = 0, index = 0;
        while(binaryNum > 0) {
            int bit = binaryNum % 10;
            binaryNum = binaryNum / 10;
            decimal += bit * Math.pow(2, index);
            index++;
        }
        return decimal;
    }

    static String decimalToBinary(int decimal) {
        String suffix = "";
        if (decimal > 1) {
            suffix = decimalToBinary(decimal / 2);
            // bitwise operator: The above can also be replaced by:
            // suffix = decimalToBinary(decimal >> 1);
        }
        int prefix = decimal % 2;
        return prefix + suffix;
    }
    public static void main(String[] args) {
        int binary1 = 10101001, binary2 = 1001;
        System.out.println("Converting " + binary1 + " into decimal: " + binaryToDecimal(binary1) + ". Expected: 169.");
        System.out.println("Converting " + binary2 + " into decimal: " + binaryToDecimal(binary2) + ". Expected: 9.");

        int decimal1 = 131, decimal2 = 7;
        System.out.println("Converting " + decimal1 + " into binary: " + decimalToBinary(decimal1) + ". Expected: 10000011.");
        System.out.println("Converting " + decimal2 + " into binary: " + decimalToBinary(decimal2) + ". Expected: 111.");
    }
}