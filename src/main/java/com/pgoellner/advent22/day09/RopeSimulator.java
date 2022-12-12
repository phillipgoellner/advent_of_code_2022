package com.pgoellner.advent22.day09;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RopeSimulator {

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_09.txt");
        System.out.println("Part 1: " + new RopeSimulator().visitedPositions(puzzleInput));
    }
    public int visitedPositions(PuzzleInput puzzleInput) {
        Point head = new Point(0, 0);
        Point tail = new Point(0, 0);

        Set<Point> visitedPositions = new HashSet<>();
        visitedPositions.add(tail);

        for (Move currentMove : puzzleInput.lines().stream().map(this::toMoves).flatMap(List::stream).toList()) {
            PointTransformation transformation = transform(head, tail, currentMove);
            head = transformation.head();
            tail = transformation.tail();
            visitedPositions.add(tail);
        }

        return visitedPositions.size();
    }

    private PointTransformation transform(Point head, Point tail, Move move) {
        return switch (move) {
            case Up -> transformUp(head, tail);
            case Down -> transformDown(head, tail);
            case Left -> transformLeft(head, tail);
            case Right -> transformRight(head, tail);
        };
    }

    private PointTransformation transformUp(Point head, Point tail) {
        Point newHead = new Point(head.x(), head.y() + 1);
        int newTailX = tail.x();
        int newTailY = tail.y();

        if ((newHead.y() - newTailY) > 1) {
            newTailY = newHead.y() - 1;

            if (newHead.x() != tail.x()) {
                newTailX = newHead.x();
            }
        }

        return new PointTransformation(newHead, new Point(newTailX, newTailY));
    }

    private PointTransformation transformDown(Point head, Point tail) {
        Point newHead = new Point(head.x(), head.y() - 1);
        int newTailX = tail.x();
        int newTailY = tail.y();

        if ((newTailY - newHead.y()) > 1) {
            newTailY = newHead.y() + 1;

            if (newHead.x() != tail.x()) {
                newTailX = newHead.x();
            }
        }

        return new PointTransformation(newHead, new Point(newTailX, newTailY));
    }

    private PointTransformation transformLeft(Point head, Point tail) {
        Point newHead = new Point(head.x() - 1, head.y());
        int newTailX = tail.x();
        int newTailY = tail.y();

        if ((newTailX - newHead.x()) > 1) {
            newTailX = newHead.x() + 1;

            if (newHead.y() != tail.y()) {
                newTailY = newHead.y();
            }
        }

        return new PointTransformation(newHead, new Point(newTailX, newTailY));
    }

    private PointTransformation transformRight(Point head, Point tail) {
        Point newHead = new Point(head.x() + 1, head.y());
        int newTailX = tail.x();
        int newTailY = tail.y();

        if ((newHead.x() - newTailX) > 1) {
            newTailX = newHead.x() - 1;

            if (newHead.y() != tail.y()) {
                newTailY = newHead.y();
            }
        }


        return new PointTransformation(newHead, new Point(newTailX, newTailY));
    }

    private List<Move> toMoves(String line) {
        String[] splitLine = line.split(" ");
        int numberOfSteps = Integer.parseInt(splitLine[1]);

        return switch (splitLine[0]) {
            case "U" -> Collections.nCopies(numberOfSteps, Move.Up);
            case "D" -> Collections.nCopies(numberOfSteps, Move.Down);
            case "L" -> Collections.nCopies(numberOfSteps, Move.Left);
            case "R" -> Collections.nCopies(numberOfSteps, Move.Right);
            default -> List.of();
        };
    }
}

enum Move {
    Up, Down, Left, Right
}

