package unionFind;

public class QuickFind implements UnionFind {
//o(N) union and init, o(1) find
	
	private int[] arr;
	
	public QuickFind(int N)
	{
		arr = new int[N];
		for(int i = 0 ; i < N ; i++)
			arr[i] = i;
	}

	public boolean connected(int p, int q) throws ArrayIndexOutOfBoundsException {
		if(p >= arr.length || q >= arr.length )
			throw new ArrayIndexOutOfBoundsException();
		
		return arr[p] == arr[q];
		
	}

	public void union(int p, int q) {
		for(int i = 0 ; i < arr.length ; i++)
		{
			if( arr[p] == arr[i])
				arr[i] = arr[q];	
		}
		
	}

	
	
}
