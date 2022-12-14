package com.pgoellner.advent22.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Monkey {
    private final int id;
    private final List<Integer> heldItems;
    private final Operation operation;
    private final int test;
    private final int recipientIfTestTrue;
    private final int recipientIfTestFalse;

    private int itemsInspected;

    public Monkey(List<String> descriptor) {
        this(descriptor.stream().map(line -> line + "\n").reduce("", String::concat));
    }

    public Monkey(String descriptor) {
        String[] lines = descriptor.split("\n");

        id = Integer.parseInt(lines[0].replace("Monkey ", "").replace(":", ""));
        heldItems = startingItemsFromString(lines[1]);
        operation = new Operation(lines[2].replace("  Operation: ", ""));
        test = Integer.parseInt(lines[3].replace("  Test: divisible by ", ""));
        recipientIfTestTrue = Integer.parseInt(lines[4].replace("    If true: throw to monkey ", ""));
        recipientIfTestFalse = Integer.parseInt(lines[5].replace("    If false: throw to monkey ", ""));

        itemsInspected = 0;
    }

    private List<Integer> startingItemsFromString(String startingItemsLine) {
        return Stream.of(startingItemsLine.replace("  Starting items: ", "").split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Handover> inspectItems() {
        List<Handover> handovers = new ArrayList<>();

        for (int itemToBeInspected : heldItems) {
            itemToBeInspected = operation.calculateNew(itemToBeInspected);
            itemToBeInspected /= 3;

            if (itemToBeInspected % test == 0) {
                handovers.add(new Handover(recipientIfTestTrue, itemToBeInspected));
            } else {
                handovers.add(new Handover(recipientIfTestFalse, itemToBeInspected));
            }

            itemsInspected++;
        }

        heldItems.clear();

        return handovers;
    }

    public void catchItem(int item) {
        heldItems.add(item);
    }

    public int itemsInspected() {
        return itemsInspected;
    }
}

class Operation {
    private final String operator;
    private final String[] operands;
    public Operation(String term) {
        if (term.contains("*")) {
            operator = "*";
        } else {
            operator = "+";
        }

        operands = term.replace("new = ", "").split(" ");
    }

    public int calculateNew(int old) {
        int a = operands[0].equals("old") ? old : Integer.parseInt(operands[0]);
        int b = operands[2].equals("old") ? old : Integer.parseInt(operands[2]);

        if (operator.equals("*")) {
            return a * b;
        } else {
            return a + b;
        }
    }
}
