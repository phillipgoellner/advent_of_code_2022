package com.pgoellner.advent22.day16;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class ValvePressureTest {
    private static final List<String> valveOverview = List.of(
            "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
            "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
            "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
            "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
            "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
            "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
            "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
            "Valve HH has flow rate=22; tunnel leads to valve GG",
            "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
            "Valve JJ has flow rate=21; tunnel leads to valve II");

    private final static PuzzleInput puzzleInput = new TestInput(valveOverview);

    public static void main(String[] args) {
        maximum_pressure_release_of_1651();
    }

    private static void maximum_pressure_release_of_1651() {
        int pressureReleased = new PressureCalculator().maximumReleasablePressure(puzzleInput);
        assert pressureReleased == 1651 : "Expected 1651 released pressure, but got " + pressureReleased;
    }
}
