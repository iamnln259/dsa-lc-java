Questions
Are the elements in the input array unique: Yes
What is the maximum size of N?
Can the input be empty?Yes
Approach
Concept: We explore a decision tree. For every number, we have two choices: Include it or Exclude it.
Trade-off: Time O(N.2^N). Space O(N) (recursion stack). This is the most versatile template for all subset/permutation/combination problems
Edge Cases
Empty Array: nums = []. Loop doesnt run. Adds [] to result. Returns [[]]. Correct.
Single Element: nums = [1]. Returns [[], [1]]. Correct.
Duplicates: Since the problem statement usually guarantees unique elements for LeetCode 78, this logic works. If duplicates existed ([1, 2, 2]), this code would produce duplicate subsets ([2] and [2]). 
We would need to sort and add if(i > start && nums[i] == nums[i-1]) continue;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // 1. Initialize result list
        List<List<Integer>> result = new ArrayList<>();
        
        // 2. Start Backtracking
        // We pass:
        // - result: to collect answers
        // - tempList: the current subset we are building
        // - nums: the input array
        // - start: the index we are currently considering
        backtrack(result, new ArrayList<>(), nums, 0);
        
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        // 3. Add the current subset to results
        // CRITICAL: We must make a NEW copy (new ArrayList<>(tempList)).
        // If we just added 'tempList', we would add a reference to the 
        // same object, which keeps changing. The result would be a list of empty lists.
        result.add(new ArrayList<>(tempList));

        // 4. Iterate through remaining elements
        for (int i = start; i < nums.length; i++) {
            
            // 5. Decision: Include nums[i]
            tempList.add(nums[i]);
            
            // 6. Recurse: Move to the next element (i + 1)
            // We pass 'i + 1' ensures we don't reuse the same element 
            // and don't go backwards (avoiding duplicates like [1,2] and [2,1]).
            backtrack(result, tempList, nums, i + 1);
            
            // 7. Backtrack: Exclude nums[i]
            // We remove the last element added to "undo" the choice
            // so we can try the next option in the loop.
            tempList.remove(tempList.size() - 1);
        }
    }
}

Test Case 1: nums = [1, 2]
Call(0): temp=[], add []. Loop i=0 (Val 1).
Add 1. temp=[1].
Call(1): temp=[1], add [1]. Loop i=1 (Val 2).
Add 2. temp=[1, 2].
Call(2): temp=[1, 2], add [1, 2]. Loop i=2. Ends. Return.
Remove 2. temp=[1].
Loop i=1 ends. Return.
Remove 1. temp=[].
Loop i=1 (Val 2) in Call(0).
Add 2. temp=[2].
Call(2): temp=[2], add [2]. Loop i=2. Ends. Return.
Remove 2. temp=[].
Result: [[], [1], [1, 2], [2]].
Test Case 2: nums = [0]
Call(0): temp=[], add []. Loop i=0 (Val 0).
Add 0. temp=[0].
Call(1): temp=[0], add [0]. Loop i=1. Ends. Return.
Remove 0. temp=[].
Result: [[], [0]].
