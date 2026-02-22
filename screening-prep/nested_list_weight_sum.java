//https://leetcode.com/problems/nested-list-weight-sum/description/
class Solution{
	public int depthSum(List<NestedInteger> nestedList){
		return dfs(nestedList,1);
	}

	private int dfs(List<NestedIntege> list, int depth){
		int sum = 0 ;
		for(NestedInteger ni : list){
			if(ni.isInteger()){
			sum += ni.getInteger() * depth ;
			}
			else{
				sum += dfs(ni.getList(), depth +1);
			}
		}
		return sum;
	}

}

//TC : O(N)
//SC : O(depth)
