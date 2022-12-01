package com.pgoellner.advent22.day01;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CaloriesCalculator {

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_01.txt");
        System.out.println("Part 1: " + new CaloriesCalculator().highestCaloriesCountFor(puzzleInput));
        System.out.println("Part 2: " + new CaloriesCalculator().highestCaloriesCountForTop(3, puzzleInput));
    }

    public int highestCaloriesCountFor(PuzzleInput puzzleInput) {
        List<String> inputLines = puzzleInput.lines();
        int highestCaloriesCount = 0;

        int currentSum = 0;
        for (String line : inputLines) {
            if (line.equals("")) {
                if (currentSum > highestCaloriesCount) {
                    highestCaloriesCount = currentSum;
                }
                currentSum = 0;
            } else {
                currentSum += Integer.parseInt(line);
            }
        }

        return highestCaloriesCount;
    }

    public int highestCaloriesCountForTop(int n, PuzzleInput puzzleInput) {
        List<Elf> elves = new ArrayList<>();
        List<String> elfCaloriesLines = new ArrayList<>();

        for (String line : puzzleInput.lines()) {

            if (line.equals("")) {
                elves.add(new Elf(elfCaloriesLines));
                elfCaloriesLines = new ArrayList<>();
            } else {
                elfCaloriesLines.add(line);
            }
        }

        return elves.stream()
                .map(Elf::calories)
                .sorted(Comparator.reverseOrder())
                .toList()
                .subList(0, n).stream()
                .reduce(0, Integer::sum);
    }
}
