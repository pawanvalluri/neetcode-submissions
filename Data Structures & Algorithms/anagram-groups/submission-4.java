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
}
