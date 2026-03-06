class Solution{
	public boolean isValid(String s){
		Stack<Character> stack = new Stack<>();
		for(int i = 0 ; i < s.length(); i++){
			char ch = s.charAt(i);
			if(ch == '(' || ch == '{' || ch == '['){
				stack.push(ch);
			}
			else if(!stack.isEmpty() && ch == ')' && stack.peek() == '('){
				stack.pop();
			}
			else if(!stack.isEmpty() && ch == '}' && stack.peek() == '{'){
				stack.pop();
			}
			else if(!stack.isEmpty() && ch == ']' && stack.peek() == '['){
				stack.pop();
			}
			else{
				return false;
			}
		}
		return stack.isEmpty();
	}
}
class Solution {
    public boolean isValid(String s) {
        // 1. Guard Clause: Odd length strings cannot be valid
        if (s.length() % 2 != 0) {
            return false;
        }
        // 2. Use ArrayDeque (modern standard) instead of legacy Stack class
        // Deque allows us to push/pop from the head (LIFO behavior)
        Deque<Character> stack = new ArrayDeque<>();
        // 3. Iterate through every character
        for (char c : s.toCharArray()) {  
            // 4. If it's an OPENING bracket, push the EXPECTED CLOSING bracket
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } 
            // 5. If it's a CLOSING bracket
            else {
                // If stack is empty (no opener) OR the top doesn't match 'c'
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        // 6. Final check: Stack must be empty for the string to be valid
        return stack.isEmpty();
    }
}
