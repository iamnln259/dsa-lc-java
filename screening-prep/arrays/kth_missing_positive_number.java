class Solution {
    public int findKthPositive(int[] arr, int k) {
        // 1. Initialize Binary Search Boundaries
        // We include 'arr.length' as the upper bound because the answer 
        // might lie strictly after the last element.
        int left = 0;
        int right = arr.length;

        // 2. Standard Binary Search Loop
        while (left < right) {
            int mid = left + (right - left) / 2;

            // 3. Calculate Missing Count
            // Ideally, at index 'mid', the value should be (mid + 1).
            // Example: At index 0, value should be 1. At index 4, value should be 5.
            // The difference tells us how many numbers are missing up to this point.
            int missingCount = arr[mid] - (mid + 1);

            // 4. Decision Logic
            if (missingCount < k) {
                // We haven't passed the k-th missing number yet.
                // The answer is to the right.
                left = mid + 1;
            } else {
                // We found at least k missing numbers (or more).
                // The k-th missing number is to the left (or exactly here).
                right = mid;
            }
        }

        // 5. The Formula Derivation
        // At the end of the loop, 'left' is the index where the missing count becomes >= k.
        // This means there are exactly 'left' elements in the array strictly smaller than our answer.
        // 
        // Logic:
        // If the array was empty, the k-th missing number would be k.
        // For every number in the array smaller than our target, the target shifts up by 1.
        // Since there are 'left' such numbers:
        // Result = k + left
        return left + k;
    }
}
