/*
Questions
Nums1 has enough space to hold both arrays?
Are the arrays sorted in ascending or descending order?
Can m or n be zero?
Approach
1. Merge and Sort 
2. Two Pointers With Extra Space O(M+N), O(M) extra space
3. Three Pointers 
utilize the empty space at the end of nums1. 
Compare the largest elements from both arrays (the ends). Place the larger one at the very end of nums1.
O(M+N). Space O(1) 
Edge Cases 
m = 0: The loop while (p2 >= 0) effectively acts as a copy loop, moving all of nums2 into nums1.
n = 0: p2 starts at -1. The loop never runs. nums1 remains unchanged (which is correct).
Duplicate values: The comparison > ensures stability or arbitrary order. Since we fill from the back, duplicates are placed side-by-side correctly.
*/
Code
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 1. Initialize three pointers
        // p1 points to the last valid element in nums1
        int p1 = m - 1;
        
        // p2 points to the last element in nums2
        int p2 = n - 1;
        
        // pMerge points to the end of the total array (where we write values)
        int pMerge = m + n - 1;

        // 2. Iterate backwards while there are still elements in nums2
        // We only care about p2 >= 0. If p1 runs out first, we just copy the rest of nums2.
        // If p2 runs out first, the rest of nums1 is already in place!
        while (p2 >= 0) {
            
            // 3. Compare the largest remaining elements
            // Note: We must check p1 >= 0 to ensure we don't access index -1
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                // If nums1's element is larger, place it at the end
                nums1[pMerge] = nums1[p1];
                p1--;
            } else {
                // Otherwise (nums2 is larger OR p1 is exhausted), place nums2 element
                nums1[pMerge] = nums2[p2];
                p2--;
            }
            
            // 4. Move the write pointer backward
            pMerge--;
        }
    }
}

Dry Run
/*
Test Case 1: Standard Merge
Input: nums1 = [1, 2, 3, 0, 0, 0], m = 3, nums2 = [2, 5, 6], n = 3
Init: p1=2 (val 3), p2=2 (val 6), pMerge=5.
Iter 1: 3 vs 6. 6 is bigger. nums1[5] = 6. p2 becomes 1. pMerge becomes 4.
Iter 2: 3 vs 5. 5 is bigger. nums1[4] = 5. p2 becomes 0. pMerge becomes 3.
Iter 3: 3 vs 2. 3 is bigger. nums1[3] = 3. p1 becomes 1. pMerge becomes 2.
Iter 4: 2 vs 2. Tied (goes to else). nums1[2] = 2. p2 becomes -1. pMerge becomes 1.
End: p2 < 0. Loop terminates.
Result: [1, 2, 2, 3, 5, 6]. Correct.
Test Case 2: nums1 Exhausted First (Edge Case)
Input: nums1 = [4, 5, 6, 0, 0, 0], m = 3, nums2 = [1, 2, 3], n = 3
(Note: This input implies a final sorted array, but wait... if nums1 starts with larger numbers, let's reverse the scenario to show the edge case properly).
Correct Edge Case Input: nums1 = [0, 0, 0], m=0, nums2 = [1, 2, 3], n=3.
Init: p1 = -1, p2 = 2, pMerge = 2.
Iter 1: p1 is -1. Condition p1 >= 0 fails. Enter else.
nums1[2] = nums2[2] (3). p2 becomes 1.
Iter 2: p1 is -1. Enter else.
nums1[1] = nums2[1] (2). p2 becomes 0.
Iter 3: p1 is -1. Enter else.
nums1[0] = nums2[0] (1). p2 becomes -1.
End: Loop terminates.
Result: [1, 2, 3]. Correct.
*/
