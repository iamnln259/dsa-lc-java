Questions:
Are there any spaces or special characters in the directory names?
Is the input path guaranteed to be an absolute path starting with a slash?
Approach:
Split the path by /. Push valid directories onto a Stack. Pop them off when encountering ...
Trade-off: Conceptually correct, but java.util.Stack is a legacy class extending Vector. It applies synchronization locks on every operation, making it unnecessarily slow in a single-threaded environment.
Time complexity is O(N) and space is O(N).

class Solution {
    public String simplifyPath(String path) {
        // 1. ArrayDeque is the modern, fast alternative to java.util.Stack
        Deque<String> stack = new ArrayDeque<>();
        
        // 2. Split the path by the delimiter. 
        // Note: Multiple slashes "//" will result in empty string "" components.
        String[] components = path.split("/");
        
        // 3. Process each component of the path
        for (String dir : components) {
            
            // 4. A ".." means we go up one level, so we pop the last directory from our stack
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.removeLast(); // Safely move up a directory
                }
            } 
            // 5. Ignore current directory ".", empty strings, and the ".." we already handled
            else if (!dir.equals(".") && !dir.equals("")) {
                // It's a valid directory name, push it onto the stack
                stack.addLast(dir);
            }
        }
        
        // 6. Reconstruct the simplified path
        StringBuilder result = new StringBuilder();
        
        // ArrayDeque naturally iterates from first inserted to last inserted (bottom to top of stack)
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        
        // 7. If the stack was empty, we are at the root
        return result.length() > 0 ? result.toString() : "/";
    }
}


Test Case 1: Subdirectories and Trailing Slashes
Input: path = "/home//foo/"
Split: ["", "home", "", "foo"]
Iteration 1 (""): Ignored.
Iteration 2 ("home"): Pushed to stack. Stack: ["home"].
Iteration 3 (""): Ignored.
Iteration 4 ("foo"): Pushed to stack. Stack: ["home", "foo"].
Reconstruction: Iterates home, then foo. Appends /home then /foo.
Result: "/home/foo". Correct.
Test Case 2: Deep Traversal and Reversal
Input: path = "/a/./b/../../c/"
Split: ["", "a", ".", "b", "..", "..", "c"]
Iter "": Ignored.
Iter "a": Stack: ["a"]
Iter ".": Ignored. Stack remains ["a"].
Iter "b": Stack: ["a", "b"]
Iter "...": Pop! Stack becomes ["a"].
Iter "...": Pop! Stack becomes [] (Empty).
Iter "c": Stack: ["c"]
Reconstruction: Iterates c. Appends /c.
Result: "/c". Correct.
