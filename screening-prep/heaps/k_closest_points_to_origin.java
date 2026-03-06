class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // 1. Guard Clause: If K matches N, return everything.
        if (k == points.length) {
            return points;
        }
        // 2. Define a Max-Heap (PriorityQueue).
        // We use a custom comparator.
        // Logic: (b - a) creates a descending order (Max-Heap).
        // We calculate distance squared (x^2 + y^2) to avoid expensive Math.sqrt().
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(
                (b[0] * b[0] + b[1] * b[1]), // Distance of point b
                (a[0] * a[0] + a[1] * a[1]) // Distance of point a
        ));
        // 3. Iterate over every point in the input
        for (int[] point : points) {
            // 4. Add the current point to the heap
            maxHeap.add(point);
            // 5. If the heap exceeds size K, remove the 'furthest' point.
            // Since it's a Max-Heap, poll() removes the element with the largest distance.
            // This ensures the heap always holds the K smallest distances seen so far.
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        // 6. Convert the Heap into the result array
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }
}
//O(NLogK), O(K)
