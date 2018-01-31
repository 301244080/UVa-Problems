import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;



public class PreFix{
	class Suffix {
	 int index;
	 String str;
	 Suffix(int i, String s) {
	  index = i;
	  str = s;
	 }
	}
	public int smallestRotationIndex(String s, int n) {
	 s += s;
	 Suffix[] suff = new Suffix[n];
	 for (int i = 0; i < n; i++) {
	  suff[i] = new Suffix(i, s.substring(i, i + n));
		}
	 Arrays.sort(suff, (a, b) -> a.str.compareTo(b.str));
	 return suff[0].index;
	}
	public static void main(String[] args) {
	 PreFix p = new PreFix();
	 Scanner in = new Scanner(System.in);
	 int n = in.nextInt();
	 for (int i = 0; i < n; i++) {
	  int len = in.nextInt();
	  String s = in.next();
	  System.out.println(p.smallestRotationIndex(s, len));
	 }
		   in.close();  
	}
}