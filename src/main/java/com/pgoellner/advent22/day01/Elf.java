package com.pgoellner.advent22.day01;

import java.util.List;

public class Elf {
    private final int calories;

    public Elf(List<String> caloriesLines) {
        this.calories = caloriesLines.stream().map(Integer::parseInt).reduce(0, Integer::sum);
    }

    public int calories() {
        return this.calories;
    }
}
