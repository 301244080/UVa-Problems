#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;

// maximum length of cond_not_taken_branch_cost
// 100 lines * 1001 letters + 10
const int maxn = 1001 * 100 + 10;

struct SuffixArray {
  int s[maxn];      // original char array (the last char must be 0)
  int sa[maxn];     // suffix array
  int rank[maxn];   // rank array. rank[0] = n-1, which is the last char
  int height[maxn]; // height array
  int t[maxn], t2[maxn], c[maxn]; // helper arrays
  int n; // number of chars

  // clear the whole suffix array, set all chars back to 0
  void clear() { n = 0; memset(sa, 0, sizeof(sa)); }

  // m为最大字符值加1。调用之前需设置好s和n
  // m is the largest char + 1, need to set s and n before calling it

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
    int i, k = 0;
    for(i = 0; i < n; i++) rank[sa[i]] = i;
    for(i = 0; i < n; i++) {
      if(k) k--;
      int j = sa[rank[i]-1];
      while(s[i+k] == s[j+k]) k++;
      height[rank[i]] = k;
    }
  }
};

const int maxc = 100 + 10; // maximum number of strings
const int maxl = 1000 + 10; // maximum length of one string

SuffixArray sa;
int n;
char word[maxl];
int idx[maxn];
int flag[maxc];

// check if [L,R) is shared by more than 1/2 of DNAs
bool good(int L, int R) {
  memset(flag, 0, sizeof(flag));
  // if(R - L <= n/2) return false;
  int cnt = 0;

  // find how many DNAs are sharing this string
  for(int i = L; i < R; i++) {
    int x = idx[sa.sa[i]];
    if(x != n && !flag[x]) {
      flag[x] = 1; cnt++;
    }
  }
  return cnt > n/2;
}

void print_sub(int L, int R) {
  for(int i = L; i < R; i++)

    // covert 1, 2, 3 back to a, b, c
    printf("%c", sa.s[i] - 1 + 'a');
  printf("\n");
}

bool print_solutions(int len, bool print) {

  int L = 0;
  for(int R = 1; R <= sa.n; R++) {

    // 如果走到底了，或者有一个height的值比这个length小，就判断
    if(R == sa.n || sa.height[R] < len) { // 新开一段
      if(good(L, R)) {
        if(print)
            print_sub(sa.sa[L], sa.sa[L] + len);
        else
            return true;
      }
      L = R;
    }
  }
  return false;
}

void solve(int maxlen) {
  if(!print_solutions(1, false))
    printf("?\n");
  else {
    int L = 1, R = maxlen, M;

    // binary search for the length
    while(L < R) {
      M = L + (R-L+1)/2;

      // if we can't find the length that satisfies the codition, try a shorter length by increasing the index of the height array
      if(print_solutions(M, false))
          L = M;

      // other wise try longer length by decreasing the index of height array
      else
          R = M-1;
    }
    print_solutions(L, true);
  }
}

// add a character to s[] and idx[]
void add(int ch, int i) {
  idx[sa.n] = i;
  sa.s[sa.n++] = ch;
}

int main() {
  // freopen("p11107.txt","r",stdin);
  int cases = 0;
  while(scanf("%d", &n) == 1 && n) {

    // print out an empty line between cases
    if(cases++ > 0)
        printf("\n");

    // use maxlen to keep track of the biggest length among all DNAs
    int maxlen = 0;
    sa.clear();

    // start reading one senario
    for(int i = 0; i < n; i++) {
      scanf("%s", word);
      int sz = strlen(word);

      // find the biggest length among all DNAs
      maxlen = max(maxlen, sz);
      for(int j = 0; j < sz; j++)
        // mapping a, b, c to 1, 2, 3
        add(word[j] - 'a' + 1, i);

      // adds end character between words
      add(100 + i, n); // 结束字符
    }

    // adds 0 to the end of the array
    add(0, n);

    // start solving current senario

    // if it only contains one form
    if(n == 1) printf("%s\n", word);
    else {
      sa.build_sa(100 + n);
      sa.build_height();
      solve(maxlen);
    }
  }

  // fclose(stdin);
  return 0;
}
