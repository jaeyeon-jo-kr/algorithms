package main

import (
	"fmt"
)

type pos struct {
	x int
	y int
}

type space struct {
	n         int
	w         int
	accidents []pos
	pol1      *pos
	pol2      *pos
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
func distance(current pos, accident pos) int {
	return abs(current.x-accident.x) + abs(current.y-accident.y)
}
func searchdp(pol1 pos, pol2 pos, accidents []pos) (int, []int) {
	accidentLen := len(accidents)
	currentPosition1 := pol1
	currentPosition2 := pol2
	totalDistance := 0
	dp := make([]int, accidentLen)

	for i := 0; i < accidentLen; i++ {
		nextPosition := accidents[i]
		distance1 := distance(currentPosition1, nextPosition)
		distance2 := distance(currentPosition2, nextPosition)

		if distance1 < distance2 {
			currentPosition1 = nextPosition
			dp[i] = 1
			totalDistance += distance1
		} else {
			currentPosition2 = nextPosition
			dp[i] = 2
			totalDistance += distance2
		}
	}

	return totalDistance, dp

}
func search(pol1 pos, pol2 pos, accidents []pos) (int, []int) {

	if len(accidents) == 1 {
		target := accidents[0]
		pol1Dist := distance(pol1, target)
		pol2Dist := distance(pol2, target)

		if pol1Dist < pol2Dist {
			return pol1Dist, []int{1}
		} else {
			return pol2Dist, []int{2}
		}
	} else {
		newPos := accidents[0]
		tail := accidents[1:]

		pol1WorksDist, pol1Work := search(newPos, pol2, tail)
		pol1Dist := distance(pol1, newPos)
		pol1TotalDist := pol1WorksDist + pol1Dist

		pol2WorksDist, pol2Work := search(pol1, newPos, tail)
		pol2Dist := distance(pol2, newPos)
		pol2TotalDist := pol2WorksDist + pol2Dist

		if pol1TotalDist < pol2TotalDist {
			appended := append([]int{1}, pol1Work...)
			return pol1TotalDist, appended
		} else {
			appended := append([]int{2}, pol2Work...)
			return pol2TotalDist, appended
		}

	}
}

func solution() (int, []int) {
	var a int
	var b []int
	return a, b
}

func main() {
	//road number
	var s space

	fmt.Scan(&s.n)

	//accidentNumbers
	fmt.Scan(&s.w)

	s.accidents = make([]pos, s.w)
	//accidents
	for index := 0; index < s.w; index++ {
		fmt.Scan(&s.accidents[index].x)
		fmt.Scan(&s.accidents[index].y)
	}

	s.pol1 = new(pos)
	s.pol1.x = 1
	s.pol1.y = 1

	s.pol2 = new(pos)
	s.pol2.x = s.n
	s.pol2.y = s.n

	totalDist, works := searchdp(*s.pol1, *s.pol2, s.accidents)

	fmt.Println(totalDist)

	for index := 0; index < s.w; index++ {
		fmt.Println(works[index])
	}

}
