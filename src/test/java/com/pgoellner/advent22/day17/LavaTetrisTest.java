package com.pgoellner.advent22.day17;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class LavaTetrisTest {
    private static final List<String> gasJets = List.of(
            ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>");

    private final static PuzzleInput puzzleInput = new TestInput(gasJets);

    public static void main(String[] args) {
        tower_height_of_3068();
//        tower_height_of_1514285714288();
    }

    private static void tower_height_of_3068() {
        long towerHeight = new LavaTetris().simulateStoneFall(puzzleInput);
        assert towerHeight == 3068 : "Expected tower height of 3068, but got " + towerHeight;
    }

//    private static void tower_height_of_1514285714288() {
//        long towerHeight = new LavaTetris().simulateStoneFall(puzzleInput, 1_000_000_000_000L);
//        assert towerHeight == 1_514_285_714_288L : "Expected tower height of 1514285714288, but got " + towerHeight;
//    }
}
