/*Questions
Is the array definitely sorted?
Can the array contain duplicates? Yes. 
What should we return if the array is empty or target is missing? [1,-1]

Approach
Concept: Run Binary Search twice.
Lower Bound: When nums[mid] == target, record it but search the left half to see if there's an earlier occurrence.
Upper Bound: When nums[mid] == target, record it but search the right half to see if there's a later occurrence.
Trade-off: Time $O(\log N)$. Space $O(1)$. This guarantees logarithmic time even if the array is all duplicates.

Edge Cases
Empty Array: N=0. Loop while(begin <= end) doest run. Returns [-1, -1]. Correct.
Target Not Found: findBound returns -1. Correct.
All Elements are Target: [8, 8, 8]. First finds index 0, Last finds index 2. Correct.
Single Element: [1], target 1. Loop runs once. Sets candidate, narrows bounds, returns 0. Correct.
*/


class Solution {
    public int[] searchRange(int[] nums, int target) {
        // 1. Initialize result with -1 (not found state)
        int[] result = new int[]{-1, -1};
        
        // 2. Find the First Occurrence (Left Bound)
        result[0] = findBound(nums, target, true);
        
        // Optimization: If the target wasn't found at all, 
        // there is no need to search for the last occurrence.
        if (result[0] == -1) {
            return result;
        }
        
        // 3. Find the Last Occurrence (Right Bound)
        result[1] = findBound(nums, target, false);
        
        return result;
    }

    // Helper function to perform modified Binary Search
    // isFirst: true searches for Left Bound, false searches for Right Bound
    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0;
        int end = N - 1;
        int candidate = -1; // Stores the potential answer found so far

        while (begin <= end) {
            int mid = begin + (end - begin) / 2;

            if (nums[mid] == target) {
                // Found the target! Record the index.
                candidate = mid;
                
                // CRITICAL LOGIC: Don't stop.
                if (isFirst) {
                    // If looking for First, try searching in the LEFT half
                    end = mid - 1;
                } else {
                    // If looking for Last, try searching in the RIGHT half
                    begin = mid + 1;
                }
            } else if (nums[mid] > target) {
                // Target is smaller, look left
                end = mid - 1;
            } else {
                // Target is larger, look right
                begin = mid + 1;
            }
        }
        
        return candidate;
    }
}

/*
Dry Run 

Test Case 1: nums = [5, 7, 7, 8, 8, 10], target = 8
Phase 1: Find First (isFirst = true)
begin=0, end=5. mid=2 (Val 7). 7 < 8. Move Right. begin=3.
begin=3, end=5. mid=4 (Val 8). 8 == 8.
candidate = 4.
isFirst is true, so look left. end = 3.
begin=3, end=3. mid=3 (Val 8). 8 == 8.
candidate = 3.
isFirst is true, so look left. end = 2.
begin=3, end=2. Loop terminates. Return 3.
Phase 2: Find Last (isFirst = false)
begin=0, end=5. mid=2 (Val 7). Move Right. begin=3.
begin=3, end=5. mid=4 (Val 8). 8 == 8.
candidate = 4.
isFirst is false, so look right. begin = 5.
begin=5, end=5. mid=5 (Val 10). 10 > 8. Move Left. end=4.
begin=5, end=4. Loop terminates. Return 4.
Result: [3, 4].

Test Case 2: nums = [5, 7, 7, 8, 8, 10], target = 6
Phase 1: Find First
begin=0, end=5. mid=2 (Val 7). 7 > 6. Move Left. end=1.
begin=0, end=1. mid=0 (Val 5). 5 < 6. Move Right. begin=1.
begin=1, end=1. mid=1 (Val 7). 7 > 6. Move Left. end=0.
begin=1, end=0. Loop terminates. candidate is still -1. Return -1.
Result: [-1, -1] (Optimized return).
