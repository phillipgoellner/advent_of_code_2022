package com.pgoellner.advent22.day17;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.Point;
import com.pgoellner.advent22.PuzzleInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LavaTetris {
    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_17.txt");
        System.out.println("Part 1: " + new LavaTetris().simulateStoneFall(puzzleInput));
    }

    public int simulateStoneFall(PuzzleInput puzzleInput) {
        GasJets jets = new GasJets(puzzleInput.lines().get(0));
        RockDispenser rocks = new RockDispenser();

        List<RockPosition> placedRocks = new ArrayList<>();
        int highestPoint = 0;

        while (placedRocks.size() < 2022) {
            RockPosition fallingRock = new RockPosition(rocks.getRock(), new Point(2, highestPoint + 3));

            while (!fallingRock.stopped()) {
                fallingRock.jetPush(jets.dispense(), placedRocks);
                fallingRock.fallDown(placedRocks);
            }

            placedRocks.add(fallingRock);
            highestPoint = placedRocks.stream()
                    .max(Comparator.comparingInt(RockPosition::toweringHeight))
                    .map(RockPosition::toweringHeight).orElseThrow();
        }

        return highestPoint;
    }
}

class RockDispenser {
    private int currentRock = 0;

    public Rock getRock() {
        Rock newRock = switch (currentRock) {
            case 0 -> Rock.Minus;
            case 1 -> Rock.Plus;
            case 2 -> Rock.L;
            case 3 -> Rock.I;
            default -> Rock.Square;
        };

        currentRock++;
        if (currentRock == 5) {
            currentRock = 0;
        }

        return newRock;
    }
}
