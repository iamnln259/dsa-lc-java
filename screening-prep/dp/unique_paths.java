Questions
Can there be obstacles in the grid?
Will the result fit in a standard Integer?
What are the constraints on m and n?
Can I optimize space
Approach 
Concept: The number of ways to reach cell (i, j) is the sum of ways to reach the cell above it (i-1, j) and the cell to the left (i, j-1).
Trade-off: Time $O(m \times n)$. Space $O(m \times n)$. Clear, readable, and standard.

Edge Cases 
1x1 Grid: m=1, n=1. Loop sets dp[0][0]=1. Returns 1. Correct.
Single Row/Col: m=1, n=10. Loop sets all dp[0][j]=1. Returns 1. Correct.
Large Inputs: m=100, n=100. Standard int holds up to $2 \times 10^9$. The path count for 100x100 is massive, 
but the problem constraints usually cap inputs such that the result fits in int (or long).

Code 

class Solution {
    public int uniquePaths(int m, int n) {
        // 1. Create a DP table
        // dp[i][j] represents the number of unique paths to reach cell (i, j)
        int[][] dp = new int[m][n];

        // 2. Iterate through the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                // 3. Base Case: First Row and First Column
                // There is only 1 way to reach any cell in the first row: Move Right repeatedly.
                // There is only 1 way to reach any cell in the first col: Move Down repeatedly.
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } 
                // 4. Recursive Step (DP Transition)
                // For any other cell, the ways to reach it = ways from top + ways from left
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        // 5. Return the value at the bottom-right corner
        return dp[m - 1][n - 1];
    }
}

Dry run 

Test Case 1: m = 3, n = 2 (3 rows, 2 columns)
Init Table: 3x2 grid filled with 0s.
i=0 (First Row):
dp[0][0] = 1
dp[0][1] = 1
i=1 (Second Row):
j=0: dp[1][0] = 1 (First col base case).
j=1: dp[1][1] = dp[0][1] (1) + dp[1][0] (1) = 2.
i=2 (Third Row):
j=0: dp[2][0] = 1.
j=1: dp[2][1] = dp[1][1] (2) + dp[2][0] (1) = 3.
Return: dp[2][1] which is 3.
  
Test Case 2: m = 3, n = 7 (Standard Example)
Row 0: All 1s. [1, 1, 1, 1, 1, 1, 1]
Row 1: [1, 2, 3, 4, 5, 6, 7] (Each cell sums left + top)
Row 2: [1, 3, 6, 10, 15, 21, 28]
Return: 28.
