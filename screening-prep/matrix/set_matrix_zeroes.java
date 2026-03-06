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
//O(M*N) , O(1)
