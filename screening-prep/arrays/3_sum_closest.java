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
