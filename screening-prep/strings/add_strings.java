class Solution {
    public String addStrings(String num1, String num2) {
        // 1. Initialize Pointers
        // We start from the end of both strings (Least Significant Digit)
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        
        // 2. Initialize State
        // StringBuilder is mutable and efficient for appending characters.
        StringBuilder result = new StringBuilder();
        int carry = 0;
        
        // 3. Main Loop
        // Run as long as there are digits in either string OR a remaining carry
        while (p1 >= 0 || p2 >= 0 || carry != 0) {
            
            // 4. Extract Digits
            // If pointer is valid (>= 0), get the digit.
            // ASCII Math: '5' - '0' = 5.
            // If pointer is out of bounds, use 0 (padding with imaginary leading zeros).
            int x1 = (p1 >= 0) ? num1.charAt(p1) - '0' : 0;
            int x2 = (p2 >= 0) ? num2.charAt(p2) - '0' : 0;
            
            // 5. Calculate Sum for this position
            int sum = x1 + x2 + carry;
            
            // 6. Update Result and Carry
            // If sum is 15 -> append 5 (15 % 10), new carry is 1 (15 / 10).
            result.append(sum % 10);
            carry = sum / 10;
            
            // 7. Move Pointers Left
            p1--;
            p2--;
        }
        
        // 8. Reverse and Return
        // We appended digits from ones -> tens -> hundreds.
        // The result is currently backwards (e.g., "51" instead of "15").
        return result.reverse().toString();
    }
}
