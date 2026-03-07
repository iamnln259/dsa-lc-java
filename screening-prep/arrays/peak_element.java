Questions:
What if there are multiple peaks
What counts as the edge of the array?
Can neighbors be equal?
Is the O(Log N) constraint strict?

Approach:
Concept: We look at the slope at mid.
If nums[mid] < nums[mid+1]: We are on an uphill slope. Since the array eventually ends at -infty, a peak must exist to the right.
If nums[mid] > nums[mid+1]: We are on a downhill slope. A peak must exist to the left (or be mid itself).
Trade-off: Time O(log N). Space O(1)
Potential Edge Cases:
Single Element: nums = [1]. Loop 0 < 0 is false immediately. Returns 0. Correct.
Strictly Increasing: [1, 2, 3]. Logic always goes right (left = mid + 1). Ends at last index. Correct.
Strictly Decreasing: [3, 2, 1]. Logic always goes left (right = mid). Ends at index 0. Correct.

class Solution {
    public int findPeakElement(int[] nums) {
        // Edge case: single element is always a peak
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        // 1. Initialize Binary Search Pointers
        int left = 0;
        int right = nums.length - 1;

        // 2. Loop until pointers converge
        // We use 'left < right' instead of 'left <= right' because
        // we are searching for a specific condition (peak), not a specific value.
        // We want to stop when the search space reduces to a single element.
        while (left < right) {
            
            // 3. Calculate Midpoint
            // Standard overflow-safe formula
            int mid = left + (right - left) / 2;

            // 4. Compare with Right Neighbor
            // We only need to compare mid with mid+1 to determine the slope.
            if (nums[mid] < nums[mid + 1]) {
                // Rising Slope: The peak must be to the right.
                // We move 'left' past 'mid' because 'mid' is definitely NOT the peak
                // (it is smaller than its right neighbor).
                left = mid + 1;
            } else {
                // Falling Slope: The peak is either at 'mid' or to the left.
                // We keep 'mid' in the search space because it could be the peak itself.
                right = mid;
            }
        }

        // 5. Return Result
        // When left == right, we have found a local maximum.
        return left;
    }
}

Test Case 1: nums = [1, 2, 3, 1]
Init: left=0, right=3.
Iter 1:
mid = 1 (Value 2).
Compare nums[1] (2) vs nums[2] (3).
$2 < 3$. Rising slope.
left becomes mid + 1 = 2.
Iter 2:
mid = 2 (Value 3). left=2, right=3.
Compare nums[2] (3) vs nums[3] (1).
$3 > 1$. Falling slope.
right becomes mid = 2.
End: left (2) equals right (2). Loop terminates.
Return: 2 (Index of value 3). Correct.
Test Case 2: nums = [1, 2, 1, 3, 5, 6, 4] (Multiple Peaks)
Init: left=0, right=6.
Iter 1: mid=3 (Value 3). Compare with nums[4] (5). Rising. left=4.
Iter 2: mid=5 (Value 6). Range [4, 6]. Compare with nums[6] (4). Falling. right=5.
Iter 3: mid=4 (Value 5). Range [4, 5]. Compare with nums[5] (6). Rising. left=5.
End: left (5) equals right (5).
Return: 5 (Index of value 6). Correct.
