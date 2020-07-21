import java.util.Comparator;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/find-median-from-data-stream/discuss/74047/JavaPython-two-heap-solution-O(log-n)-add-O(1)-find

- Given that integers are read from a data stream. Find median of elements read so far, in efficient way. 
    For simplicity assume there are no duplicates. e.g. let us consider the stream 5, 15, 1, 3 ...

    * After reading 1st element of stream - 5 -> median - 5
    * After reading 2nd element of stream - 5, 15 -> median - 10
    * After reading 3rd element of stream - 5, 15, 1 -> median - 5
    * After reading 4th element of stream - 5, 15, 1, 3 -> median - 4, so on...

- Making it clear, when the input size is odd, we take the middle element of sorted data. If the input size is 
    even, we pick average of middle two elements in sorted stream.

- The solution is to use two heaps -- 'small' (Max heap) and 'large' (Min Heap) -- each representing half of 
    the current list. The length of smaller half is kept to be n/2 at all time and the length of the larger half 
    is either n/2 or n/2 + 1 depending on n's parity.
- This way we only need to peek the two heaps' top number to calculate median.
- Any time before we add a new number, there are two scenarios, (total n numbers, k = n / 2):
    1) length of (small, large) == (k, k)
    2) length of (small, large) == (k, k + 1)

    After adding the number, total (n + 1) numbers, they will become:
    1) length of (small, large) == (k, k + 1)
    2) length of (small, large) == (k + 1, k + 1)

- Consider the first scenario. We know the 'large' will gain one more item and 'small' will remain the same size. 
    But we cannot just push the item into 'large'. We should push the new number into 'small'. Then we pop the 
    maximum item from 'small' then push it into 'large'. Similar for scenario 2, in the reverse order.
- Therefore to add a number, we have 3 O(log n) heap operations. Altogether, the add operation is O(log n). The 
    findMedian operation is O(1).
- Time Complexity: O(log n) insert, O(1) find
*/
class StreamMedianFinder {
    private PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder()); // Max heap
    private PriorityQueue<Integer> large = new PriorityQueue<>(); // Min Heap

    public void addNum(int num) {
        if (small.size() == large.size()) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if (large.isEmpty()) {
            // No element present
            return -1;
        } else if (small.size() == large.size()) {
            return (large.peek() + small.peek()) / 2.0;
        } else {
            return large.peek();
        }
    }

}
public class AOS_05_MedianInStream {
    public static void main(String[] args) {
        int [] stream = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};

        StreamMedianFinder streamMedianFinder = new StreamMedianFinder();
        for (int i = 0; i < stream.length; i++) {
            streamMedianFinder.addNum(stream[i]);
            System.out.println("Stream: " + printStream(stream, i) + ". Median: " + streamMedianFinder.findMedian());
        }
    }

    static String printStream(int[] stream, int n) {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i <= n; i++) {
            sb.append(stream[i] + " ");
        }
        sb.append("]");
        return sb.toString();
    }
}