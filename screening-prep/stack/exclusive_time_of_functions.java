/*Questions
Are the logs perfectly chronological? - Yes the logs are sorted by timestamp
Can a function call itself recursively ? Yes
Is it possible for a function to start and end at the same time? If yes can we count it to be 1 unit of time?

Approach
We only push the function id onto a stack of integers. We maintain a single prevTime variable.
When a new function starts, we pause the currently running function (the one on top of the stack), credit it with the time elapsed since prevTime, and then push the new function.
When a function ends, we pop it, credit it with currTime - prevTime + 1, and update prevTime.
Trade-off: Strictly O(L) time and optimal O(L) space. This is the industry standard.

Potential Edge Case
Deep Recursion: A function calls itself multiple times (0:start, 0:start, 0:end, 0:end). 
The stack perfectly handles this because we push the ID 0 multiple times, and the popped elapsed times correctly aggregate into the single res[0] bucket.
Massive Timestamps: The timestamp can be up to 10^9. If we tried to simulate this by advancing a clock millisecond by millisecond 
*/
//Code 
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        // Result array where index corresponds to the function ID
        int[] res = new int[n];
        
        // Stack to simulate the execution call stack (stores function IDs)
        Stack<Integer> stack = new Stack<>();
        
        // Tracks the timestamp of the last processed event
        int prevTime = 0;
        
        for (String log : logs) {
            // Split the log into: [function_id, start_or_end, timestamp]
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            String action = parts[1];
            int currTime = Integer.parseInt(parts[2]);
            
            if (action.equals("start")) {
                // If another function is currently running, pause it and add its elapsed time
                if (!stack.isEmpty()) {
                    res[stack.peek()] += currTime - prevTime;
                }
                
                // Push the new function onto the stack and reset the time anchor
                stack.push(id);
                prevTime = currTime;
                
            } else { // action.equals("end")
                // The current function finishes. 
                // We add "+ 1" because the end time is at the END of the current unit of time.
                res[stack.pop()] += currTime - prevTime + 1;
                
                // The next event starts at the very beginning of the NEXT unit of time
                prevTime = currTime + 1;
            }
        }
        
        return res;
    }
}
/*
Dry Run 

Test Case 1: Standard Nested Calls
Input: n = 2, logs = ["0:start:0", "1:start:2", "1:end:5", "0:end:6"]
Log 1 (0:start:0):
Stack is empty.
Push 0 to stack. prevTime = 0.
Stack: [0]
Log 2 (1:start:2):
Stack is not empty. Peek is 0.
Add elapsed time to 0: currTime (2) - prevTime (0) = 2. res[0] = 2.
Push 1 to stack. prevTime = 2.
Stack: [0, 1]
Log 3 (1:end:5):
Action is end. Pop stack (popped 1).
Add elapsed time to 1: currTime (5) - prevTime (2) + 1 = 4. res[1] = 4.
prevTime = 5 + 1 = 6.
Stack: [0]
Log 4 (0:end:6):
Action is end. Pop stack (popped 0).
Add elapsed time to 0: currTime (6) - prevTime (6) + 1 = 1. res[0] = 2 + 1 = 3.
prevTime = 6 + 1 = 7.
Result: res = [3, 4]. Correct.

Test Case 2: Instant Execution (Start and End on same timestamp)
Input: n = 1, logs = ["0:start:2", "0:end:2"]
Log 1 (0:start:2): Stack pushes 0. prevTime = 2.
Log 2 (0:end:2): * Pops 0.
Math: currTime (2) - prevTime (2) + 1 = 1. res[0] = 1.
Result: res = [1]. Correct, it executed for exactly 1 unit of time during second 2.
Trade Offs 
*/
