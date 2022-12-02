package com.pgoellner.advent22.day02;

public enum Move {
    Rock(1), Paper(2), Scissors(3);

    private final int score;

    Move(int score) {
        this.score = score;
    }

    public static Move fromAbbreviation(String abbreviation) {
        return switch (abbreviation) {
            case "A", "X" -> Move.Rock;
            case "B", "Y" -> Move.Paper;
            default -> Move.Scissors;
        };
    }

    public int score() {
        return this.score;
    }
}
