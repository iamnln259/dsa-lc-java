class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null ) return new ArrayList<>();

        Map<Integer, List<Integer>> columnTable = new HashMap<>();
        Queue<Pair<TreeNode,Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root,0));

        int min = 0 , max = 0;
        while(!queue.isEmpty()){
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int col = pair.getValue();
            columnTable.computeIfAbsent(col , x -> new ArrayList<>()).add(node.val);
            min =  Math.min(min , col);
            max =  Math.max(max , col);
            if (node.left != null ) queue.offer(new Pair<>(node.left , col-1));
            if (node.right != null) queue.offer(new Pair<>(node.right , col+1));
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int i = min; i <= max ; i++){
            result.add(columnTable.get(i));
        }

        return result;
    }
}
//O(N) , O(N)
