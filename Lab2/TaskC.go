package main

import (
	"fmt"
	"math/rand"
	"time"
)

type Monk struct {
	energy int
	temple bool
}

func NewMonk(energy int, temple bool) *Monk {
	return &Monk{energy: energy, temple: temple}
}

func battle(a Monk, b Monk, c chan Monk) {
	if a.energy >= b.energy {
		c <- a
	} else {
		c <- b
	}
}

func main() {
	s1 := rand.NewSource(time.Now().UnixNano())
	r1 := rand.New(s1)
	var monks []Monk
	numberOfParticipants := 8
	for i := 0; i < numberOfParticipants; i++ {
		m := NewMonk(r1.Intn(100)+1, i%2 == 0)
		monks = append(monks, *m)
	}

	c := make(chan Monk)
	for numberOfParticipants > 1 {
		if numberOfParticipants > 2 {
			fmt.Println("1 /", numberOfParticipants/2, "participants:", monks)
		} else {
			fmt.Println("Final participants:", monks)
		}
		for i := 0; i < numberOfParticipants; i += 2 {
			go battle(monks[i], monks[i+1], c)
		}
		numberOfParticipants /= 2
		monks = nil
		for i := 0; i < numberOfParticipants; i++ {
			monks = append(monks, <-c)
		}

	}
	fmt.Println("Winner:", monks)
	if monks[0].temple {
		fmt.Println("Guan-Yin has won having energy:", monks[0].energy)
	} else {
		fmt.Println("Guan-Yan has won having energy:", monks[0].energy)
	}
}
