/*
Questions
What happens if the reversed number exceeds Integer.MAX_VALUE? return 0
How do we handle negative numbers?

Approach 
Concept: Peel off the last digit using x % 10. Build the new number using result * 10 + digit.
Trade-off: Time $O(\log x)$. Space $O(1)$.
Overflow Handling:
Option A (Use Long): Store result in long. At the end, check if result > Integer.MAX_VALUE. (Easier, but might be "cheating" if 64-bit is banned).
Option B (Strict 32-bit Check): Before doing result * 10 + digit, check if result is already close to the limit. (The strictly correct way).

Edge Cases
*/
class Solution {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            // 1. Extract the last digit
            int pop = x % 10;      
            // 2. Remove the last digit from x
            x /= 10;
            // 3. Check for Overflow BEFORE appending
            // Integer.MAX_VALUE is 2147483647
            // Integer.MIN_VALUE is -2147483648  
            // Positive Overflow Check:
            // If result > MAX/10, multiplying by 10 will definitely overflow.
            // If result == MAX/10, we must check if adding 'pop' will overflow (pop > 7).
            if (result > Integer.MAX_VALUE / 10 || 
               (result == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            // Negative Overflow Check:
            // If result < MIN/10, multiplying by 10 will definitely underflow.
            // If result == MIN/10, we check if pop < -8.
            if (result < Integer.MIN_VALUE / 10 || 
               (result == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            // 4. Append the digit
            result = result * 10 + pop;
        }
        return result;
    }
}


/*
Dry Run 
Test Case 1: x = 123
Init: result = 0.
Iter 1:
pop = 3. x = 12.
Check: 0 is safe.
result = $0 \times 10 + 3 = 3$.
Iter 2:
pop = 2. x = 1.
Check: 3 is safe.
result = $3 \times 10 + 2 = 32$.
Iter 3:
pop = 1. x = 0.
Check: 32 is safe.
result = $32 \times 10 + 1 = 321$.
End: Return 321.
Test Case 2: x = 1534236469 (Will Overflow)
...Fast forward to when result = 964632435, x = 1, pop = 1...
We enter the loop.
Check: result (964,632,435) > MAX_VALUE / 10 (214,748,364).
964... > 214... is True.
Action: Return 0 immediately.

*/
