/*
Before coding, clarify these points to verify edge cases:
"Is 'n' guaranteed to be valid?" (i.e., $1 \le n \le \text{length}$. LeetCode says yes, but in real life, checking bounds is good).
"What if we need to remove the head node?" (This is the trickiest case. It suggests we should use a "Dummy Node" to handle head deletion generically).
"Is it a singly or doubly linked list?" (Singly. This confirms we cannot look backward).

Approach 1: Two Pass (Length Calculation)
Concept: Iterate once to calculate length. Iterate again to index length - n and remove.
Trade-off: Time $O(2L)$ (which simplifies to $O(L)$), Space $O(1)$.
Critique: Valid, but iterating twice is unnecessary I/O overhead if the list is stored in disjoint memory or on disk.
Approach 2: One Pass (Two Pointers) -- OPTIMAL
Concept: Use two pointers, fast and slow. Move fast n steps ahead first. Then move both at the same speed. 
When fast hits the end, slow will be exactly at the node before the target.
Trade-off: Time $O(L)$ (One Pass). Space $O(1)$.
Verdict: This is the standard "Hire" solution.
*/
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1. Initialize Dummy Node
        // The dummy node points to the head. This simplifies edge cases
        // where the head itself needs to be removed.
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 2. Initialize Pointers
        // Both start at dummy.
        ListNode slow = dummy;
        ListNode fast = dummy;

        // 3. Move Fast Pointer 'n + 1' steps ahead
        // Why n + 1? Because we want 'slow' to stop ONE node BEFORE the target.
        // If we want to remove the Nth node, we need access to the (N+1)th node from end.
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 4. Move Both Pointers until Fast hits the end
        // Maintain the gap of 'n + 1' between them.
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 5. Remove the Target Node
        // At this point, 'slow' is just before the node we want to delete.
        // We bypass the target node by pointing slow.next to the target's next.
        slow.next = slow.next.next;

        // 6. Return the new head
        // We return dummy.next because 'head' might have been deleted.
        return dummy.next;
    }
}

Input: head = [1, 2, 3, 4, 5], n = 2 (Remove 4).
Init: dummy -> 1. slow at dummy, fast at dummy.
Gap Setup: Move fast $2+1 = 3$ steps.
fast points to node 3.
Slide:
fast moves to 4, slow moves to 1.
fast moves to 5, slow moves to 2.
fast moves to null, slow moves to 3.
Delete: slow is at 3.
slow.next (was 4) becomes slow.next.next (5).
List: 1->2->3->5.
Return: dummy.next (1). Correct.
Test Case 2: Remove Head (Edge Case)
Input: head = [1, 2], n = 2 (Remove 1).
Init: dummy -> 1 -> 2.
Gap Setup: Move fast $2+1 = 3$ steps.
fast moves dummy->1->2->null. fast is null.
Slide: while (fast != null) is false immediately. Loop doesn't run. slow stays at dummy.
Delete: slow is at dummy.
slow.next (was 1) becomes slow.next.next (2).
List from dummy: dummy->2.
Return: dummy.next (2). Correct.

Remove Head: Handled by dummy. Returns the second node.
Remove Tail ($n=1$): fast creates a gap of 2. slow stops at the second-to-last node. slow.next becomes null. Correct.
Single Node List ($n=1$): dummy -> 1. fast moves past 1 to null. slow stays at dummy. dummy points to null. Returns empty list. Correct.
