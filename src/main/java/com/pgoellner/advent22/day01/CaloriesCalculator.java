package com.pgoellner.advent22.day01;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

import java.util.List;

public class CaloriesCalculator {

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_01.txt");
        System.out.println(new CaloriesCalculator().highestCaloriesCountFor(puzzleInput));
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
}
