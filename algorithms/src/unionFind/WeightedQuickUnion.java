package unionFind;

public class WeightedQuickUnion implements UnionFind {
	//in the array each entry represents its parent in a set of trees 
	//an entry whose value is the same as its parent is a root
	// 0 | 1 | 2 | 3
	// --------------    parent of 0 is 1, parent of 1 is 2, 2 and 3 are roots
	// 1 | 2 | 2 | 3     	
	
	/*
	initialization : O(n)
	union : log(n)
	find : log(n)
	 */

private int[] arr;
private int[] weight; //the weight of a root is the number of its descendants
//to avoid having a long tree, always make the subtree with less weight a descendant 
// to the one with bigger weight
	
	public WeightedQuickUnion(int N)
	{
		arr = new int[N];
		weight = new int[N];		
		for(int i = 0 ; i < N ; i++)
		{
			arr[i] = i;
			weight[i] = 1;
	
		}
	}

	private int root(int p)
	{
		while( p != arr[p] )
		{
			arr[p] = arr[arr[p]]; //root compression, to flatten the tree
			//make every node point to its grandparent (halving the path)
			p = arr[p];
		}
		return p;
	}


	public boolean connected(int p, int q)  {
		//if they are connected they have the same root
		return root(q) == root(p);	
	}

	public void union(int p, int q) {
		//union is fast, just gotta make  the root of p equal to the root of q
		int rootP = root(p);
		int rootQ = root(q);
		
		if(rootP == rootQ) return;

		// make the tree with smaller weight a child of the one with the bigger weight
		/*
			with weighting the depth of any node is at most log(n), with n the number of
			items, proof is :
	for a given node x of a tree T1, the depth of x increase by one only when T1 is merged
	into another tree T2 such as weight(T2) >= weight(T1)
	The tree containing x at least doubles in weight
	The weight of the tree containing x can double at most log(n) times

		 */
		if(weight[rootQ] >= weight[rootP])
		{
			weight[rootQ] += weight[rootP];
			arr[rootP] = rootQ;
	
		}
		else
		{
			weight[rootP] += weight[rootQ];
			arr[rootQ] = rootP;
		}
		
		
	}


}
