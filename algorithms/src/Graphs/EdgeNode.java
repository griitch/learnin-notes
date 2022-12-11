package Graphs;

import java.util.HashSet;
import java.util.Set;

public class EdgeNode {
	public int i; //index in the array
	public Set<EdgeNode> neighbors;
	public char letter;
	
	public EdgeNode(int i,char l) {
		super();
		this.i = i;
		this.letter = l;
		neighbors = new HashSet<EdgeNode>();
	}
	
}
