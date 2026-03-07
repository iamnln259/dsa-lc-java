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

//Version 2.0

class Solution{
    public int minEatingSpeed(int[] piles, int H){
        int low = 1;
        int high = piles[0];
        for(int pile : piles){
            high = Math.max(high, pile);
        }
        while(low < high){
            int mid = low + (high - low)/2;
            if(!isPossibleToEatAll(piles, H, mid)){
                low = mid + 1;
            }else{
                high = mid;
            }
        }
        return low;
    }
    private boolean isPossibleToEatAll(int[] piles, int hours, int currK){
        long count = 0;
        for(long pile : piles){
            count += pile/currK;
            if(pile%currK != 0){
                count ++;
            }
        }
        return (count <= hours);
    }
}
