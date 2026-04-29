class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        //Early Exit
        if (k < 1 || nums == null || nums.length < k) {
            throw new IllegalArgumentException("..");
        }

        //Compile a map of frequency
        Map<Integer,Integer> map = new HashMap<>();
        for (int n: nums) map.put(n, map.getOrDefault(n, 0) + 1); 

        //[1=>1,2=>2,3=>3] k = 2

        //Add it to a min heap while removing the element, if # is more than k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a,b) -> map.get(a) - map.get(b)
        );

        for(int key: map.keySet()) {
            minHeap.add(key); //[2,3]
            if (minHeap.size() > k) minHeap.poll();
        }

        //Construct a result with remaining
        int[] result = new int[k];
        for (int i=0; i<k; i++) {
            result[i] = minHeap.poll();
        }

        return result;
    }
}
