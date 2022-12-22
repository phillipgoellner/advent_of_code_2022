package com.pgoellner.advent22.day21;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

import java.util.*;
import java.util.stream.Collectors;

public class MonkeyOperators {

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_21.txt");
        System.out.println("Part 1: " + new MonkeyOperators().resolve(puzzleInput));
    }

    public long resolve(PuzzleInput puzzleInput) {

        List<Optional<Operator>> builders = puzzleInput.lines().stream()
                .map(OperatorBuilder::new)
                .map(builder -> builder.build(Map.of()))
                .toList();

        Set<Operator> allOperators = builders.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

        while (builders.stream().anyMatch(Optional::isEmpty)) {
            Map<String, Operator> operatorMapping = new HashMap<>();

            for (Operator operator : allOperators) {
                operatorMapping.put(operator.name, operator);
            }

            builders = puzzleInput.lines().stream()
                    .map(OperatorBuilder::new)
                    .map(builder -> builder.build(operatorMapping))
                    .toList();

            allOperators = builders.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
        }

        Map<String, Operator> operatorMapping = new HashMap<>();

        for (Operator operator : allOperators) {
            operatorMapping.put(operator.name, operator);
        }

        return operatorMapping.get("root").resolve();
    }
}

class Operator {
    public final String name;
    private final String operator;
    private final Operator left;
    private final Operator right;


    public Operator(String name, String operator, Operator left, Operator right) {
        this.name = name;
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public long resolve() {
        return switch (operator) {
            case "+" -> left.resolve() + right.resolve();
            case "-" -> left.resolve() - right.resolve();
            case "*" -> left.resolve() * right.resolve();
            default -> left.resolve() / right.resolve();
        };
    }
}

class Value extends Operator {
    private final long number;

    public Value(String name, long number) {
        super(name, "", null, null);
        this.number = number;
    }

    @Override
    public long resolve() {
        return this.number;
    }
}

record OperatorBuilder(String operatorDescriptor) {
    public Optional<Operator> build(Map<String, Operator> existingOperators) {
        String[] operatorSplit = operatorDescriptor.split(": ");
        String operatorName = operatorSplit[0];
        String operation = "";

        if (operatorDescriptor.contains("+")) {
            operation = "+";
        } else if (operatorDescriptor.contains("-")) {
            operation = "-";
        } else if (operatorDescriptor.contains("*")) {
            operation = "*";
        } else if (operatorDescriptor.contains("/")) {
            operation = "/";
        }

        if (operation.equals("")) {
            return Optional.of(new Value(operatorName, Integer.parseInt(operatorSplit[1])));
        } else {
            String[] operands = operatorSplit[1].split(" ");
            String left = operands[0];
            String right = operands[2];
            if (existingOperators.containsKey(left) && existingOperators.containsKey(right)) {
                return Optional.of(
                        new Operator(operatorName, operation, existingOperators.get(left), existingOperators.get(right))
                );
            }
        }

        return Optional.empty();
    }
}
