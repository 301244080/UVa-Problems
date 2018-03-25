#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;

const int maxn = 21;
int map[maxn][maxn];
int visited[maxn];
int saved[maxn];

int DFS(int s, int e, int d, int size)
{
    // total routes
    int sum = 0;
    if (s == e) {
        printf("1");
        for (int i = 1; i < d; i++)
            printf(" %d", saved[i]);
        printf("\n");

        return 1;
    }
    else {
        for (int i = 2; i <= size; i++) {
            if (!visited[i] && map[i][e] && map[s][i] == 1) {

                // mark the route as visiteded
                visited[i] = 1;

                // save the route
                saved[d] = i;
                sum += DFS(i, e, d + 1, size);
                visited[i] = 0; //
            }
        }
    }
    return sum;
}

int main()
{
    //freopen("p208.txt","w",stdout);
    int x, y, n;
    int size = 0;
    int kase = 0;
    while (cin >> n) {
        memset(map, 0, sizeof(map));
        memset(visited, 0, sizeof(visited));
        memset(saved, 0, sizeof(saved));
        size = 0;
        while (cin >> x >> y && x && y) //
        {
            map[x][y] = 1;
            map[y][x] = 1;
            if (x > size)
                size = x;
            if (y > size)
                size = y;
        }
        for (int k = 1; k <= size; k++)
            for (int i = 1; i <= size; i++)
                for (int j = 1; j <= size; j++) {
                    if (!map[i][j] && map[i][k] && map[k][j])
                        map[i][j] = 2;
                }

        printf("CASE %d:\n", ++kase);
        visited[1] = 1;
        saved[0] = 1;
        printf("There are %d routes ", DFS(1, n, 1, size));
        printf("from the firestation to streetcorner %d.\n", n);
    }

    return 0;
}
