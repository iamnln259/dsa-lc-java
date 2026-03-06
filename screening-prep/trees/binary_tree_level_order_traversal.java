class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 1. Initialize the result list
        List<List<Integer>> result = new ArrayList<>();
        
        // 2. Guard Clause: Handle empty tree
        if (root == null) {
            return result;
        }

        // 3. Initialize Queue for BFS
        // LinkedList implements Queue interface in Java
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 4. Process the Queue
        while (!queue.isEmpty()) {
            
            // 5. Determine Level Size
            // CRITICAL STEP: We capture the size of the queue *before* the inner loop.
            // This snapshot tells us exactly how many nodes belong to the CURRENT level.
            int levelSize = queue.size();
            
            // List to store values of the current level
            List<Integer> currentLevel = new ArrayList<>();

            // 6. Iterate through all nodes of the current level
            for (int i = 0; i < levelSize; i++) {
                // Remove the node from the front of the queue
                TreeNode currentNode = queue.poll();
                
                // Add its value to the level list
                currentLevel.add(currentNode.val);

                // 7. Add Children to Queue
                // These children will be processed in the NEXT iteration of the while loop (next level)
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // 8. Add the completed level to the final result
            result.add(currentLevel);
        }

        return result;
    }
}
