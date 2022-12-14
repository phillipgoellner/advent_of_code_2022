package com.pgoellner.advent22.day11;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.day10.CpuSignalStrengthCalculator;

import java.util.List;
import java.util.stream.IntStream;

public class MonkeyBusinessAnalyser {

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_11.txt");
        System.out.println("Part 1: " + new MonkeyBusinessAnalyser().simulateMonkeyBusiness(puzzleInput, 20));
    }

    public int simulateMonkeyBusiness(PuzzleInput puzzleInput, int rounds) {
        List<String> inputLines = puzzleInput.lines();

        int numberOfMonkeys = puzzleInput.lines().size() / 7 + 1;
        List<Monkey> monkeys = IntStream
                .range(0, numberOfMonkeys - 1)
                .mapToObj(monkeyId -> new Monkey(inputLines.subList(monkeyId * 7, monkeyId * 7 + 7)))
                .toList();

        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                for (Handover handover : monkey.inspectItems()) {
                    monkeys.get(handover.toMonkeyId()).catchItem(handover.item());
                }
            }
        }

        List<Integer> monkeyActivities = monkeys.stream().map(Monkey::itemsInspected).sorted().toList();

        return monkeyActivities.get(monkeyActivities.size() - 1) * monkeyActivities.get(monkeyActivities.size() - 2);
    }
}
