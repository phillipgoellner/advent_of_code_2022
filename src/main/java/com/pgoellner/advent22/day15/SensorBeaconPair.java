package com.pgoellner.advent22.day15;

import com.pgoellner.advent22.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public record SensorBeaconPair(Point sensor, Point beacon) {

    public Stream<Point> sensorCoverageForLine(int line) {
        int range = DistanceCalculator.manhattanDistance(sensor, beacon);
        List<Point> coveredPoints = new ArrayList<>();

        for (int x = sensor.x() - range; x < sensor.x() + range; x++) {
                Point currentPoint = new Point(x, line);
                if (DistanceCalculator.manhattanDistance(sensor, currentPoint) <= range) {
                    coveredPoints.add(currentPoint);
                }
        }

        return coveredPoints.stream();
    }
}
