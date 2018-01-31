#include <iostream>
#include <cstdlib>
#include <cstring>
#include <cstdio>
#include <string>
#include <sstream>      // std::stringstream, std::stringbuf

using namespace std;

// int dx[] = {1, -1, 0, 0, -1, 1, 1, -1};
// int dy[] = {1, -1, 1, -1, 0, 0, -1, 1};
int  dxy[8][2] = {1,0,0,1,-1,0,0,-1,1,1,1,-1,-1,1,-1,-1};
char text[52][52];
char word[20][52];
int positionX, positionY;
int s[400];


const int maxn = 50 * 8;

struct SuffixArray {
  int s[maxn];      // original char array (the last char must be 0)
  int sa[maxn];     // suffix array
  int rank[maxn];   // rank array. rank[0] = n-1, which is the last char
  int t[maxn], t2[maxn], c[maxn]; // helper arrays
  int n; // number of chars

  // clear the whole suffix array, set all chars back to 0
  void clear() { n = 0; memset(sa, 0, sizeof(sa)); }

  // m is the largest char + 1, need to set s and n before calling it
  void build_sa(int m) {
    int i, *x = t, *y = t2;

    // bucket sort sa
    for(i = 0; i < m; i++) c[i] = 0;
    for(i = 0; i < n; i++) c[x[i] = s[i]]++;
    for(i = 1; i < m; i++) c[i] += c[i-1];
    for(i = n-1; i >= 0; i--) sa[--c[x[i]]] = i;

    // doubling algorithm, k starts from 2^0 to 2^n (2^n > maxlen)
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


};

void testIO(char text[52][52], char word[20][52], int n, int m, int l) {
    // test I/O
    for (int i = 0; i < n; i++){
        for (int j = 0; j < m; j++) {
            printf("%c", text[i][j]);
        }
        printf("\n");
    }

    printf("\n");
    // test I/O
    for (int i = 0; i < l; i++) {
        for (int j = 0; word[i][j]; j++) {
            printf("%c", word[i][j]);
        }
        printf("\n");
    }
}

SuffixArray sa;

void add(int ch) {
    sa.s[sa.n++] = ch;
}

void findInSuffixArray(char *buf, int n, int m, int l) {

}

void solve(int n, int m, int l) {
    char buf[52];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            for (int k = 0; k < 8; k++) {
                buf[0] = text[i][j];
                int xx = i, yy = j, count = 1;
                while (1) {
                    xx += dxy[k][0];
                    yy += dxy[k][1];
                    if (xx < 0 || xx >= n || yy < 0 || yy >= m)
                        break;
                    buf[count++] = text[xx][yy];
                }
                findInSuffixArray(buf, n, m, l);
            }
}




int main(){
    int T, n, m, l;
    freopen("10010.in", "r", stdin);
    scanf("%d", &T);
    // while (!scanf("%d", &T))
    while (T--) {
        getchar();
        scanf("%d%d", &n, &m);

        for (int i = 0; i < n; i++)
            scanf("%s", text[i]);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (text[i][j] >= 'A' && text[i][j] <= 'Z')
                    text[i][j] += 'a' - 'A';

        scanf("%d", &l);

        for (int i = 0; i < l; i++)
            scanf("%s", word[i]);

        for (int i = 0; i < l; i++)
            for (int j = 0; word[i][j]; j++)
                if (word[i][j] >= 'A' && word[i][j] <= 'Z')
                    word[i][j] += 'a' - 'A';

        testIO(text, word, n, m, l);

        solve(n, m, l);

    }
}
