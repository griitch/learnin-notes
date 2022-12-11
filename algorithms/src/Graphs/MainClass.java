package Graphs;

public class MainClass {
	public static void main(String[] args) {
		UndirectedUnweightedGraph g = new UndirectedUnweightedGraph(6);
//		
//		g.addEdge('a', 'b');
//		g.addEdge('a', 'c');
//		g.addEdge('b', 'd');
//		g.addEdge('b', 'e');
//		g.addEdge('b', 'f');
//		g.addEdge('f', 'd');
//		g.addEdge('g', 'f');
//		g.addEdge('g', 'c');
//		g.addEdge('g', 'e');		
//		g.addEdge('k', 'j');
//		g.addEdge('l', 'm');
//		g.addEdge('k', 'm');
//		g.addEdge('h', 'i');
//		
//		g.printShortestPath('a', 'f');
//		g.printConnectedComponents();
//		
//		g.addEdge('a', 'b');
//		g.addEdge('b', 'c');
//		g.addEdge('c', 'd');
//		g.addEdge('d', 'e');
//		g.addEdge('e', 'b');
//		g.addEdge('e', 'a');
//		g.addEdge('a', 'f');
		g.addEdge('a', 'b');
		g.addEdge('a', 'd');
		g.addEdge('a', 'c');
		g.addEdge('c', 'f');
		g.addEdge('d', 'e');
		g.DFS(0);	
		System.out.println(' ');
		g.BFS(0);
	}
}
