package com.pgoellner.advent22.day06;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

public class CommunicationTuner {
    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_06.txt");
        System.out.println("Part 1: " + new CommunicationTuner().findMarkerIndex(puzzleInput));
    }

    public int findMarkerIndex(PuzzleInput puzzleInput) {
        String bufferLine = puzzleInput.lines().get(0);

        int markerIndex = 4;

        while (!isMarker(bufferLine.substring(markerIndex - 4, markerIndex))) {
            markerIndex++;
        }

        return markerIndex;
    }

    private boolean isMarker(String testString) {
        char[] characters = testString.toCharArray();

        for (int i = 0; i < characters.length; i++) {
            for (int j = i+1; j < characters.length; j++) {
                if (characters[i] == characters[j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
