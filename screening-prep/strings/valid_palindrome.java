class Solution {
    public boolean isPalindrome(String s) {
        // 1. Guard Clause: Empty strings are technically palindromes
        if (s == null || s.isEmpty()) {
            return true;
        }
        // 2. Initialize two pointers
        int left = 0;
        int right = s.length() - 1;
        // 3. Iterate until the pointers meet in the middle
        while (left < right) {       
            // 4. Move 'left' forward until we find a valid alphanumeric char
            // We must check 'left < right' to avoid IndexOutOfBounds
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }   
            // 5. Move 'right' backward until we find a valid alphanumeric char
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // 6. Compare characters (Case Insensitive)
            // We convert both to lowercase before comparing
            char leftChar = Character.toLowerCase(s.charAt(left));
            char rightChar = Character.toLowerCase(s.charAt(right));
            if (leftChar != rightChar) {
                // Mismatch found immediately
                return false;
            }
            // 7. Move pointers inward for the next iteration
            left++;
            right--;
        }
        // 8. If we survived the loop, it's a palindrome
        return true;
    }
}
//O(N), O(1)
