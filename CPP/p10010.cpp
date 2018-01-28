#include<cstdlib>
#include<cstdio>
// #include<cstring>
#include<string>
#include<iostream>
#include<stdio.h>
#include<stdlib.h>
using namespace std;

int N, m, n;
int l;


int main(){


    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    freopen("10010.in","r",stdin);
    cin >> N;
    cout << N << endl;
    cin >> m >> n;
    cout << m << endl;
    cout << n << endl;


    char grid[m][n];
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cin >> grid[i][j];

            // test IO
            cout << grid[i][j];
        }
        cout << endl;
    }

    // test IO
    cout << grid[7][10] << endl;

    // read an empty line
    int l;
    cin >> l;
    cout << l << endl;

    // char temp;
    // cin >> temp;
    // getchar();

    char words[l][n];
    char temp[n];
    cin.getline(temp, n);
    // string tmp;
    for (int i = 0; i < l; i++) {
        cin.getline(words[i], n);
        cout << words[i] << endl;
    }

    fclose(stdin);
    // fclose(stdout);
    return 0;
}
