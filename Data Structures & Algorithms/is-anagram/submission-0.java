class Solution {
    public boolean isAnagram(String s, String t) {
        //Early Exit: either is null, or empty
        if (s == null && t == null) {
            return true;
        }
        if (String.isEmpty(s) == String.isEmpty(t)) {
            return true;
        }
        //EE: if char count is different
        if (s.length != t.length) {
            return false;
        }
        
        //Build a map for each string
        Map<Character,Integer> sMap = makeMap(s);
        Map<Character,Integer> tMap = makeMap(t);
        //Compare the contents of the map
        //Start with key size
        if (sMap.keyset().size() != tMap.keyset().size()) {
            return false;
        }
        //compare each pair
        for (char c: sMap.keyset()) {
            if (!tMap.keyset().contains(c)) return false;
            if (tMap.get(c) != sMap.get(c)) return false;
        }
        return true;
    }

    private Map<Character,Integer> makeMap(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (char c: s) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    } 
}
