Questions
The grid is immutable right? Yes. 
How larger the grid can get? because the recursion will crash the jvm. 
Are diagonal constraints accepted? or strictly vertical or horizontal
Approach
DFS-Iterate through the grid. When you find a '1', increment your island count, and immediately trigger a recursive DFS to "sink"
 the entire island (flip all connected '1's to '0's).
Trade-off: Fast to write and conceptually elegant. Time complexity is O(M * N). However, 
the space complexity is bounded by the recursion call stack, which is O(M * N) in the worst-case scenario (a grid completely filled 
with '1's in a snake-like pattern).

Potential Edge Cases
Code
class Solution {
    public int numIslands(char[][] grid) {
        // 1. Guard clause: Handle empty grids instantly
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int islandCount = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        // 2. Iterate through every single cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                
                // 3. If we find undiscovered land, we found a new island!
                if (grid[r][c] == '1') {
                    islandCount++;
                    
                    // 4. Sink the entire connected landmass so we don't double-count it later
                    sinkIsland(grid, r, c);
                }
            }
        }
        
        return islandCount;
    }
    
    // Helper function to recursively sink the island
    private void sinkIsland(char[][] grid, int r, int c) {
        // 5. Base cases for stopping the recursion:
        // - We went out of bounds (off the edge of the map)
        // - We hit water ('0')
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }
        
        // 6. Mutate the state: turn the current land into water
        grid[r][c] = '0';
        
        // 7. Recursively explore and sink all 4 adjacent directions (up, down, left, right)
        sinkIsland(grid, r - 1, c); // Up
        sinkIsland(grid, r + 1, c); // Down
        sinkIsland(grid, r, c - 1); // Left
        sinkIsland(grid, r, c + 1); // Right
    }
}


Dry Run
1 1 0
0 0 1

* **Init:** `islandCount = 0`
* **r=0, c=0:** `grid[0][0]` is '1'. 
    * `islandCount` becomes 1.
    * `sinkIsland(0, 0)` triggers.
    * `grid[0][0]` becomes '0'.
    * Explores Right (`0, 1`). It's a '1'. It becomes '0'.
    * Explores Down (`1, 0`). It's water ('0'). Returns.
    * Explores Left/Up. Out of bounds. Returns.
    * Grid is now: 
      `0 0 0`
      `0 0 1`
* **r=0, c=1:** Now '0'. Skip.
* **r=0, c=2:** Is '0'. Skip.
* **r=1, c=0 to c=1:** Are '0'. Skip.
* **r=1, c=2:** `grid[1][2]` is '1'.
    * `islandCount` becomes 2.
    * `sinkIsland(1, 2)` triggers, sinks it to '0'.
* **Result:** `2` islands.

#### Test Case 2: One Giant Island
**Input:** ```text
1 1
1 1

r=0, c=0: grid[0][0] is '1'.
islandCount becomes 1.
sinkIsland(0, 0) triggers.
Flips [0][0] to '0'.
Recursively visits right ([0][1]), flips to '0'.
From [0][1], visits down ([1][1]), flips to '0'.
From [1][1], visits left ([1][0]), flips to '0'.
The entire matrix is instantly converted to '0's in a single DFS chain.
Remaining loops: Every subsequent cell check sees a '0' and skips.
Result: 1 island.
