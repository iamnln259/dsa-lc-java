/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * // @return true if this NestedInteger holds a single integer, rather than a nested list.
 * public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // Set this NestedInteger to hold a single integer.
 * public void setInteger(int value);
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * public void add(NestedInteger ni);
 *
 * // @return the nested list that this NestedInteger holds, if it holds a nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
/*
Questions:
1.How deep can the nest go ?
2.Is the input immutable
3.Can the list contain empty nested lists


Approaches:
Traverse the list. Maintain a currentDepth variable. If you see an Integer, add value * depth to the total. If you see a List, recurse with depth + 1.
O(N), O(D)
*/
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        // 1. Kick off recursion starting at depth 1
        return dfs(nestedList, 1);
    }

    // Helper function to traverse the nested structure
    private int dfs(List<NestedInteger> list, int depth) {
        int totalSum = 0;

        // 2. Iterate through every element in the current list level
        for (NestedInteger nested : list) {
            
            // 3. Case A: It's a single integer
            if (nested.isInteger()) {
                // Multiply value by current depth and add to total
                totalSum += nested.getInteger() * depth;
            } 
            // 4. Case B: It's a nested list
            else {
                // Recurse deeper! Increment depth by 1.
                // Add the result of the sub-problem to our total.
                totalSum += dfs(nested.getList(), depth + 1);
            }
        }
        
        return totalSum;
    }
}
