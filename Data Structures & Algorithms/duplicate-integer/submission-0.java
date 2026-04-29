class Solution {
    public boolean hasDuplicate(int[] nums) { //[1,2,3,3]
        //Early Exit
        if (nums == null || nums.length <= 1) {
            return false;
        }
        Set<Integer> visited = new HashSet<>(); //[]
        for (int num: nums) { //num=3
            if (visited.contains(num)) { //F
                return true;
            }
            visited.add(num); //[1,2,3]
        }
        //Build a set for numbers we encounter
        //if number exists in set, return true
        //else return false
        return false;
    }
}