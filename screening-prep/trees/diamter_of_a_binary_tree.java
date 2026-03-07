/*
Questions:
1.Diamter is degined by the number of nodes or edge? A-B-C => length is 2 or 3?
2.Can the longest path start and end at the same subtree? 
3.The Path does not need to go through the ROOT.
4.Diameter of a Single Node Tree = 0, right?
Approaches:
1.For every node, calculate left and right, take the max. O(N^2)
2.DFS, with Global Maximum. Bottom to top POST Order Traversal. Every Node calculate the (leftHeight+rightHeight) and compare with Global max.O(N),O(H)
*/
class Solution {
    // 1. Global variable to store the maximum diameter found so far.
    // We use a class-level variable (or a 1-element array) to persist state across recursive calls.
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        // 2. Reset the global state (important if the object is reused)
        maxDiameter = 0;
        
        // 3. Start the traversal. We don't care about the return value (height) here,
        // we only care about the side-effect update to maxDiameter.
        calculateHeight(root);
        
        return maxDiameter;
    }
    
    // Helper function: Returns the HEIGHT of the subtree rooted at 'node'.
    // Side effect: Updates 'maxDiameter' using the path passing through 'node'.
    private int calculateHeight(TreeNode node) {
        // 4. Base Case: An empty tree has height 0
        if (node == null) {
            return 0;
        }
        
        // 5. Recursively calculate the height of the left and right subtrees
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        
        // 6. CRITICAL STEP: The diameter passing strictly through THIS node 
        // is simply the sum of the longest path on the left and the longest on the right.
        // We update the global maximum if this path is the longest we've seen.
        int currentPathLength = leftHeight + rightHeight;
        maxDiameter = Math.max(maxDiameter, currentPathLength);
        
        // 7. Return step: What does the PARENT need to know?
        // The parent needs to know the height of *this* subtree to extend its own path.
        // We take the max of left/right and add 1 (for the current node itself).
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
