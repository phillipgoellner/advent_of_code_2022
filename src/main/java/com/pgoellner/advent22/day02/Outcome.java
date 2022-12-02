package com.pgoellner.advent22.day02;

public enum Outcome {
    leftWins, rightWins, draw;

    public static Outcome forMoves(Move left, Move right) {
        return switch (left) {
            case Rock -> switch (right) {
                case Paper -> rightWins;
                case Scissors -> leftWins;
                default -> draw;
            };

            case Paper -> switch (right) {
                case Rock -> leftWins;
                case Scissors -> rightWins;
                default -> draw;
            };

            default -> switch (right) {
                case Rock -> rightWins;
                case Paper -> leftWins;
                default -> draw;
            };
        };
    }

    public static int gameOutcomeScore(Outcome outcome) {
        return switch (outcome) {
            case leftWins -> 0;
            case rightWins -> 6;
            default -> 3;
        };
    }
}
