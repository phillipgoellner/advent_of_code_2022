package com.pgoellner.advent22.day13;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.day12.PathFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PacketChecker {
    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_13.txt");
        System.out.println("Part 1: " + new PacketChecker().sumOfOrderedIndices(puzzleInput));
    }

    public int sumOfOrderedIndices(PuzzleInput puzzleInput) {
        return toPacketPairs(puzzleInput).stream()
                .filter(PacketChecker::packetsAreInOrder)
                .map(PacketPair::index)
                .reduce(0, Integer::sum);
    }

    private List<PacketPair> toPacketPairs(PuzzleInput puzzleInput) {
        int lineNumber = puzzleInput.lines().size();

        return IntStream
                .range(0, (lineNumber + 1) / 3)
                .mapToObj(index -> toPacketPair(index + 1, puzzleInput.lines().subList(index * 3, index * 3 + 3))).toList();
    }

    private PacketPair toPacketPair(int index, List<String> inputLines) {
        PacketValue left = toValue(inputLines.get(0));
        PacketValue right = toValue(inputLines.get(1));

        return new PacketPair(index, left, right);
    }

    private PacketValue toValue(String line) {
        if (line.equals("[]")) {
            return new PacketValueList();
        } else if (!line.contains(",")) {
            if (!line.contains("[")) {
                return new PacketValueInt(Integer.parseInt(line));
            }
        } else if (!line.contains("[")) {
            return new PacketValueList(
                    Stream.of(line.split(",")).map(this::toValue).toList()
            );
        }

        List<String> tokens = new ArrayList<>();
        String buffer = "";
        int bracketPairs = 0;

        for (char character : line.substring(1, line.length() - 1).toCharArray()) {
            if (character == '[') {
                bracketPairs++;
            } else if (character == ']') {
                bracketPairs--;
            }

            if (bracketPairs == 0) {
                if (character == ',') {
                    tokens.add(buffer);
                    buffer = "";
                } else {
                    buffer += character;
                }
            } else {
                buffer += character;
            }
        }

        if (!buffer.equals("")) {
            tokens.add(buffer);
        }

        return new PacketValueList(
                tokens.stream().map(this::toValue).toList()
        );
    }

    public static boolean packetsAreInOrder(PacketPair pair) {
        PacketValue left = pair.left();
        PacketValue right = pair.right();

        ComparisonResult comparisonResult = comparePackets(left, right);
        return (comparisonResult == ComparisonResult.RightBigger);
    }

    public static ComparisonResult comparePackets(PacketValue left, PacketValue right) {
        if (left instanceof PacketValueInt && right instanceof PacketValueInt) {
            int leftValue = ((PacketValueInt) left).value;
            int rightValue = ((PacketValueInt) right).value;

            if (leftValue > rightValue) {
                return ComparisonResult.LeftBigger;
            } else if (leftValue < rightValue) {
                return ComparisonResult.RightBigger;
            } else {
                return ComparisonResult.Equal;
            }
        } else if (left instanceof PacketValueList && right instanceof PacketValueList) {
            int comparisonIndex = 0;
            int rightMax = ((PacketValueList) right).values().size();

            for (PacketValue value : ((PacketValueList) left).values()) {
                if (comparisonIndex == rightMax) {
                    return ComparisonResult.LeftBigger;
                }

                ComparisonResult itemComparisonResult = comparePackets(
                        value,
                        ((PacketValueList) right).values().get(comparisonIndex)
                );

                if (itemComparisonResult != ComparisonResult.Equal) {
                    return itemComparisonResult;
                }

                comparisonIndex++;
            }

            if (comparisonIndex + 1 <= rightMax) {
                return ComparisonResult.RightBigger;
            } else {
                return ComparisonResult.Equal;
            }

        } else if (left instanceof PacketValueInt) {
            return comparePackets(new PacketValueList(left), right);
        } else {
            return comparePackets(left, new PacketValueList(right));
        }
    }
}

record PacketPair(int index, PacketValue left, PacketValue right) {
}

class PacketValue {
}

class PacketValueList extends PacketValue {
    private final List<PacketValue> values;

    public PacketValueList(List<PacketValue> values) {
        this.values = values;
    }

    public PacketValueList(PacketValue... values) {
        this.values = List.of(values);
    }

    public List<PacketValue> values() {
        return values.stream().toList();
    }

    public String toString() {
        return values.toString();
    }
}

class PacketValueInt extends PacketValue {
    public final int value;

    public PacketValueInt(int value) {
        this.value = value;
    }

    public String toString() {
        return "" + value;
    }
}

enum ComparisonResult {
    LeftBigger, RightBigger, Equal
}
