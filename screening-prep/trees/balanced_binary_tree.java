Questions:

Just to confirm the definition: a tree is balanced if the heights of the two subtrees of any node never differ by more than 1, correct?
Is an empty tree (null root) considered balanced? YES 
Can I assume the tree size fits within standard memory limits, or could it be massively deep? 

Approach 
Top To Down : O(N^2) call the same function on the same node repeatedly .
Bottom Up, Post Order Approach :
Calculate the height from the bottom up. We ask the left child for its height, then the right child. If at any point a subtree violates the balance rule, we immediately return a sentinel value (like -1) that bubbles all the way up to the top, short-circuiting the rest of the tree.
Trade-off: Time Complexity is $O(N)$. Every node is visited exactly once. This is the optimal, industry-standard approach.

Edge Cases 

The Empty Tree ([]): Handled gracefully. checkHeight(null) immediately returns 0. The main function evaluates 0 != -1 which is true.
A "Linked List" Tree (Heavily Skewed): If a tree is just a straight line to the left (1 -> 2 -> 3 -> 4),
 the first deep comparison at node 3 will see left=1, right=0. Valid. 
Node 2 will see left=2, right=0. Invalid! It immediately returns -1 and aborts, preventing unnecessary operations.
code 
class Solution {
    public boolean isBalanced(TreeNode root) {
        // If the helper function returns -1, the tree is unbalanced.
        // Otherwise, it returns the actual height of the tree, meaning it is balanced.
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        // 1. Base Case: An empty tree has a height of 0
        if (node == null) {
            return 0;
        }

        // 2. Check the left subtree. If it's unbalanced, bubble up the -1 immediately.
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        // 3. Check the right subtree. If it's unbalanced, bubble up the -1 immediately.
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        // 4. Check the current node's balance. 
        // If the height difference is strictly greater than 1, it's unbalanced.
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // 5. If it is balanced, return the height of this current subtree
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

Dry Run 

      3
     / \
    9  20
      /  \
     15   7

checkHeight(3): Calls left on 9.
checkHeight(9): Calls left/right (both null, return 0). max(0,0) + 1 = 1. Returns 1 to root.
checkHeight(20): Calls left on 15, right on 7.
checkHeight(15): Leaves return 0. Height is 1.
checkHeight(7): Leaves return 0. Height is 1.
Back at 20: abs(1 - 1) == 0. Height is max(1,1) + 1 = 2. Returns 2 to root.
Back at root (3): leftHeight is 1, rightHeight is 2. abs(1 - 2) == 1. It is valid. Returns max(1,2) + 1 = 3.
Final Output: 3 != -1 is true.

         1
        / \
       2   2
      / \
     3   3
    / \
   4   4

checkHeight(4) nodes both return 1 to node 3.
Left node 3: left=1, right=1. Returns 2 to node 2.
Right node 3 (leaf): Returns 1 to node 2.
Left node 2: leftHeight is 2, rightHeight is 1. Diff is 1. Returns 3 to node 1.
Right node 2 (child of root): Leaves are null. Returns 1 to node 1.
Back at root (1): leftHeight is 3, rightHeight is 1. abs(3 - 1) == 2. This is $> 1$.
Root returns -1.
Final Output: -1 != -1 is false.
