// Common Bit Tasks: Get, Set and Clear bit
public class BM_02_BasicBitOperations {
    // Get Bit
    static int getBit(int num, int index) {
        return (num & (1 << index)) >> index;
    }

    // Set Bit
    static int setBit(int num, int index) {
        return num | (1 << index);
    }

    // Clear a particular bit
    static int clearBit(int num, int index) {
        int mask = 1 << index;
        return num & (~ mask);
    }

    // Clear from MSB till a particular bit (inclusive)
    static int clearMSBToBit(int num, int index) {
        int mask = (1 << index) - 1;
        return num & mask;
    } 

    // Clear from a particular bit (inclusive) to 0th index
    static int clearBitToZero(int num, int index) {
        int mask = (~0) << index;
        return num & mask;
    } 
    public static void main(String[] args) {
        int num = 169;

        System.out.println("Num = " + num + ". Binary = " + getBinaryDisplay(num)); // 10101001

        System.out.println("\n********** Get Bit **********");
        System.out.println("Get bit at index 2: " + getBit(num, 2));
        System.out.println("Get bit at index 3: " + getBit(num, 3));
        System.out.println("Get bit at index 5: " + getBit(num, 5));

        System.out.println("\n********** Set Bit **********");
        System.out.println("Set bit at index 0: " + getBinaryDisplay(setBit(num, 0)));
        System.out.println("Set bit at index 2: " + getBinaryDisplay(setBit(num, 2)));
        System.out.println("Set bit at index 4: " + getBinaryDisplay(setBit(num, 4)));

        System.out.println("\n********** Clear Bit **********");
        System.out.println("Clear bit at index 0: " + getBinaryDisplay(clearBit(num, 0)));
        System.out.println("Clear bit at index 1: " + getBinaryDisplay(clearBit(num, 1)));

        System.out.println("\n********** Clear Bit Through N **********");
        System.out.println("Clear Bit Through index 2: " + getBinaryDisplay(clearMSBToBit(num, 2)));
        System.out.println("Clear Bit Through index 4: " + getBinaryDisplay(clearMSBToBit(num, 4)));

        System.out.println("\n********** Clear Bit Through Zero **********");
        System.out.println("Clear Bit Through Zero at index 2: " + getBinaryDisplay(clearBitToZero(num, 2)));
        System.out.println("Clear Bit Through Zero at index 4: " + getBinaryDisplay(clearBitToZero(num, 4)));
    }

    static String getBinaryDisplay(int num) {
        String prefix = "";
        if (num > 1) {
            prefix = getBinaryDisplay(num / 2);
        }
        return prefix + (num % 2);
    }
}