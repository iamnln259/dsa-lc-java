Questions
What is the range of the input number?
What if the number is already the largest possible? we should return the same number
Do we handle negative numbers?
Why are we swapping ? to get a max Face Value to the number.

Approach
Concept:
Record the last index of each digit (0-9).
Iterate through the digits from left to right.
For each digit, check if there is a larger digit that appears later in the array.
If yes, swap the current digit with that larger digit and return immediately.
Trade-off: Time $O(N)$. Space $O(1) (array of size 10).
Verdict: This is the most efficient and elegant solution.

Edge Cases:
Already Max (9973):
i=0 ('9'): Loop d from 9 down to 9+1 (doesn't run).
i=1 ('9'): Loop d doesn't run.
i=2 ('7'): d=9 (last[9]=1 not > 2), d=8 (none).
Returns original 9973. Correct.
Single Digit (5): n=1. Loops finish without swap. Returns 5. Correct.
Zero (0): Works identical to single digit.

Code 
class Solution {
    public int maximumSwap(int num) {
        // 1. Convert to char array for easy indexing
        char[] digits = Integer.toString(num).toCharArray();
        int n = digits.length;

        // 2. Pre-compute the Last Occurrence of every digit (0-9)
        // last[d] stores the index where digit 'd' appears last in the number.
        int[] last = new int[10];
        for (int i = 0; i < n; i++) {
            // '0' has ASCII value 48. digits[i] - '0' gives integer value.
            last[digits[i] - '0'] = i;
        }

        // 3. Iterate to find the first "Bad" Digit
        // We look for the first digit (from left) that is smaller than 
        // some digit that appears later in the array.
        for (int i = 0; i < n; i++) {
            int currentDigit = digits[i] - '0';

            // Check for a larger digit (9 down to currentDigit + 1)
            // We start from 9 because we want the LARGEST possible swap.
            for (int d = 9; d > currentDigit; d--) {
                
                // If a larger digit 'd' exists AND it appears AFTER the current position 'i'
                if (last[d] > i) {
                    
                    // 4. Perform the Swap
                    // Swap digits[i] with digits[last[d]]
                    char temp = digits[i];
                    digits[i] = digits[last[d]];
                    digits[last[d]] = temp;

                    // 5. Return immediately
                    // Since we improved the most significant digit possible with the 
                    // largest possible value, this is guaranteed to be the max.
                    return Integer.parseInt(new String(digits));
                }
            }
        }

        // If no swap occurred, the number was already max.
        return num;
    }
}

Dry Run 
Test Case 1: num = 2736
Last Map: {2:0, 7:1, 3:2, 6:3} (Indices).
i = 0 (Val '2'):
Check d=9... No.
Check d=8... No.
Check d=7. last[7] is 1.
Is 1 > 0? Yes.
Swap: Swap index 0 ('2') and index 1 ('7'). Array: 7236.
Return: 7236.
Test Case 2: num = 1993 (Duplicate Max Digits)
Last Map: {1:0, 9:2, 3:3}. (Note: last[9] is 2, not 1).
i = 0 (Val '1'):
Check d=9. last[9] is 2.
Is 2 > 0? Yes.
Swap: Swap index 0 ('1') and index 2 ('9'). Array: 9913.
Return: 9913.
Note: If we swapped with index 1 (the first 9), result would be 9193, which is smaller. The last[] logic handles this edge case perfectly.
