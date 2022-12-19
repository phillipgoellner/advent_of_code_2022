package com.pgoellner.advent22.day15;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class SensorCoverageTest {
    private static final List<String> heightMap = List.of(
            "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
            "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
            "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
            "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
            "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
            "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
            "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
            "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
            "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
            "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
            "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
            "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
            "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
            "Sensor at x=20, y=1: closest beacon is at x=15, y=3");

    private final static PuzzleInput puzzleInput = new TestInput(heightMap);

    public static void main(String[] args) {
        coverage_of_26_for_row_10();
    }

    static void coverage_of_26_for_row_10() {
        int positionsCovered = new SensorCoverageCalculator().coverageOnLine(puzzleInput, 10);
        assert positionsCovered == 26 : "Expected 26 covered positions, but got " + positionsCovered;
    }
}
