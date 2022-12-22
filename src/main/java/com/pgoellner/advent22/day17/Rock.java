package com.pgoellner.advent22.day17;

import com.pgoellner.advent22.Point;

import java.util.List;

public enum Rock {
    Minus(1, 4, List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0))),
    Plus(3, 3, List.of(new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2))),
    L(3, 3, List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(2, 1), new Point(2, 2))),
    I(4, 1, List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3))),
    Square(2, 2, List.of(new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)));

    Rock(int height, int width, List<Point> points) {
        this.height = height;
        this.width = width;
        this.points = points;
    }

    public final int height;
    public final int width;
    public final List<Point> points;

    public List<Point> translateFromPosition(Point position) {
        return points.stream().map(point -> new Point(point.x() + position.x(), point.y() + position.y())).toList();
    }
}
