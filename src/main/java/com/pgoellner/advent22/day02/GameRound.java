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
        return this.ownMove.score;
    }

    private enum Move {
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
    }

    private enum Outcome {
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
}
