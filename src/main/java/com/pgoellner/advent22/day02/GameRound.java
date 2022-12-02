package com.pgoellner.advent22.day02;

public class GameRound {
    private final Move opponentMove;
    private final Move ownMove;

    public GameRound(String roundDescription) {
        String[] moves = roundDescription.split(" ");
        opponentMove = Move.fromAbbreviation(moves[0]);
        ownMove = Move.fromAbbreviation(moves[1]);
    }

    public int roundScore() {
        return Outcome.gameOutcomeScore(Outcome.forMoves(opponentMove, ownMove)) + moveScore();
    }

    private int moveScore() {
        return this.ownMove.score();
    }
}
