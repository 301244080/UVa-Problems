import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Main {
	static final int INF = (int)1e8;
	static int subCityNumber[]= new int[201];
	static ArrayList<Integer>[] adjList;
	static int[] cost,total;
	static int[][] dp;
	
	
	static int dfs(int index)
	{
		total[index]=1;
		for(int i=0;i<adjList[index].size();i++)
		{
			
			//temp city
			int temp=adjList[index].get(i);
			total[index]=total[index]+dfs(temp);
			//System.out.println(total[index]);
		}
		
		dp[index][0]=0;
		dp[index][1]=cost[index];
		if(index>0)
		{
			for(int i=1;i<=total[index];i++)
			{
				dp[index][i]=cost[index];
			}
		}
		for(int i=0;i<adjList[index].size();i++)
		{
			int temp=adjList[index].get(i);
			for(int j=total[index];j>=1;j--)
			{
				for(int k=1;k<=total[temp]&&k<=j;k++)
				{
				
					
					dp[index][j]=Math.min(dp[index][j],dp[index][j-k]+dp[temp][k]);
				}
			}
			
		}
		return total[index];
	}
	
	static void readFile() throws IOException
	{
		  
		StringBuilder output = new StringBuilder();
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		//Scanner scanner = new Scanner(new FileReader("test.txt"));
		while(true)
		{
			if(scanner.hasNextLine())
			{
			String firstLine = scanner.nextLine();
			if(firstLine.equals("#"))
			{
				break;
			}
			
			TreeMap<String,Integer> tree=new TreeMap<String,Integer>();
			
			StringTokenizer st = new StringTokenizer(firstLine);
			int numberOfCity=0,numberOfVote=0;
			if(st.hasMoreTokens())
			{
				numberOfCity = Integer.parseInt(st.nextToken());
			}
			
			//numberOfCity = scanner.nextInt();
			cost=new int[numberOfCity+1];
			if(st.hasMoreTokens())
			{
				numberOfVote = Integer.parseInt(st.nextToken());
			}
			
			//numberOfVote = scanner.nextInt();
			
			adjList = new ArrayList[numberOfCity+1];
			String[][] city = new String[numberOfCity+1][];
			boolean[] parent=new boolean[numberOfCity+1];
			Arrays.fill(parent,false);
			//adjList[0] to record city which has no parent
			adjList[0] = new ArrayList<Integer>(numberOfCity+1);
			for(int i=1;i<=numberOfCity;i++)
			{
				if(scanner.hasNextLine())
				{
					city[i]=scanner.nextLine().split(" ");
				}
				cost[i]=Integer.parseInt(city[i][1]);
				//give each name a map number
				tree.put(city[i][0],i );
				adjList[i] = new ArrayList<Integer>(city[i].length - 2);
				
				//System.out.println(tree);
			}
			for(int i=1;i<=numberOfCity;i++)
			{
				
				for(int j=2;j<city[i].length;j++)
				{
					adjList[i].add(tree.get(city[i][j]));
					parent[tree.get(city[i][j])]=true;
				}
				
			}
			for(int i=1;i<=numberOfCity;i++)
			{
				if(parent[i]==false)
				{
					adjList[0].add(i);
				}
			}
			dp = new int[205][205];
			for(int index=0;index <= numberOfCity; index++)
			{
				
				Arrays.fill(dp[index],INF);
			}
			int cost2= INF;
			total=new int[numberOfCity+1];
			dfs(0);
			for(int i=numberOfVote+1;i<=numberOfCity+1;i++)
			{
				cost2=Math.min(cost2,dp[0][i]);
			}
			output.append(cost2).append("\n");
//			///output testing
//			for(int index=0;index <= numberOfCity; index++)
//			{
//				System.out.println("city "+index+adjList[index]);
//				System.out.println("parent "+parent[index]);
//			}
//			
//			//System.out.println(Arrays.deepToString(dp));
			System.out.println(cost2);
			}
		}
		
		
		String result2 = output.toString();
		//result2= result2.substring(0, result2.length()-1);
		//System.out.println(result2);
		scanner.close();
	}
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		
		long start = System.currentTimeMillis();
		readFile();
		
		long end = System.currentTimeMillis();

		NumberFormat formatter = new DecimalFormat("#0.00000");
		//System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		return;

		
	}
}
