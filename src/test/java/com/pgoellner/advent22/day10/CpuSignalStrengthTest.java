package com.pgoellner.advent22.day10;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class CpuSignalStrengthTest {
    private static final List<String> dataStreamBuffer;

    static {
        dataStreamBuffer = List.of(
                "addx 15",
                "addx -11",
                "addx 6",
                "addx -3",
                "addx 5",
                "addx -1",
                "addx -8",
                "addx 13",
                "addx 4",
                "noop",
                "addx -1",
                "addx 5",
                "addx -1",
                "addx 5",
                "addx -1",
                "addx 5",
                "addx -1",
                "addx 5",
                "addx -1",
                "addx -35",
                "addx 1",
                "addx 24",
                "addx -19",
                "addx 1",
                "addx 16",
                "addx -11",
                "noop",
                "noop",
                "addx 21",
                "addx -15",
                "noop",
                "noop",
                "addx -3",
                "addx 9",
                "addx 1",
                "addx -3",
                "addx 8",
                "addx 1",
                "addx 5",
                "noop",
                "noop",
                "noop",
                "noop",
                "noop",
                "addx -36",
                "noop",
                "addx 1",
                "addx 7",
                "noop",
                "noop",
                "noop",
                "addx 2",
                "addx 6",
                "noop",
                "noop",
                "noop",
                "noop",
                "noop",
                "addx 1",
                "noop",
                "noop",
                "addx 7",
                "addx 1",
                "noop",
                "addx -13",
                "addx 13",
                "addx 7",
                "noop",
                "addx 1",
                "addx -33",
                "noop",
                "noop",
                "noop",
                "addx 2",
                "noop",
                "noop",
                "noop",
                "addx 8",
                "noop",
                "addx -1",
                "addx 2",
                "addx 1",
                "noop",
                "addx 17",
                "addx -9",
                "addx 1",
                "addx 1",
                "addx -3",
                "addx 11",
                "noop",
                "noop",
                "addx 1",
                "noop",
                "addx 1",
                "noop",
                "noop",
                "addx -13",
                "addx -19",
                "addx 1",
                "addx 3",
                "addx 26",
                "addx -30",
                "addx 12",
                "addx -1",
                "addx 3",
                "addx 1",
                "noop",
                "noop",
                "noop",
                "addx -9",
                "addx 18",
                "addx 1",
                "addx 2",
                "noop",
                "noop",
                "addx 9",
                "noop",
                "noop",
                "noop",
                "addx -1",
                "addx 2",
                "addx -37",
                "addx 1",
                "addx 3",
                "noop",
                "addx 15",
                "addx -21",
                "addx 22",
                "addx -6",
                "addx 1",
                "noop",
                "addx 2",
                "addx 1",
                "noop",
                "addx -10",
                "noop",
                "noop",
                "addx 20",
                "addx 1",
                "addx 2",
                "addx 2",
                "addx -6",
                "addx -11",
                "noop",
                "noop",
                "noop");
    }

    private final static PuzzleInput puzzleInput = new TestInput(dataStreamBuffer);

    public static void main(String[] args) {
        total_signal_strength_of_13140();
        signal_strength_of_420_at_20th_cycle();
        signal_strength_of_1140_at_60th_cycle();
        signal_strength_of_1800_at_100th_cycle();
        signal_strength_of_2940_at_140th_cycle();
        signal_strength_of_2880_at_180th_cycle();
        signal_strength_of_3960_at_220th_cycle();

        crt_produces_correct_image();
    }

    static void total_signal_strength_of_13140() {
        int totalSignalStrength = new CpuSignalStrengthCalculator().strengthProduct(puzzleInput);
        assert totalSignalStrength == 13140 : "Expected signal strength of 13140, but instead got " + totalSignalStrength;
    }

    static void signal_strength_of_420_at_20th_cycle() {
        int signalStrength = new CpuSignalStrengthCalculator().strengthInCycle(puzzleInput, 20);
        assert signalStrength == 420 : "Expected signal strength of 420 in cycle 20, but instead got " + signalStrength;
    }

    static void signal_strength_of_1140_at_60th_cycle() {
        int signalStrength = new CpuSignalStrengthCalculator().strengthInCycle(puzzleInput, 60);
        assert signalStrength == 1140 : "Expected signal strength of 1140 in cycle 60, but instead got " + signalStrength;
    }

    static void signal_strength_of_1800_at_100th_cycle() {
        int signalStrength = new CpuSignalStrengthCalculator().strengthInCycle(puzzleInput, 100);
        assert signalStrength == 1800 : "Expected signal strength of 1800 in cycle 100, but instead got " + signalStrength;
    }

    static void signal_strength_of_2940_at_140th_cycle() {
        int signalStrength = new CpuSignalStrengthCalculator().strengthInCycle(puzzleInput, 140);
        assert signalStrength == 2940 : "Expected signal strength of 2940 in cycle 140, but instead got " + signalStrength;
    }

    static void signal_strength_of_2880_at_180th_cycle() {
        int signalStrength = new CpuSignalStrengthCalculator().strengthInCycle(puzzleInput, 180);
        assert signalStrength == 2880 : "Expected signal strength of 2880 in cycle 180, but instead got " + signalStrength;
    }

    static void signal_strength_of_3960_at_220th_cycle() {
        int signalStrength = new CpuSignalStrengthCalculator().strengthInCycle(puzzleInput, 220);
        assert signalStrength == 3960 : "Expected signal strength of 3960 in cycle 220, but instead got " + signalStrength;
    }

    static void crt_produces_correct_image() {
        String expectedImage = """
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....""";

        String crtImage = new CpuSignalStrengthCalculator().produceCrtImage(puzzleInput);
        assert expectedImage.equals(crtImage): "Expected image did not match produced image:\n" + crtImage;
    }
}
