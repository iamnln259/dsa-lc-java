Questions
Do the nodes have a reference to the root? You must Traverse backwards to reach the root. 
Can a node be a descendant of itself? YES.
Are p and q guaranteed to be in the same tree? : Yes 

Approach 
Concept: Use two pointers a and b.
Logic: Move both pointers up one step at a time.
If a reaches the root (null), jump it to q s starting position.
If b reaches the root (null), jump it to p s starting position.
Why it works: By switching tracks, both pointers travel the exact same total distance
 (Distance_P + Distance_Q) before colliding at the intersection.
Time O(H). Space O(1).


Edge Cases 
p is ancestor of q: The logic handles this naturally. The pointer starting at p will go up, hit null, jump to q, and eventually meet the other pointer at p.
Same Node: p == q. while (a != b) is false immediately. Returns p. Correct.
Root: If p or q is root, the traversal simply handles parent as null and switches tracks.

Code 

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        // 1. Initialize Pointers
        // We start two runners, one at p and one at q.
        Node a = p;
        Node b = q;

        // 2. Iterate until they meet
        // They are guaranteed to meet at the LCA because we switch tracks.
        // Even if they don't intersect (impossible per constraints), they would meet at 'null'.
        while (a != b) {
            
            // 3. Move Pointer A
            // If 'a' reaches the top (null), reset it to the START of the other path (q).
            // Otherwise, move up to the parent.
            if (a == null) {
                a = q;
            } else {
                a = a.parent;
            }

            // 4. Move Pointer B
            // If 'b' reaches the top (null), reset it to the START of the other path (p).
            // Otherwise, move up to the parent.
            if (b == null) {
                b = p;
            } else {
                b = b.parent;
            }
            
        }
        // 5. Return the meeting point
        return a;
    }
}

Dry Run 

Test Case 1: Simple Tree
Tree: Root(3) -> Left(5), Right(1). p = 5, q = 1.
Init: a = 5, b = 1.
Iter 1:
a moves to parent (3).
b moves to parent (3).
Check: a == b (3 == 3). Loop ends.
Return: 3.

Test Case 2: Uneven Depth
Tree: 3 -> 5 -> 6. p = 6 (Depth 2), q = 5 (Depth 1). LCA is 5.
Iter 1: a=6, b=5.
a moves to 5.
b moves to 3.
Iter 2: a=5, b=3.
a moves to 3.
b moves to null (root's parent).
Iter 3: a=3, b=null.
a moves to null.
b jumps to p (6).
Iter 4: a=null, b=6.
a jumps to q (5).
b moves to 5.
Iter 5: a=5, b=5. Match!
Return: 5.
