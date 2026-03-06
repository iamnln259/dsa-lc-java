class Solution {
    public int maxProfit(int[] prices) {
        // 1. Guard Clause: If array is empty or has only 1 day, no transaction possible.
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // 2. Initialize 'minPrice' to the highest possible integer.
        // This ensures the first price we see will effectively become the minimum.
        int minPrice = Integer.MAX_VALUE;
        
        // 3. Initialize 'maxProfit' to 0 (default if no profitable trade exists).
        int maxProfit = 0;

        // 4. Single pass through the price array
        for (int price : prices) {
            // 5. Update the valley: Did we find a new lowest price to buy at?
            if (price < minPrice) {
                minPrice = price;
            } 
            // 6. Update the peak: If we sold at today's price (minus our current minPrice),
            // is this the best profit we've seen so far?
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }
}
