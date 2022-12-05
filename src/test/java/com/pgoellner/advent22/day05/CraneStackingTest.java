package com.pgoellner.advent22.day05;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;
import java.util.Stack;

public class CraneStackingTest {
    private static final List<String> cleanupTasks = List.of(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 ",
            "",
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2");

    private final static PuzzleInput puzzleInput = new TestInput(cleanupTasks);

    public static void main(String[] args) {
        returns_cmz_as_crate_message();
        creates_empty_CreateStacks();
        parses_lines_for_non_empty_stacks();
        applies_single_instruction_correctly();
        top_crate_message();

        returns_mcd_as_crate_message();

        System.out.println("All tests passed");
    }

    static void returns_cmz_as_crate_message() {
        String createMessage = new CraneOperation().followStackingProcedure(puzzleInput);
        assert createMessage.equals("CMZ") : "Expected message 'CMZ' but got '" + createMessage + "'";
    }

    static void returns_mcd_as_crate_message() {
        String createMessage = new CraneOperation().followStackingProcedure9001(puzzleInput);
        assert createMessage.equals("MCD") : "Expected message 'MCD' but got '" + createMessage + "'";
    }

    static void creates_empty_CreateStacks() {
        CrateStacks crateStacks = new CrateStacks(List.of(" 1   2   3 "));

        assert crateStacks.equals(new CrateStacks(List.of(new Stack<>(), new Stack<>(), new Stack<>()), true));
    }

    static void parses_lines_for_non_empty_stacks() {
        CrateStacks crateStacks = new CrateStacks(List.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3 "));

        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        Stack<String> stack3 = new Stack<>();

        stack1.push("Z");
        stack1.push("N");

        stack2.push("M");
        stack2.push("C");
        stack2.push("D");

        stack3.push("P");

        assert crateStacks.equals(new CrateStacks(List.of(stack1, stack2, stack3), true));
    }

    static void applies_single_instruction_correctly() {
        CrateStacks crateStacks = new CrateStacks(List.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3 "));

        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        Stack<String> stack3 = new Stack<>();

        stack1.push("Z");
        stack1.push("N");
        stack1.push("D");

        stack2.push("M");
        stack2.push("C");

        stack3.push("P");

        crateStacks.apply(new Instruction("move 1 from 2 to 1"));

        assert crateStacks.equals(new CrateStacks(List.of(stack1, stack2, stack3), true));
    }

    static void top_crate_message() {
        CrateStacks crateStacks = new CrateStacks(List.of(
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3 "));

        String topCrateMessage = crateStacks.topCrateMessage();
        assert topCrateMessage.equals("NDP") : "Expected 'NDP' as top crates, but got '" + topCrateMessage + "'";
    }
}
