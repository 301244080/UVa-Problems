#include<cstdlib>
#include<cstdio>
#include<cstring>
#include<iostream>
using namespace std;

int N, m, n;
int l;


int main(){

  freopen("10010.in","r",stdin);
  scanf("%d", &N);
  for (int i = 1; i <= N; i++) {
    scanf("%d%d", &m, &n);
    printf("%d\n%d\n%d\n",N, m, n);
    char grid[m+1][n+1];
    char temp;
    scanf("%c",&temp);
    for (int j = 1; j <= m; j++) {
      for (int k = 1; k <= n; k++) {
        scanf("%c", &grid[j][k]);
      }
      scanf("%c",&temp);
    }

    // test I/O
    for (int a = 1; a <= m; a++) {
      for (int b = 1; b <= n; b++) {
        printf("%c", grid[a][b]);
      }
      printf("\n");
    }

    scanf("%d", l);
    printf("%d\n", l);

  }

  fclose(stdin);
  fclose(stdout);
  return 0;
}
;
