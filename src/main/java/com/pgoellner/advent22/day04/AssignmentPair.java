package com.pgoellner.advent22.day04;

public class AssignmentPair {
    private final Range leftAssignment;
    private final Range rightAssignment;

    public AssignmentPair(String assignmentLine) {
        String[] splitLine = assignmentLine.split(",");
        leftAssignment = new Range(splitLine[0]);
        rightAssignment = new Range(splitLine[1]);
    }

    public boolean oneRangeContainsAnother() {
        return leftAssignment.contains(rightAssignment) || rightAssignment.contains(leftAssignment);
    }

    class Range {
        private final int start;
        private final int end;

        public Range(String rangeString) {
            String[] splitRange = rangeString.split("-");
            start = Integer.parseInt(splitRange[0]);
            end = Integer.parseInt(splitRange[1]);
        }

        boolean contains(Range other) {
            return this.start <= other.start && this.end >= other.end;
        }
    }
}
