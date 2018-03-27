

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Constrained_Circular_Permutations {
	static int N, E, M, ans;

	// a represents the value of each position
	static int[] a = new int[14];
	
	// b represents if the value of a position is set or not 
	static boolean[] b = new boolean[14];
	static boolean judge;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StringBuilder output = new StringBuilder();
		//BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader (new FileReader ("C:\\Users\\yy957\\Desktop\\CMPT409\\UVa-Problems\\Main\\inputs\\p2608.txt"));
		
		//N = Integer.parseInt(br.readLine());	
		N = scanner.nextInt();
		while (N-- > 0) {
			//String s[] = br.readLine().split(" ");
//			E = Integer.parseInt(s[0]);
//			M = Integer.parseInt(s[1]);
			E= scanner.nextInt();
			M= scanner.nextInt();
			String res=solve();
			output.append(res);
			
			if (N > 0)
			{
//				//System.out.println("\n");
			output.append("\n"+"\n");
				
			}
		}
		String result2 = output.toString();
		result2= result2.substring(0, result2.length());
		System.out.println(result2);
		scanner.close();
		return;
	}
	
	static String solve () {
		ans = 0;
		Arrays.fill(b, false);
		
		// Always set 1 to the first position
		a[1] = 1;
		b[1] = true;
		
		for (int i = 2; i <= E - 1; i++)
			for (int j = i + 1; j <= E; j++)
				if (i + j + 1 <= M) {
					judge = true;
					a[E] = i;
					a[2] = j;
					b[i] = b[j] = true;
					
					DFS(3);
					
					// back-tracking
					b[i] = b[j] = false;
					
				}
		
		return "Permutation size:    " + E+"\n"+"Maximum triplet sum: " + M+"\n"+"Valid permutations:  " + ans;
//		System.out.println("Maximum triplet sum: " + M);
//		System.out.println("Valid permutations:  " + ans);
	}
	
	static void DFS (int curt) {
		
		if (curt == E) {
			if (a[curt] + a[curt - 1] + a[curt - 2] <= M)
				ans++;
			return;				
		}
		
		for (int i = 2; i <= E; i++) {
			if (!b[i]) {
				if (i + a[curt - 1] + a[curt - 2] <= M) {
					b[i] = true;
					a[curt] = i;
					DFS(curt + 1);
					
					// back-tracking
					b[i] = false;
				}
			}
		}
	}

}

