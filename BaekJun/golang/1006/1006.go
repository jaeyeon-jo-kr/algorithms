package main

import (
	"fmt"
	"math"
)

//첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 다음과 같이 구성되어있다.

//둘째 줄에는 (구역의 개수)/2 값 N과 특수 소대원의 수 W가 주어진다. (1 ≤ N ≤ 10000, 1 ≤ W ≤ 10000).

//셋째 줄에는 1~N번째 구역에 배치된 적의 수가 주어지고,
//넷째 줄에는 N+1 ~ 2N번째 구역에 배치된 적의 수가 공백으로 구분되어 주어진다.
// (1 ≤ 각 구역에 배치된 최대 적의 수 ≤ 10000)
//단, 한 구역에서 특수 소대원의 수보다 많은 적이 배치된 구역은 존재하지 않는다. (따라서, 각 구역에 배치된 최대 적의 수 ≤ W)

type sector struct {
	y int
	x int

	count int
}

type node struct {
	b1        *sector
	b2        *sector
	blockSize int
}

func getNodeString(n node) string {
	if n.blockSize == 1 {
		return fmt.Sprintf("(%d, %d)", n.b1.x, n.b1.y)

	}
	if n.blockSize == 2 {
		return fmt.Sprintf("((%d, %d),(%d,%d))", n.b1.x, n.b1.y, n.b2.x, n.b2.y)
	}
	return ""
}

type visitInfo struct {
	unvisited map[sector]bool
	weight    int
	nodes     map[node]bool
}

func main() {
	t := 0
	fmt.Scanln(&t)

	n := 0
	fmt.Scan(&n)

	w := 0
	fmt.Scanln(&w)

	r := make([]int, t)
	for i := 0; i < t; i++ {
		r[i] = test(n, w)
	}

	for i := 0; i < t; i++ {
		fmt.Println(r[i])
	}
}

func test(n int, w int) int {
	enemies := make([]sector, 2*n)
	unvisited := make(map[sector]bool)

	for j := 0; j < 2; j++ {
		for k := 0; k < n; k++ {
			var enemy sector
			enemy.y = j
			enemy.x = k
			fmt.Scan(&enemy.count)
			enemies[j*n+k] = enemy
			unvisited[enemy] = true
		}
	}

	v := visitInfo{unvisited, 0, make(map[node]bool)}
	min := visitSector(enemies[0], v, n, w)
	return min
}

func visitWithNeighbor(s sector, v visitInfo, n int, w int) int {
	min := math.MaxInt32
	neighbors := getValidNeighbors(s, v.unvisited, n, w)
	for _, neighbor := range neighbors {
		node := createNode2(s, neighbor)

		nv := v
		nv.nodes[node] = true
		delete(nv.unvisited, s)
		delete(nv.unvisited, neighbor)
		nv.weight++

		fmt.Print("unvisited : ")
		fmt.Println(len(nv.unvisited))

		if len(nv.unvisited) == 0 {
			keys := ""
			for key := range nv.nodes {
				keys += getNodeString(key)
				keys += ","
			}
			fmt.Println(keys)

			if nv.weight < min {
				min = nv.weight
			}
		}

		for another := range nv.unvisited {
			weight := visitSector(another, nv, n, w)
			if weight < min {
				min = weight
			}
			break
		}
	}

	return min
}
func visitSector(s sector, v visitInfo, n int, w int) int {
	min := math.MaxInt32
	nv := v
	node := createNode(s)
	nv.nodes[node] = true
	delete(nv.unvisited, s)
	nv.weight++

	fmt.Print("unvisited : ")
	fmt.Println(len(nv.unvisited))

	if len(nv.unvisited) == 0 {
		keys := ""
		for key := range nv.nodes {
			keys += getNodeString(key)
			keys += ","
		}
		fmt.Println(keys)
		return nv.weight
	}

	for sector := range nv.unvisited {
		weight := visitWithNeighbor(sector, nv, n, w)

		if weight < min {
			min = weight
		}
		break
	}

	return min
}

func abs(i int) int {
	j := i
	if j < 0 {
		return -j
	}
	return j
}
func isNeighbor(sec1 sector, sec2 sector, n int) bool {
	if sec1.x == sec2.x && sec1.y != sec2.y {
		return true
	}

	if sec1.y == sec2.y && (sec1.x-sec2.x+n)%n == 1 {
		return true
	}

	if sec1.y == sec2.y && (sec2.x-sec1.x+n)%n == 1 {
		return true
	}

	return false
}

func getNeighbors(self sector, unvisited map[sector]bool, n int) []sector {
	i := 0
	neighbors := make([]sector, 4, 4)
	for enemy := range unvisited {
		if isNeighbor(enemy, self, n) {
			neighbors[i] = enemy
			i++
		}
	}
	fmt.Print("Neighbor size : ")
	fmt.Println(i)
	return neighbors[:i]
}

func getValidNeighbors(self sector,
	unvisited map[sector]bool, n int, w int) []sector {
	neighbors := getNeighbors(self, unvisited, n)
	i := 0
	valids := make([]sector, len(neighbors))
	for _, n := range neighbors {

		if self.count+n.count <= w {
			valids[i] = n
			i++
		}
	}
	fmt.Print("Valid Neighbor size : ")
	fmt.Println(i)

	return valids[:i]
}
func createNode2(sec1 sector, sec2 sector) node {
	return node{&sec1, &sec2, 2}
}
func createNode(sec1 sector) node {
	return node{&sec1, nil, 1}
}
