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
