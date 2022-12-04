package com.pgoellner.advent22.day04;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

public class CampCleanup {
    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_04.txt");
        System.out.println("Part 1: " + new CampCleanup().numberOfOverlappingAssignments(puzzleInput));
    }

    public int numberOfOverlappingAssignments(PuzzleInput puzzleInput) {
        return puzzleInput.lines().stream().map(AssignmentPair::new).map(assignmentPair -> {
            if (assignmentPair.oneRangeContainsAnother()) {
                return 1;
            } else {
                return 0;
            }
        }).reduce(0, Integer::sum);
    }
}
