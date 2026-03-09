/*
Questions
How sparse are the vectors?
What is the range of values? Will the dot product overflow a standard int? Should I use long?)
Is the dimension (length) of the vectors the same?
Is one vector much denser than the other?

Approach
HashMap (Optimal for Random Access)
Concept: Store Map<Index, Value> for non-zero elements.
Logic: Iterate through the smaller map. Check if the index exists in the larger map.
Trade-off: Time O(min(N, M)) Space O(N + M).
Verdict: This is often preferred because it automatically optimizes for the case 
where one vector is very sparse and the other is dense.

Edge Cases
All Zeros: Map is empty. Loop never runs. Returns 0. Correct.
Full Array (Dense): Map stores all elements. Performance degrades to linear, but overhead of hashing makes it slower than raw arrays (Trade-off).
Large Values: If nums[i] is close to Integer.MAX_VALUE, the product might overflow. In Java, int * int wraps around. 
If the interviewer asks about overflow, suggest changing return type to long.
*/
Code
class SparseVector {
    
    // 1. Data Structure Selection
    // We use a Map to store ONLY non-zero elements.
    // Key: Index, Value: The number at that index.
    private Map<Integer, Integer> indexMap;

    SparseVector(int[] nums) {
        indexMap = new HashMap<>();
        
        // 2. Compression Step
        // Only store values that contribute to the dot product (non-zeros).
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                indexMap.put(i, nums[i]);
            }
        }
    }
    
    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        // 3. Optimization Strategy
        // We want to iterate over the vector with FEWER non-zero elements.
        // Looking up in a large map is O(1). Iterating a large map is expensive.
        // So: Iterate Small, Lookup Large.
        
        Map<Integer, Integer> smallMap = this.indexMap;
        Map<Integer, Integer> largeMap = vec.indexMap;
        
        // Swap if necessary to ensure we iterate the smaller one
        if (smallMap.size() > largeMap.size()) {
            smallMap = vec.indexMap;
            largeMap = this.indexMap;
        }
        
        int result = 0;
        
        // 4. Calculate Dot Product
        for (Integer index : smallMap.keySet()) {
            // If the index exists in the other vector, we have a match.
            // (Only non-zero * non-zero contributes to the sum)
            if (largeMap.containsKey(index)) {
                result += smallMap.get(index) * largeMap.get(index);
            }
        }
        
        return result;
    }
}
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);

/*
Dry Run:
Input: nums1 = [1, 0, 0, 2, 3], nums2 = [0, 3, 0, 4, 0]
Init V1: Stores {0:1, 3:2, 4:3}. Size 3.
Init V2: Stores {1:3, 3:4}. Size 2.
dotProduct(V1, V2):
Identify Small Map: V2 (Size 2).
Identify Large Map: V1 (Size 3).
Iterate V2 keys:
Key 1: Is it in V1? No. (Skip).
Key 3: Is it in V1? Yes. Value is 2.
Product = 4 (from V2) * 2 (from V1) = 8.
Sum = 8.
Result: 8.
Test Case 2: No Overlap
Input: nums1 = [0, 1, 0], nums2 = [0, 0, 1]
Init V1: {1:1}.
Init V2: {2:1}.
dotProduct:
Iterate V1 (Size 1).
Key 1: Is it in V2? No.
Result: 0.
*/

