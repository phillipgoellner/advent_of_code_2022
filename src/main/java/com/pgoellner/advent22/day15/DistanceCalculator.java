package com.pgoellner.advent22.day15;

import com.pgoellner.advent22.Point;

public class DistanceCalculator {

    public static int manhattanDistance(Point p1, Point p2) {
        int xDistance = Math.abs(p1.x() - p2.x());
        int yDistance = Math.abs(p1.y() - p2.y());

        return xDistance + yDistance;
    }
}

