package com.pgoellner.advent22.day16;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Valve {
    public final String name;
    private boolean opened;
    private final int flowRate;
    private final List<String> connectsTo;

    public Valve(String pipeString) {
        name = pipeString.replace("Valve ", "").split(" ")[0];
        opened = false;
        flowRate = Integer.parseInt(pipeString.split("has flow rate=")[1].split(";")[0]);
        connectsTo = List.of(
                pipeString
                        .replace("tunnel leads to valve", "tunnels lead to valves")
                        .split("tunnels lead to valves ")[1]
                        .split(", ")
        );
    }

    public void open() {
        opened = true;
    }

    public Stream<ValveTuple> availableTuples(int timeLeft, Map<String, Valve> valveLookup) {
        if (timeLeft <= 0) {
            return Stream.of();
        }

        return availableTuples(timeLeft, valveLookup, Set.of(), 0);
    }

    private Stream<ValveTuple> availableTuples(int timeLeft, Map<String, Valve> valveLookup, Set<String> visitedValves, int distanceTraveled) {
        if (timeLeft <= 0 || distanceTraveled > timeLeft) {
            return Stream.of();
        }

        int openingCost = distanceTraveled + 1;

        return Stream.concat(
                Stream.of(new ValveTuple(this, maximumPossiblePressureRelease(timeLeft - openingCost), openingCost)),
                connectsTo
                        .stream()
                        .filter(valve -> !visitedValves.contains(valve))
                        .map(valveLookup::get)
                        .flatMap(valve -> valve.availableTuples(
                                        timeLeft,
                                        valveLookup,
                                        Stream.concat(visitedValves.stream(), Stream.of(name)).collect(Collectors.toSet()),
                                        distanceTraveled + 1
                                )
                        )
        );
    }

    public int maximumPossiblePressureRelease(int minutesLeft) {
        if (opened) {
            return 0;
        }
        return minutesLeft * this.flowRate;
    }


    public String toString() {
        return name;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
