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
int s[2502];
char buf[2502];
int row[52];
int res[52][2];


const int maxn = 2502;

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

bool invalid(int xx, int yy, int m, int n) {
    if (xx < 0 || xx >= n || yy < 0 || yy >= m)
        return true;
    return false;
}

int cmp_suffix(char *pattern, int p, int m) {
    int res = strncmp(pattern, buf + sa.sa[p], m);
    // printf("checking string diff: %s and %s, p is: %d, res is: %d\n", pattern, buf + sa.sa[p], p, res);
    return res;
}

void findxy(int n, int m, int k, int wordnum) {
    printf("k: %d, n: %d, m: %d\n", k, n, m);
    int diff = k % (m-1);
    k -= diff;
    printf("k is changed to: %d\n", k);
    int y = k%m;
    int x = (k - y) / m;
    printf("Candidate: %d %d\n", x, y);
    printf("res[%d]: %d %d\n", wordnum, res[wordnum][0], res[wordnum][1]);
    if (x < res[wordnum][0]) {
        printf("Updating solution: %d %d\n", x, y);
        res[wordnum][0] = x;
        res[wordnum][1] = y;
    }
    else if (x == res[wordnum][0])
        if (y <= res[wordnum][1]) {
            printf("Updating solution: %d %d\n", x, y);
            res[wordnum][0] = x;
            res[wordnum][1] = y;
        }
}

//在 Suffix Array 数组的(lb, ub) 区间寻找字符串 pattern
void find(char* pattern, int lb, int ub, int wordnum, int n, int m) {
        int len = strlen(pattern);
        // printf("m is: %d\n", m);
        // printf("%s\n", "checking the lowerst string");
        if(cmp_suffix(pattern, lb, m) < 0) return; //如果当前最小的字典序都大于模版串的字典序，肯定无法匹配

        // printf("%s\n", "checking the highest string");
        if(cmp_suffix(pattern, ub, m) > 0) return; //如果当前最大的字典序都小于模版串的字典序，肯定无法匹配
        printf("%s\n", "Binary Search starts");
        while(lb <= ub) {
            int mid = lb + (ub - lb) / 2;
            printf("current mid is: %d\n", mid);
            int res = cmp_suffix(pattern, mid, len);
            if(!res) {
                int index = sa.sa[mid];
                printf("Match! Index: %d\n", index);
                findxy(n, m, index, wordnum);
                find(pattern, mid + 1, ub, wordnum, n, m); //(mid, ub) 可能还有匹配
                find(pattern, lb, mid - 1, wordnum, n, m); //(lb, mid) 可能还有匹配
                return;
            }
            if(res < 0) ub = mid - 1; //如果模版串的字典序比后缀 sa[mid] 小，那么解的范围变为 (lb, mid)
            else lb = mid + 1;    //如果模版串的字典序比后缀 sa[mid] 小，那么解的范围变为 (mid, ub)
        }
        printf("%s\n", "no finding!");
}

