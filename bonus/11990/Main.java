import java.util.*;  
  
public class Main {  
    static int n,m;  
    static long ans;  
    static int[] orgArr=new int[200005];  
    static int[] bb=new int[200005];  
    static int[] cc=new int[100005];  
    static int[] dd=new int[100005];  
    static int[] mm=new int[200005];  
    static int[] bit=new int[200005];  
    static Pair[] paris=new Pair[100005];  
    static void add(int i,int x)  
    {  
        for(;i<=n;i+=i&-i)  
            bit[i]+=x;  
    }  
    static int sum(int i)  
    {  
        int res=0;  
        for(;i>0;i-=i&-i)  
            res+=bit[i];  
        return res;  
    }  
    static void cdq(int l,int r)  
    {  
        if(l==r) return;  
        int mid=(l+r)/2;  
        cdq(l,mid);  
        for(int i=l;i<=r;i++)  
            paris[i]=new Pair(cc[i],mm[cc[i]],i);  
        work(l,r);  
        for(int i=l;i<=r;i++)  
            paris[i]=new Pair(mm[cc[i]],cc[i],i);  
        work(l,r);  
        cdq(mid+1,r);  
    }  
    static void work(int l,int r)  
    {  
        int mid=(l+r)/2;  
        Arrays.sort(paris,l,r+1);  
        for(int i=l;i<=r;i++)  
        {  
            if(paris[i].z<=mid)  
                add(paris[i].y,1);  
            else  
                dd[paris[i].z]+=sum(paris[i].y);  
        }  
        for(int i=l;i<=r;i++)  
            if(paris[i].z<=mid) add(paris[i].y,-1);  
    }  
    static class Pair implements Comparable<Pair>  
    {  
        int x,y,z;  
        Pair(int a,int b,int c)  
        {  
            x=a;
            y=b;
            z=c;  
        }  
        public int compareTo(Pair p) {  
            return p.x-x;  
        }  
    }  
  
    public static void main(String[] args) {  
        Scanner in=new Scanner(System.in);  
        while(in.hasNext())  
        {  
            n=in.nextInt();m=in.nextInt();  
            for(int i=1;i<=n;i++)  
            {  
                orgArr[i]=in.nextInt();  
                mm[orgArr[i]]=i;  
            }  
            ans=0;  
            for(int i=n;i>0;i--)  
            {  
                bb[i]=sum(orgArr[i]);  
                ans+=bb[i];  
                bb[i]+=i+bb[i]-orgArr[i];  
                add(orgArr[i],1);  
            }  
            Arrays.fill(bit,0);  
            for(int i=1;i<=m;i++)  
                cc[i]=in.nextInt();  
            Arrays.fill(dd,0);  
            cdq(1,m);  
            for(int i=1;i<=m;i++)  
            {  
                System.out.println(ans);  
                ans-=bb[mm[cc[i]]]-dd[i];  
            }  
        }  
    }  
}  

