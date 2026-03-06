// Left oder Approach , Queue. 
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> visible_values = new ArrayList<>();
        if( root == null ) return visible_values;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i< size; i++){
                TreeNode current = queue.remove();
                if(i == size -1){
                    visible_values.add(current.val);
                }
                if(current.left != null){
                    queue.add(current.left);
                }
                if(current.right != null){
                    queue.add(current.right);
                }
            }
        }
        return visible_values;
    }
}

// DFS apprach, get rid of Queue

class Solution{
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> result = new ArrayList<>();
        dfs(root, 0 , result);
        return result;
    }
    private void dfs(TreeNode node, int depth, List<Integer> result){
        if(node == null ){
            return;
        }
        if(depth == result.size()){
            result.add(node.val);
        }

        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }
}
