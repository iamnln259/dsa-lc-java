/*
Questions
Are the input lists disjoint internally : Yes
Are the lists sorted? : yes Sorted by Start Time
What if one list is empty? : Return Empty

Edge Cases
Empty Lists: While loop condition i < len && j < len fails immediately. Returns empty array. Correct.
Touching Intervals: [1, 2] vs [2, 3]. MaxStart=2, MinEnd=2. Intersection [2, 2]. Correct.
One fully inside another: [1, 10] vs [2, 3]. MaxStart=2, MinEnd=3. Intersection [2, 3]. 
Smaller one ends first, pointer moves. Correct.

Approach
Two Pointer. i for List A, j for List B
Determine the Start of Intersection : max (Start A, Start B)
Detemine the End of Intersection: min(End A, End B)
if Start <= End, it is a Valid Intersection. 
Move the Pointer of the Interval, that Ends first. 
Time: O(N+M) , O(N+M) for output storage.
Code
*/
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // 1. Initialize Pointers and Result List
        List<int[]> result = new ArrayList<>();
        int i = 0; // Pointer for firstList
        int j = 0; // Pointer for secondList
        // 2. Loop until one list is exhausted
        // Since intervals are sorted, we can scan both lists linearly.
        while (i < firstList.length && j < secondList.length) {    
            // 3. Find the potential intersection range
            // An intersection must start at the LATER of the two start times.
            // An intersection must end at the EARLIER of the two end times.
            int startMax = Math.max(firstList[i][0], secondList[j][0]);
            int endMin = Math.min(firstList[i][1], secondList[j][1]);
            // 4. Validate Intersection
            // If start <= end, it's a valid intersection (even if it's a single point [5,5])
            if (startMax <= endMin) {
                result.add(new int[]{startMax, endMin});
            }
            // 5. Advance Pointers (The Greedy Logic)
            // We discard the interval that finishes first.
            // Why? Because sorted order guarantees it cannot overlap with any subsequent 
            // interval in the other list.
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        // 6. Convert List to Array
        return result.toArray(new int[result.size()][]);
    }
}
/*

Dry Run
Test Case 1: Standard Overlap

Input: A = [[0,2], [5,10]], B = [[1,5], [8,12]]
Iter 1: A[0]=[0,2], B[0]=[1,5].
start = max(0, 1) = 1. end = min(2, 5) = 2.
1 <= 2. Add [1, 2].
A ends first (2 < 5). i++.
Iter 2: A[1]=[5,10], B[0]=[1,5].
start = max(5, 1) = 5. end = min(10, 5) = 5.
5 <= 5. Add [5, 5]. (Point intersection).
B ends first (5 < 10). j++.
Iter 3: A[1]=[5,10], B[1]=[8,12].
start = max(5, 8) = 8. end = min(10, 12) = 10.
8 <= 10. Add [8, 10].
A ends first (10 < 12). i++.
End: i out of bounds. Return [[1,2], [5,5], [8,10]].

No Overlap
Input: A = [[1, 3]], B = [[5, 9]]
Iter 1: A[0]=[1,3], B[0]=[5,9].
start = 5. end = 3.
5 <= 3 is False. No add.
A ends first (3 < 9). i++.
End: i out of bounds. Return [].
*/
