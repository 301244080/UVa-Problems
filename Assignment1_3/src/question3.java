/*
 * Question 3
 */
import java.io.*;
import java.util.*;
public class question3 {
	


static ArrayList<Integer>[] adjList;
static ArrayList<Integer>[] temp;
static int path[]= new int[5005];
static int visited[]=new int[5005];
static int length;


	static boolean dfs(int left, int right, int distance)
	{
		
		if(right==left)
		{
			if(distance%2==0)
			{
				int index=right;
				for(int i=0;i<distance/2;i++)
				{
					index=path[index];
				}
				System.out.println("The fleas meet at "+ index);
			}
			else
			{
				int index=right;
				int a;
				for(int i=0;i<(distance-1)/2;i++)
				{
					index=path[index];
				}
				a=path[index];
				if(index>a)
					{
						int temp=index;
						index=a;
						a=temp;
					}
					System.out.println("The fleas jump forever between "+index+" and "+a);
			}
			return true;
		}
		for(int i=0;i<adjList[left].size();i++)
		{
			int b = adjList[left].get(i);
			if(visited[b]==0)
			{
				visited[b]=1;
				path[b]=left;
				if(dfs(b,right,distance+1))
				{
					return true;
				}
			}
		}
		
		return false;
		
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException 
	{
		int totalNode;
		int numberOfLoops=0;
		
		BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
		while(true)
		{
			
			totalNode =Integer.parseInt(reader.readLine());
			if(totalNode==0)
			{
				break;
			}
			adjList = new ArrayList[totalNode+1];
			
			for(int index=1;index <= totalNode; index++)
			{
				adjList[index] = new ArrayList<Integer>();
			}
			
			for(int i = 0; i < totalNode-1; i++)
			{
				StringTokenizer st = new StringTokenizer(reader.readLine());
				int leftInt = Integer.parseInt(st.nextToken());
				if(leftInt>=adjList.length)
				{
					temp = new ArrayList[adjList.length];
					for(int index=1;index <= adjList.length-1; index++)
					{
						temp[index] = adjList[index];
					}
					adjList = new ArrayList[leftInt+1];
					for(int index=1;index <= leftInt; index++)
					{
						adjList[index] = new ArrayList<Integer>();
					}
					for(int index=1;index <= temp.length-1; index++)
					{
						adjList[index] = temp[index];
					}
				}
				int rightInt = Integer.parseInt(st.nextToken());
				if(rightInt>=adjList.length)
				{
					temp = new ArrayList[adjList.length];
					for(int index=1;index <= adjList.length-1; index++)
					{
						temp[index] = adjList[index];
					}
					adjList = new ArrayList[rightInt+1];
					for(int index=1;index <= rightInt; index++)
					{
						adjList[index] = new ArrayList<Integer>();
					}
					for(int index=1;index <= temp.length-1; index++)
					{
						adjList[index] = temp[index];
					}
				}
				adjList[leftInt].add(rightInt); 
				adjList[rightInt].add(leftInt);
			}
			
		

			int NumberOfCase = Integer.parseInt(reader.readLine());
			while(NumberOfCase-->0){
				StringTokenizer st = new StringTokenizer(reader.readLine());
				int leftInt2 = Integer.parseInt(st.nextToken());
				int rightInt2 = Integer.parseInt(st.nextToken());
				length=0;
				Arrays.fill(path, 0);
				Arrays.fill(visited, 0);
				//dfs(leftInt2,rightInt2,-1);
				visited[leftInt2]=1;
				dfs(leftInt2,rightInt2,0);
				
//				if(length%2!=0)
//				{
//					System.out.println("The fleas meet at "+ path[length/2]);
//					
//				}
//				else
//				{
//					int a= path[length/2-1];
//					int b= path[length/2];
//					if(a>b)
//					{
//						int temp=a;
//						a=b;
//						b=temp;
//					}
//					System.out.println("The fleas jump forever between "+a+" and "+b);
//				}
			}
			numberOfLoops++;
			
			//print out the adjacent list 
			for(int index=1;index <= totalNode; index++)
			{
				System.out.println(adjList[index]);
			}
			
			//System.out.println(adjList[1].get(0));
		}
//		System.out.println(numberOfLoops);

	}
}


