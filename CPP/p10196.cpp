#include<cstdlib>
#include<cstdio>
#include<cstring>
#include<iostream>
using namespace std;

struct che {
	int x[10], y[10], step_num, direction;
};

int chess[200];
int n = 8, game_num;
char map[10][10];
che step[10];


bool Init()
{
	/****************************/
	step[1].x[1] = 1; //pawn  //small
	step[1].y[1] = -1;

	step[1].x[2] = 1;
	step[1].y[2] = 1;

	step[1].step_num = 1;
	step[1].direction = 2;
	//---------------------------
	step[2].x[1] = -1; //rook
	step[2].y[1] = 0;

	step[2].x[2] = 1;
	step[2].y[2] = 0;

	step[2].x[3] = 0;
	step[2].y[3] = -1;

	step[2].x[4] = 0;
	step[2].y[4] = 1;

	step[2].step_num = 8;
	step[2].direction = 4;
	//---------------------------
	step[3].x[1] = -1; //bishop
	step[3].y[1] = -1;

	step[3].x[2] = -1;
	step[3].y[2] = 1;

	step[3].x[3] = 1;
	step[3].y[3] = -1;

	step[3].x[4] = 1;
	step[3].y[4] = 1;

	step[3].step_num = 8;
	step[3].direction = 4;
	//---------------------------
	step[4].x[1] = -1; //queen
	step[4].y[1] = -1;

	step[4].x[2] = -1;
	step[4].y[2] = 1;

	step[4].x[3] = 1;
	step[4].y[3] = -1;

	step[4].x[4] = 1;
	step[4].y[4] = 1;

	step[4].x[5] = -1;
	step[4].y[5] = 0;

	step[4].x[6] = 1;
	step[4].y[6] = 0;

	step[4].x[7] = 0;
	step[4].y[7] = -1;

	step[4].x[8] = 0;
	step[4].y[8] = 1;

	step[4].step_num = 8;
	step[4].direction = 8;
	//--------------------------
	step[5].x[1] = -1; //king
	step[5].y[1] = -1;

	step[5].x[2] = -1;
	step[5].y[2] = 1;

	step[5].x[3] = 1;
	step[5].y[3] = -1;

	step[5].x[4] = 1;
	step[5].y[4] = 1;

	step[5].x[5] = -1;
	step[5].y[5] = 0;

	step[5].x[6] = 1;
	step[5].y[6] = 0;

	step[5].x[7] = 0;
	step[5].y[7] = -1;

	step[5].x[8] = 0;
	step[5].y[8] = 1;

	step[5].step_num = 1;
	step[5].direction = 8;

	//----------------------------
	step[6].x[1] = -1; //knight
	step[6].y[1] = -2;

	step[6].x[2] = -2;
	step[6].y[2] = -1;

	step[6].x[3] = -2;
	step[6].y[3] = 1;

	step[6].x[4] = -1;
	step[6].y[4] = 2;

	step[6].x[5] = 1;
	step[6].y[5] = -2;

	step[6].x[6] = 2;
	step[6].y[6] = -1;

	step[6].x[7] = 2;
	step[6].y[7] = 1;

	step[6].x[8] = 1;
	step[6].y[8] = 2;

	step[6].step_num = 1;
	step[6].direction = 8;
	//----------------------------
	step[7].x[1] = -1; //PAWN  //Capital
	step[7].y[1] = -1;

	step[7].x[2] = -1;
	step[7].y[2] = 1;

	step[7].step_num = 1;
	step[7].direction = 2;
	/*****************************/


	bool flag = false;
	char temp;
	for (int i=1;i<=n;i++)
	{

		for (int j=1;j<=n;j++)
		{
			scanf("%c",&map[i][j]);
			if (map[i][j]!='.') flag = true;
		}
		scanf("%c",&temp);
	}
	if (flag) scanf("%c",&temp);

	chess[46] = 0;

	chess[80] = 7;//PAWN
	chess[112] = -1; //pawn

	chess[82] = 2;//rook
	chess[114] = -2;

	chess[66] = 3;//bishop
	chess[98] = -3;

	chess[81] = 4; //queen
	chess[113] = -4;

	chess[75] = 5; //king
	chess[107] = -5;

	chess[78] = 6; //knight
	chess[110] = -6;

	return flag;
}

void Print(int result)
{
	if (!result) printf("Game #%d: no king is in check.\n", ++game_num);
	if (result == 1) printf("Game #%d: black king is in check.\n", ++game_num);
	if (result == -1) printf("Game #%d: white king is in check.\n", ++game_num);
}

int Out(int** a, int x, int y)
{
	if (x <= 0 || x >= 9) return -1; //out of
	if (y <= 0 || y >= 9) return -1;

	if (a[x][y] == 0) return 0; // can move
	if (a[x][y] == 666) return 1; //in check

	return -1;
}

bool Judge(int** a, int x, int y)
{
	int chess_type = abs(a[x][y]);
	for (int i=1;i<=step[chess_type].direction;i++)
	{
		int cx = x;
		int cy = y;
		for (int j=1;j<=step[chess_type].step_num;j++)
		{
			cx = cx + step[chess_type].x[i];
			cy = cy + step[chess_type].y[i];
			int out = Out(a, cx, cy);
			if (out == -1) //out of board or cannot move
				break;
			else if (out == 1) //in check
				return true;
		}
	}
	return false;
}

void Main()
{
	int result = 0;

	int** a = new int* [12];
	int** b = new int* [12];
	for (int i=0;i<=10;i++)
	{
		a[i] = new int[12];
		b[i] = new int[12];
	}

	for (int i=1;i<=n;i++)
		for (int j=1;j<=n;j++)
		{
			a[i][j] = chess[(int)map[i][j]];
			if (a[i][j] < 0) a[i][j] = -100;
			if (chess[(int)map[i][j]] == -5) a[i][j] = 666;
		}

	for (int i=1;i<=n;i++)
		for (int j=1;j<=n;j++)
		{
			b[i][j] = chess[(int)map[i][j]];
			if (b[i][j] > 0) b[i][j] = -100;
			if (chess[(int)map[i][j]] == 5) b[i][j] = 666;
		}

	for (int i=1;i<=n;i++)
		for (int j=1;j<=n;j++)
			if (a[i][j] > 0  &&  a[i][j] < 10)
				if (Judge(a,i,j))
				{
					result = 1;
					Print(result);
					return;
				}


	for (int i=1;i<=n;i++)
		for (int j=1;j<=n;j++)
			if (b[i][j]<0  &&  b[i][j] >= -10)
				if (Judge(b,i,j))
				{
					result = -1;
					Print(result);
					return;
				}

	Print(result);
}

int main()
{
	freopen("in","r",stdin);

	game_num = 0;
	while (Init())
		Main();

	fclose(stdin);
	return 0;
}
