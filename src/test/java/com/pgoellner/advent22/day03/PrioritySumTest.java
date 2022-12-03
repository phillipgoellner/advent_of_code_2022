package com.pgoellner.advent22.day03;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class PrioritySumTest {
    private static final List<String> rucksackContents = List.of(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw");

    private final static PuzzleInput puzzleInput = new TestInput(rucksackContents);

    public static void main(String[] args) {
        returns_157_as_rucksack_item_priority();

        System.out.println("All tests passed");
    }

    public static void returns_157_as_rucksack_item_priority() {
        int itemPriority = new PriorityCalculator().calculateForRucksack(puzzleInput);
        assert itemPriority == 157 : "Expected item priorities of 157 but got " + itemPriority;
    }
}
