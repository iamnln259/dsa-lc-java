/*
Questions 
How do we handle whitespace? (Ignore leading whitespace, but stop parsing if whitespace appears after digits start).
What happens on overflow? (Clamp the result. Return Integer.MAX_VALUE or Integer.MIN_VALUE. Do not throw an exception).
What if non-digit characters appear (Stop parsing immediately and return whatever we have built so far).
Can we use long to store the intermediate result (It simplifies the code, but strictly speaking, 
a 32-bit environment might not support 64-bit integers. I will show the solution using purely int to demonstrate strict memory management).
Approach 
Concept: Iterate through the string.
Skip leading spaces.
Check for + / -.
Read digits.
Before adding a digit, check if result * 10 + digit would overflow.
Trade-off: Time $O(N)$. Space $O(1)$. This is the most impressive solution as it adheres to strict type constraints.
*/
//Code 

class Solution {
    public int myAtoi(String s) {
        // 1. Guard Clause
        if (s == null || s.length() == 0) {
            return 0;
        }
        int index = 0;
        int n = s.length();
        int sign = 1;
        int result = 0;
        // 2. Skip Leading Whitespace
        // We avoid s.trim() because it creates a new String object (O(N) space).
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }
        // Check if string was only whitespace
        if (index == n) {
            return 0;
        }
        // 3. Check Sign
        // Only check if we haven't reached the end
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = (s.charAt(index) == '-') ? -1 : 1;
            index++;
        }
        // 4. Convert Digits
        while (index < n) {
            char currentChar = s.charAt(index);

            // If we encounter a non-digit, we stop immediately.
            if (!Character.isDigit(currentChar)) {
                break;
            }
            int digit = currentChar - '0';
            // 5. Check for Overflow BEFORE appending
            // Integer.MAX_VALUE = 2147483647 (ends in 7)
            // Integer.MIN_VALUE = -2147483648 (ends in 8, but we handle logic via positive numbers)
            // Condition 1: result > MAX/10 -> Multiplying by 10 will definitely overflow.
            // Condition 2: result == MAX/10 -> Multiplying by 10 is safe, but adding digit might overflow.
            // Since MAX ends in 7, adding 8 or 9 causes overflow.
            if (result > Integer.MAX_VALUE / 10 || 
               (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        // Append digit
            result = result * 10 + digit;
            index++;
        }
        return result * sign;
    }
}
/*

Edge Cases 
Empty String / Null: Handled by guard clause.
Only Whitespace: Loop skips spaces, hits end of string. Returns 0.
Overflow: "2147483648".
Parses until ...64. Next digit 8.
Checks digit > 7. True.
Returns Integer.MAX_VALUE.
Signs: "+-12" $\to$ Reads +, expects digits. Finds -. Breaks. Returns 0. Correct.

Dry Run 
Test Case 1: s = " -42"
Skip Space: index moves from 0 to 3.
Sign: s.charAt(3) is -. sign = -1. index becomes 4.
Digit 4:
result = 0. Check overflow: Safe.
result = 0 * 10 + 4 = 4.
Digit 2:
result = 4. Check overflow: Safe.
result = 4 * 10 + 2 = 42.
End: index out of bounds.
Return: 42 * -1 = -42.

Test Case 2: s = "4193 with words"
Sign: None. sign = 1.
Digit 4: result = 4.
Digit 1: result = 41.
Digit 9: result = 419.
Digit 3: result = 4193.
Next Char: ' ' (space). Character.isDigit is false.
Break Loop.
Return: 4193.
*/
