class Solution {
    public boolean isStrobogrammatic(String num) {
        // 1. Initialize Pointers
        int left = 0;
        int right = num.length() - 1;

        // 2. Iterate until pointers cross
        // We use <= because for odd-length strings, we must check the middle digit against itself.
        while (left <= right) {
            char l = num.charAt(left);
            char r = num.charAt(right);

            // 3. Validation Logic
            // We check if the pair (l, r) is a valid strobogrammatic pair.
            if (!isValidPair(l, r)) {
                return false;
            }

            // 4. Move Inward
            left++;
            right--;
        }

        return true;
    }

    // Helper: Checks valid rotational pairs
    // Valid Pairs: (0,0), (1,1), (8,8), (6,9), (9,6)
    private boolean isValidPair(char left, char right) {
        switch (left) {
            case '0': return right == '0';
            case '1': return right == '1';
            case '8': return right == '8';
            case '6': return right == '9';
            case '9': return right == '6';
            default: return false; // 2, 3, 4, 5, 7 are invalid
        }
    }
}
