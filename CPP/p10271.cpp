#include <iostream>
using namespace std;
#include <cstring>
#include <stdio.h>

int c[5005];
int dp[5005][1005];
const int INF = 0x3f3f3f3f;

int main() {
    int T;
    scanf("%d",&T);
    while(T--) {
        int n,k;
        scanf("%d %d",&k,&n);
        k = k + 8;
        for(int i = n; i > 0; i--)
            scanf("%d",&c[i]);
        for(int i = 1; i <= n; i++) {
            dp[i][0] = 0;
            for(int j = 1; j <= n; j++){
                dp[i][j] = INF;
            }
        }
        for(int i = 3;i <= n; i++) {
            for(int j = 1; j <= k; j++) {
                if(i >= j * 3 && dp[i - 2][j - 1] != INF)
                    dp[i][j] = min(dp[i - 1][j],dp[i - 2][j - 1] + (c[i] - c[i - 1])*(c[i] - c[i - 1]));
            }
        }
        printf("%d\n",dp[n][k]);
    }
    return 0;
}
