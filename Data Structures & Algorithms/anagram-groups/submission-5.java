class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //Early Exit

        if (strs == null || strs.length == 0) {
            throw new IllegalArgumentException("..");
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String str: strs) {
            //Sort each string
            //Add each string to a Map<String,List<String>> key is sorted string
            char[] strChars = str.toCharArray();
            Arrays.sort(strChars);
            String newValue = new String(strChars);
            
            List<String> newList;
            if (map.keySet().contains(newValue)) {
                newList = map.get(newValue);
            } else {
                newList = new ArrayList<>();
            }
            newList.add(str);
            map.put(newValue, newList);
        }

        return new ArrayList<>(map.values());
    }

     public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            // 1. Initialize frequency array
            int[] counts = new int[26];
            for (char c : str.toCharArray()) {
                counts[c - 'a']++;
            }

            // 2. Build a unique key string from the counts
            // Example: "1#0#2#0..." representing 1 'a', 0 'b', 2 'c'...
            StringBuilder sb = new StringBuilder();
            for (int count : counts) {
                sb.append('#'); 
                sb.append(count);
            }
            String key = sb.toString();

            // 3. Group in map
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
