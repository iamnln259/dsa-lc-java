Questions
Are the elements unique?
Is the array guaranteed to be rotated?
What if the array is empty? return -1

Approach 

One-Pass Binary Search (Optimal & Expected)
Concept: Inside the standard Binary Search loop, check which half is sorted.
Logic:
If nums[low] <= nums[mid]: The Left Half is sorted. Check if target is in that range.
Else: The Right Half is sorted. Check if target is in that range.
Trade-off: Time O(log N)$. Space O(1). This is the cleanest solution.

Edge Cases 


Code 

class Solution {
    public int search(int[] nums, int target) {
        // 1. Guard Clause
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        // 2. Binary Search Loop
        while (low <= high) {
            // Avoid overflow with (low + high) / 2
            int mid = low + (high - low) / 2;

            // 3. Target Found
            if (nums[mid] == target) {
                return mid;
            }

            // 4. Determine which half is sorted
            // Check if the left side [low ... mid] is sorted
            if (nums[low] <= nums[mid]) {
                // 5. Logic for Sorted Left Side
                // Check if target lies strictly within this sorted range
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1; // Target is here, discard right
                } else {
                    low = mid + 1;  // Target is not here, go right
                }
            } 
            // 6. Logic for Sorted Right Side (nums[low] > nums[mid])
            else {
                // Check if target lies strictly within this sorted range
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;  // Target is here, discard left
                } else {
                    high = mid - 1; // Target is not here, go left
                }
            }
        }

        // 7. Target not found
        return -1;
    }
}

Dry Run 
Test Case 1: Target in Rotated Right (nums = [4,5,6,7,0,1,2], target = 0)
Init: low=0 (4), high=6 (2).
Iter 1:
mid = 3 (Value 7).
Is nums[0] (4) <= nums[3] (7)? Yes. Left half is sorted.
Is target (0) between 4 and 7? No.
Go Right: low = mid + 1 = 4.
Iter 2:
low=4 (0), high=6 (2).
mid = 5 (Value 1).
Is nums[4] (0) <= nums[5] (1)? Yes. Left half (of current window) is sorted.
Is target (0) between 0 and 1? Yes.
Go Left: high = mid - 1 = 4.
Iter 3:
low=4, high=4.
mid = 4 (Value 0).
nums[mid] == target? Yes. Return 4.
Test Case 2: Target Not Found (nums = [4,5,6,7,0,1,2], target = 3)
Iter 1: mid=7. Left sorted. Target (3) not in [4, 7]. Go Right (low=4).
Iter 2: mid=1. Left ([0...1]) sorted. Target (3) not in [0, 1]. Go Right (low=6).
Iter 3: low=6 (2), high=6 (2). mid=2.
2 != 3.
Left sorted (2 <= 2). Target not in [2, 2].
low becomes 7.
End: low (7) > high (6). Loop breaks. Return -1.
