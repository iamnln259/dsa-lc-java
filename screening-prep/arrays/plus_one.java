class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // 1. Iterate backwards from the last digit (Least Significant)
        for (int i = n - 1; i >= 0; i--) {
            
            // 2. Simple Case: No Carry Needed
            // If the digit is less than 9 (e.g., 0-8), simply increment it.
            // Since there is no carry, we are done immediately.
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // Early return for efficiency
            }

            // 3. Carry Case
            // If the digit was 9, it becomes 10.
            // In our array, we set it to 0 and loop again to handle the carry
            // for the next digit to the left.
            digits[i] = 0;
        }

        // 4. Edge Case: Overflow (All 9s)
        // If the loop completes without returning, it means we had a carry 
        // that propagated all the way through (e.g., 99 -> 00 -> need 100).
        // We need a new array with size N + 1.
        int[] newNumber = new int[n + 1];
        
        // Java initializes int arrays to 0 by default.
        // So [0, 0, 0] is already set. We just need to set the Most Significant Digit to 1.
        newNumber[0] = 1;
        
        return newNumber;
    }
}
