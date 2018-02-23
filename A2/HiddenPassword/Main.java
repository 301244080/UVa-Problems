import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class Main {
	public static final int SIZE = 256;
    static int length;
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

	static int finalResult(int[] suffixArray, int strLen)
	{
		
		CharSequence ans = null;
		int index[]= new int[strLen*2];
		int answer=strLen*2;
		Arrays.fill(index, strLen*3);
		for(int i=0;i<suffixArray.length;i++)
		{
            // for check i is still in original str
			if(strLen*2-suffixArray[i]>=strLen){
                answer=suffixArray[i];
                int j = 1;
                while(i < strLen*2-1)
				{
					if(strLen*2-suffixArray[i+j]>=strLen)
					{
						if(answer>suffixArray[i+j])
						{
							answer=suffixArray[i+j];
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
                    j++;
				}
            }
            // System.out.println(SA[i]);
		}
		return 0;
	}


	public static void main(String[] args) throws IOException {
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
        		int result=finalResult(sa1,length);
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
}