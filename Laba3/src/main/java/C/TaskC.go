package main

import (
	"fmt"
	"math/rand"
	"time"
)

type Smoker struct {
	name  string
	thing int
}

func NewSmoker(name string, comp int) *Smoker {
	return &Smoker{name: name, thing: comp}
}

func (smoker Smoker) tryToSmoke(semaphore semaphore, c chan []int) {
	for {
		onTable := <-c
		toSmoke := true

		for i := 0; i < len(onTable); i++ {
			if onTable[i] == smoker.thing {
				toSmoke = false
			}
		}
		if toSmoke {
			fmt.Println("This time " + smoker.name + " is smoking")
			time.Sleep(3 * time.Second) // time of smoking
			semaphore.release()
		}
	}
}

const (
	Paper   int = 0
	Tobacco     = 1
	Matches     = 2
)

func remove(slice []int, s int) []int {
	return append(slice[:s], slice[s+1:]...)
}

type semaphore chan int

func (semaphore semaphore) acquire() {
	semaphore <- 0
}

func (semaphore semaphore) release() {
	<-semaphore
}

func intermediary(semaphore semaphore, r1 *rand.Rand, c chan []int) {
	for {
		if len(semaphore) == 0 {
			onTable := remove([]int{Tobacco, Paper, Matches}, r1.Intn(3))
			fmt.Println(onTable)

			for i := 0; i < 3; i++ {
				c <- onTable
			}

			semaphore.acquire()
		}
	}
}

func main() {
	s1 := rand.NewSource(time.Now().UnixNano())
	r1 := rand.New(s1)
	c := make(chan []int, 3)

	semaphore := make(semaphore, 1)

	firstSmoker := NewSmoker("firstSmoker", Paper)
	secondSmoker := NewSmoker("secondSmoker", Tobacco)
	thirdSmoker := NewSmoker("thirdSmoker", Matches)

	go intermediary(semaphore, r1, c)
	go firstSmoker.tryToSmoke(semaphore, c)
	go secondSmoker.tryToSmoke(semaphore, c)
	go thirdSmoker.tryToSmoke(semaphore, c)

	for {
	}
}
