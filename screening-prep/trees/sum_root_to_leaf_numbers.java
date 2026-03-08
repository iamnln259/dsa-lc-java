/*
Questions
What is the range of node values? 0 to 9 : All are single digits.
What is the maximum depth of the tree?
Can the Root be NULL? Yes. 
Approach
1. DFS with String Concatenation : Not suggested. 
2. If leaf: Return newSum.
If internal node: Return dfs(left) + dfs(right).
O(N), O(H)

Edge Cases 
Root is null: Guard clause returns 0. Correct.
Single Node: dfs(Node 5, 0) -> sum=5. Leaf check true. Returns 5. Correct.
One Child Missing: If Node 1 has left 2 and right null:
Left returns 12.
Right returns dfs(null) which is 0.
Total 12 + 0 = 12. Correct.
*/

Code
class Solution {
    public int sumNumbers(TreeNode root) {
        // 1. Kick off the recursion with an initial sum of 0
        return dfs(root, 0);
    }

    // Helper function to traverse the tree
    private int dfs(TreeNode node, int currentSum) {
        // 2. Base Case: If node is null, it contributes 0 to the sum.
        // This handles cases where a node has only one child.
        if (node == null) {
            return 0;
        }

        // 3. Update the number formed so far
        // Shift previous digits to the left (multiply by 10) and add current digit
        // Example: Prev=12, Node=3 -> 12 * 10 + 3 = 123
        currentSum = currentSum * 10 + node.val;

        // 4. Leaf Check (The Critical Logic)
        // If this is a leaf, we have finished forming a number. Return it.
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // 5. Recursive Step
        // If it's not a leaf, continue down BOTH branches.
        // The total sum is the sum of numbers found in the left subtree 
        // PLUS the sum of numbers found in the right subtree.
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}

Dry Run
/*
Test Case 1: Simple Tree [1, 2, 3]
Start: dfs(Node 1, 0).
currentSum becomes 0 * 10 + 1 = 1.
Not a leaf. Recurse Left and Right.
Left Branch: dfs(Node 2, 1).
currentSum becomes 1 * 10 + 2 = 12.
Is Leaf? Yes. Return 12.
Right Branch: dfs(Node 3, 1).
currentSum becomes 1 * 10 + 3 = 13.
Is Leaf? Yes. Return 13.
Back to Root: Returns Left (12) + Right (13) = 25.
Test Case 2: Deep Tree [4, 9, 0, 5, 1]
Root (4): Sum = 4.
Left (9): Sum = 4*10 + 9 = 49.
Left of 9 (5): Sum = 49*10 + 5 = 495. Leaf -> Return 495.
Right of 9 (1): Sum = 49*10 + 1 = 491. Leaf -> Return 491.
Node 9 returns: 495 + 491 = 986.
Right (0): Sum = 4*10 + 0 = 40.
Is Leaf? Yes. Return 40.
Total: 986 + 40 = 1026.
*/
