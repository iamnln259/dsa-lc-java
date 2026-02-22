import java.util.*;

// Definition for a binary tree node.
// public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode(int x) { val = x; }
// }

class Solution {
    // Helper class to bind a node to its column index
    class ColumnNode {
        TreeNode node;
        int col;
        ColumnNode(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 1. Edge Case: Empty tree
        if (root == null) {
            return result;
        }
        
        // 2. Data structures for BFS and grouping
        Map<Integer, List<Integer>> columnTable = new HashMap<>();
        Queue<ColumnNode> queue = new LinkedList<>();
        
        // 3. Initialize tracking variables
        int minColumn = 0;
        int maxColumn = 0;
        
        // 4. Start BFS with the root at column 0
        queue.offer(new ColumnNode(root, 0));
        
        while (!queue.isEmpty()) {
            ColumnNode current = queue.poll();
            TreeNode node = current.node;
            int col = current.col;
            
            // 5. Add the node's value to its corresponding column in the map
            columnTable.putIfAbsent(col, new ArrayList<>());
            columnTable.get(col).add(node.val);
            
            // 6. Update min and max column bounds
            minColumn = Math.min(minColumn, col);
            maxColumn = Math.max(maxColumn, col);
            
            // 7. Enqueue children with their respective column offsets
            if (node.left != null) {
                queue.offer(new ColumnNode(node.left, col - 1));
            }
            if (node.right != null) {
                queue.offer(new ColumnNode(node.right, col + 1));
            }
        }
        
        // 8. Build the final result using the tracked min and max bounds
        for (int i = minColumn; i <= maxColumn; i++) {
            result.add(columnTable.get(i));
        }
        
        return result;
    }
}
