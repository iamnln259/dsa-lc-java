Questions

Approach 
Transpose the Matrix - Rows to Column
Reverse the values, horizontally in each row.
Edge Cases 
Empty Matrix: []. Guard clause handles it.
1x1 Matrix: [[1]].
Transpose loop j = i + 1 never runs (0 < 0 is false).
Reverse loop left < right never runs (0 < 0 is false).
Matrix remains [[1]]. Correct.
Rectangular Matrix: If passed (despite problem constraints), this code would throw an IndexOutOfBoundsException
 during transpose because matrix[j][i] would try to access a row index that doesn't exist if rows < cols.

Code 
class Solution{
    public void rotate(int[][] matrix){
        transposeMatrix(matrix);
        reverseRows(matrix);
    }
    private void transposeMatrix(int[][] matrix){
        for(int i = 0 ; i < matrix.length; i++){
            for(int j = 0 ; j <=i ; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }
    private void reverseRows(int[][] matrix){
        for(int i = 0 ; i < matrix.length; i++){
            int left = 0;
            int right = matrix.length - 1;
            while(left < right){
                int temp = matrix[i][left];
                matrix[i][left]= matrix[i][right];
                matrix[i][right] = temp;
                left ++;
                right --;
            }
        }
    }
}


Dry Run 

Input:
1 2 3
4 5 6
7 8 9

Step 1: Transpose (Swap (i,j) with (j,i)):
Swap (0,1) & (1,0): 2 and 4 swap.
Swap (0,2) & (2,0): 3 and 7 swap.
Swap (1,2) & (2,1): 6 and 8 swap.
Result:
1 4 7
2 5 8
3 6 9

Step 2: Reverse Each Row:
Row 0: 1 4 7 $\to$ 7 4 1
Row 1: 2 5 8 $\to$ 8 5 2
Row 2: 3 6 9 $\to$ 9 6 3
Final Output:
7 4 1
8 5 2
9 6 3
