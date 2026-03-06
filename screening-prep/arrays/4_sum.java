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
