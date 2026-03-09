Questions
Is this strictly a Binary Search Tree
Are the node values unique?
What is the range of values for the sum?
Can the root be null?

Approach 
Use the BST property to "prune" (skip) irrelevant branches.
If node.val < low: The current node is too small. All nodes in its left subtree are even smaller. Ignore left; go Right.
If node.val > high: The current node is too big. All nodes in its right subtree are even bigger. Ignore right; go Left.
If low <= node.val <= high: This node counts! Add it, and check both children.
Trade-off: Time is O(N) in the worst case (if the range covers the whole tree), but on average it is much faster 
(closer to O(H), where H is height, for narrow ranges). Space is O(H) for recursion stack.
Edge Cases
Root is null: Handled by base case (returns 0).
Range covers the entire tree: The code effectively visits every node (equivalent to standard DFS).
Range excludes the entire tree: The pruning logic skips almost everything immediately (returns 0 quickly).
Single Node Tree: Works correctly (checks constraints and returns value or 0).

Code
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        // 1. Base Case: If the node is null, it contributes 0 to the sum.
        if (root == null) {
            return 0;
        }

        // 2. Optimization 1: Current node is TOO SMALL.
        // Logic: Since root.val < low, all nodes in the Left Subtree
        // are strictly smaller than root.val, so they are DEFINITELY < low.
        // We prune (skip) the Left Subtree entirely and only check the Right.
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }

        // 3. Optimization 2: Current node is TOO BIG.
        // Logic: Since root.val > high, all nodes in the Right Subtree
        // are strictly larger than root.val, so they are DEFINITELY > high.
        // We prune the Right Subtree entirely and only check the Left.
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }

        // 4. Valid Case: low <= root.val <= high
        // This node is inside the range. We add its value.
        // However, its Left child *might* be smaller (but still > low),
        // and its Right child *might* be larger (but still < high).
        // So we must explore BOTH children.
        return root.val + 
               rangeSumBST(root.left, low, high) + 
               rangeSumBST(root.right, low, high);
    }
}


Dry Run 

Test Case 1: Standard Tree
Tree: [10, 5, 15, 3, 7, null, 18], Range: [7, 15]
Node 10: $7 \le 10 \le 15$. Valid.
Add 10.
Recurse Left (Node 5).
Recurse Right (Node 15).
Node 5 (Left Child): $5 < 7$ (Too Small).
Prune Left (Node 3 is skipped!).
Recurse Right (Node 7).
Node 7 (Right Child of 5): $7 \le 7 \le 15$. Valid.
Add 7.
Left/Right children are null $\to$ return 0.
Node 5 returns 7.
Node 15 (Right Child of 10): $7 \le 15 \le 15$. Valid.
Add 15.
Recurse Left (null) $\to$ 0.
Recurse Right (Node 18).
Node 18 (Right Child of 15): $18 > 15$ (Too Big).
Prune Right.
Recurse Left (null) $\to$ 0.
Node 18 returns 0.
Node 15 returns 15 + 0 = 15.
Back to Root: 10 (root) + 7 (from left) + 15 (from right) = 32.
Total Sum: 32.
Test Case 2: Out of Bounds
Tree: [10, 5, 15], Range: [20, 30]
Node 10: $10 < 20$ (Too Small).
Skip Left (Node 5 is ignored).
Recurse Right (Node 15).
Node 15: $15 < 20$ (Too Small).
Skip Left.
Recurse Right (null).
Result: 0.
