package com.pgoellner.advent22.day05;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

import java.util.List;

public class CraneOperation {

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_05.txt");
        System.out.println("Part 1: " + new CraneOperation().followStackingProcedure(puzzleInput));
    }

    public String followStackingProcedure(PuzzleInput puzzleInput) {
        Drawing procedureDrawing = new Drawing(puzzleInput.lines());
        return procedureDrawing.resolveProcedure();
    }

    class Drawing {
        private final CrateStacks initialStack;
        private final List<Instruction> procedure;

        public Drawing(List<String> drawingLines) {
            int separatorIndex = drawingLines.indexOf("");

            initialStack = new CrateStacks(drawingLines.subList(0, separatorIndex));
            procedure = drawingLines.subList(separatorIndex + 1, drawingLines.size()).stream().map(Instruction::new).toList();
        }

        public String resolveProcedure() {
            procedure.forEach(initialStack::apply);

            return initialStack.topCrateMessage();
        }
    }
}
