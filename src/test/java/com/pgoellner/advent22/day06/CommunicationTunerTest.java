package com.pgoellner.advent22.day06;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;
import java.util.Map;

public class CommunicationTunerTest {
    private static final List<String> dataStreamBuffer = List.of("mjqjpqmgbljsphdztnvjfqwrcgsmlb");

    private final static PuzzleInput puzzleInput = new TestInput(dataStreamBuffer);

    public static void main(String[] args) {
        returns_7_as_marker_index();
        test_multiple_test_inputs();

        System.out.println("All tests passed");
    }

    static void returns_7_as_marker_index() {
        int markerIndex = new CommunicationTuner().findMarkerIndex(puzzleInput, 4);
        assert markerIndex == 7 : "Marker index of 7 got " + markerIndex;
    }

    static void test_multiple_test_inputs() {
        Map<String, Integer> testInputs = Map.of(
                "bvwbjplbgvbhsrlpgdmjqwftvncz", 5,
                "nppdvjthqldpwncqszvftbrmjlhg", 6,
                "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10,
                "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11
        );

        for (String messageBuffer : testInputs.keySet()) {
            int markerIndex = new CommunicationTuner().findMarkerIndex(new TestInput(List.of(messageBuffer)), 4);
            int expectedIndex = testInputs.get(messageBuffer);

            assert markerIndex == expectedIndex : "Marker index of " + expectedIndex + " got " + markerIndex;
        }
    }
}
