#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

const int MAX_N = 100;
const int MAX_W = 450 * 100;

int w[MAX_N + 1];
ll dp[MAX_W / 2 + 1];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    int TC; cin >> TC;
    for (int tc = 0; tc < TC; tc++) {
        if (tc != 0) cout << "\n";

        int N; cin >> N;
        for (int i = 1; i <= N; i++)
            cin >> w[i];

        ll W = accumulate(w + 1, w + N + 1, 0ll);

        memset(dp, 0, sizeof(dp));

        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = W / 2; j >= w[i]; j--) {
                dp[j] |= (dp[j - w[i]] << 1);
            }
        }


        if (N & 1) {
            for (int j = W / 2; j >= 0; j--) {
                ll flag1 = (1ll << (N / 2));
                ll flag2 = (1ll << (N / 2 + 1));
                if ((dp[j] & flag1) || (dp[j] & flag2)) {
                    cout << j << " " << W - j << "\n";
                    break;
                }
            }
        }
        else {
            for (int j = W / 2; j >= 0; j--) {
                if (dp[j] & (1ll << (N / 2))) {
                    cout << j << " " << W - j << "\n";
                    break;
                }
            }
        }
    }

    return 0;
}