void solve(int n, int m, int l, int minlen) {

    // case 1
    // ->
    memset(buf, 0, sizeof(buf));
    int count = 0;
    sa.clear();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            char c = text[i][j];
            buf[count++] = c;
            printf("buf[%d]: %c\n", count - 1, buf[count-1]);
            add(c - 'a' + 1);
        }
        buf[count++] = '~';
        printf("buf[%d]: %c\n", count - 1, buf[count-1]);
        add('~' - 'a' + 1);
    }
    add(0);
    printf("\n");
    sa.build_sa(80);
    for (int i = 0; i < sa.n; i++) {
        printf("%d ",sa.s[i]);
    }
    printf("\n");
    printf("%s\n", "Printing SA...");
    for (int i = 0; i <= count; i++) {
        printf("sa[%d]: %d, buf[%d] is: %c\n", i, sa.sa[i], sa.sa[i], buf[sa.sa[i]]);
    }
    printf("\n");
    printf("%d\n", l);
    for (int i = 0; i < l; i++) {
        printf("%s\n", word[i]);
        // printf("starting search with count: %d, and word: %s\n", word[i]);
        find(word[i], 1, count, i, n, m);
        printf("\n\n");
    }

    // case 2
    // <-
    memset(buf, 0, sizeof(buf));
    count = 0;
    sa.clear();
    for (int i = 0; i < n; i++) {
        for (int j = m - 1; j >= m; j--) {
            char c = text[i][j];
            buf[count++] = c;
            printf("buf[%d]: %c\n", count - 1, buf[count-1]);
            add(c - 'a' + 1);
        }
        buf[count++] = '~';
        printf("buf[%d]: %c\n", count - 1, buf[count-1]);
        add('~' - 'a' + 1);
    }
    add(0);
    printf("\n");
    sa.build_sa(80);
    for (int i = 0; i < sa.n; i++) {
        printf("%d ",sa.s[i]);
    }
    printf("\n");
    printf("%s\n", "Printing SA...");
    for (int i = 0; i <= count; i++) {
        printf("sa[%d]: %d, buf[%d] is: %c\n", i, sa.sa[i], sa.sa[i], buf[sa.sa[i]]);
    }
    printf("\n");
    printf("%d\n", l);
    for (int i = 0; i < l; i++) {
        printf("%s\n", word[i]);
        // printf("starting search with count: %d, and word: %s\n", word[i]);
        find(word[i], 1, count, i, n, m);
        printf("\n\n");
    }

    // case 4
    // buttom-up
    memset(buf, 0, sizeof(buf));
    count = 0;
    sa.clear();
    for (int i = 0; i < m; i++) {
        for (int j = n - 1; j >= n; j--) {
            char c = text[i][j];
            buf[count++] = c;
            printf("buf[%d]: %c\n", count - 1, buf[count-1]);
            add(c - 'a' + 1);
        }
        buf[count++] = '~';
        printf("buf[%d]: %c\n", count - 1, buf[count-1]);
        add('~' - 'a' + 1);
    }
    add(0);
    printf("\n");
    sa.build_sa(80);
    for (int i = 0; i < sa.n; i++) {
        printf("%d ",sa.s[i]);
    }
    printf("\n");
    printf("%s\n", "Printing SA...");
    for (int i = 0; i <= count; i++) {
        printf("sa[%d]: %d, buf[%d] is: %c\n", i, sa.sa[i], sa.sa[i], buf[sa.sa[i]]);
    }
    printf("\n");
    printf("%d\n", l);
    for (int i = 0; i < l; i++) {
        printf("%s\n", word[i]);
        // printf("starting search with count: %d, and word: %s\n", word[i]);
        find(word[i], 1, count, i, n, m);
        printf("\n\n");
    }

    // case 5
    memset(buf, 0, sizeof(buf));
    count = 0;
    sa.clear();
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            char c = text[i][j];
            buf[count++] = c;
            printf("buf[%d]: %c\n", count - 1, buf[count-1]);
            add(c - 'a' + 1);
        }
        buf[count++] = '~';
        printf("buf[%d]: %c\n", count - 1, buf[count-1]);
        add('~' - 'a' + 1);
    }
    add(0);
    printf("\n");
    sa.build_sa(80);
    for (int i = 0; i < sa.n; i++) {
        printf("%d ",sa.s[i]);
    }
    printf("\n");
    printf("%s\n", "Printing SA...");
    for (int i = 0; i <= count; i++) {
        printf("sa[%d]: %d, buf[%d] is: %c\n", i, sa.sa[i], sa.sa[i], buf[sa.sa[i]]);
    }
    printf("\n");
    printf("%d\n", l);
    for (int i = 0; i < l; i++) {
        printf("%s\n", word[i]);
        // printf("starting search with count: %d, and word: %s\n", word[i]);
        find(word[i], 1, count, i, n, m);
        printf("\n\n");
    }


}




int main(){
    int T, n, m, l;
    freopen("10010.in", "r", stdin);
    scanf("%d", &T);

    for (int i = 0; i < 52; i++)
        for (int j = 0; j < 2; j++)
            res[i][j] = 52;
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

        int minlen = 52;
        for (int i = 0; i < l; i++) {
            int j;
            for (j = 0; word[i][j]; j++) {
                if (word[i][j] >= 'A' && word[i][j] <= 'Z')
                    word[i][j] += 'a' - 'A';
            }
            if (j < minlen)
                minlen = j;
        }

        printf("Minimum Length: %d\n", minlen);
        testIO(text, word, n, m, l);

        printf("%s\n","start solving problem...");
        solve(n, m, l, minlen);

    }
}
