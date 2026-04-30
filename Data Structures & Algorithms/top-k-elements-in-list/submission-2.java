class Solution {
    public int[] topKFrequent1(int[] nums, int k) {
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
    public int[] topKFrequent(int[] nums, int k) {
        //Early Exit
        if (k < 1 || nums == null || nums.length < k) {
            throw new IllegalArgumentException("..");
        }

        //Compile a map of frequency
        Map<Integer,Integer> map = new HashMap<>();
        for (int n: nums) map.put(n, map.getOrDefault(n, 0) + 1); 

        //Build a bucket of freq -> int value

        Map<Integer, List<Integer>> bucket = new HashMap<>();

        for (int num: map.keySet()) {
            int freq = map.get(num);

            bucket.computeIfAbsent(freq, l -> new ArrayList<>());
            List entry = bucket.get(freq);
            entry.add(num);

            bucket.put(freq, entry);
        }

        int[] result = new int[k];
        int j=0;

        for (int i = nums.length; i >= 0; i--) {
            if (bucket.containsKey(i) && bucket.get(i).size() > 0) {
                for (int m: bucket.get(i)) {
                    result[j++] = m;
                    if (j >= k) return result;
                }
            }
        }

        return result;
    }
}
