package com.pgoellner.advent22.day02;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

public class TournamentScoreCalculator {
    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_02.txt");
        System.out.println("Part 1: " + new TournamentScoreCalculator().calculateForStrategyGuide(puzzleInput));
        System.out.println("Part 1: " + new TournamentScoreCalculator().calculateForAdaptiveStrategyGuide(puzzleInput));
    }

    public int calculateForStrategyGuide(PuzzleInput puzzleInput) {
        return puzzleInput
                .lines()
                .stream().map(GameRound::new)
                .map(GameRound::roundScore)
                .reduce(0, Integer::sum);
    }

    public int calculateForAdaptiveStrategyGuide(PuzzleInput puzzleInput) {
        return puzzleInput
                .lines()
                .stream().map(AdaptiveGameRound::new)
                .map(AdaptiveGameRound::score)
                .reduce(0, Integer::sum);
    }
}
