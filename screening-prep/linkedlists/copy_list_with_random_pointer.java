Questions: 
Can the random pointer be null?
Can a random pointer point to the node itself?
Do I need to preserve the original list structure?

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Step 1: Weave the cloned nodes into the original list
        // Original: A -> B -> C
        // Weaved:   A -> A' -> B -> B' -> C -> C'
        Node curr = head;
        while (curr != null) {
            Node clonedNode = new Node(curr.val);
            clonedNode.next = curr.next;
            curr.next = clonedNode;
            curr = clonedNode.next; // Move to the next original node
        }

        // Step 2: Assign the random pointers for the cloned nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                // The clone's random is the original random's clone (which is exactly next to it)
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next; // Move to the next original node
        }

        // Step 3: Unweave the lists (Restore original, extract clone)
        curr = head;
        Node clonedHead = head.next;
        Node cloneCurr = clonedHead;

        while (curr != null) {
            // Restore the original list
            curr.next = curr.next.next;
            
            // Advance the cloned list pointer, carefully checking for the end
            if (cloneCurr.next != null) {
                cloneCurr.next = cloneCurr.next.next;
            }
            
            // Move forward
            curr = curr.next;
            cloneCurr = cloneCurr.next;
        }

        return clonedHead;
    }
}
