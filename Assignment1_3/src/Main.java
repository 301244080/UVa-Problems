/*
 * Question 3
 */
import java.io.*;
import java.util.*;
public class Main {
	


static ArrayList<Integer>[] adjList;
static ArrayList<Integer>[] temp;
static int path[]= new int[5005];
static int visited[]=new int[5005];
static int length;


	static boolean dfs(int start, int end, int distance)
	{	
		if(end==start)
		{
			if(distance%2==0)
			{
				int index=end;
				for(int i=0;i<distance/2;i++)
				{
					index=path[index];
				}
				System.out.println("The fleas meet at "+ index+".");
			}
			else
			{
				int index=end;
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
					System.out.println("The fleas jump forever between "+index+" and "+a+".");
			}
			return true;
		}
		for(int i=0;i<adjList[start].size();i++)
		{
			int b = adjList[start].get(i);
			if(visited[b]==0)
			{
				visited[b]=1;
				path[b]=start;
				if(dfs(b,end,distance+1))
				{
					//System.out.println("path"+b);
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
		//int numberOfLoops=1;
		//BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
		//Scanner scanner = new Scanner(new FileReader("test.txt"));
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		while(true)
		{			
			totalNode =scanner.nextInt();

			if(totalNode==0)
			{
				if(scanner.hasNext()){
				totalNode =scanner.nextInt();

				}
				else
				{
//					System.out.println(" ");
//					System.out.println("program end");
					scanner.close();
					break;
				}	
			}
//			System.out.println(" ");
//			System.out.println("This is case number: "+ numberOfLoops);
//			System.out.println(" ");
			adjList = new ArrayList[totalNode+1];
			
			for(int index=1;index <= totalNode; index++)
			{
				adjList[index] = new ArrayList<Integer>();
			}
			
			for(int i = 0; i < totalNode-1; i++)
			{

				int leftInt = scanner.nextInt();
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
				int rightInt = scanner.nextInt();
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

			int NumberOfCase = scanner.nextInt();
			while(NumberOfCase-->0){

				int leftInt2 = scanner.nextInt();
				int rightInt2 = scanner.nextInt();
				length=0;
				Arrays.fill(path, 0);
				Arrays.fill(visited, 0);
				visited[leftInt2]=1;
				dfs(leftInt2,rightInt2,0);
				
				
			}
			//numberOfLoops++;
			
			
			//print out the adjacent list 
//			for(int index=1;index <= totalNode; index++)
//			{
//				System.out.println("node "+index+adjList[index]);
//			}
			
		}
	
		return;
	}
}


