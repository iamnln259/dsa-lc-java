Qustions
How large is the input? if it is really huge, hash set will be out of memory.
Is the input mutable? If yes, I can sort the input. otherwise I have to use a hash set 
What is the range of the integer? If the range is smaller, i can go with boolean array or bit set. 

Approach 
Lenera Search For each element, search the rest of the array for a match.
Trade-off: Time is $O(N^2)$. Space is $O(1)$. Immediate Reject (Too slow)
Concept: Sort the array first. Then iterate once to check if nums[i] == nums[i+1].
Trade-off: Time is $O(N \log N)$. Space is $O(1)$ (or $O(\log N)$ depending on sort implementation).
Verdict: Good if memory is tight and we are allowed to modify the input.
Concept: Iterate through the array. For each number, check if it exists in a HashSet. If yes, return true. If no, add it.
Trade-off: Time is O(N). Space is O(N). This is the industry standard solution because memory is usually cheaper than CPU time.

Edge Case 
Empty Array ([]): Handled by guard clause. Returns false.
Single Element ([1]): Handled by guard clause. Returns false.
Negative Numbers ([-1, -1]): HashSet handles integers (positive or negative) correctly. Returns true.
Max Integer ([2147483647, 2147483647]): Fits in int, hashed correctly. Returns true.

Code 
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // 1. Guard Clause: Small arrays cannot have duplicates
        if (nums == null || nums.length < 2) {
            return false;
        }

        // 2. Initialize HashSet with a capacity equal to array length
        // Optimization: Setting initial capacity prevents resizing overhead 
        // as the set grows.
        Set<Integer> seen = new HashSet<>(nums.length);

        // 3. Iterate through the array once
        for (int num : nums) {
            
            // 4. The 'add' method returns false if the element is ALREADY present.
            // This is cleaner than writing: if (seen.contains(num)) return true; seen.add(num);
            if (!seen.add(num)) {
                // Duplicate found immediately
                return true;
            }
        }

        // 5. If loop finishes, no duplicates were found
        return false;
    }
}

Dry Run

Test Case 1: Duplicates Present ([1, 2, 3, 1])
Init: seen = {}.
Iter 1 (1): seen.add(1) returns true. seen = {1}.
Iter 2 (2): seen.add(2) returns true. seen = {1, 2}.
Iter 3 (3): seen.add(3) returns true. seen = {1, 2, 3}.
Iter 4 (1): seen.add(1) returns false (1 is already there).
Return: true. Correct.
Test Case 2: All Distinct ([1, 2, 3, 4])
Init: seen = {}.
Iter 1-4: All add operations return true. seen = {1, 2, 3, 4}.
Loop Ends: No return triggered.
Return: false. Correct.
