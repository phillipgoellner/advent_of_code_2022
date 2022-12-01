package com.pgoellner.advent22;

import java.util.List;

public record TestInput(List<String> lines) implements PuzzleInput {

    @Override
    public List<String> lines() {
        // Generate a copy of the list
        return this.lines.stream().toList();
    }
}
