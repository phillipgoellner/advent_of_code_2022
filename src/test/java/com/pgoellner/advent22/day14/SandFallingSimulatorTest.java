package com.pgoellner.advent22.day14;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class SandFallingSimulatorTest {
    private static final List<String> heightMap = List.of(
            "498,4 -> 498,6 -> 496,6",
            "503,4 -> 502,4 -> 502,9 -> 494,9");

    private final static PuzzleInput puzzleInput = new TestInput(heightMap);

    public static void main(String[] args) {
        structure_can_hold_24_units_of_sand();
    }

    static void structure_can_hold_24_units_of_sand() {
        int unitsOfSand = new SandFallingSimulator().sandUnitCapacity(puzzleInput);
        assert unitsOfSand == 24 : "Expected 24 sand units, but got " + unitsOfSand;
    }
}
