package com.pgoellner.advent22.day11;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class KeepAwayMonkeysTest {

    private static final List<String> dataStreamBuffer = List.of(
            "Monkey 0:",
            "  Starting items: 79, 98",
            "  Operation: new = old * 19",
            "  Test: divisible by 23",
            "    If true: throw to monkey 2",
            "    If false: throw to monkey 3",
            "",
            "Monkey 1:",
            "  Starting items: 54, 65, 75, 74",
            "  Operation: new = old + 6",
            "  Test: divisible by 19",
            "    If true: throw to monkey 2",
            "    If false: throw to monkey 0",
            "",
            "Monkey 2:",
            "  Starting items: 79, 60, 97",
            "  Operation: new = old * old",
            "  Test: divisible by 13",
            "    If true: throw to monkey 1",
            "    If false: throw to monkey 3",
            "",
            "Monkey 3:",
            "  Starting items: 74",
            "  Operation: new = old + 3",
            "  Test: divisible by 17",
            "    If true: throw to monkey 0",
            "    If false: throw to monkey 1",
            "");

    private final static PuzzleInput puzzleInput = new TestInput(dataStreamBuffer);

    public static void main(String[] args) {
        monkey_business_of_10605_after_20_rounds();
    }

    static void monkey_business_of_10605_after_20_rounds() {
        int monkeyBusiness = new MonkeyBusinessAnalyser().simulateMonkeyBusiness(puzzleInput, 20);
        assert monkeyBusiness == 10605 : "Expected monkey business of 10605, but got " + monkeyBusiness;
    }
}
