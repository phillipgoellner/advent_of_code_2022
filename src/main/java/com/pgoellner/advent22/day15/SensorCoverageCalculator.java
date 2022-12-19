package com.pgoellner.advent22.day15;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.Point;
import com.pgoellner.advent22.PuzzleInput;

import java.util.Set;
import java.util.stream.Collectors;

public class SensorCoverageCalculator {
    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_15.txt");
        System.out.println("Part 1: " + new SensorCoverageCalculator().coverageOnLine(puzzleInput, 2_000_000));
    }

    public int coverageOnLine(PuzzleInput puzzleInput, int line) {
        Set<Point> coveredPoints = puzzleInput
                .lines().stream()
                .map(this::fromInputLine)
                .flatMap(pair -> pair.sensorCoverageForLine(line).filter(point -> point.y() == line))
                .collect(Collectors.toSet());

        puzzleInput
                .lines().stream()
                .map(this::fromInputLine)
                .forEach(pair -> coveredPoints.remove(pair.beacon()));

        return coveredPoints.size();
    }

    private SensorBeaconPair fromInputLine(String line) {
        return new SensorBeaconPair(sensorFromInputLine(line), beaconFromInputLine(line));
    }

    private Point sensorFromInputLine(String line) {
        String[] sensorSplit = line.replace("Sensor at x=", "").split(", y=");

        int x = Integer.parseInt(sensorSplit[0]);
        int y = Integer.parseInt(sensorSplit[1].split(":")[0]);

        return new Point(x, y);
    }

    private Point beaconFromInputLine(String line) {
        String[] beaconSplit = line.split(": closest beacon is at x=")[1].split(", y=");

        int x = Integer.parseInt(beaconSplit[0]);
        int y = Integer.parseInt(beaconSplit[1]);

        return new Point(x, y);
    }

}
