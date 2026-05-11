class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) { // 3, 1-0
        //Options for Graph: DFS / BFS
        //BFS options: queue or heap

        int[] empty = new int[0];
        List<Integer> result = new ArrayList<>();
        
        //EarlyExit
        if (numCourses == 0 || prerequisites == null) {
            return empty;
        }

        List<List<Integer>> adj = new ArrayList<>();
        int[] inDeg = new int[numCourses];
        int[] visited = new int[numCourses];

        //Build a Adjacency Matrix and capture indegree for each node
        for (int i=0;i<numCourses;i++) {
            adj.add(new ArrayList<>());
        }

        //adj={[],[],[]}

        for (int i=0; i<prerequisites.length; i++) {
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            inDeg[prerequisites[i][0]]++;
        }

        //adj={[],[0],[]}

        //min heap
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int i=0; i<numCourses; i++) {
            if (inDeg[i] == 0) {
                heap.offer(i); //heap-> 1->0, 2->0, 0->1
            }
        }

        //original heap-> 1->0, 2->0, 0->1

        //heap-> 0->0, 2->0
        //adj={[],[0],[]}
        //inDeg={0,0,0}
        //visited={0,1,0}
        //result={1, 0, 2}

        //Pick the node with 0 in degrees
        while (heap.peek() != null) { 
            int i = heap.poll(); // 0->0
            //Mark as visited
            visited[i] = 1;
            //Add it to result
            result.add(i);
            //Reduce the indegree for any of its dependents
            for (int j: adj.get(i)) {
                inDeg[j]--;
                if (inDeg[j] == 0) {
                    heap.offer(j);
                }
            }
        }

        //If result != n, then cycle, return empty
        if (result.size() != numCourses) {
            return empty; // Cycle
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
