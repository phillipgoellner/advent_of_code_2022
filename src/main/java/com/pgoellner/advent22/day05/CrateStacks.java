package com.pgoellner.advent22.day05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CrateStacks {
    private final List<Stack<String>> stacks;

    public CrateStacks(List<Stack<String>> stacks, boolean alibi) {
        this.stacks = stacks;
    }

    public CrateStacks(List<String> stackLines) {
        int numberOfStacks = 1 + stackLines.get(stackLines.size() - 1).length() / 4;

        stacks = new ArrayList<>();
        for (int i = 0; i < numberOfStacks; i++) {
            stacks.add(new Stack<>());
        }

        new LinkedList<>(stackLines).descendingIterator().forEachRemaining(line -> {
            if (line.contains("[")) {
                for (int i = 0; i < numberOfStacks; i++) {
                    int letterPosition = i * 4 + 1;
                    String createLetter = line.substring(letterPosition, letterPosition + 1);
                    if (!createLetter.equals(" ")) {
                        stacks.get(i).push(createLetter);
                    }
                }
            }
        });
    }

    public void apply(Instruction instruction) {
        for (int i = 0; i < instruction.cratesToMove(); i++) {
            stacks.get(instruction.end() - 1).push(stacks.get(instruction.start() - 1).pop());
        }
    }

    public String topCrateMessage() {
        return stacks.stream().map(Stack::peek).reduce("", String::concat);
    }

    public int hashCode() {
        return this.stacks.toString().hashCode();
    }

    public boolean equals(Object object) {
        return object instanceof CrateStacks && this.hashCode() == object.hashCode();
    }
}
