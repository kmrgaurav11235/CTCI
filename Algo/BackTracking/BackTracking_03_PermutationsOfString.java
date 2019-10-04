/*
Write a program to print all permutations of a given string
Lecture: https://www.youtube.com/watch?v=78t_yHuGg-0
*/
class BackTracking_03_PermutationsOfString {
    static void moveChar (StringBuilder from, StringBuilder to, int fromIndex, int toIndex) {
        char nextChar = from.charAt(fromIndex);
        // Remove char
        from.deleteCharAt(fromIndex);
        to.insert(toIndex, nextChar);
    }
    static void permuteUtil(StringBuilder fixedString, StringBuilder leftString) {
        if (leftString.length() == 0) {
            System.out.print(fixedString.toString() + " ");
            return;
        }
        
        for (int i = 0; i < leftString.length(); i++) {
            // Choose the next character and fix it
            // Do this by moving this character from leftString to the end of fixedString
            moveChar(leftString, fixedString, i, fixedString.length());

            // Recursion
            permuteUtil(fixedString, leftString);

            // Un-fix the fixed character
            moveChar(fixedString, leftString, fixedString.length() - 1, i);
        }
    }

    static void permute(String str) {
        StringBuilder fixedString = new StringBuilder("");
        StringBuilder leftString = new StringBuilder(str);
        permuteUtil(fixedString, leftString);
    }

    static String swap (String str, int firstIndex, int secondIndex) {
        char [] charArray = str.toCharArray();

        // swap
        char temp = charArray[firstIndex];
        charArray[firstIndex] = charArray[secondIndex];
        charArray[secondIndex] = temp;

        return String.valueOf(charArray);
    }

    static void permuteAliterUtil(String str, int left, int right) {
        if (left == right) {
            System.out.print(str + " ");
            return;
        }
        for (int i = left; i <= right; i++) {
            // Choose the next character and fix it
            str = swap(str, left, i);
            // Recursion
            permuteAliterUtil(str, left + 1, right);
            // Un-fix the fixed character
            str = swap(str, left, i);
        }
    }

    static void permuteAliter(String str) {
        permuteAliterUtil(str, 0, str.length() - 1);
    }
    public static void main(String[] args) {
        String str = "ABC"; 
        System.out.println("Permutations of string = " + str + ":");
        permute(str); 

        // Instead of actually seperating the string into two strings, we can instead use
        // a marker variable to create virtual seperation
        System.out.println("\n\nPermutations of string = " + str + " (alternative method):");
        permuteAliter(str); 
    }
}