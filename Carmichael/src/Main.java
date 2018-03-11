
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Scanner;



public class Main {
	
	static int modfunction(long a, int n,int mod)
	{
		long result = 1;
		while(n>0)
		{
			if((n&1)==1)//bitwise and
			{
				result=result*a%mod;
			}
				a=a*a%mod;
			
			
			n>>=1;//bit manipulation is equal to divide by 2 to lower, but the number of loops is upper.
		}
		
		return (int)result;
	}
	static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
}
	static void readFile() throws IOException
	{
		  
		StringBuilder output = new StringBuilder();
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		//Scanner scanner = new Scanner(new FileReader("test.txt"));
		while(true)
		{
			if(scanner.hasNextInt())
			{
				
			boolean isNormal=false;
			int input=scanner.nextInt();
			if(input==0)
			{
				break;
			}
			if(isPrime(input))
			{
				isNormal=true;
			}
			else
			{
				for(int i=2;i<input;i++)		
				{
					if(modfunction(i,input,input)!=i)
					{
						//System.out.println(modfunction(i,input,input)+"   i is "+i);
						isNormal=true;
						break;
					}
				}
			}
			if(isNormal)
			{
				output.append(input+" is normal.").append("\n");
				//System.out.println(input+" is normal.");
			}
			else
			{
				output.append("The number "+input+" is a Carmichael number.").append("\n");
				//System.out.println("The number "+input+" is a Carmichael number.");
			}
			//System.out.println(input);
			
			}
			else{
				break;
			}
		}
		
		
		String result2 = output.toString();
		result2= result2.substring(0, result2.length()-1);
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
