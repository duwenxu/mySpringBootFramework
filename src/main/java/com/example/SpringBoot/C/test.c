#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <windows.h>
#define N 35
#pragma warning(disable: 4996)
int str[22][N] = { 0 }, plane = 9, width = 24, speed = 3, density = 30, score = 0, death = 0;
//全局变量：界面、我机初始位、界面宽度、敌机速度、敌机密度、得分、死亡
void show(int a[][N]);//打印函数
void movebul(int[][N]);//子弹移动函数
void moveplane(int[][N]);//敌机移动函数
int main()
{
    int i = 0, j = 0;
    str[21][plane] = 1;
    str[0][5] = 3;
    while (1)
    {
        if (kbhit())
            switch (getch())//控制左右移动和子弹
        {
            case 'a':
            case 'A':
                if (plane>0)str[21][plane] = 0, str[21][--plane] = 1;
                break;
            case 'd':
            case 'D':
                if (plane<width - 2)str[21][plane] = 0, str[21][++plane] = 1;
                break;
            case 'w':
            case 'W':
                str[20][plane] = 2; break;
                break;
        }
        if (++j%density == 0)//控制生产敌机的速度
        {
            j = 0; srand(time(NULL));
            str[0][rand() % width] = 3;
        }
        if (++i%speed == 0)//控制敌机移动速度，相对于子弹移动速度
            moveplane(str);
        movebul(str);
        show(str);
        if (i == 30000)i = 0;//以免i 越界
    }
    system("pause");
    return 0;
}
void show(int a[][N])
{
    system("cls");
    int i, j;
    for (i = 0; i<22; i++)
    {
        a[i][width - 1] = 4;
        for (j = 0; j<width; j++)
        {
            if (a[i][j] == 0)printf(" ");
            if (a[i][j] == 1)printf("\5");//输出我机的符号
            if (a[i][j] == 2)printf(".");//子弹
            if (a[i][j] == 3)printf("\4"); //输出敌机符号
            if (a[i][j] == 4)printf("|");
            if (i == 0 && j == width - 1)printf("得分：%d", score);//右上角显示得分
            if (i == 1 && j == width - 1)printf("死亡：%d", death);
        }
        printf("\n");//Sleep(100);
    }
    Sleep(80);
}
void movebul(int a[][N])//子弹移动函数
{
    int i, j;
    for (i = 0; i<22; i++)
    for (j = 0; j<width; j++)
    {
        if (i == 0 && a[i][j] == 2)
            a[i][j] = 0;
        if (a[i][j] == 2)
        {
            if (a[i - 1][j] == 3)
                score += 10;//,printf("\7")
            a[i][j] = 0, a[i - 1][j] = 2;
        }
    }
}
void moveplane(int a[][N])//敌机移动函数
{
    int i, j;
    for (i = 21; i >= 0; i--)//从最后一行往上是为了避免把敌机直接冲出数组。
    for (j = 0; j<width; j++)
    {
        if (i == 21 && a[i][j] == 3)a[i][j] = 0;//底行赋值0 以免越界。
        if (a[i][j] == 3)a[i][j] = 0, a[i + 1][j] = 3;
    }
    if (a[20][plane] == 3 && a[21][plane] == 1)//敌机和我机相碰
        death++;
}