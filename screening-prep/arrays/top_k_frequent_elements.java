Questions
Are we optimizing for time or space-This dictates whether you use the O(N) Bucket Sort which has a slightly higher memory footprint, 
or the O(N log K) Min-Heap which is more memory-efficient
Is the data bounded and fitting entirely in memory, or is it a continuous stream?
If it is s a stream, Bucket Sort is impossible. You must use a Min-Heap. Confirming it fits in memory validates the Bucket Sort approach
Can I assume the answer is always unique?

Approach
Concept: Count frequencies, then maintain a Min-Heap of exactly size K. As you iterate through the map, push elements in. If the heap exceeds size K, pop the smallest frequency.
Trade-off: Time complexity is O(N log K). Space is O(N) for the map, but only O(K) for the heap. This is the industry standard for real-time streaming data.

Concept: The maximum possible frequency of any element is N (the length of the array). 
We can create an array of Lists where the index represents the frequency. We populate this array, 
then scan it backwards from the end to gather the top K elements.
Trade-off: Time complexity is a strictly linear O(N). Space complexity is O(N). Tell the interviewer: 
"If this were a streaming data problem, I would use the Min-Heap. But since we have the entire dataset in memory, 
Bucket Sort guarantees an optimal linear time complexity. I will implement the Bucket Sort."

Potential Edge Cases 
k equals the number of unique elements: Perfectly handled. The reverse loop will naturally drain all buckets and stop right at the end.
Negative Numbers: The array handles this flawlessly. The negative numbers are stored as keys inside the lists, 
while the indices of the bucket array represent the frequency, which is strictly a positive integer starting at 1.

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Build a frequency map of the elements
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // 2. Create the buckets. The maximum frequency is nums.length.
        // We use nums.length + 1 so the indices align perfectly with frequencies (1 to N).
        List<Integer>[] buckets = new List[nums.length + 1];
        
        // 3. Populate the buckets. The frequency is the index.
        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        
        // 4. Gather the top k frequent elements by scanning from right to left (highest frequency first)
        int[] result = new int[k];
        int resultIndex = 0;
        
        for (int i = buckets.length - 1; i >= 0 && resultIndex < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    result[resultIndex++] = num;
                    // Stop once we've collected exactly K elements
                    if (resultIndex == k) {
                        return result;
                    }
                }
            }
        }
        
        return result;
    }
}

Test Case 1: Standard Input
Input: nums = [1, 1, 1, 2, 2, 3], k = 2
Phase 1 (Frequencies): Map becomes {1: 3, 2: 2, 3: 1}.
Phase 2 (Buckets init): buckets array of size 7 (from index 0 to 6).
Phase 3 (Populating):
Key 1 has freq 3. buckets[3] = [1].
Key 2 has freq 2. buckets[2] = [2].
Key 3 has freq 1. buckets[1] = [3].
Phase 4 (Scanning Backwards):
i = 6, 5, 4: All null. Skip.
i = 3: Not null. Contains [1]. result[0] = 1. resultIndex becomes 1.
i = 2: Not null. Contains [2]. result[1] = 2. resultIndex becomes 2.
resultIndex == 2 matches k. Early return.
Output: [1, 2]. Correct.
Test Case 2: Array of Identical Elements
Input: nums = [7, 7, 7, 7], k = 1
Phase 1: Map becomes {7: 4}.
Phase 2: buckets array of size 5 (0 to 4).
Phase 3: Key 7 has freq 4. buckets[4] = [7].
Phase 4:
Scan from end. i = 4 contains [7].
result[0] = 7. resultIndex becomes 1.
Target k met. Return.
Output: [7]. Correct.

Time Complexity: O(N). We iterate through the array once to build the map O(N).
 We iterate through the map to build the buckets O(U) where U is unique elements. We iterate backwards through the bucket array O(N). Total time is strictly bounded linearly by N.
Space Complexity: O(N). The frequency map takes O(U) space. The bucket array takes O(N + 1) space. 
Total auxiliary space scales linearly with the input size.

Max Heas Solution

