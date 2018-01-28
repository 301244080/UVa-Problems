import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class Main {
	public static final int SIZE = 256;
	static int length;
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
	static String[] makeSuffixArray(int length, String input){
		
		String[] suffixArray = new String[length];
		for (int i = 0; i < length; i++)
		{
			if(input.substring(i,length)!=null){
				
		}
			//suffixArray[i] = st.substring(i,length);
			//suffixArray[i] = input.substring(i,length)+input.substring(0,i);
			//suffixArray[i]= new String[input,i];
		}
		
		
		return suffixArray;
	}
	
	public static String lcp(String str1, String str2){
        int lenSize = Math.min(str1.length(),str2.length());
        for (int i = 0; i < lenSize; i++) {
            if (str1.charAt(i) != str2.charAt(i))
                return str1.substring(0, i);
        }
        return str1.substring(0,lenSize);
    }
	static int finalResult(int[] SA, int length, CharSequence temp)
	{
		
		CharSequence ans = null;
		int index[]= new int[length*2];
		int answer=length*2;
		Arrays.fill(index, length*3);
		for(int a=0;a<SA.length;a++)
		{
			if(length*2-SA[a]>=length){
				//ans=temp.subSequence(SA[a], length*2).subSequence(0, length);
				//ans=temp.subSequence(SA[a], length+SA[a]);
				//System.out.println(ans);
				//System.out.println(SA[a]);
				//return SA[a];
				answer=SA[a];
				for(int i=1;1<length*2-a;i++)
				{
					if(length*2-SA[a+i]>=length)
					{
						if(answer>SA[a+i])
						{
							answer=SA[a+i];
						}
						else
						{
							return answer;
						}
					}
					else
					{
						return answer;
					}
					
				}
				
			}
			
			//System.out.println(temp.substring(sa1[a]));
		}
//		for(int a=SA.length-1;a>0;a--)
//		{
//			if(length*2-SA[a]>=length)
//			{
//				CharSequence compare=temp.subSequence(SA[a], length+SA[a]);
////				if(temp.subSequence(SA[a], length+SA[a]).equals(ans))
////				{
////					return SA[a];
////					//break;
////					//System.out.println(index[a]);
////				}
////				for(int b=SA[a];b<length+SA[a];b++)
////				{
////					
////				}
//			}
//				
//		}
//		for(int a=0;a<SA.length;a++)
//		{
//			if(length-SA[a]+1>=length){
//				ans=temp.subSequence(SA[a], length*2).subSequence(0, length);
//				System.out.println(ans);
//				if(length-SA[a+1]+1>=length)
//				//return SA[a];
//				break;
//			}
//			
//			//System.out.println(temp.substring(sa1[a]));
//		}
		//Arrays.sort(index);
		//System.out.println(index[0]);
		
		return 0;
	}

	public static int[] suffixArray(CharSequence S) 
	{
	    int n = S.length();
	    Integer[] order = new Integer[n];
	    for (int i = 0; i < n; i++)
	      order[i] = n - 1 - i;

	    // stable sort of characters
	    Arrays.sort(order, (a, b) -> Character.compare(S.charAt(a), S.charAt(b)));

	    int[] sa = new int[n];
	    int[] classes = new int[n];
	    for (int i = 0; i < n; i++) {
	      sa[i] = order[i];
	      classes[i] = S.charAt(i);
	    }
	    // sa[i] - suffix on i'th position after sorting by first len characters
	    // classes[i] - equivalence class of the i'th suffix after sorting by first len characters

	    for (int len = 1; len < n; len *= 2) {
	      int[] c = classes.clone();
	      for (int i = 0; i < n; i++) {
	        // condition sa[i - 1] + len < n simulates 0-symbol at the end of the string
	        // a separate class is created for each suffix followed by simulated 0-symbol
	        classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && sa[i - 1] + len < n && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
	      }
	      // Suffixes are already sorted by first len characters
	      // Now sort suffixes by first len * 2 characters
	      int[] cnt = new int[n];
	      for (int i = 0; i < n; i++)
	        cnt[i] = i;
	      int[] s = sa.clone();
	      for (int i = 0; i < n; i++) {
	    	  
	    	  //System.out.println(len);
	        // s[i] - order of suffixes sorted by first len characters
	        // (s[i] - len) - order of suffixes sorted only by second len characters
	        int s1 = s[i] - len;
	        // sort only suffixes of length > len, others are already sorted
	        if (s1 >= 0)
	          sa[cnt[classes[s1]]++] = s1;
	      }
	    }
	    return sa;
	  }
	
	
	
	static void solve() throws IOException
	{
		
		////------using buffereadreader
//		StringBuilder output = new StringBuilder();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
//		int numberOfCase;
//		String abc;
//		while(true){
//		numberOfCase=Integer.parseInt(reader.readLine());
//	
//		while(numberOfCase-->0)
//		{	
////			
////	
//	        String line = reader.readLine();
//	        String[] data=line.split(" ");
//			//StringTokenizer st = new StringTokenizer(reader.readLine());
//
//
//			length = Integer.parseInt(data[0]);
//
//			
//			
//			 System.out.println(length);
//	        		String temp=data[1];
//	        		temp=temp+temp;
//	        		int[] sa1 = suffixArray(temp);
//	        		int result=finalResult(sa1,length, temp);
//	        		
//	        		output.append(result).append("\n");
//		}
//		
//		String result2 = output.toString();
//	    System.out.println(result2);
//		reader.close();
//		break;
//		}

		
		/////-----using scanner
		StringBuilder output = new StringBuilder();
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		while(true)
		{
			int numberOfCase = scanner.nextInt();
			while(numberOfCase-->0)
			{
				int length = scanner.nextInt();
				String temp=scanner.next();
				temp=temp+temp;
				int[] sa1 = suffixArray(temp);
        		int result=finalResult(sa1,length, temp);
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
		//result2= result2.substring(0, result2.length()-1);
		System.out.println(result2);
		
		scanner.close();
		//System.exit(0); 
		 
	}
	public static void main(String[] args) throws IOException {
		
		
		
	
		long start = System.currentTimeMillis();
		solve();
		
		long end = System.currentTimeMillis();

		NumberFormat formatter = new DecimalFormat("#0.00000");
		//System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		return;

		
	}
}
