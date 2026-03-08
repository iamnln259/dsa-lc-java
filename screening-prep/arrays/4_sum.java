Questions
Approach
Sorting + Two Pointers (Optimal)
Concept: Sort the array. Fix the first number (i), fix the second number (j). Now we have a 2Sum problem on the 
remaining subarray (find left and right such that sum is target - nums[i] - nums[j]).
Trade-off: Time $O(N^3)$. Space $O(1)$ (ignoring output list). This is the standard acceptable solution.
Optimization: We can add "pruning" logic. If the smallest possible sum is already $> target$, we break. 
If the largest possible sum is $< target$, we continue.

Edge Cases
Integer Overflow: Inputs like [10^9, 10^9, 10^9, 10^9] will fail without long.
Fewer than 4 elements: Guard clause handles this.
All Duplicates: [2,2,2,2,2], target 8. Code finds one [2,2,2,2], then skips all other 2s. Correct.
Code 
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 1. Initialize Result List
        List<List<Integer>> result = new ArrayList<>();
        
        // Guard Clause
        if (nums == null || nums.length < 4) {
            return result;
        }

        // 2. Sort the Array
        // Essential for Two Pointers and Duplicate Skipping
        Arrays.sort(nums);
        int n = nums.length;

        // 3. First Loop (Fix 'i')
        for (int i = 0; i < n - 3; i++) {
            
            // Skip Duplicates for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Optimization 1: Minimum possible sum check
            // If nums[i] combined with the 3 next smallest numbers is already > target, 
            // no valid quadruple exists (since array is sorted). Break.
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;

            // Optimization 2: Maximum possible sum check
            // If nums[i] combined with the 3 largest numbers is < target, 
            // then nums[i] is too small to be part of a solution. Continue to next i.
            if ((long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;

            // 4. Second Loop (Fix 'j')
            for (int j = i + 1; j < n - 2; j++) {
                
                // Skip Duplicates for j
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Similar Optimizations for j
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if ((long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;

                // 5. Two Pointers (Fix 'left' and 'right')
                int left = j + 1;
                int right = n - 1;
                
                while (left < right) {
                    // CRITICAL: Cast to long to prevent Integer Overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // Skip duplicates for left and right after finding a match
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // We need a larger sum
                    } else {
                        right--; // We need a smaller sum
                    }
                }
            }
        }

        return result;
    }
}

Dry Run

Test Case 1: nums = [1, 0, -1, 0, -2, 2], target = 0
Sort: [-2, -1, 0, 0, 1, 2]
i = 0 (Val -2):
j = 1 (Val -1):
left = 2 (Val 0), right = 5 (Val 2).
sum = -2 + (-1) + 0 + 2 = -1. Too small (< 0). left++.
left = 3 (Val 0).
sum = -2 + (-1) + 0 + 2 = -1. Too small. left++.
left = 4 (Val 1).
sum = -2 + (-1) + 1 + 2 = 0. Match! Add [-2, -1, 1, 2].
i = 1 (Val -1):
j = 2 (Val 0):
left = 3 (Val 0), right = 5 (Val 2).
sum = -1 + 0 + 0 + 2 = 1. Too big (> 0). right--.
...eventually finds [-1, 0, 0, 1].
Result: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]].
