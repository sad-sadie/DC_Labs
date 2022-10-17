package main

import (
	"fmt"
	"golang.org/x/sync/semaphore"
	"math/rand"
	"time"
)

var sem *semaphore.Weighted //=

type Client struct {
	id int
}

func NewClient(id int) *Client {
	return &Client{id: id}
}

func (client Client) call() {
	fmt.Println("Client with id ", client.id, " is now waiting.")

	start := time.Now()
	for !sem.TryAcquire(int64(1)) {
		if time.Now().Sub(start) > (time.Second*time.Duration(rand.Intn(3)) + time.Second*3) {
			fmt.Println("Client with id ", client.id, " is done waiting and will try later.")
			time.Sleep(5 * time.Second)
			client.call()
			return
		}
	}
	fmt.Println("Client with id ", client.id, " is now with operator.")
	time.Sleep(time.Second*time.Duration(rand.Intn(5)) + time.Second*5)
	fmt.Println("Client with id ", client.id, " has been served.")
	sem.Release(int64(1))
}

func main() {
	sem = semaphore.NewWeighted(int64(5))
	rand.Seed(time.Now().Unix())
	for i := 1; i <= 10; i++ {
		go NewClient(i).call()
		time.Sleep(time.Second)
	}
	for {
	}
}
