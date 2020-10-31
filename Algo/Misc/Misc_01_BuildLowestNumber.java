/*
https://www.geeksforgeeks.org/build-lowest-number-by-removing-n-digits-from-a-given-number/

- Given a string 'str' of digits and an integer 'n', build the lowest possible number by removing 'n' digits 
    from the string and not changing the order of input digits.

- Examples:
    Input: str = "4325043", n = 3
    Output: "2043"

    Input: str = "765028321", n = 5
    Output: "0221"

    Input: str = "121198", n = 2
    Output: "1118" 
- Instead of removing characters from str, we will construct the new number by taking characters from str.
- The idea is based on the fact that among first (n+1) characters, one character must be there in resultant 
    number. So, we pick the smallest of first (n+1) digits and put it in result. Let us say this character 
    is present at 'minIndex'.
- We will not use characters that come before minIndex. This is because the restriction on not changing the 
    order of input digits. If we use any character before minIndex, it will be larger than str[minIndex] and
    it will come before str[minIndex] in the result. So, it will not be lowest possible number.
- For the next step, we will recur for remaining characters.
- Algorithm:
    buildLowestNumber(str, n, res)
    1) Initialize result as empty string: res = ""
    2) If n == 0, then there is nothing to remove. Append the whole 'str' to 'res' and return
    3) Let 'len' be length of 'str'. If 'len' is smaller or equal to n, then everything can be removed. Append 
        nothing to 'res' and return
    4) Find the smallest character among first (n+1) characters of 'str'. Let the index of smallest character 
        be minIndex. Append 'str[minIndex]' to 'res' and recur for substring after minIndex and for n = n-minIndex
        buildLowestNumber(str[minIndex+1..len-1], n-minIndex).
*/
public class Misc_01_BuildLowestNumber {
    private static void buildLowestNumberUtil(String str, int n, StringBuilder result) {
        if (n == 0) {
            result.append(str);
            return;
        } else if (str.length() <= n) {
            return;
        }

        // Get the smallest number among the first n + 1
        int minIndex = 0;
        for (int i = 1; i <= n; i++) {
            if (str.charAt(minIndex) > str.charAt(i)) {
                minIndex = i;
            }
        }

        // Add it to result
        result.append(str.charAt(minIndex));

        // Recurse
        buildLowestNumberUtil(str.substring(minIndex + 1), n - minIndex, result);
    }

    static String buildLowestNumber(String str, int n) {
        StringBuilder result = new StringBuilder();
        buildLowestNumberUtil(str, n, result);
        return result.toString();
    }

    public static void main(String[] args) {
        String str1 = "121198"; 
        int n1 = 2; 
        System.out.println(buildLowestNumber(str1, n1)); 

        String str2 = "4325043"; 
        int n2 = 3; 
        System.out.println(buildLowestNumber(str2, n2)); 

        String str3 = "765028321"; 
        int n3 = 5; 
        System.out.println(buildLowestNumber(str3, n3)); 
    }
}
