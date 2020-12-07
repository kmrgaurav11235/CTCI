import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://www.geeksforgeeks.org/merging-intervals/
https://leetcode.com/problems/merge-intervals/discuss/21222/A-simple-Java-solution

- Given a set of time intervals in any order, merge all overlapping intervals into one and output the 
    result which should have only mutually exclusive intervals. Let the intervals be represented as 
    pairs of integers for simplicity. 
- For example, let the given set of intervals be {{1,3}, {2,4}, {5,7}, {6,8}}. The intervals {1,3} and 
    {2,4} overlap with each other, so they should be merged and become {1, 4}. Similarly, {5, 7} and 
    {6, 8} should be merged and become {5, 8}.
- The idea is to first sort the intervals according to the starting time. Once we have the sorted 
    intervals, we can combine all intervals in a linear traversal. In sorted array of intervals, if 
    interval[i] doesn’t overlap with interval[i-1], then interval[i+1] cannot overlap with interval[i-1] 
    because starting time of interval[i+1] must be greater than or equal to interval[i]. 
- Algorithm:
    1) Sort all intervals in increasing order of start time.
    2) Traverse sorted intervals starting from first interval, do following for every interval:
        a) If current interval is not first interval and it overlaps with previous interval, then merge 
            it with previous interval. Keep doing it while the interval overlaps with the previous one.         
        b) Else add current interval to output list of intervals.
- Time: O(n Log n). Space: O(1)
*/
public class AM_02_MergeOverlappingIntervals {
    static int[][] mergeIntervals(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        // Sort by ascending order of interval start
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);

        // Will be used to create the output array
        List<int[]> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(intervals[0]);

        // Stores index of last element in output array 'mergedIntervals'
        int index = 0;

        // Traverse all input Intervals
        for (int i = 1; i < intervals.length; i++) {
            //
            if (mergedIntervals.get(index)[1] >= intervals[i][0]) {
                // If this interval overlaps with the previous one -> Merge previous and current
                // Intervals
                mergedIntervals.get(index)[0] = Math.min(mergedIntervals.get(index)[0], intervals[i][0]);
                mergedIntervals.get(index)[1] = Math.max(mergedIntervals.get(index)[1], intervals[i][1]);
            } else {
                // Disjoint intervals, add the curr interval as a new element in output array
                // 'mergedIntervals'
                mergedIntervals.add(intervals[i]);
                index++;
            }
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
    public static void main(String[] args) {
        int [][] intervals = {
            {15, 18},
            {1, 3},
            {2, 6},
            {8, 10}
        };
        int [][] mergeIntervals = mergeIntervals(intervals); 
        System.out.println("Merged Intervals: ");
        for (int[] interval : mergeIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
