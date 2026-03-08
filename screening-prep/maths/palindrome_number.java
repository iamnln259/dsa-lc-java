Questions
Are negative numbers considered palindrome? No. 
Accetable to convert int to a string? NO .

Approach 

Convert To String -Convert the number to a string, use two pointers (start and end), and compare characters moving inward.
Trade-off: Time complexity is O(N) where N is the number of digits. 
Space complexity is also O(N) because we are allocating a new string object in memory.

Reverse Half the Number (Optimal)
Concept: We only need to reverse the second half of the digits and compare it to the first half. For example, 
if the number is 1221, we reverse the 21 into 12, and check if it equals the remaining 12.
This natively prevents any overflow because the reversed half can never exceed the bounds of the original 32-bit integer.
Trade-off: Optimal O(log N) time and pure O(1) space. This is the implementation you should choose.

Edge Cases 

Zero (x = 0): Handled gracefully. 0 < 0 is false. 0 % 10 == 0 is true, but 0 != 0 is false. The initial if statement allows 0 to pass. 
The while loop condition 0 > 0 is false, so it skips the loop. It returns 0 == 0 (True).
Multiples of 10 (x = 10): Triggers the (x % 10 == 0 && x != 0) condition and returns false immediately. If we didnt have this check, 
the loop would build revertedNumber = 0, 
chop x to 1, break, and then check 1 == 0 or 1 == 0 / 10, which safely returns false, but the explicit check makes it faster.

Code 

class Solution {
    public boolean isPalindrome(int x) {
        // 1. Handle edge cases mathematically
        // Negative numbers are not palindromes.
        // If a number ends in 0, the first digit must also be 0. 
        // The only number that satisfies this is 0 itself.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        
        // 2. Reverse the digits until we reach the middle
        // We know we hit the middle when the original number becomes 
        // less than or equal to the reverted number.
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 3. Compare the halves
        // If the length is even, x == revertedNumber (e.g., 1221 -> x = 12, reverted = 12)
        // If the length is odd, we can drop the middle digit using revertedNumber / 10 
        // (e.g., 12321 -> x = 12, reverted = 123 -> 123 / 10 = 12)
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
Dry Run 

Input: x = 121
Initial Check: 121 is not < 0 and does not end in 0. Proceed.
Iteration 1:
revertedNumber = 0 * 10 + 1 = 1.
x = 121 / 10 = 12.
Loop condition check: 12 > 1 (True. Continue).
Iteration 2:
revertedNumber = 1 * 10 + 2 = 12.
x = 12 / 10 = 1.
Loop condition check: 1 > 12 (False. Break).
Final Check: Does x == revertedNumber (1 == 12)? No. Does x == revertedNumber / 10 (1 == 12 / 10 $\rightarrow$ 1 == 1)? Yes.
Result: true.
Test Case 2: Even Number of Digits
Input: x = 1221
Initial Check: Proceed.
Iteration 1:
revertedNumber = 0 * 10 + 1 = 1.
x = 1221 / 10 = 122.
Iteration 2:
revertedNumber = 1 * 10 + 2 = 12.
x = 122 / 10 = 12.
Loop condition check: 12 > 12 (False. Break).
Final Check: Does x == revertedNumber (12 == 12)? Yes.
Result: true.
