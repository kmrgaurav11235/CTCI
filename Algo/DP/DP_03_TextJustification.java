/*
Lecture: https://youtu.be/ENyox7kNKeY?t=1028
*/
class DP_03_TextJustification {
    // A utility function to print the solution 
    int printSolution (int p[], int n) 
    { 
        int k; 
        if (p[n] == 1) {
            k = 1;
        } else {
            k = printSolution (p, p[n]-1) + 1; 
        }

        System.out.println("Line number " + k + ": " +  
                    "From word no. "+ p[n] + " to " + n); 
        return k; 
    }

    private int calculateBadness(int numChars, int maxWidth) {
        if (numChars > maxWidth) {
            return Integer.MAX_VALUE;
        } else {
            return Math.pow(maxWidth - numChars, 3);
        }
    }

    int justifyTextUtil(int [] words, int [] minBadnes, boolean [] lineBreak, int startIndex, int endIndex, int maxWidth) {
        if (minBadnes[startIndex] != -1) {
            // Already calculated
            return minBadnes[startIndex];
        }
        if (startIndex == endIndex) {
            minBadnes[startIndex] = 0;
            return 0;
        }
        int badness = Integer.MAX_VALUE;

        int lineLength = words[startIndex];
        // int currIndex = startIndex + 1;????
        for (int i = startIndex + 1; i <= endIndex; i++) {
            int currBadness = calculateBadness(i, maxWidth);
            if (currBadness == Integer.MAX_VALUE) {
                break;
                // Break out of for-loop as there is no need test more cases
            } else {
                currBadness = currBadness + justifyTextUtil(words, minBadnes, lineBreak, i, endIndex, maxWidth);
            }

        }

        minBadnes[startIndex] = badness;
        return badness;
    }

    void justifyText(int [] words, int maxWidth) {
        /*
        For this problem, our choice happens after every word: should we put a line break there or not.
        After point when we have run out of space in the line (calculateBadness returns MAX_VALUE), the
        choices end.
        */
        int numWords = words.length;

        int minBadness[] = new int[numWords]; // Min badness possible after starting at index n and going till end
        for (int i = 0; i < numWords; i++) {
            minBadness[i] = -1;
        }

        boolean lineBreak[] = new boolean[numWords]; 
        // Is there a line-break after n-th word? Already initialized to false. Parent pointer.

        justifyTextUtil(words, minBadness, lineBreak, 0, numWords - 1, maxWidth);
    }

    public static void main(String[] args) {
        DP_03_TextJustification textJustification = new DP_03_TextJustification();

        int words[] = {3, 2, 2, 5};
        int maxWidth = 6; 
        textJustification.justifyText(words, numWords, maxWidth); 
    }
}