package leetcode;

import java.util.*;

public class DoesPathExists  {

    // https://leetcode.com/problems/find-if-path-exists-in-graph/
    public static boolean validPath(int n, int[][] edges, int source, int destination) {

        List<Integer>[] adjList = new List[n];

        for(var edge : edges) {
            putBidirectionalEdge(adjList, edge);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[edges.length];
        q.add(source);

        while ( !q.isEmpty() ){

            Integer curr = q.poll();
            List<Integer> neighbors = adjList[curr];

            if(neighbors == null)
                continue;

            for(int i : neighbors){
                if(i == destination)
                    return true;

                if( !visited[i])
                    q.add(i);
            }

            visited[curr] = true;
        }
        return false;
    }

    private static void putBidirectionalEdge(List<Integer>[] adjList, int[] edge) {
        if(adjList[edge[0]] == null) {
            adjList[edge[0]] = new ArrayList<>();
        }
        if(adjList[edge[1]] == null) {
            adjList[edge[1]] = new ArrayList<>();
        }

        adjList[edge[1]].add(edge[0]);
        adjList[edge[0]].add(edge[1]);

    }

    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {

        List<Integer> l = new ArrayList<>();

        Map<Integer,Integer> map1 = new TreeMap<>();
        Map<Integer,Integer> map2 = new TreeMap<>();

        for (int n : arr) {
            map1.put(n,map1.getOrDefault(n,0) + 1);
        }
        for (int n : brr) {
            map2.put(n,map2.getOrDefault(n,0) + 1);
        }

        for(Map.Entry<Integer,Integer> e : map2.entrySet() ){

            if( !map1.containsKey(e.getKey()) ) {
                l.add(e.getKey());
            } else if (!map1.get(e.getKey()).equals(e.getValue())) {
                l.add(e.getKey());
            }
        }

        return l;
    }
}
