package com.pgoellner.advent22.day05;

public class Instruction {
    private final int crateAmount;
    private final int startIndex;
    private final int endIndex;

    public Instruction(String instructionLine) {
        String[] fromSplit = instructionLine.split(" from ");

        crateAmount = Integer.parseInt(fromSplit[0].replace("move ", ""));

        String[] toSplit = fromSplit[1].split(" to ");
        startIndex = Integer.parseInt(toSplit[0]);
        endIndex = Integer.parseInt(toSplit[1]);
    }

    public int cratesToMove() {
        return crateAmount;
    }

    public int start() {
        return startIndex;
    }

    public int end() {
        return endIndex;
    }
}
