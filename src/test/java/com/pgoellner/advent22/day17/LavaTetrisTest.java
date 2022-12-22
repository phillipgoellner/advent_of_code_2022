package com.pgoellner.advent22.day17;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;
import com.pgoellner.advent22.day16.PressureCalculator;

import java.util.List;

public class LavaTetrisTest {
    private static final List<String> gasJets = List.of(
            ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>");

    private final static PuzzleInput puzzleInput = new TestInput(gasJets);

    public static void main(String[] args) {
        tower_height_of_3068();
    }

    private static void tower_height_of_3068() {
        int towerHeight = new LavaTetris().simulateStoneFall(puzzleInput);
        assert towerHeight == 3068 : "Expected tower height of 3068, but got " + towerHeight;
    }
}
