package com.pgoellner.advent22.day02;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class TournamentStrategyTest {
    private static final List<String> strategyGuide = List.of(
            "A Y",
            "B X",
            "C Z");


    private final static PuzzleInput puzzleInput = new TestInput(strategyGuide);

    public static void main(String[] args) {
        returns_15_as_tournament_score();
        returns_12_as_adaptive_tournament_score();

        System.out.println("All tests passed");
    }

    public static void returns_15_as_tournament_score() {
        int score = new TournamentScoreCalculator().calculateForStrategyGuide(puzzleInput);
        assert score == 15 : "Expected tournament score of 15 but got " + score;
    }

    public static void returns_12_as_adaptive_tournament_score() {
        int score = new TournamentScoreCalculator().calculateForAdaptiveStrategyGuide(puzzleInput);
        assert score == 12 : "Expected tournament score of 12 but got " + score;
    }
}
