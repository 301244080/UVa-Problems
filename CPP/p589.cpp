#include <iostream>
#include <stdio.h>
#include <string.h>
#include <vector>
#include <string>
#include <queue>
#include <map>
#define maxm 0x3f3f3f3f

using namespace std;

const int d[4][2] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
const int N = 25;
char g[N][N];
int r, c, p_num;
const char s1[10] = "NSEW";
const char s2[10] = "nsew";
string sol;
map<int, bool> vis[20][20][20][20];
typedef pair<int, int> piar;
piar s, b, t;

struct State {
    int px, py, bx, by, num;
    string path;

    // default constructer
    State() {}
    State(int _px, int _py, int _bx, int _by, int _num) {
        px = _px; py = _py; bx = _bx; by = _by; num = _num;
        path = "";
    }
    friend bool operator < (State a, State b) {
        if (a.num != b.num)
            return a.num > b.num;
        return a.path.length() > b.path.length();
    }
};

bool isGood(int x, int y) {
    if (x < 0 || x >= r || y < 0 || y >= c || g[x][y] != '.')
        return false;
    return true;
}

void init() {
    sol = "";
    p_num = maxm;
    for (int i = 0; i < r; i++) {
        scanf("%s", g[i]);
        for (int j = 0; j < c; j++) {
            if (g[i][j] == 'S')
                s = make_pair(i, j);
            else if (g[i][j] == 'B')
                b = make_pair(i, j);
            else if (g[i][j] == 'T')
                t = make_pair(i, j);
            if (g[i][j] != '#')
                g[i][j] = '.';
            for (int k = 0; k < r; k++)
                for (int l = 0; l < c; l++)
                    vis[i][j][k][l].clear();
        }
    }
}

void solve() {
    priority_queue<State> Q;
    vis[s.first][s.second][b.first][b.second][0] = 1;
    Q.push(State(s.first, s.second, b.first, b.second, 0));
    while (!Q.empty()) {
        State p = Q.top();
        Q.pop();
        if (p.bx == t.first && p.by == t.second) {
            if (p_num > p.num) {
                p_num = p.num;
                sol = p.path;
            } else if (p_num == p.num) {
                if (sol.length() > p.path.length())
                    sol = p.path;
            }
        else
            break;
        continue;
        }
        for (int i = 0; i < 4; i++) {
            State q = p;
            q.px += d[i][0];
            q.py += d[i][1];
            if (!isGood(q.px, q.py)) continue;
            q.path += s2[i];
            if (q.px == q.bx && q.py == q.by) {
                q.bx += d[i][0], q.by += d[i][1];
                if (!isGood(q.bx, q.by)) continue;
                q.num++;
                if (q.num > p_num) continue;
                q.path[q.path.length() - 1] = s1[i];
            }
            if (vis[q.px][q.py][q.bx][q.by][sol.length()]) continue;
            vis[q.px][q.py][q.bx][q.by][sol.length()] = true;
            Q.push(q);
        }
    }
}

int main() {
    int cas = 0;
    while (~scanf("%d%d", &r, &c) && r || c) {
        init();
        printf("Maze #%d\n", ++cas);
        solve();
        if (sol.length())
            cout << sol << endl;
        else
            printf("Impossible.\n");
        printf("\n");
    }
    return 0;
}
