/*
Questions
Is the array sorted?
Are there duplicate numbers in the array?
Is there guaranteed to be exactly one solution?
Can I use the same element twice?
Approach
Two Nested Loops
One-Pass Hash Table 
Iterate through the array once. For every element x, calculate the complement (target - x). Check if the complement is already in our map. If yes, we found the pair. If no, store x and continue.
Time is O(N). Space is O(N). This is the most efficient and cleanest implementation.
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 1. Guard clause: Although problem guarantees a solution, 
        // in production we always check for null or insufficient inputs.
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two numbers");
        }
        
        // 2. Map to store the number (key) and its index (value)
        // We use a Map to reduce the lookup time from O(N) to O(1)
        Map<Integer, Integer> numMap = new HashMap<>();
        
        // 3. Iterate through the array exactly once
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            
            // 4. Calculate the 'complement' we need to find to reach the target
            int complement = target - currentNum;
            
            // 5. Check if we have seen this complement before
            if (numMap.containsKey(complement)) {
                // Target acquired! Return the index of the complement and the current index.
                return new int[] { numMap.get(complement), i };
            }
            
            // 6. If not found, add the current number and its index to the map for future checks
            numMap.put(currentNum, i);
        }
        
        // 7. Fallback (should not be reached given problem constraints)
        throw new IllegalArgumentException("No two sum solution found");
    }
}
