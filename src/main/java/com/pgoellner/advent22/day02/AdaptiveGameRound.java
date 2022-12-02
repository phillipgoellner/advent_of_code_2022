package com.pgoellner.advent22.day02;

public class AdaptiveGameRound {
    private final Move opponentMove;
    private final Outcome neededOutcome;

    public AdaptiveGameRound(String roundDescription) {
        String[] moves = roundDescription.split(" ");
        opponentMove = Move.fromAbbreviation(moves[0]);
        neededOutcome = switch (moves[1]) {
            case "X" -> Outcome.leftWins;
            case "Z" -> Outcome.rightWins;
            default -> Outcome.draw;
        };
    }

    public int score() {
        return Outcome.gameOutcomeScore(neededOutcome) + requiredOwnMove().score();
    }

    private Move requiredOwnMove() {
        return switch (neededOutcome) {
            case leftWins -> switch (opponentMove) {
                case Rock -> Move.Scissors;
                case Paper -> Move.Rock;
                default -> Move.Paper;
            };

            case rightWins -> switch (opponentMove) {
                case Rock -> Move.Paper;
                case Paper -> Move.Scissors;
                default -> Move.Rock;
            };

            default -> opponentMove;
        };
    }
}
