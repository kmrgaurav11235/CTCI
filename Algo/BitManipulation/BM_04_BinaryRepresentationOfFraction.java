/*
- Given a real number between 0 and 1 (e.g. 0.72) that is passed as a double, print its binary representation.
- If the number cannot be represented accurately in binary with at most 32 characters, print "ERROR". 
- Binary representation of a non-integer: We can use analogy to a decimal number to figure it out. e.g.
    Given a binary number 
        0.101(bin) = 1 * (1/2) ^ 1 + 0 * (1/2) ^ 2 + 1 * (1/2) ^ 3
- So, to print the decimal part, we can multiply the number by 2 and check if 2n is >= 1. If it is, this bit
    is 1, otherwise 0.
- Alternatively, rather than multiplying the number by 2 and comparing it to 1, we can also keep dividing it
    by (1/2)^1, (1/2)^2, (1/2)^3 ... etc.
*/
public class BM_04_BinaryRepresentationOfFraction {

    static String printBinary(double num) {
        if (num <= 0 || num >= 1) {
            return "ERROR";
        }
        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; (i <= 32) && (num > 0); i++) {
            if (i == 32) {
                // Setting the limit of 32 characters
                return "ERROR";
            }
            num = num * 2;
            if (num >= 1) {
                num = num - 1;
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        double num = 0.125;
        System.out.println("Binary representation of " + num + " = " + printBinary(num));

        num = 0.72000026702880859375;
        System.out.println("Binary representation of " + num + " = " + printBinary(num));
    }
}