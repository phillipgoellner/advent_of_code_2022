package com.pgoellner.advent22.day08;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class TreeSurveyorTest {

    private static final List<String> dataStreamBuffer = List.of(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390");

    private final static PuzzleInput puzzleInput = new TestInput(dataStreamBuffer);

    public static void main(String[] args) {
        total_of_21_trees_are_visible();
    }

    static void total_of_21_trees_are_visible() {
        int treesVisible = new TreeSurveyor().countVisibleTrees(puzzleInput);
        assert treesVisible == 21 : "Expected total of 21 visible trees, but got " + treesVisible;
    }
}
