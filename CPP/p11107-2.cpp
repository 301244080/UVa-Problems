#include<cstdio>
#include<cstring>
#include<cmath>
#include<cstdlib>
#include<iostream>
#include<algorithm>
#include<vector>
#include<map>
#include<queue>
#include<stack>
#include<string>
#include<map>
#include<set>
#define eps 1e-6
#define LL long long
using namespace std;

const int maxc = 100 + 50;
const int maxl = 1000 + 100;
const int maxn = 101 * 1000 + 100;
char word[maxl];
int n, kase;

struct SuffixArray {

    int s[maxn];      /// 原始字符数组（最后一个字符应必须是0，而前面的字符必须非0）
    int sa[maxn];     // 后缀数组,sa[0]一定是n-1，即最后一个字符
    int rank[maxn];   // 名次数组
    int height[maxn]; // height数组
    int t[maxn], t2[maxn], c[maxn]; // 辅助数组
    int n; // 字符个数

    void clear() { n = 0; memset(sa, 0, sizeof(sa)); }

    /// m为最大字符值加1。!!! 调用之前需设置好s和n
    void build_sa(int m) {
       int i, *x = t, *y = t2;
        for(i = 0; i < m; i++) c[i] = 0;
        for(i = 0; i < n; i++) c[x[i] = s[i]]++;
        for(i = 1; i < m; i++) c[i] += c[i-1];
        for(i = n-1; i >= 0; i--) sa[--c[x[i]]] = i;
        for(int k = 1; k <= n; k <<= 1) {
            int p = 0;
            for(i = n-k; i < n; i++) y[p++] = i;
            for(i = 0; i < n; i++) if(sa[i] >= k) y[p++] = sa[i]-k;
            for(i = 0; i < m; i++) c[i] = 0;
            for(i = 0; i < n; i++) c[x[y[i]]]++;
            for(i = 0; i < m; i++) c[i] += c[i-1];
            for(i = n-1; i >= 0; i--) sa[--c[x[y[i]]]] = y[i];
            swap(x, y);
            p = 1; x[sa[0]] = 0;
            for(i = 1; i < n; i++)
                x[sa[i]] = y[sa[i-1]]==y[sa[i]] && y[sa[i-1]+k]==y[sa[i]+k] ? p-1 : p++;
            if(p >= n) break;
            m = p;
        }
    }

    void build_height() {
        int i, j, k = 0;
        for(i = 0; i < n; i++) rank[sa[i]] = i;
        for(i = 0; i < n; i++) {
            if(k) k--;
            j = sa[rank[i]-1];
            while(s[i+k] == s[j+k]) k++;
            height[rank[i]] = k;
        }
    }
} sa;

int idx[maxn];
void add_char(int i, int x) {  //把i添加到s末尾，属于第x组
    idx[sa.n] = x;
    sa.s[sa.n++] = i;
}

int flag[maxc];      //flag数组表示第i个字符串是否含有当前枚举的后缀
bool check(int M) {
    int L = 0;
    for(int i = 1; i <= sa.n; i++) {
        if(i == sa.n || sa.height[i] < M) {
            if(i-L > n/2) {
                memset(flag, 0, sizeof(flag));
                int cnt = 0;
                for(int j = L; j < i; j++) {
                    if(idx[sa.sa[j]] != n && !flag[idx[sa.sa[j]]]) {
                        cnt++;
                        flag[idx[sa.sa[j]]] = 1;
                    }
                }
                if(cnt > n/2) return true;
            }
            L = i;
        }
    }
    return false;
}

void print_sub(int L, int R) {  //打印指定位置子串 ,左闭右开
    for(int i = L; i < R; i++) printf("%c", sa.s[i]+'a'-1);
    puts("");
}
void print_ans(int M) {  //打印答案
    if(!M) {
        puts("?"); return ;
    }
    int L = 0;
    for(int i = 1; i <= sa.n; i++) {
        if(i == sa.n || sa.height[i] < M) {
            if(i-L > n/2) {
                memset(flag, 0, sizeof(flag));
                int cnt = 0;
                for(int j = L; j < i; j++) {
                    if(idx[sa.sa[j]] != n && !flag[idx[sa.sa[j]]]) {
                        cnt++;
                        flag[idx[sa.sa[j]]] = 1;
                    }
                }
                if(cnt > n/2) print_sub(sa.sa[L], sa.sa[L]+M);            //只有这里与check不同
            }
            L = i;
        }
    }
}

int m;
void init() {
    sa.clear();
    m = 30;
    for(int i = 0; i < n; i++) {
        cin >> word;
        int len = strlen(word);
        for(int j = 0; j < len; j++) add_char(word[j]-'a'+1, i);
        add_char(m+i, n);
    }
    add_char(0, n);
}

void solve() {
    if(kase++) puts("");
    sa.build_sa(m+n);
    sa.build_height();
    int L = 0, R = 1000;
    while(L <= R) {
        int M = (L+R) >> 1;
        if(check(M)) L = M + 1;
        else R = M - 1;
    }
//  cout << R << endl;
    print_ans(R);
}

int main() {
//  freopen("input.txt", "r", stdin);
    while(cin >> n && n) {
        init();
        solve();
    }
    return 0;
}  
