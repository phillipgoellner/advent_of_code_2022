package com.pgoellner.advent22.day04;

import com.pgoellner.advent22.*;

import java.util.List;

public class CampCleanupTest {
    private static final List<String> cleanupTasks = List.of(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8");

    private final static PuzzleInput puzzleInput = new TestInput(cleanupTasks);

    public static void main(String[] args) {
        returns_2_as_overlapping_assignments();

        System.out.println("All tests passed");
    }

    public static void returns_2_as_overlapping_assignments() {
        int itemPriority = new CampCleanup().numberOfOverlappingAssignments(puzzleInput);
        assert itemPriority == 2 : "Expected item priorities of 2 but got " + itemPriority;
    }
}
