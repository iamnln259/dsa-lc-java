class Solution {
    public String removeDuplicates(String s) {
        // 1. Guard Clause
        if (s == null || s.isEmpty()) {
            return s;
        }

        // 2. Initialize StringBuilder to act as our Stack
        // We generally allocate s.length() capacity to avoid resizing overhead, 
        // though the result will likely be smaller.
        StringBuilder stack = new StringBuilder(s.length());

        // 3. Iterate through the string
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            
            // 4. Check the "Top" of our stack
            int stackLen = stack.length();
            
            // If stack is not empty AND the top element matches current char
            if (stackLen > 0 && stack.charAt(stackLen - 1) == currentChar) {
                // 5. Cancel out (Pop)
                // We remove the last character from the builder
                stack.deleteCharAt(stackLen - 1);
            } else {
                // 6. Push
                // No match, so we add the character to the stack
                stack.append(currentChar);
            }
        }

        return stack.toString();
    }
}
//O(N), O(N)
