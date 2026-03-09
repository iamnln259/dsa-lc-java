Questions
What is the maximum value of N
Do we need to return them in a specific order?
Is N=0 a valid input?

Approach 
We build the string character by character. We track two counters: open (count of ( used) and close (count of ) used).
Rules:
We can place an ( if open < n.
We can place a ) if close < open. (Crucial: You can't close a bracket that hasn't been opened).
Trade-off: Time is $O(4^n}/{sqrt{n}})$ (N-th Catalan Number). Space is $O(N)$ (Recursion depth).
 This is optimal because we never generate a "dead end" string.

Edge Cases 

Code

class Solution {
    public List<String> generateParenthesis(int n) {
        // 1. Initialize result list
        List<String> result = new ArrayList<>();
        
        // 2. Start the recursive backtracking
        // Parameters: 
        // - result: accumulator for valid strings
        // - current: the string being built
        // - open: number of '(' used so far
        // - close: number of ')' used so far
        // - max: the target number of pairs (n)
        backtrack(result, new StringBuilder(), 0, 0, n);
        
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // 3. Base Case: The string is complete
        // If the current string length is 2 * n, we have used all pairs.
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // 4. Decision 1: Add an Opening Bracket '('
        // We can only add '(' if we haven't used all n of them yet.
        if (open < max) {
            current.append("(");
            // Recurse with incremented open count
            backtrack(result, current, open + 1, close, max);
            // Backtrack: remove the character we just added to try the next possibility
            current.deleteCharAt(current.length() - 1);
        }

        // 5. Decision 2: Add a Closing Bracket ')'
        // We can only add ')' if we have more open brackets than closed ones.
        // This ensures validity (we never close a bracket that doesn't exist).
        if (close < open) {
            current.append(")");
            // Recurse with incremented close count
            backtrack(result, current, open, close + 1, max);
            // Backtrack: remove the character
            current.deleteCharAt(current.length() - 1);
        }
    }
}
Dry Run 
Test Case 1: n = 2
Target length: 4.
Start: "", open=0, close=0.
Add (: "(", open=1, close=0.
Option A (Add (): open < 2 is true.
"((", open=2, close=0.
Now open < 2 is false. Only close < open is valid.
Add ): "(()", open=2, close=1.
Add ): "(())", open=2, close=2. Length 4. Add to Result. Backtrack.
Option B (Add )): close < open (0 < 1) is true.
"()", open=1, close=1.
Now close < open (1 < 1) is false. Must add (.
Add (: "()(", open=2, close=1.
Add ): "()()", open=2, close=2. Length 4. Add to Result. Backtrack.
Result: ["(())", "()()"].
Test Case 2: n = 1
Start: "".
Add (: "(".
Only close < open valid.
Add ): "()".
End: Length 2. Result ["()"].
