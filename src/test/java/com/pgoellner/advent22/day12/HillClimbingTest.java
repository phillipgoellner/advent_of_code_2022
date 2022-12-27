package com.pgoellner.advent22.day12;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class HillClimbingTest {

    private static final List<String> heightMap = List.of(
            "Sabqponm",
            "abcryxxl",
            "accszExk",
            "acctuvwj",
            "abdefghi");

    private final static PuzzleInput puzzleInput = new TestInput(heightMap);

    public static void main(String[] args) {
        shortest_path_has_31_steps();
    }

    static void shortest_path_has_31_steps() {
        int shortestPathTest = new PathFinder().stepsForShortestPath(puzzleInput);
        assert shortestPathTest == 31 : "Expected shortest path to have 31 steps, but got " + shortestPathTest;
    }
}
