/*
Questions
What is the range of the weights?
How frequently is pickIndex called f called once, linear scan is fine ($O(N)$). 
If called millions of times, we need $O(\log N)$ or $O(1)$ lookup).
Can weights be zero? Constraints usually say positive integers. If 0 is allowed, 
we must handle the edge case where an index has 0 probability
Does the total sum of weights fit in an Integer?

Approach
Precompute a cumulative sum array (CDF). This effectively gives each index a "range" on a number line.
Generate a random number between 1 and totalSum.
Use Binary Search to find which range that number falls into.
Trade-off: Precomputation Time: O(N). Pick Time: O(log N). Space: O(N).


Edge Cases 
Single Element: w = [100]. prefixSums = [100]. rand generates 1 to 100. 
Binary search always returns index 0. Correct.
Max Value: Random returns totalSum (max possible). The binary search will correctly converge on the last element.
Zero Weights: The problem statement typically says $w[i] \ge 1$. If 0 was allowed (e.g., [1, 0, 3]), 
prefix sums would be [1, 1, 4].
If target is 1, binary search finds the first 1 (index 0). Index 1 is skipped appropriately. The logic holds.
Code
*/
class Solution {
    // 1. Array to store cumulative sums (Prefix Sums)
    private int[] prefixSums;
    // 2. Variable to store the total weight sum
    private int totalSum;
    // 3. Random object for generating numbers
    private Random rand;

    // Constructor: O(N) Time
    public Solution(int[] w) {
        this.prefixSums = new int[w.length];
        this.rand = new Random();
        this.totalSum = 0;
        
        // 4. Build the Prefix Sum array
        // Example w: [1, 3] -> prefixSums: [1, 4]
        // This maps index 0 to range [1, 1] and index 1 to range [2, 4]
        for (int i = 0; i < w.length; i++) {
            totalSum += w[i];
            prefixSums[i] = totalSum;
        }
    }
    
    // Pick Logic: O(log N) Time
    public int pickIndex() {
        // 5. Generate a random target between 1 and totalSum (inclusive)
        // nextInt(totalSum) returns 0 to totalSum-1. Adding 1 shifts it to 1 to totalSum.
        int target = rand.nextInt(totalSum) + 1;
        
        // 6. Binary Search to find the first prefixSum >= target
        int left = 0;
        int right = prefixSums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (target > prefixSums[mid]) {
                // Target is larger than the current cumulative range, move right
                left = mid + 1;
            } else {
                // Target is within or equal to the current range, try to narrow down to the left
                right = mid;
            }
        }
        
        return left;
    }
}

/*
Dry Run

Test Case 1: Standard
Input: w = [1, 3]
Init: totalSum = 4. prefixSums = [1, 4].
Call pickIndex():
rand generates target. Lets say target = 3.
Binary Search:
left=0, right=1, mid=0.
prefixSums[0] is 1.
3 > 1 is True. left becomes 1.
Loop condition 1 < 1 is False.
Return left (1).

Test Case 2: Uniform Distribution
Input: w = [1, 1, 1]
Init: totalSum = 3. prefixSums = [1, 2, 3].
Call pickIndex():
rand generates target = 1.
Binary Search:
left=0, right=2, mid=1.
prefixSums[1] is 2.
1 > 2 is False. right becomes 1.
Loop left=0, right=1, mid=0.
prefixSums[0] is 1.
1 > 1 is False. right becomes 0.
Loop ends.
Return 0.
Logic check: Target 1 belongs to index 0 (range 0-1). Correct.
