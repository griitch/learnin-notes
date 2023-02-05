package Graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import unionFind.WeightedQuickUnion;

public class UndirectedWeightedGraph {
	List<LinkedList<WeightedEdge>> edges;
	int nVertices;
	int nEdges;
	
	
	public UndirectedWeightedGraph(int n) {
		nVertices = n;
		edges = new ArrayList<LinkedList<WeightedEdge>>(n);
		
		for(int i = 0 ; i < n ; i++) {
			edges.add(new LinkedList<WeightedEdge>());
		}	
	}
	
	public void addEdge(int a,int b,int weight) {
		// since this is an undirected graph we have to add 2 edges (a,b) and (b,a)

		WeightedEdge A = new WeightedEdge(a,b, weight); // edge with b on the other side
		
		WeightedEdge B = new WeightedEdge(b,a, weight);
		
		edges.get(a).add(A);
		edges.get(b).add(B);
	}
	
	
	
	public WeightedEdge[] primMST(int start) {
		
		int edgeCount = 0;
		
		WeightedEdge[] mstEdges = new WeightedEdge[nVertices-1]; 
		
		boolean[] isVisited = new boolean[nVertices];
		
		
		PriorityQueue<WeightedEdge> q = new PriorityQueue<WeightedEdge>(new edgeComparator(-1));
		
		for( WeightedEdge edge : edges.get(start)) {
			if( !isVisited[edge.adj] )
			q.add(edge);
		}
		
		isVisited[start] = true;
		
		while( !q.isEmpty() && edgeCount != nVertices ) {
			
			WeightedEdge edge = q.poll();
			if(isVisited[edge.adj]) {
				continue;
			}
			
			mstEdges[edgeCount++] = edge;
			
			for( WeightedEdge edge2 : edges.get(edge.adj)) {
				if( !isVisited[edge2.adj] )
				q.add(edge2);
			}
			
			isVisited[edge.adj] = true;
			
		}
		return mstEdges;
		
	}
	
	public WeightedEdge[] kruskal() {
		
		int edgeCount = 0;
		
		WeightedEdge[] mstEdges = new WeightedEdge[nVertices-1]; 
		
		PriorityQueue<WeightedEdge> q = new PriorityQueue<WeightedEdge>(new edgeComparator(-1));
		
		WeightedQuickUnion set = new WeightedQuickUnion(nVertices);
		
		for(int i = 0 ; i < edges.size() ; i++) {
			for (WeightedEdge e : edges.get(i)) {
				q.add(e);
			}
		}
				
		while( !q.isEmpty() && edgeCount != nVertices ) {
			
			WeightedEdge edge = q.poll();
			
			if( !set.connected(edge.adj, edge.from)) {
				set.union(edge.adj, edge.from);
				mstEdges[edgeCount++] = edge;
			}	
		}
		return mstEdges;
		
	}
	
	
	
	public void dijkstra(int start) {
		int[][] res = new int[nVertices][2]; // res[i][0] : shortest distance, res[i][1] : previous node

		boolean[] isVisited = new boolean[nVertices];
		
		PriorityQueue<IndexDistancePair> q = new PriorityQueue<IndexDistancePair>(new IndexDistancePairComp(-1));
		
		for(int i = 0 ; i < nVertices ; i++ ) {
			res[i][0] = Integer.MAX_VALUE;
			res[i][1] = -1;
		}
		
		res[start][0] = 0;		
		q.add(new IndexDistancePair(start, 0));
		
		
		while( !q.isEmpty() ) {
			
			IndexDistancePair toVisit  = q.poll();
			
			isVisited[toVisit.index] = true;
			
			for( WeightedEdge edge : edges.get(toVisit.index)) {
				
				if(isVisited[edge.adj])
					continue;
				
				int newdist = edge.weight + res[toVisit.index][0];
				
				if(newdist < res[edge.adj][0]) {
					res[edge.adj][0] = newdist;
					res[edge.adj][1] = toVisit.index;
					q.add(new IndexDistancePair(edge.adj, newdist));
				}	
			}	
		}
		
		System.out.println("node  shortest  prev");
		for(int i = 0 ; i < nVertices ; i++ ) {
			
			System.out.print(i+ "\t" + res[i][0] +"\t" + res[i][1]);
			System.out.println();
		}
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		UndirectedWeightedGraph g = new UndirectedWeightedGraph(5);
		g.addEdge(0, 1, 6);
		g.addEdge(0, 3, 1);
		g.addEdge(1, 3, 2);
		g.addEdge(1, 4, 2);
		g.addEdge(1, 2, 5);
		g.addEdge(3, 4, 1);
		g.addEdge(4, 2, 5);
		
		
		WeightedEdge[] mst = g.primMST(0);
		//for(WeightedEdge s : mst) { System.out.println(s); }

		mst = g.kruskal();
		//for(WeightedEdge s : mst) {	System.out.println(s);}
	
		g.dijkstra(0);
	}
	
	
}

class edgeComparator implements Comparator<WeightedEdge> {
	
	int minOrMaxHeap; // 1 = max, -1 = min
	
	// maximum spanning tree can be implemented w  a max heap	
	
	public edgeComparator(int minOrMaxHeap) {
		super();
		this.minOrMaxHeap = minOrMaxHeap;
	}

	@Override
	public int compare(WeightedEdge o1, WeightedEdge o2) {
		return  minOrMaxHeap * (o2.weight - o1.weight);
	}
	
}

class IndexDistancePairComp implements Comparator<IndexDistancePair> {
	
	int minOrMaxHeap; // 1 = max, -1 = min
	
	// maximum spanning tree can be implemented w  a max heap	
	
	public IndexDistancePairComp(int minOrMaxHeap) {
		super();
		this.minOrMaxHeap = minOrMaxHeap;
	}

	@Override
	public int compare(IndexDistancePair o1, IndexDistancePair o2) {
		return  minOrMaxHeap * (o2.distance - o1.distance);
	}
	
}
	
class IndexDistancePair {
	int index;
	
	int distance;
	public IndexDistancePair(int index, int distance) {
		super();
		this.index = index;
		this.distance = distance;
	}
}

