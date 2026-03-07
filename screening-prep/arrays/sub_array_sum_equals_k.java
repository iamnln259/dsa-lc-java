/*
Questions:
1.Can the Array contain negative number? YES
2.Array Size Bounds - (2 to the powe 10)
3.Can the Araay contain ZEROes ?

Approaches:
1.Nested Loops, O(n^2)
2.Prefix Sum + HashMAp , O(N), O(N)
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        // 1. Map to store the frequency of prefix sums encountered so far.
        // Key: Prefix Sum value
        // Value: Count of times this sum has occurred
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        
        // 2. Base Case Initialization:
        // A sum of 0 has occurred exactly once (conceptually, before the array starts).
        // This handles cases where a subarray starting from index 0 equals k.
        prefixSumMap.put(0, 1);
        
        int count = 0;
        int currentSum = 0;
        
        // 3. Single pass iteration
        for (int num : nums) {
            // 4. Update the running prefix sum
            currentSum += num;
            
            // 5. The Core Logic:
            // Check if there exists a previous prefix sum 'X' such that:
            // currentSum - X = k  =>  X = currentSum - k
            if (prefixSumMap.containsKey(currentSum - k)) {
                // If found, add the number of times that prefix sum occurred to our count.
                count += prefixSumMap.get(currentSum - k);
            }
            
            // 6. Add/Update the current prefix sum to the map for future checks
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}
