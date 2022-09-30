package main

import (
	"fmt"
	"strconv"
	"sync"
)

var cities = [][]int{
	{0, 4, 0, 3},
	{4, 0, 6, 7},
	{0, 6, 0, 3},
	{3, 7, 3, 0},
}
var rwLock sync.RWMutex
var wg sync.WaitGroup

const MaxUint = ^uint(0)
const IntMax = int(MaxUint >> 1)

func changePrice(city1 int, city2 int, price int) {
	rwLock.Lock()
	if cities[city1][city2] != 0 {
		cities[city1][city2] = price
		cities[city2][city1] = price
	}
	rwLock.Unlock()
	fmt.Println("Price between city " + strconv.Itoa(city1) + " and " +
		strconv.Itoa(city2) + " changed to " + strconv.Itoa(price))
	print()
	defer wg.Done()
}

func addRoute(city1 int, city2 int, price int) {
	rwLock.Lock()
	if cities[city1][city2] == 0 {
		cities[city1][city2] = price
		cities[city2][city1] = price
	}
	rwLock.Unlock()
	fmt.Println("Route between " + strconv.Itoa(city1) + " and " +
		strconv.Itoa(city2) + " was added with price " + strconv.Itoa(price))
	print()
	defer wg.Done()

}
func deleteRoute(city1 int, city2 int) {
	rwLock.Lock()
	if cities[city1][city2] != 0 {
		cities[city1][city2] = 0
		cities[city2][city1] = 0
	}
	rwLock.Unlock()
	fmt.Println("Route between " + strconv.Itoa(city1) + " and " +
		strconv.Itoa(city2) + " was deleted")
	print()
	defer wg.Done()
}

func addNewCity() {
	rwLock.Lock()
	newSize := len(cities) + 1
	newGraph := make([][]int, newSize)
	for i := 0; i < newSize; i++ {
		newGraph[i] = make([]int, newSize)
	}
	for i := 0; i < newSize; i++ {
		for j := 0; j < newSize; j++ {
			if i != newSize-1 && j != newSize-1 {
				newGraph[i][j] = cities[i][j]
			} else {
				newGraph[i][j] = 0
			}
		}
	}
	cities = newGraph
	rwLock.Unlock()
	fmt.Println("Added new city " + strconv.Itoa(len(cities)-1))
	print()
	defer wg.Done()
}
func deleteCity(cityToDelete int) {
	rwLock.Lock()
	newSize := len(cities) - 1
	newGraph := make([][]int, newSize)
	for i := 0; i < newSize; i++ {
		newGraph[i] = make([]int, newSize)
	}
	iSize := 0
	jSize := 0
	for i := 0; i < len(cities); i++ {
		jSize = 0
		for j := 0; j < len(cities); j++ {
			if j != cityToDelete {
				newGraph[iSize][jSize] = cities[i][j]
				jSize++
			}
		}
		if i == cityToDelete {
			continue
		}
		iSize++
	}
	cities = newGraph
	rwLock.Unlock()
	fmt.Println("Deleted city " + strconv.Itoa(cityToDelete))
	print()
	defer wg.Done()
}

func print() {
	rwLock.RLock()
	for i := 0; i < len(cities); i++ {
		for j := 0; j < len(cities); j++ {
			fmt.Print(cities[i][j], " ")
		}
		fmt.Print("\n")
	}
	fmt.Print("\n")
	rwLock.RUnlock()
}
func minDistance(dist []int, sptSet []bool) int {

	min := IntMax
	var minIndex int

	for v := 0; v < len(cities); v++ {
		if sptSet[v] == false && dist[v] <= min {
			min = dist[v]
			minIndex = v
		}

	}

	return minIndex
}
func dijkstra(city1 int, city2 int) {
	rwLock.RLock()
	dist := make([]int, len(cities))

	sptSet := make([]bool, len(cities)) // sptSet[i] will be true if vertex i is
	for i := 0; i < len(cities); i++ {
		dist[i] = IntMax
		sptSet[i] = false
	}

	dist[city1] = 0

	for count := 0; count < len(cities)-1; count++ {
		u := minDistance(dist, sptSet)
		sptSet[u] = true
		for v := 0; v < len(cities); v++ {
			if !sptSet[v] && cities[u][v] != 0 && dist[u] != IntMax && dist[u]+cities[u][v] < dist[v] {
				dist[v] = dist[u] + cities[u][v]
			}
		}
	}

	fmt.Println("Shortest route from " + strconv.Itoa(city1) + " to " + strconv.Itoa(city2) + " cost " +
		strconv.Itoa(dist[city2]) + " grivnas")
	rwLock.RUnlock()
	print()
	defer wg.Done()
}
func main() {
	var choice int
	print()
	for {
		fmt.Println("1: Change price \n2: Add new route \n3: Delete existed route \n4: Add new city " +
			"\n5: Delete existed city \n6: Price between two cities")
		fmt.Scan(&choice)
		wg.Add(1)
		switch choice {
		case 1:
			fmt.Println("Enter first, second cities and new price:")
			var city1, city2, price int
			fmt.Scan(&city1, &city2, &price)
			go changePrice(city1, city2, price)

		case 2:
			fmt.Println("Enter first, second cities and price of new route:")
			var city1, city2, price int
			fmt.Scan(&city1, &city2, &price)
			go addRoute(city1, city2, price)

		case 3:
			fmt.Println("Enter first, second cities:")
			var city1, city2 int
			fmt.Scan(&city1, &city2)
			go deleteRoute(city1, city2)

		case 4:
			go addNewCity()
		case 5:
			fmt.Println("Enter city to delete:")
			var city1 int
			fmt.Scan(&city1)
			go deleteCity(city1)
		case 6:
			fmt.Println("Enter two cities: ")
			var city1, city2 int
			fmt.Scan(&city1, &city2)
			go dijkstra(city1, city2)
		default:
			fmt.Println("Invalid input")
			wg.Done()
		}
		wg.Wait()
	}
}
