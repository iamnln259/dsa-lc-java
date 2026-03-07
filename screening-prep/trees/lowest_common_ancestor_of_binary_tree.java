/*
Questions:
1.Is this a Binary Search Tree (BST) or just a regular Binary Tree?
2.Are all node values unique
3.Are p and q guaranteed to exist in the tree?
4.Can a node be its own ancestor? YES

Approaches:
1.Path Storage (Iterative/Backtracking)
2.Recursive DFS (Optimal & Expected)
Concept: Traverse the tree.
If we find p or q, return that node up.
If a node receives a non-null return from both left and right, it means p is on one side and q is on the other. Therefore, this current node is the split point (LCA).
If a node receives a return from only one side, pass that return up (we found one, but haven't found the merge point yet).
O(N), O(H)
*/

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. Base Case: 
        // If we reached the bottom (null), return null.
        // OR
        // If we found either p or q, return the node itself.
        // This stops the recursion for this branch because we don't need to look below p or q.
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // 2. Recurse Left
        // Ask the left subtree: "Did you find p or q?"
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        
        // 3. Recurse Right
        // Ask the right subtree: "Did you find p or q?"
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);
        
        // 4. The "Split" Check (The Critical Logic)
        // If both left and right returned non-null values, it means p is in one subtree 
        // and q is in the other. Therefore, CURRENT root is the LCA.
        if (leftResult != null && rightResult != null) {
            return root;
        }
        
        // 5. Propagation
        // If only one side returned non-null, that means both p and q are in that one subtree
        // (or we only found one of them so far). We bubble that result up.
        // If both are null, we return null (neither found here).
        return (leftResult != null) ? leftResult : rightResult;
    }
}
