package com.pgoellner.advent22.day06;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

public class CommunicationTuner {
    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_06.txt");
        System.out.println("Part 1: " + new CommunicationTuner().findMarkerIndex(puzzleInput, 4));
        System.out.println("Part 2: " + new CommunicationTuner().findMarkerIndex(puzzleInput, 14));
    }

    public int findMarkerIndex(PuzzleInput puzzleInput, int markerLength) {
        String bufferLine = puzzleInput.lines().get(0);

        int markerIndex = markerLength;

        while (!isMarker(bufferLine.substring(markerIndex - markerLength, markerIndex))) {
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
