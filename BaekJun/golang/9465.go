package main

import "fmt"

func max(x int, y int) int {
	if x > y {
		return x
	}
	return y
}

func solve() {
	n := 0

	fmt.Scanf("%d\n", &n)
	dp := [2][]int{}
	stickers := [2][]int{}

	dp[0] = make([]int, n)
	dp[1] = make([]int, n)

	stickers[0] = make([]int, n)
	stickers[1] = make([]int, n)

	for i := 0; i < n; i++ {
		fmt.Scanf("%d", &stickers[0][i])
	}
	fmt.Scanf("\n")
	for i := 0; i < n; i++ {
		fmt.Scanf("%d", &stickers[1][i])
	}

	dp[0][0] = stickers[0][0]
	dp[1][0] = stickers[1][0]

	for i := 1; i < n; i++ {
		dp[0][i] = max(dp[0][i-1], dp[1][i-1]+stickers[0][i])
		dp[1][i] = max(dp[1][i-1], dp[0][i-1]+stickers[1][i])
	}

	fmt.Println(max(dp[0][n-1], dp[1][n-1]))

}

func main() {
	t := 0
	fmt.Scanf("%d\n", &t)

	for i := 0; i < t; i++ {
		solve()
	}
}
