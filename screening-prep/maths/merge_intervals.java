/*
Questions:
Is the input list already sorted? NO.
How do we handle intervals that just touch?[1, 4] and [4, 5]. Standard rule: they merge into [1, 5].
Can the intervals be empty or null?
Approach:
Sort the intervals based on their start times.
Logic: Once sorted, iterate through. If the current interval starts before the previous one ends, they overlap. We merge them by extending the previous interval's end time. If they don't overlap, we push the current interval to our result list and make it the new "active" interval.
Time is $O(N \log N)$ due to sorting. Space is $O(N)$ to hold the output.
Potential Edge Cases to Highlight
Fully Contained: [[1, 10], [2, 3]] -> Becomes [[1, 10]]. Handled by Math.max.
Single Point Overlap: [[1, 2], [2, 3]] -> Becomes [[1, 3]]. Handled by <=.
Unsorted Input: [[2,3], [1,4]]. The Arrays.sort step is mandatory to fix this.
*/

class Solution {
    public int[][] merge(int[][] intervals) {
        // 1. Guard Clause: Handle empty or single-interval inputs immediately
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 2. Sort the intervals by their START time (index 0).
        // Java's Arrays.sort uses a variant of Timsort (Merge Sort + Insertion Sort).
        // Lambda: (a, b) -> Integer.compare(a[0], b[0])
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();
        
        // 3. Initialize the 'current' interval with the first one
        int[] currentInterval = intervals[0];
        result.add(currentInterval);

        // 4. Iterate through the rest of the intervals
        for (int[] nextInterval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = nextInterval[0];
            int nextEnd = nextInterval[1];

            // 5. Check for overlap
            // Since we sorted by start time, nextStart >= currentStart is guaranteed.
            // We only need to check if the next interval starts BEFORE the current one ends.
            if (nextStart <= currentEnd) {
                // MERGE: Extend the current interval's end to the max of both ends.
                // We modify the array in-place within the result list (reference copy).
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // NO OVERLAP: The gap is confirmed.
                // Move 'currentInterval' pointer to this new interval and add it to the list.
                currentInterval = nextInterval;
                result.add(currentInterval);
            }
        }

        // 6. Convert List<int[]> back to int[][]
        return result.toArray(new int[result.size()][]);
    }
}
