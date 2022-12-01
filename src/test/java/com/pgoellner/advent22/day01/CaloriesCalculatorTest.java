package com.pgoellner.advent22.day01;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class CaloriesCalculatorTest {
    private final static List<String> input = List.of("1000","2000","3000",
        "",
        "4000",
        "",
        "5000","6000",
        "",
        "7000","8000","9000",
        "",
        "10000");
    private final static PuzzleInput puzzleInput = new TestInput(input);

    public static void main(String[] args) {
        returns_24000_calories_for_test_lines();
        returns_45000_as_top_3_calories_count_for_test_lines();

        System.out.println("All tests passed");
    }

    public static void returns_24000_calories_for_test_lines() {
        int calculatedResult = new CaloriesCalculator().highestCaloriesCountFor(puzzleInput);
        assert calculatedResult == 24000 : "Highest calories count was " + calculatedResult + ", but expected 24000";
    }

    public static void returns_45000_as_top_3_calories_count_for_test_lines() {
        int calculatedResult = new CaloriesCalculator().highestCaloriesCountForTop(3, puzzleInput);
        assert calculatedResult == 45000 : "Highest calories count was " + calculatedResult + ", but expected 45000";
    }
}
