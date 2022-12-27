package com.pgoellner.advent22.day12;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PathFinder {

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_12.txt");
        System.out.println("Part 1: " + new PathFinder().stepsForShortestPath(puzzleInput));
    }

    public int stepsForShortestPath(PuzzleInput puzzleInput) {
        List<String> inputLines = puzzleInput.lines();

        char[][] heightMap = new char[inputLines.size()][inputLines.get(0).length()];
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);

        for (int row = 0; row < heightMap.length; row++) {
            for (int col = 0; col < heightMap[row].length; col++) {
                heightMap[row][col] = inputLines.get(row).charAt(col);

                if (heightMap[row][col] == 'S') {
                    start = new Point(row, col);
                    heightMap[row][col] = 'a';
                } else if (heightMap[row][col] == 'E') {
                    end = new Point(row, col);
                    heightMap[row][col] = 'z';
                }
            }
        }

        Map<Point, List<Point>> connectionMapping = new HashMap<>();

        for (int row = 0; row < heightMap.length; row++) {
            for (int col = 0; col < heightMap[row].length; col++) {
                connectionMapping.put(new Point(row, col), feasiblePoints(heightMap, new Point(row, col)));
            }
        }

        return findPaths(start, end, connectionMapping).stream().map(Path::stepsRequired).sorted().findFirst().orElseThrow();
    }

    private List<Point> feasiblePoints(char[][] heightMap, Point startingPosition) {
        List<Point> connections = new ArrayList<>();
        char pointHeight = heightMap[(int) startingPosition.x()][(int) startingPosition.y()];

        connections.add(new Point(startingPosition.x(), startingPosition.y() - 1));
        connections.add(new Point(startingPosition.x(), startingPosition.y() + 1));
        connections.add(new Point(startingPosition.x() - 1, startingPosition.y()));
        connections.add(new Point(startingPosition.x() + 1, startingPosition.y()));

        return connections.stream().filter(point -> {
            try {
                char height = heightMap[(int) point.x()][(int) point.y()];

                return height - pointHeight == 0 || height - pointHeight == 1;
            } catch (ArrayIndexOutOfBoundsException ignored) {
                return false;
            }
        }).toList();
    }

    private List<Path> findPaths(Point start, Point end, Map<Point, List<Point>> connections) {
        return findPaths(start, end, connections, new Path().addWaypoint(start));
    }

    private List<Path> findPaths(Point start, Point end, Map<Point, List<Point>> connections, Path path) {
        List<Point> connectingPoints = connections.get(start);
        if (connectingPoints.contains(end)) {
            return List.of(path.addWaypoint(end));
        } else {
            return connectingPoints.stream().flatMap(point -> {
                if (path.containsWaypoint(point)) {
                    return Stream.of();
                } else {
                    return findPaths(point, end, connections, path.addWaypoint(point)).stream();
                }
            }).toList();
        }
    }
}

class Path {
    private final List<Point> wayPoints = new ArrayList<>();

    public Path addWaypoint(Point point) {
        Path newPath = new Path();
        newPath.wayPoints.addAll(wayPoints);
        newPath.wayPoints.add(point);

        return newPath;
    }

    public boolean containsWaypoint(Point waypoint) {
        return wayPoints.contains(waypoint);
    }

    public int stepsRequired() {
        return wayPoints.size() - 1;
    }
}
