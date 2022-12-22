package com.pgoellner.advent22.day17;

import com.pgoellner.advent22.Point;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RockPosition {
    private final Rock rockType;
    private Point position;
    private boolean stopped;

    public RockPosition(Rock type, Point startingPosition) {
        rockType = type;
        position = startingPosition;
        stopped = false;
    }

    public void jetPush(PushDirection direction, List<RockPosition> presentRocks) {
        Point oldPosition = position;

        switch (direction) {
            case Left -> {
                if (position.x() > 0) {
                    position = new Point(position.x() - 1, position.y());
                }
            }
            case Right -> {
                if (position.x() + rockType.width < 7) {
                    position = new Point(position.x() + 1, position.y());
                }
            }
        }

        if (collidesWith(presentRocks)) {
            position = oldPosition;
        }
    }

    public void fallDown(List<RockPosition> presentRocks) {
        if (!stopped) {
            position = new Point(position.x(), position.y() - 1);
            if (collidesWith(presentRocks)) {
                position = new Point(position.x(), position.y() + 1);
                stopped = true;
            } else if (position.y() == -1) {
                position = new Point(position.x(), 0);
                stopped = true;
            }
        }
    }

    private boolean collidesWith(List<RockPosition> presentRocks) {
        Set<Point> filledPoints = presentRocks.stream()
                .flatMap(rock -> rock.rockType.translateFromPosition(rock.position).stream())
                .collect(Collectors.toSet());

        return rockType.translateFromPosition(position).stream().anyMatch(filledPoints::contains);
    }

    public boolean stopped() {
        return this.stopped;
    }

    public int toweringHeight() {
        return position.y() + rockType.height;
    }
}
