package com.pgoellner.advent22.day10;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

import java.util.List;
import java.util.Stack;

public class CpuSignalStrengthCalculator {
    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_10.txt");
        System.out.println("Part 1: " + new CpuSignalStrengthCalculator().strengthProduct(puzzleInput));
        System.out.println("Part 2:\n" + new CpuSignalStrengthCalculator().produceCrtImage(puzzleInput));
    }

    public int strengthProduct(PuzzleInput puzzleInput) {
        int totalStrength = 0;

        totalStrength += strengthInCycle(puzzleInput, 20);
        totalStrength += strengthInCycle(puzzleInput, 60);
        totalStrength += strengthInCycle(puzzleInput, 100);
        totalStrength += strengthInCycle(puzzleInput, 140);
        totalStrength += strengthInCycle(puzzleInput, 180);
        totalStrength += strengthInCycle(puzzleInput, 220);

        return totalStrength;
    }

    public int strengthInCycle(PuzzleInput puzzleInput, int cycle) {
        Cpu cpu = new Cpu();
        cpu.loadInstructions(puzzleInput.lines());

        cpu.runCycles(cycle);

        return cpu.registerValue() * cycle;
    }

    public String produceCrtImage(PuzzleInput puzzleInput) {
        return new Crt(puzzleInput.lines()).produceImage();
    }
}

class Cpu {
    private int registerX;
    private final Stack<String> instructionMemory;

    public Cpu() {
        this.registerX = 1;
        this.instructionMemory = new Stack<>();
    }

    public void loadInstructions(List<String> instructions) {
        for (int i = instructions.size() - 1; i >= 0; i--) {
            this.instructionMemory.push(instructions.get(i));
        }
    }

    public void runCycles(int numberOfCycles) {
        for (int i = 0; i < numberOfCycles - 1; i++) {
            oneCycle();
        }
    }

    public void oneCycle() {
        String cycleInstruction = this.instructionMemory.pop();

        if (cycleInstruction.startsWith("addx")) {
            this.instructionMemory.push("[p] " + cycleInstruction);
        } else if (cycleInstruction.startsWith("[p] addx")) {
            int addValue = Integer.parseInt(cycleInstruction.replace("[p] addx ", ""));
            this.registerX += addValue;
        }
    }

    public int registerValue() {
        return registerX;
    }
}

class Crt {
    private final Cpu cpu;
    private int drawingPosition;

    public Crt(List<String> cpuInstructions) {
        cpu = new Cpu();
        cpu.loadInstructions(cpuInstructions);

        drawingPosition = 0;
    }

    public String produceImage() {

        String image = "";

        for (int i = 0; i < 240; i++) {
            if (i != 0 && i % 40 == 0) {
                image += "\n";
                drawingPosition = 0;
            }

            if (Math.abs(cpu.registerValue() - drawingPosition) <= 1) {
                image += "#";
            } else {
                image += ".";
            }

            cpu.oneCycle();
            drawingPosition++;
        }

        return image;
    }
}
