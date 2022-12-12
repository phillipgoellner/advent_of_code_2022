package com.pgoellner.advent22.day09;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class RopeBridgeTest {

    private static final List<String> dataStreamBuffer = List.of(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2");

    private final static PuzzleInput puzzleInput = new TestInput(dataStreamBuffer);

    public static void main(String[] args) {
        tail_visited_13_positions();
    }

    static void tail_visited_13_positions() {
        int visitedPositions = new RopeSimulator().visitedPositions(puzzleInput);
        assert visitedPositions == 13 : "Expected tail to have visited 13 positions, but instead visited " + visitedPositions;
    }
}
