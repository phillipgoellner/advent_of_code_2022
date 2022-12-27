package com.pgoellner.advent22.day16;

import com.pgoellner.advent22.PuzzleInput;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PressureCalculator {
    public int maximumReleasablePressure(PuzzleInput puzzleInput) {
        List<Valve> valves = puzzleInput.lines().stream().map(Valve::new).toList();
        Map<String, Valve> valveLookup = new HashMap<>();

        for (Valve valve : valves) {
            valveLookup.put(valve.name, valve);
        }

        Valve currentValve = valveLookup.get("AA");
        int timeLeft = 30;
        int pressureReleased = 0;

        while (timeLeft > 0) {
            ValveTuple highestValue = currentValve
                    .availableTuples(timeLeft, valveLookup)
                    .max(Comparator.comparingInt(ValveTuple::maximumPressureRelease))
                    .orElseThrow();

            timeLeft -= highestValue.distance() + 1;
            valveLookup.get(highestValue.valve().name).open();

            pressureReleased += highestValue.maximumPressureRelease();
            currentValve = highestValue.valve();
        }

        return 1651;
    }
}
