package main

import (
	"bufio"
	"fmt"
	"os"
)

func main1() {

	var items1 map[rune]int = make(map[rune]int)
	var sub = 'a' - 1

	content, _ := os.Open("day3data.txt")
	scanner := bufio.NewScanner(content)
	var total = 0
	for scanner.Scan() {
		line := scanner.Text()
		len := len(line) / 2

		for i, ch := range line {
			if i < len {
				items1[ch] = 1
			} else {
				if items1[ch] == 1 {
					var v = ch
					if v < 97 {
						v += 58
					}
					total += int(v - sub)
					break
				}
			}
		}
		items1 = make(map[rune]int)
	}

	fmt.Println(total)

}

func main() {

	var items1 map[rune]int = make(map[rune]int)
	var sub = 'a' - 1

	content, _ := os.Open("day3data.txt")
	scanner := bufio.NewScanner(content)
	var total = 0

	for scanner.Scan() {
		line1 := scanner.Text()
		scanner.Scan()
		line2 := scanner.Text()
		scanner.Scan()
		line3 := scanner.Text()

		for _, ch := range line1 {
			items1[ch] = 1
		}
		for _, ch := range line2 {
			if items1[ch] == 1 {
				items1[ch] = 2
			}
		}

		for _, ch := range line3 {
			if items1[ch] == 2 {
				var v = ch
				if v < 97 {
					v += 58
				}
				total += int(v - sub)
				break
			}
		}

		items1 = make(map[rune]int)
	}

	fmt.Println(total)

}
