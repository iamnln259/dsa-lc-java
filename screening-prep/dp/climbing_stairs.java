class Solution {
    public int climbStairs(int n) {
        // 1. Base Cases
        // If n is 1, there is only 1 way (1 step).
        if (n <= 1) {
            return 1;
        }
        
        // 2. Initialize State
        // 'prev1' represents the number of ways to reach the step immediately before the current one (i-1).
        // 'prev2' represents the number of ways to reach the step two steps back (i-2).
        // Base case setup for n=2: 
        // To reach step 2, we could come from step 1 (1 way) or step 0 (1 way).
        int prev1 = 1; // Represents ways to reach (i-1)
        int prev2 = 1; // Represents ways to reach (i-2) (Conceptually step 0)
        
        // 3. Iterative Calculation
        // We start calculating from step 2 up to n.
        for (int i = 2; i <= n; i++) {
            
            // The number of ways to reach current step 'i' is the sum of:
            // - Ways to reach (i-1) [then take 1 step]
            // - Ways to reach (i-2) [then take 2 steps]
            int current = prev1 + prev2;
            
            // 4. Shift the Window
            // Prepare for the next iteration (i+1).
            // The current step becomes the new 'prev1'.
            // The old 'prev1' becomes the new 'prev2'.
            prev2 = prev1;
            prev1 = current;
        }
        
        // After the loop finishes, 'prev1' holds the value for the n-th step.
        return prev1;
    }
}
//O(N), O(1)
