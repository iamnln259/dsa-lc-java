class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // 1. Define Search Space
        // Minimum speed: 1 banana per hour.
        // Maximum speed: The size of the largest pile (eating faster than the 
        // largest pile doesn't save extra hours per pile).
        int left = 1;
        int right = 1; 
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        // 2. Binary Search
        while (left < right) {
            int mid = left + (right - left) / 2;

            // 3. Feasibility Check
            if (canFinish(piles, h, mid)) {
                // If we can finish at speed 'mid', this might be the answer,
                // OR there might be a slower valid speed to the left.
                right = mid; 
            } else {
                // If we cannot finish, 'mid' is too slow.
                // We strictly need a faster speed.
                left = mid + 1;
            }
        }

        // When left == right, we found the minimum speed.
        return left;
    }

    // Helper: Calculates if Koko can finish within 'h' hours at speed 'k'
    private boolean canFinish(int[] piles, int h, int speed) {
        long hoursUsed = 0; // Use long to prevent overflow

        for (int pile : piles) {
            // Formula for ceiling division: Math.ceil(pile / speed)
            // Equivalent integer math: (pile + speed - 1) / speed
            // Example: pile=7, speed=3. (7+3-1)/3 = 9/3 = 3 hours.
            hoursUsed += (pile + speed - 1) / speed;
        }

        return hoursUsed <= h;
    }
}
