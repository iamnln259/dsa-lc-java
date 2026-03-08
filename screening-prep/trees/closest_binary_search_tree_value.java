Questions:
Can the Root Be Null ? - No 
What if there is a Tie? target = 4.5 , node values are 4, 5 
What is the range of values ?
Is the target an Int or float/Double?

Approach:
Iterative Binary Search
Start at root. Update the "closest" variable. Move left or right depending on the target.
Trade-off: Time is O(H). Space is O(1). This is the most robust solution for production systems.

Code:

class Solution {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        TreeNode current = root;

        while (current != null) {
            int val = current.val;
            
            // Calculate differences
            double currentDiff = Math.abs(val - target);
            double closestDiff = Math.abs(closest - target);

            // LOGIC FIX:
            // Update if strictly closer, OR if equal distance but current value is smaller
            if (currentDiff < closestDiff || (currentDiff == closestDiff && val < closest)) {
                closest = val;
            }

            // Binary Search Movement
            if (target < val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        return closest;
    }
}

Why it failed on [4,2,5,1,3]
Let's assume the target was 3.5 (a common test case for this tree).
Visit 4: Diff is 0.5. Closest = 4. Move Left.
Visit 2: Diff is 1.5. No update. Move Right.
Visit 3: Diff is 0.5.
Old Code: Is 0.5 < 0.5? False. Kept 4. (Wrong, expected 3).
New Code: Is 0.5 < 0.5? False. OR is 0.5 == 0.5 AND 3 < 4? True. Update to 3. (Correct).
