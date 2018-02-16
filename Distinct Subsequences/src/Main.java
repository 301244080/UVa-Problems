import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
	public static String solve(String X,String Z)
	{
		//Since this is the DP problem, so we used Biginteger to allocate more memory.
		BigInteger dp[][]=new BigInteger [101][10001];
		int lengthX=X.length();
		int lengthZ=Z.length();
		
		//initial dp[i][j] to zero, allocate all the memory
		for(int i=0;i<lengthZ;i++)
		{
			for(int j=0;j<lengthX;j++)
			{
				dp[i][j]=BigInteger.ZERO;
			}
		}
		//if the first element in z equals to the first element in x, let the dp[0][0] equals to 1
		if(X.charAt(0)==Z.charAt(0))
		{
			dp[0][0]=BigInteger.ONE;
		}
		//compare the first element in Z to all X, and if equal then add 1 else not.
		for(int i=1;i<lengthX;i++)
		{
			if(Z.charAt(0)==X.charAt(i))
			{
				dp[0][i]=dp[0][i-1].add(BigInteger.ONE);
			}
			else
			{
				dp[0][i]=dp[0][i-1];
			}
		}
		//compare rest of the elements, if Z(i)=X(j) and dp[i-1][j-1] !=0, then dp[i][j]=dp[i-1][j-1]+dp[i][j-1], else dp[i][j]=dp[i][j-1]
		for(int i=1;i<lengthZ;i++)
		{
			for(int j=1;j<lengthX;j++)
			{
				if(Z.charAt(i)==X.charAt(j)&&(dp[i-1][j-1]!=BigInteger.ZERO))
				{
					dp[i][j]=dp[i-1][j-1].add(dp[i][j-1]);
				}
				else
				{
					dp[i][j]=dp[i][j-1];
				}
						
			}
			
		}
		//print the dp 
//		for(int i=0;i<lengthZ;i++)
//		{
//			for(int j=0;j<lengthX;j++)
//			{
//				System.out.print(dp[i][j]);
//						
//			}
//			System.out.println(" ");
//		}
//		System.out.println("--------");
		return dp[lengthZ-1][lengthX-1].toString();
	}
	
	static void readFile() throws IOException
	{
		  
		StringBuilder output = new StringBuilder();
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		//Scanner scanner = new Scanner(new FileReader("test.txt"));
		while(true)
		{
			int numberOfCase = scanner.nextInt();
			while(numberOfCase-->0)
			{
				String result;
				String X = scanner.next();//long string
				String Z = scanner.next();//short string
				//System.out.println(X);
				//System.out.println(Z);
				result=solve(X,Z);
				if(numberOfCase==0)
        		{
        			output.append(result);
        		}
        		else
        		{
        			output.append(result).append("\n");
        		}
				
			}
			break;
		}
		String result2 = output.toString();
		System.out.println(result2);
		
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
