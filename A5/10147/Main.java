import java.util.*;
import java.io.*;

public class Main {
    	
	static class Town
	{
		int x, y;
		
		Town(int a, int b) { x = a; y = b; }
		
		int distance(Town t) { return (x - t.x)*(x - t.x) + (y - t.y)*(y - t.y); }
	}
	
	static class Edge implements Comparable<Edge>
	{
		int u, v, w;
		
		Edge(int x, int y, int z) { u = x; v = y; w = z; }
		
		public int compareTo(Edge e) { return w - e.w; }
	}
	
	static class UnionFind
	{
		int[] p, rank;
		int sets;
		
		UnionFind(int N)
		{
            sets = N;
			p = new int[sets];
			rank = new int[N];
			for(int i = 0; i < N; ++i)
				p[i] = i;
		}
		
		int findSet(int x) { return p[x] == x ? x : (p[x] = findSet(p[x])); }
		
		boolean union(int x, int y)
		{
			x = findSet(x);
			y = findSet(y);
			
			if(x == y)
				return false;
			if(rank[x] > rank[y])
				p[y] = x;
			else
			{
				p[x] = y;
				if(rank[x] == rank[y])
					++rank[y];
			}
			--sets;
			return true;
		}
	}
			
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
        PrintWriter out = new PrintWriter(System.out);
        
		int caseNum = sc.nextInt();
		while(caseNum-->0)
		{
        
            int townNum = sc.nextInt();
			Town[] towns = new Town[townNum];
			for(int i = 0; i < townNum; ++i)
				towns[i] = new Town(sc.nextInt(), sc.nextInt());
			PriorityQueue<Edge> edgePq = new PriorityQueue<Edge>();
			for(int i = 0, k = 0; i < townNum; ++i)
			{
				Town t = towns[i];
				for(int j = i + 1; j < townNum; ++j, ++k)
					edgePq.offer(new Edge(i, j, t.distance(towns[j])));
			}
        
            int connectTownNum = sc.nextInt();
            UnionFind uf = new UnionFind(townNum);
            
            while(connectTownNum-->0)
				uf.union(sc.nextInt() - 1, sc.nextInt() - 1);
            if(uf.sets == 1) 
                out.println("No new highways need");
			else
			{
				Edge[] ans = new Edge[uf.sets - 1];
				int k = 0;
				while(uf.sets != 1)
				{
					Edge e = edgePq.poll();
					if(uf.union(e.u, e.v))
						ans[k++] = e;
				}
				for(Edge e: ans)
					out.printf("%d %d\n", e.u + 1, e.v + 1);
			}
			if(caseNum != 0)
				out.println();
		}
		out.flush();
		out.close();
	}

}