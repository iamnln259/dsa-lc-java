Questions
Is the array sorted? (Usually no. 
What if there are multiple triplets with the same minimum difference? (Any one of them is fine, as we just return the sum).
Can the input array have fewer than 3 elements? (Constraints say $3 \le N$, so we dont need a check for that).
Will the sum overflow a standard integer? (Constraints say values are around 1000. Max sum $\approx 3000$. int is safe).

Approach
Concept: First, sort the array. Fix one number (nums[i]) and use two pointers (left, right) to find the other two numbers.
Logic:
Calculate currentSum = nums[i] + nums[left] + nums[right].
If currentSum is closer to target than our best record, update the record.
If currentSum < target, we need a larger sum $\to$ move left pointer to the right.
If currentSum > target, we need a smaller sum $\to$ move right pointer to the left.
Trade-off: Time $O(N^2)$. Space $O(\log N)$ (for sorting). This is the standard interview solution.

Edge Cases
Exact Match: The logic return currentSum inside the loop handles this instantly for $O(1)$ best-case scenarios.
Length = 3: The loop runs once. Returns the sum of all elements. Correct.
Large Negative Numbers: Math.abs handles negative differences correctly.
Code

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 1. Sort the array
        // Sorting allows us to use the Two Pointer technique efficiently.
        // Time cost: O(N log N)
        Arrays.sort(nums);

        // 2. Initialize the result with the first possible triplet
        // We assume the first 3 elements are the "closest" until proven otherwise.
        int closestSum = nums[0] + nums[1] + nums[2];

        // 3. Iterate through the array
        // We fix one number (nums[i]) and look for the other two.
        // We stop at length - 2 because we need at least 2 numbers after 'i'.
        for (int i = 0; i < nums.length - 2; i++) {
            
            // Initialize pointers
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                // 4. Update Global Closest
                // If the current diff is smaller than the best diff seen so far, update.
                if (Math.abs(target - currentSum) < Math.abs(target - closestSum)) {
                    closestSum = currentSum;
                }

                // 5. Pointer Movement Logic
                if (currentSum < target) {
                    // We need a larger sum -> Move Left pointer forward (to bigger numbers)
                    left++;
                } else if (currentSum > target) {
                    // We need a smaller sum -> Move Right pointer backward (to smaller numbers)
                    right--;
                } else {
                    // Exact match found! Diff is 0. Cannot get closer than this.
                    return currentSum;
                }
            }
        }

        return closestSum;
    }
}



Dry Run
Test Case 1: nums = [-1, 2, 1, -4], target = 1
Sort: [-4, -1, 1, 2]
Init: closestSum = -4 + (-1) + 1 = -4.
i = 0 (Val -4):
left = 1 (Val -1), right = 3 (Val 2).
sum = -4 + (-1) + 2 = -3.
Diff |-3 - 1| = 4. Current best diff |-4 - 1| = 5.
Update: closestSum becomes -3.
sum (-3) < target (1) $\to$ left++.
left = 2 (Val 1), right = 3 (Val 2).
sum = -4 + 1 + 2 = -1.
Diff |-1 - 1| = 2. Best diff 4.
Update: closestSum becomes -1.
sum (-1) < target (1) $\to$ left++.
left (3) meets right (3). Loop ends.
i = 1 (Val -1):
left = 2 (Val 1), right = 3 (Val 2).
sum = -1 + 1 + 2 = 2.
Diff |2 - 1| = 1. Best diff 2 (from sum -1).
Update: closestSum becomes 2.
sum (2) > target (1) $\to$ right--. Loop ends.
Return: 2.
