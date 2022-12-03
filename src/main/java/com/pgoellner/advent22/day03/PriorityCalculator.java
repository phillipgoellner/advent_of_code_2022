package com.pgoellner.advent22.day03;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.day02.TournamentScoreCalculator;

import java.util.*;

public class PriorityCalculator {

    private final Map<String, Integer> itemPriorityLookup;

    public PriorityCalculator() {
        itemPriorityLookup = new HashMap<>();

        for (int i = 65; i < 91; i++) {
            itemPriorityLookup.put("" + ((char) i), i - 38);
        }

        for (int i = 97; i < 123; i++) {
            itemPriorityLookup.put("" + ((char) i), i - 96);
        }
    }

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_03.txt");
        System.out.println("Part 1: " + new PriorityCalculator().calculateForRucksack(puzzleInput));
    }

    public int calculateForRucksack(PuzzleInput puzzleInput) {
        return puzzleInput.lines().stream()
                .map(Rucksack::new)
                .map(Rucksack::duplicateItemType)
                .map(this::priorityFor)
                .reduce(0, Integer::sum);
    }

    private int priorityFor(String character) {
        return itemPriorityLookup.getOrDefault(character, 0);
    }
}
