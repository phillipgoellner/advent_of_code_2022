package com.pgoellner.advent22.day01;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class CaloriesCalculatorTest {
    public static void main(String[] args) {
        returns_24000_calories_for_test_lines();

        System.out.println("All tests passed");
    }

    public static void returns_24000_calories_for_test_lines() {
        var input = List.of("1000","2000","3000",
                "",
                "4000",
                "",
                "5000","6000",
                "",
                "7000","8000","9000",
                "",
                "10000");

        PuzzleInput puzzleInput = new TestInput(input);

        int calculatedResult = new CaloriesCalculator().highestCaloriesCountFor(puzzleInput);
        assert calculatedResult == 24000 : "Highest calories count was " + calculatedResult + ", but expected 24000";
    }
}
