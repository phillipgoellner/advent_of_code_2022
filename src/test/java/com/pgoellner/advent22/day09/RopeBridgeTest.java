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

    private static final List<String> largerDataExample = List.of(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20"
    );

    private final static PuzzleInput puzzleInput = new TestInput(dataStreamBuffer);
    private final static PuzzleInput largerPuzzleInput = new TestInput(largerDataExample);

    public static void main(String[] args) {
        tail_visited_13_positions();
        tail_visited_1_position();
        tail_visited_36_position();
    }

    static void tail_visited_13_positions() {
        int visitedPositions = new RopeSimulator().visitedPositions(puzzleInput);
        assert visitedPositions == 13 : "Expected tail to have visited 13 positions, but instead visited " + visitedPositions;
    }

    static void tail_visited_1_position() {
        int visitedPositions = new RopeSimulator().visitedPositions(puzzleInput, 9);
        assert visitedPositions == 1 : "Expected tail to have visited 1 position, but instead visited " + visitedPositions;
    }

    static void tail_visited_36_position() {
        int visitedPositions = new RopeSimulator().visitedPositions(largerPuzzleInput, 9);
        assert visitedPositions == 36 : "Expected tail to have visited 36 positions, but instead visited " + visitedPositions;
    }
}
