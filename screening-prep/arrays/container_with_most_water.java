Questions
Can the array be empty or have fewer than 2 elements?
Are the heights strictly positive?
Does the array fit in memory?

Approach 
Start with the widest possible container (first and last lines).
Logic: Calculate the area. To potentially find a bigger area, we must find a taller line.
If left line is shorter than right, moving right inward wont help (width decreases, and height is limited by left). 
So, we must move left.
If right line is shorter, move right.
Trade-off: Time is $O(N)$. Space is $O(1)$. This is the standard interview solution.

Edge Cases 
Two Elements: Loop runs once. Returns correct area.
All Same Heights: Logic works correctly (moves one side until they meet).
Zero Heights: min(0, X) is 0. Area is 0. Logic holds.

Code 
class Solution {
    public int maxArea(int[] height) {
        // 1. Guard Clause: Need at least 2 lines to form a container
        if (height == null || height.length < 2) {
            return 0;
        }

        // 2. Initialize Pointers
        // left starts at the beginning, right starts at the end
        int left = 0;
        int right = height.length - 1;
        
        // Variable to track the maximum area found so far
        int maxArea = 0;

        // 3. Loop until the pointers meet
        while (left < right) {
            
            // 4. Calculate Current Area
            // The height of the container is limited by the SHORTER line
            int currentHeight = Math.min(height[left], height[right]);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;

            // Update global max
            maxArea = Math.max(maxArea, currentArea);

            // 5. Decision: Which pointer to move?
            // Greedily move the pointer pointing to the shorter line.
            // Why? Because if we keep the shorter line and move the other one,
            // the width decreases, and the height is still limited by this short line.
            // The only chance to get a bigger area is to find a taller line to replace the short one.
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

Dry Run 
Test Case 1: Standard ([1, 8, 6, 2, 5, 4, 8, 3, 7])
Init: left=0 (1), right=8 (7). Width=8.
Height = min(1, 7) = 1.
Area = $1 \times 8 = 8$. Max = 8.
Move left (1 < 7).
Iter 2: left=1 (8), right=8 (7). Width=7.
Height = min(8, 7) = 7.
Area = $7 \times 7 = 49$. Max = 49.
Move right (7 < 8).
Iter 3: left=1 (8), right=7 (3). Width=6.
Height = min(8, 3) = 3.
Area = $3 \times 6 = 18$. Max = 49.
Move right (3 < 8).
...Fast Forward...
Iter X: left=1 (8), right=6 (8). Width=5.
Height = min(8, 8) = 8.
Area = $8 \times 5 = 40$. Max = 49.
Final Result: 49.
Test Case 2: Short ([1, 1])
Init: left=0 (1), right=1 (1). Width=1.
Height = 1.
Area = 1. Max = 1.
Move right (Equal logic usually moves right, doesn't matter).
Loop Ends: left < right is false.
Result: 1.
