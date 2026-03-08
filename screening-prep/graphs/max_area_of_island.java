Questions
Input Grid is Immutable. Yes.
Digonal connection is not possible. Yes.
Maximum Grid Size? 
What should be returned if the Grid is empty or null? ZERO.
Approach
DFS , Recursive
Iterate through the grid. When you find a 1, trigger a recursive DFS. Inside the recursion, count the cell (add 1), set it to 0 (mark visited),
 and explore neighbors.
Trade-off: Time O(M dot N). Space O(M dot N) (recursion stack in worst case).


Code
class Solution {
    // Standard directions array for cleaner neighbor exploration
    // Up, Down, Left, Right
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        // 1. Guard Clause
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // 2. Iterate over every cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                
                // 3. If we find land (1), start a DFS to find the area of this island
                if (grid[i][j] == 1) {
                    // The dfs function will calculate the area AND "sink" the island
                    // so we don't visit it again.
                    int currentArea = dfs(grid, i, j);
                    
                    // Update global maximum
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }

        return maxArea;
    }

    // Helper: Recursive DFS to calculate area
    private int dfs(int[][] grid, int r, int c) {
        // 4. Base Cases (Out of bounds OR Water)
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        // 5. Mark as Visited ("Sink" the cell)
        // We set it to 0 so we never count it again.
        grid[r][c] = 0;

        // 6. Calculate Area: 1 (for this cell) + area of all connected neighbors
        int area = 1;
        
        // Explore all 4 directions
        for (int[] dir : DIRECTIONS) {
            area += dfs(grid, r + dir[0], c + dir[1]);
        }
        
        // Alternatively, without the loop:
        // area += dfs(grid, r + 1, c);
        // area += dfs(grid, r - 1, c);
        // area += dfs(grid, r, c + 1);
        // area += dfs(grid, r, c - 1);

        return area;
    }
}

Dry Run
Input: [[0, 1], [1, 1]]
(0,0): Value 0. Skip.
(0,1): Value 1. Start DFS.
Enter dfs(0,1). Mark (0,1) as 0. Area = 1.
Look Down (1,1): Value 1. Recurse.
Enter dfs(1,1). Mark (1,1) as 0. Area = 1.
Look Left (1,0): Value 1. Recurse.
Enter dfs(1,0). Mark (1,0) as 0. Area = 1.
Neighbors are 0 or Out of bounds. Return 1.
Add result (1). Current Area = 1 + 1 = 2.
Add result (2). Current Area = 1 + 2 = 3.
Update Max: maxArea = 3.
Main Loop: Continues. (1,0) is now 0. (1,1) is now 0. Skip both.
Return: 3.
Test Case 2: Disconnected Islands
Input: [[1, 0], [0, 1]]
(0,0): Value 1. Start DFS.
Sink (0,0). Neighbors are 0 or Out of bounds. Return 1.
maxArea becomes 1.
(0,1): Value 0. Skip.
(1,0): Value 0. Skip.
(1,1): Value 1. Start DFS.
Sink (1,1). Neighbors are 0 or bounds. Return 1.
maxArea remains 1 (since 1 is not > 1).
Return: 1.

Potential Edge Cases
Empty Grid: grid = [] or grid = [[]]. Code returns 0 instantly.
No Land: [[0, 0], [0, 0]]. if (grid[i][j] == 1) never triggers. Returns 0.
Full Grid: [[1, 1], [1, 1]]. First DFS consumes the entire grid. Main loop finishes on 0s. Returns total size ($M \cdot N$).
