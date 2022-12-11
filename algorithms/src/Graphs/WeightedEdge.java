package Graphs;


public class WeightedEdge {
	
	public int adj; // index of adjacent node
	public int weight;
	public int from;
	public char fromLetter;
	public char adjLetter;
	
	public WeightedEdge(int from,int adj,int weight) {
		this.adj = adj;	
		this.weight = weight;
		this.from = from;
		this.fromLetter = (char) ('a' + from);
		this.adjLetter = (char) ('a' + adj);
	}

	@Override
	public String toString() {
		return from + " -> " + adj + " weight : " + weight;
	}
	
	
	
}
