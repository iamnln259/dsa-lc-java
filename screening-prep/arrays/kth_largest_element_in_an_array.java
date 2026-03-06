//Heap Solution
//O(NLogK) , O(K)

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 1. Initialize a Min-Heap (PriorityQueue)
        // Java's PriorityQueue is a Min-Heap by default.
        // It orders elements naturally (smallest at the top).
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 2. Iterate through the array
        for (int num : nums) {
            // 3. Add current number to the heap
            minHeap.offer(num);

            // 4. Maintain Heap Size
            // If the heap size exceeds 'k', remove the smallest element.
            // Why? Because if we have k+1 elements, the smallest one (the root)
            // definitely cannot be the Kth largest. It's too small.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 5. The Root is the Answer
        // After processing all elements, the heap contains exactly the 'k' largest elements.
        // The root (minHeap.peek()) is the smallest of these 'k' giants, 
        // which by definition is the Kth largest element in the entire array.
        return minHeap.peek();
    }
}

//O(N) , O(1)

//Quick Select Approach
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 1. The Kth largest element is effectively the element at 
        // index (N - k) if the array were sorted in ascending order.
        int targetIndex = nums.length - k;
        int left = 0;
        int right = nums.length - 1;
        Random rand = new Random();

        // 2. Iterative partitioning loop
        while (left <= right) {
            // 3. Pick a random pivot index to avoid O(N^2) worst-case on sorted arrays
            int pivotIndex = left + rand.nextInt(right - left + 1);
            
            // 4. Partition the array around this pivot
            // Returns the final sorted position of the pivot
            int finalPivotPosition = partition(nums, left, right, pivotIndex);

            // 5. Binary Search logic:
            if (finalPivotPosition == targetIndex) {
                // Found it!
                return nums[finalPivotPosition];
            } else if (finalPivotPosition < targetIndex) {
                // Pivot is too far left. The target is in the right half.
                left = finalPivotPosition + 1;
            } else {
                // Pivot is too far right. The target is in the left half.
                right = finalPivotPosition - 1;
            }
        }
        
        return -1; // Should not reach here per problem constraints
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        
        // 1. Move pivot to the very end out of the way
        swap(nums, pivotIndex, right);
        
        // 2. 'storeIndex' tracks the boundary between smaller and larger elements
        int storeIndex = left;
        
        // 3. Iterate through the range
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                // If current element is smaller than pivot, move it to the 'storeIndex'
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        
        // 4. Move pivot from the end to its final correct position
        swap(nums, storeIndex, right);
        
        return storeIndex;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
