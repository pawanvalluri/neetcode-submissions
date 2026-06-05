/*

Problem Statement
You are given n tasks labeled from 0 to n - 1 and a list of dependency pairs edges, 
where each pair [u, v] means task u must be completed before task v.

Return a valid ordering of all tasks. If multiple valid orderings exist, return the lexicographically smallest one. 
If it is impossible to complete all tasks because the dependency graph contains a cycle, return an empty list.

Constraints:

0 <= n <= 100,000
0 <= len(edges) <= 200,000
Each task label is in [0, n - 1]
All dependency pairs are distinct

Examples:

Input:  (4, [[0,1], [0,2], [1,3], [2,3]])

Output: [0, 1, 2, 3]

Input:  (5, [[0,2], [1,2], [3,4]])

Output: [0, 1, 2, 3, 4]   (two components; pick smallest available each step)



- (u, v) -> u must finish before v
Corner cases:
- n=0 -> empty list
- edges=[] -> return [0..n-1]
- Cycle -> return []
- Disjointed Nodes -> add them to the list

BFS / DFS 

//Validate Input
//Build adjacencylist
//Build inDeg for each node
//Seed a min-heap with all nodes with 0 indegree
//while heap is not empty
//Get the lexically smalled node
//Add it to result
//Get all adjacent nodes
//Decrease inDeg for each adj node
//check if indeg of adj node = 0, if so, add to heap
//Check for cycle: result.size() != n
//return result

--Dry run
--Time & Space Complexity
--Real world experience w/ Topological Sorting

public interface TaskOrdered<T> {
    List<T> getOrder(int n, List<Pair<T,T>> edges);
}

public class IntTaskOrdered implements TaskOrdered<Integer> {
    List<Integer> getOrder(int n, List<Pair<Integer,Integer>> edges) {
        ...
    }
}

Tag -> Filter, Enricher

Enricher<Tag> -> calcualte Tag

Filter<Tag> -> use the tag

*/

/*
Time: O(nlogn + elogn)
Space: O(n+e)
*/
class Solution {
    public int[] findOrder(int n, int[][] edges) {
        //Validate Input
        if (n <= 0) return new int[0];

        //Build adjacencylist
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDeg = new int[n];
        List<Integer> result = new ArrayList<>();

        //(5, [[0,2], [1,2], [3,4]])
        //adj={0->{},1->{},2->{},3->{},4->{}}
        //inDeg={0->0,1->0,2->0,3->0,4->0}

        //(3, [[1,0]])
        //adj={0->{},1->{},2->{}}
        //inDeg={0->0,1->0,2->0}

        //Build inDeg for each node
        for (int i=0; i< edges.length; i++) {
            adj.get(edges[i][1]).add(edges[i][0]);
            inDeg[edges[i][0]]++;
        }
        //(5, [[0,2], [1,2], [3,4]])
        //adj={0->{2},1->{2},2->{},3->{4},4->{}}
        //inDeg={0->0,1->0,2->2,3->0,4->1}
        

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        //Seed a min-heap with all nodes with 0 indegree
        for (int i=0; i<n; i++) {
            if (inDeg[i] == 0) heap.offer(i);
        }

        //(5, [[0,2], [1,2], [3,4]])
        //adj={0->{2},1->{2},2->{},3->{4},4->{}}
        //inDeg={0->0,1->0,2->2,3->0,4->1}
        //heap={0,1,3}

        //while heap is not empty
        while (!heap.isEmpty()) {
            //Get the lexically smalled node
            int node = heap.poll();
            //Add it to result
            result.add(node);
            //Get all adjacent nodes
            for (int adjNode: adj.get(node)) {
                //Decrease inDeg for each adj node
                inDeg[adjNode]--;
                //check if indeg of adj node = 0, if so, add to heap
                if (inDeg[adjNode] == 0) heap.offer(adjNode);
            }
        }

        //(5, [[0,2], [1,2], [3,4]])
        //adj={0->{2},1->{2},2->{},3->{4},4->{}}
        //inDeg={0->0,1->0,2->0,3->0,4->0}
        //heap={}
        //result={0,1,2,3,4}

        //Check for cycle: result.size() != n
        if (result.size() != n) return new int[0];

        return result.stream().mapToInt(x->x).toArray();
    }
}
