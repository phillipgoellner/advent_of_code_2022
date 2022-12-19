package com.pgoellner.advent22.day14;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.day09.Point;
import com.pgoellner.advent22.day13.PacketChecker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SandFallingSimulator {
    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_14.txt");
        System.out.println("Part 1: " + new SandFallingSimulator().sandUnitCapacity(puzzleInput));
    }

    public int sandUnitCapacity(PuzzleInput puzzleInput) {
        Point sandStart = new Point(500, 0);
        Set<Point> rockStructure = puzzleInput.lines().stream().flatMap(this::rockPathFromScan).collect(Collectors.toSet());

        return sandCapacity(sandStart, rockStructure);
    }

    public int sandCapacity(Point sandStart, Set<Point> rockStructure) {
        Set<Point> sandUnits = new HashSet<>();

        int maxHeight = 0;

        for (Point rock : rockStructure) {
            if (maxHeight < rock.y()) {
                maxHeight = rock.y();
            }
        }

        Optional<Point> sandLocation = sandRestingPlace(sandStart, rockStructure, sandUnits, maxHeight);

        while (sandLocation.isPresent()) {
            sandUnits.add(sandLocation.get());
            sandLocation = sandRestingPlace(sandStart, rockStructure, sandUnits, maxHeight);
        }

        return sandUnits.size();
    }

    private Stream<Point> rockPathFromScan(String scan) {
        String[] waypoints = scan.split(" -> ");
        List<Point> path = new ArrayList<>();

        for (int i = 0; i < waypoints.length - 1; i++) {
            Point start = fromString(waypoints[i]);
            Point end = fromString(waypoints[i + 1]);

            path.addAll(allPointsBetween(start, end));
        }

        return path.stream();
    }

    private Point fromString(String pointDescriptor) {
        String[] coordinates = pointDescriptor.split(",");
        return new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }

    private List<Point> allPointsBetween(Point a, Point b) {
        List<Point> points = new ArrayList<>();

        if (a.x() == b.x()) {
            if (a.y() > b.y()) {
                Point tmp = b;
                b = a;
                a = tmp;
            }

            for (int i = a.y(); i <= b.y(); i++) {
                points.add(new Point(a.x(), i));
            }
        } else if (a.y() == b.y()) {
            if (a.x() > b.x()) {
                Point tmp = b;
                b = a;
                a = tmp;
            }

            for (int i = a.x(); i <= b.x(); i++) {
                points.add(new Point(i, a.y()));
            }
        }

        return points;
    }

    private Optional<Point> sandRestingPlace(Point sandStart, Set<Point> rockStructure, Set<Point> sandLocations, int maxHeight) {
        Point sand = new Point(sandStart.x(), sandStart.y());

        while (sand.y() <= maxHeight) {
            if (isBlocked(new Point(sand.x(), sand.y() + 1), rockStructure, sandLocations)) {
                if (isBlocked(new Point(sand.x() - 1, sand.y() + 1), rockStructure, sandLocations)) {
                    if (isBlocked(new Point(sand.x() + 1, sand.y() + 1), rockStructure, sandLocations)) {
                        return Optional.of(sand);
                    } else {
                        sand = new Point(sand.x() + 1, sand.y() + 1);
                    }
                } else {
                    sand = new Point(sand.x() - 1, sand.y() + 1);
                }

            } else {
                sand = new Point(sand.x(), sand.y() + 1);
            }
        }

        return Optional.empty();
    }

    private boolean isBlocked(Point point, Set<Point> rockStructure, Set<Point> sandLocations) {
        return rockStructure.contains(point) || sandLocations.contains(point);
    }
}
