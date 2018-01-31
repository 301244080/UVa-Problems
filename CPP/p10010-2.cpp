#include <iostream>
#include <cstdlib>
#include <cstring>
#include <cstdio>
#include <string>
#include <algorithm>

using namespace std;

int  dxy[8][2] = {1,0,0,1,-1,0,0,-1,1,1,1,-1,-1,1,-1,-1};
char text[52][52];
char word[20][52];
int positionX, positionY;
int s[400];

struct suffix {
    int index;
    char* suff;
};

// compare two suffixes
int cmp(struct suffix a, struct suffix b) {
    return strcmp(a.suff, b.suff) < 0 ? 1 : 0;
}

// build suffix arrays
// int* buildSuffixArray(char* txt, int n) {
//     struct suffix suffixes[n];
//
//     for (int i = 0; i < n; i++) {
//         suffixes[i].index = i;
//         suffixes[i].suff = (txt + i);
//     }
//
//     sort(suffixes, suffixes + n, cmp);
//
//     int* suffixArr = new int[n];
//     for (int i = 0; i < n; i++) {
//         suffixArr[i] = suffixes[i].index;
//     }
//
//     return suffixArr;
// }

// void searchSA(char *pat, char* txt, int* suffArr, int n) {
//     int m = strlen(pat);
//
//     int l = 0, r = n - 1;
//     while (l <= r) {
//         int mid = l + (r - 1) / 2;
//         int res = strncmp(pat, txt + suffArr[mid], m);
//
//         if (res == 0) {
//             printf("Pattern found at index: %d\n", suffArr[mid]);
//             return;
//         }
//
//         if (res < 0) r = mid - 1;
//         else l = mid + 1;
//     }
//
//     printf("%s\n", "Pattern not found");
// }




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



void findInSuffixArray(char *buf, int n, int m, int l) {

}

bool invalid(int xx, int yy, int m, int n) {
    if (xx < 0 || xx >= n || yy < 0 || yy >= m)
        return true;
    return false;
}

void search(char* buf, int n, int m, int count) {

    printf("%s\n", "Building suffix array");
    // int* suffixArr = buildSuffixArray(buf, count);
    // searchSA(word[0], buf, suffixArr, count);
}

void solve(int n, int m, int l) {
    char buf[52];
    for (int i = 0; i < 1; i++)
        for (int j = 0; j < m; j++) {
            int xx = i, yy = j;
            int count = 0;
            while (1) {
                buf[count++] = text[xx][yy];
                printf("%d %d(%c)  ", xx, yy, text[xx][yy]);
                xx += 1;
                yy += 1;
                if (invalid(xx, yy, m, n))
                    break;
            }

            printf("\n");
            printf("%s\n", "start searching");
            search(buf, n, m, count);
            return;
        }

    // for (int i = 0; i < 1; i++) {
    //     for (int j = 1; j < n; j++) {
    //         int xx = j, yy = i;
    //         int count = 1;
    //         while (1) {
    //             buf[count++] = text[xx][yy];
    //             printf("%d %d(%c)  ", xx, yy, text[xx][yy]);
    //             xx += 1;
    //             yy += 1;
    //             if (invalid(xx, yy, m, n))
    //                 break;
    //         }
    //         buf[count] = 0;
    //         printf("\n");
    //     }
    // }

    // for (int i = 0; i < 1; i++) {
    //     for (int j = 0; j < n; j++) {
    //         int xx = j, yy = i;
    //         int count = 1;
    //         while (1) {
    //             buf[count++] = text[xx][yy];
    //             printf("%d %d(%c)  ", xx, yy, text[xx][yy]);
    //             // xx += 1;
    //             yy += 1;
    //             if (invalid(xx, yy, m, n))
    //                 break;
    //         }
    //         buf[count] = 0;
    //         printf("\n");
    //     }
    // }
    // for (int i = 0; i < 1; i++) {
    //     for (int j = 0; j < n; j++) {
    //         int xx = i, yy = j;
    //         int count = 1;
    //         while (1) {
    //             buf[count++] = text[xx][yy];
    //             printf("%d %d(%c)  ", xx, yy, text[xx][yy]);
    //             xx += 1;
    //             // yy += 1;
    //             if (invalid(xx, yy, m, n))
    //                 break;
    //         }
    //         buf[count] = 0;
    //         printf("\n");
    //     }
    // }
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
        // solve(n, m, l);

    }
}
