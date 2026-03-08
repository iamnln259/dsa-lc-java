Questions
Can I modify the input list? If yes, I will go with O(1) space, else O(N) space.
What constitutes a palindrome? 1->2->2->2->1 Yes, 1->2 No
How do I handle a single Node ? It is a palindrome
Should I restore the list after checking? 
Approach

Traverse, Put values in a list or array, apply Palindrome logic on list or array. O(N), O(N)
Find the middle of the linked list . Reverse the second half. compare it with the first half. O(N), O(1)
Edge Cases
Empty Node, Single Node :
Just Two Nodes, different or same. 

Code

class Solution {
    public boolean isPalindrome(ListNode head) {
        // 1. Guard Clause
        // An empty list or single node is always a palindrome.
        if (head == null || head.next == null) {
            return true;
        }

        // 2. Find the Middle using Slow/Fast Pointers
        ListNode slow = head;
        ListNode fast = head;

        // While fast is valid and can move two steps...
        while (fast != null && fast.next != null) {
            slow = slow.next;       // Moves 1 step
            fast = fast.next.next;  // Moves 2 steps
        }
        
        // At this point, 'slow' is at the middle (odd length) or start of second half (even length).
        
        // 3. Reverse the Second Half
        // We capture the head of the reversed list in 'secondHalfHead'
        ListNode secondHalfHead = reverseList(slow);
        ListNode firstHalfHead = head;

        // 4. Compare the two halves
        // We use a temp pointer for the second half to traverse it without losing the head (needed for restore)
        ListNode p2 = secondHalfHead; 
        ListNode p1 = firstHalfHead;
        
        boolean isPal = true;

        while (isPal && p2 != null) {
            if (p1.val != p2.val) {
                isPal = false; // Mismatch found
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 5. (Optional but Recommended) Restore the List
        // We reverse the second half again to put it back to original state.
        // reverseList(secondHalfHead);

        return isPal;
    }

    // Helper: Standard Iterative List Reversal
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next; // Save next
            curr.next = prev;              // Reverse pointer
            prev = curr;                   // Move prev
            curr = nextTemp;               // Move curr
        }
        return prev; // New head of the reversed list
    }
}

ArrayList Solution
class Solution{
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }

        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while(current.next != null){
            list.add(current.val);
            current = current.next;
        }
        int left = 0 ;
        int right = list.size() - 1;
        while(left < right){
            if(list.get(left) != list.get(right)){
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}

Dry Run 
Test Case 1: Even Length [1, 2, 2, 1]
Find Middle:
slow moves to 2nd node (2). fast moves to 3rd (2).
slow moves to 3rd node (2). fast moves to null (past 4th).
Loop ends. slow is at the second 2.
Reverse:
Input to reverse: 2 -> 1 -> null.
Output: 1 -> 2 -> null. (secondHalfHead points to 1).
Compare:
p1 (Head 1) vs p2 (RevHead 1). Match. Move next.
p1 (Node 2) vs p2 (Node 2). Match. Move next.
p2 is null. Loop ends.
Return: true.
Test Case 2: Odd Length [1, 0, 1]
Find Middle:
slow moves to 0. fast moves to last 1.
fast.next is null. Loop ends. slow is at 0.
Reverse:
Input to reverse: 0 -> 1 -> null.
Output: 1 -> 0 -> null. (secondHalfHead points to 1).
Compare:
p1 (1) vs p2 (1). Match.
p1 (0) vs p2 (0). Match.
p2 is null.
Return: true.
