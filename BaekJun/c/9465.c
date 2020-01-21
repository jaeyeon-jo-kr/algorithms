#include <stdio.h>
#define MAXN 100000
#define MAX(a, b) (((a) > (b)) ? (a) : (b))
#define MIN(a, b) (((a) < (b)) ? (a) : (b))

int solve()
{
  int i = 0;
  int n;
  int stickers[2][MAXN];
  int dp[2][MAXN];

  scanf("%d\n", &n);
  for(i = 0; i < n;i++) {
      scanf("%d", &stickers[0][i]);
  }
  scanf("\n");

  for(i = 0; i < n;i++) {
    scanf("%d", &stickers[1][i]);
  }

  dp[0][0] = stickers[0][0];
  dp[1][0] = stickers[1][0];

  for(i = 1;i < n;i++) {
    dp[0][i] = MAX(dp[0][i-1], dp[1][i-1] + stickers[0][i]);
    dp[1][i] = MAX(dp[1][i-1], dp[0][i-1] + stickers[1][i]);
  }

  printf("%d\n", MAX(dp[0][n-1], dp[1][n-1]));

  return 0;
}

int main()
{

  int t = 0;
  int i = 0;


  scanf("%d\n", &t);

  for(i = 0; i < t; i++) {
    solve();
  }
}
