class Solution {
    public int removeDuplicates(int[] nums) {
        // 1. Guard Clause
        // If array is empty, there are 0 unique elements.
        if (nums.length == 0) {
            return 0;
        }
        // 2. Initialize the "Slow Pointer" (insertIndex)
        // We start at index 0 because the first element is always unique.
        // This pointer tracks where the NEXT unique element should go.
        int i = 0;
        // 3. Iterate with the "Fast Pointer" (j)
        // We start j at 1 because we compare it with nums[i] (index 0).
        for (int j = 1; j < nums.length; j++) {            
            // 4. Compare Fast Pointer with Slow Pointer
            // If the numbers are different, we found a new unique element.
            if (nums[j] != nums[i]) {        
                // Move the Slow Pointer forward to the next open slot
                i++;           
                // Overwrite that slot with the new unique value found at j
                nums[i] = nums[j];
            }
            // Else: If nums[j] == nums[i], it's a duplicate. 
            // We simply do nothing and let j increment to skip it.
        }
        // 5. Return the Length
        // Since 'i' is the index (0-based), the count is index + 1.
        return i + 1;
    }
}
