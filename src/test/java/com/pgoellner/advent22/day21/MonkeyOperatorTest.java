package com.pgoellner.advent22.day21;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class MonkeyOperatorTest {
    private static final List<String> gasJets = List.of(
            "root: pppw + sjmn",
                    "dbpl: 5",
                    "cczh: sllz + lgvd",
                    "zczc: 2",
                    "ptdq: humn - dvpt",
                    "dvpt: 3",
                    "lfqf: 4",
                    "humn: 5",
                    "ljgn: 2",
                    "sjmn: drzm * dbpl",
                    "sllz: 4",
                    "pppw: cczh / lfqf",
                    "lgvd: ljgn * ptdq",
                    "drzm: hmdt - zczc",
                    "hmdt: 32");

    private final static PuzzleInput puzzleInput = new TestInput(gasJets);

    public static void main(String[] args) {
        root_yells_152();
    }

    private static void root_yells_152() {
        long operationResult = new MonkeyOperators().resolve(puzzleInput);
        assert operationResult == 152 : "Expected tower height of 152, but got " + operationResult;
    }
}
