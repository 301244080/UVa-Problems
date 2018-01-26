import java.io.*;
import java.util.*;

public class Main {
	
	static int sort(int length,String[] input ){
		//int[] index = new int[length];

//		for(int i=0; i< length; i++){
//			input[i]
//		}
		String [] temp = new String[length];
		for(int index=0;index < length; index++)
		{
			temp[index]=input[index];
		}
		Arrays.sort(input);
		
		for(int index=0;index < length; index++)
		{
			if(temp[index]==input[0])
			{
				return index;
			}
		}
		return 0;
	}
	static String[] makeSuffixArray(int length, String input)
	{
//		StringBuilder st = new StringBuilder(input);
		String[] suffixArray = new String[length];
		for (int i = 0; i < length; i++)
		{
//			if(st.substring(i,length)!=null){
//				
//			}
//			suffixArray[i] = st.substring(i,length)+st.substring(0,i);
			suffixArray[i]= new String[input,i];
		}
		
		
		return null;
	}
	static void solve() throws NumberFormatException, IOException
	{
		StringBuilder output = new StringBuilder();
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		//Scanner scanner = new Scanner(new FileReader("test.txt"));
		//int numberOfCase = Integer.parseInt(reader.readLine());
		int numberOfCase = scanner.nextInt();
	
		while(numberOfCase-->0)
		{	
			
	
	        //String line = reader.readLine();
	        //String[] data=line.split(" ");
	        //System.out.println(data[1]);
	        //length = Integer.parseInt(data[0]);
	        if(scanner.hasNextInt())
	        {
	        	int length = scanner.nextInt();
	        	String[] input = new String[length];
	        	if(scanner.hasNext())
	        	{
	        		String temp=scanner.next();
	        		input=makeSuffixArray(length,temp);
	        		//int result=sort(length,input);
	        		//output.append(result).append("\n");
	        	}
	        }
			
			
//	        String temp=data[1];
//	        
			
			

		}
		 //String result2 = output.toString();
	     //System.out.println(result2);
		//scanner.close();
	}
	public static void main(String[] args) throws IOException {
		solve();
	}
}
