/*
Questions
Can the input lists be modified? - NO 
What if the lists have different lengths?
Are the digits stored in reverse order? YES . 234 is stored as 4 -> 3 -> 2
Can the lists be empty?

Approach
Use a carry variable. Iterate through both lists simultaneously.
Calculate sum = val1 + val2 + carry. The new digit is sum % 10. The new carry is sum / 10.
Trade-off: Time is $O(max(N, M))$. Space is $O(max(N, M))$ to create the result list.

Edge Cases 
Lists of different sizes: Handled by the ternary (l1 != null) ? val : 0.
Result is longer than inputs: Handled by the || carry > 0 condition (e.g., 99+1=100).
Empty Lists: If input is [] and [], the loop does not run, dummy.next is null. Returns null (or empty list).
*/
Code
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1. Dummy Head: A common pattern in LinkedList problems. 
        // It simplifies the code by giving us a non-null anchor to attach our results to.
        ListNode dummyHead = new ListNode(0);
        
        // 2. 'curr' is our pointer to build the new list
        ListNode curr = dummyHead;
        
        // 3. 'carry' stores the overflow from the previous addition (e.g., 5 + 6 = 11, carry is 1)
        int carry = 0;
        
        // 4. Iterate while there is data in l1, l2, OR a remaining carry
        // The '|| carry > 0' check is crucial for edge cases like 5 + 5 = 10 (extra node needed for '1')
        while (l1 != null || l2 != null || carry > 0) {
            
            // 5. Extract values. If a list has run out (null), use 0.
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            
            // 6. Calculate total sum for this column
            int sum = x + y + carry;
            
            // 7. Update carry for the next iteration
            carry = sum / 10;
            
            // 8. Create a new node with the digit (sum % 10) and attach it
            curr.next = new ListNode(sum % 10);
            
            // 9. Advance pointers
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        
        // 10. Return the actual head (next of dummy)
        return dummyHead.next;
    }
}
/*
Dry Run
Test Case 1: Standard Addition
Input: l1 = [2, 4, 3] (342), l2 = [5, 6, 4] (465)
Iteration 1:
x = 2, y = 5, carry = 0.
sum = 7. New Node: 7. New Carry: 0.
Result: dummy -> 7.
Iteration 2:
x = 4, y = 6, carry = 0.
sum = 10. New Node: 0. New Carry: 1.
Result: dummy -> 7 -> 0.
Iteration 3:
x = 3, y = 4, carry = 1.
sum = 8. New Node: 8. New Carry: 0.
Result: dummy -> 7 -> 0 -> 8.
End: Pointers null, carry 0. Return 7 -> 0 -> 8 (807).


Test Case 2: Carry Overflow at End
Input: l1 = [9, 9], l2 = [1]
Iteration 1:
x = 9, y = 1, carry = 0.
sum = 10. Node: 0. Carry: 1.
Iteration 2:
x = 9, y = 0 (l2 is null), carry = 1.
sum = 10. Node: 0. Carry: 1.
Iteration 3:
x = 0, y = 0, carry = 1. (Loop runs because carry > 0).
sum = 1. Node: 1. Carry: 0.
End: Return 0 -> 0 -> 1 (100).
*/
