package Graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class UndirectedUnweightedGraph {
	
	private EdgeNode[] edges;
	private int nEdges;
	
	public UndirectedUnweightedGraph(int nVertices) {
		edges= new EdgeNode[nVertices];
		nEdges = 0;
		for(int i = 0 ; i < nVertices ; i++) {
			edges[i] = new EdgeNode(i,(char) ('a'+i));
		}
		
	}
	
	public void addEdge(char a,char b) {
		nEdges++;
		int u =  a-'a';
		int v =  b-'a';
		edges[u].neighbors.add(edges[v]);
		edges[v].neighbors.add(edges[u]);
	}
	
	
	public void printShortestPath(char a,char b) {
		
		int s = a-'a';
		int d = b-'a';
		int[] bfsParents = BFS(s);
		System.out.print(b);
		while(bfsParents[d] != d) {	
			System.out.print( " <- "+ (char)(bfsParents[d] + 'a'));
			d = bfsParents[d];
		}
	}
	
	
	// search with the node at index s being the root
	public int[] BFS(int s) {
		
		Set<EdgeNode> undiscovered = new HashSet<EdgeNode>();
		int[] parents = new int[edges.length];
		// this parents array have an important property : the path from the root to any node uses the SMALLEST number of edges
		
		for(int k = 0 ; k < edges.length ; k++ ) {
			undiscovered.add(edges[k]);
			parents[k] = k;
		}
		
		LinkedList<EdgeNode> queue = new LinkedList<EdgeNode>();
		undiscovered.remove(edges[s]);
		queue.add(edges[s]);
		
		while(!queue.isEmpty()) {
			EdgeNode curr = queue.remove(); // dequeue
		
			// process current node 
			System.out.println(curr.letter);
			for(EdgeNode it : curr.neighbors) {
				
			// process the edge (curr,it)
				
				if(undiscovered.contains(it)) {
					undiscovered.remove(it);
					parents[it.i] = curr.i;
					queue.add(it);
				}
			}	
		}	
		return parents;
	}
	
	public void DFS(int s) {
		
		Set<EdgeNode> discovered = new HashSet<EdgeNode>();
		//undiscovered.remove(edges[s]);
		dfsHelper(edges[s],discovered);
		// can implement dfs w the same code as bfs using a stack instead of a q, but recursion is cleaner 
	}
	
	private void dfsHelper(EdgeNode current,Set<EdgeNode> discovered) {
		if(discovered.contains(current))
			// if the current node was alrdy visited backtrack to previous nodes
			return;
		discovered.add(current);
		System.out.println(current.letter);
		for( EdgeNode node : current.neighbors) {
			dfsHelper(node, discovered);
		}
	}
	
	public int connectedComponentsCount() {
		int count = 0;
		// in BFS starting from a root s, we pass on all elements of the connected component that contains s
		Set<EdgeNode> undiscovered = new HashSet<EdgeNode>();
		
		
		// to find all connected components start from the first component, any discovered node will be part of the same component
		// we then repeat the search starting from any new undiscovered node.
		// if it has not been discovered previously, that means that its not a part of the component
		
		for(int k = 1 ; k < edges.length ; k++ ) {
			undiscovered.add(edges[k]);
		}
		
		for(int i = 0 ; i < edges.length ; i++ ) {
			
			if(undiscovered.contains(edges[i])) {
				count++;
				
				// bfs code written all over : the only differences are that the parents array is not used
				LinkedList<EdgeNode> queue = new LinkedList<EdgeNode>();
				undiscovered.remove(edges[i]);
				queue.add(edges[i]);
	
				while(!queue.isEmpty()) {
					EdgeNode curr = queue.remove(); 
					for(EdgeNode it : curr.neighbors) {
						if(undiscovered.contains(it)) {
							undiscovered.remove(it);
							queue.add(it);
						}
					}
					System.out.println();
				}
			}
			
		}
		
		return count;
	}
	
	public void printConnectedComponents() {
	
		Set<EdgeNode> undiscovered = new HashSet<EdgeNode>();
		int count = 0;
		for(int k = 1 ; k < edges.length ; k++ ) {
			undiscovered.add(edges[k]);
		}
		
		for(int i = 0 ; i < edges.length ; i++ ) {
			
			if(undiscovered.contains(edges[i])) {
				LinkedList<EdgeNode> queue = new LinkedList<EdgeNode>();
				undiscovered.remove(edges[i]);
				queue.add(edges[i]);
				
				System.out.println("Connected component " + ++count);
				while(!queue.isEmpty()) {
					EdgeNode curr = queue.remove(); 
					System.out.print(curr.letter + " ");
					for(EdgeNode it : curr.neighbors) {
						if(undiscovered.contains(it)) {
							undiscovered.remove(it);
							queue.add(it);
						}
					}
					System.out.println();
				}
			}
			
		}
	}
	 
	
}

