/*
Questions
"Is the input matrix jagged?" (Usually rectangular  but good to check if rows can have different lengths).
"What are the constraints on M and N?" (Up to 200. This is small, so $O(M \cdot N)$ time is fine).
"Is the matrix mutable?" (Yes, the return type is void, implying in-place modification).
"Can I use $O(M+N)$ space?" (Start by asking this. Usually, the interviewer will say "Can you do better? Can you do it in constant space?").

Approach 
Concept: Instead of external arrays, use matrix[i][0] and matrix[0][j] to store the flags.
Conflict: matrix[0][0] belongs to both Row 0 and Col 0. If it's 0, does it mean Row 0 is zero, or Col 0 is zero?
Solution: Use a separate variable boolean col0 to handle the first column specifically. Use matrix[0][0] for the first row.
Trade-off: Time $O(M * N)$. Space $O(1)$. This is the standard "Hire" solution.
Edge Cases
Single Element [[0]]:
col0 becomes true. Loop sets matrix[0][0] = 0. Correct.
Single Element [[1]]:
col0 stays false. Loop does nothing. Correct.
Zero at 0,0:
Logic handles this by setting col0 = true (for column) and leaving matrix[0][0] = 0 (for row).
Matrix full of Zeros: Flags get set everywhere. Pass 2 re-writes 0s. Efficient and correct.
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        // 1. Guard Clause
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int m = matrix.length;    // Rows
        int n = matrix[0].length; // Columns
        // We need a separate flag for the first column because matrix[0][0]
        // overlaps between Row 0 and Column 0.
        boolean col0 = false;
        // 2. Pass 1: Mark rows and columns
        for (int i = 0; i < m; i++) {
            
            // Check if the first column needs to be zeroed
            if (matrix[i][0] == 0) {
                col0 = true;
            }
            // Check the rest of the row (starting from 1)
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // Mark the corresponding row flag
                    matrix[i][0] = 0;
                    // Mark the corresponding column flag
                    matrix[0][j] = 0;
                }
            }
        }
        // 3. Pass 2: Update the inner matrix (1...M, 1...N) using flags
        // We iterate backwards to prevent overwriting the flags in the first row/col
        // before we are done using them.
        for (int i = m - 1; i >= 0; i--) {
            // Update the inner columns (1 to N-1)
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // 4. Update the first column separately using our 'col0' variable
            if (col0) {
                matrix[i][0] = 0;
            }
        }
    }
}

/*
Dry Run
Test Case 1: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Init: col0 = false.
Pass 1:
Row 0: No zeros.
Row 1: matrix[1][0] is 1. matrix[1][1] is 0.
Mark matrix[1][0] = 0.
Mark matrix[0][1] = 0.
Row 2: No zeros.
State: [[1,0,1], [0,0,1], [1,1,1]]. col0 = false.
Pass 2:
(2,2): matrix[2][0] (1) and matrix[0][2] (1) are non-zero. No change.
(2,1): matrix[0][1] is 0. Set matrix[2][1] = 0.
(1,2): matrix[1][0] is 0. Set matrix[1][2] = 0.
(1,1): matrix[1][0] is 0. Set matrix[1][1] = 0.
i=0: Row 0 updates based on headers. matrix[0][1] is 0 $\to$ matrix[0][1] = 0.
Final: [[1,0,1],[0,0,0],[1,0,1]]. Correct.
Test Case 2: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Init: col0 = false.
Pass 1:
Row 0: matrix[0][0] is 0. col0 = true. matrix[0][3] is 0 $\to$ matrix[0][0]=0 (already 0), matrix[0][3]=0.
Row 1: No zeros.
Row 2: No zeros.
State: col0 = true. Headers set.
Pass 2:
All cells in Row 0 get zeroed because matrix[0][0] is 0.
All cells in Col 0 get zeroed because col0 is true.
All cells in Col 3 get zeroed because matrix[0][3] is 0.
Final: Correctly zeros out Row 0, Col 0, Col 3.

*/
