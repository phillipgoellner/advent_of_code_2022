package com.pgoellner.advent22.day13;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class PacketComparatorTest {
    private static final List<String> packets = List.of(
            "[1,1,3,1,1]",
            "[1,1,5,1,1]",
            "",
            "[[1],[2,3,4]]",
            "[[1],4]",
            "",
            "[9]",
            "[[8,7,6]]",
            "",
            "[[4,4],4,4]",
            "[[4,4],4,4,4]",
            "",
            "[7,7,7,7]",
            "[7,7,7]",
            "",
            "[]",
            "[3]",
            "",
            "[[[]]]",
            "[[]]",
            "",
            "[1,[2,[3,[4,[5,6,7]]]],8,9]",
            "[1,[2,[3,[4,[5,6,0]]]],8,9]",
            "");

    private final static PuzzleInput puzzleInput = new TestInput(packets);

    public static void main(String[] args) {
        sum_of_indices_is_13();
        integers_in_right_order();
        integers_out_of_order();
        lists_in_right_order();
        lists_in_right_order_2();
        lists_out_of_order();
        lists_out_of_order_2();
        mixed_in_order();
        mixed_in_order_2();
        mixed_in_order_3();
        mixed_out_of_order();
        mixed_out_of_order_2();

        packet_sorting_dividers_product_is_140();
    }

    static void sum_of_indices_is_13() {
        int sum_of_indices = new PacketChecker().sumOfOrderedIndices(puzzleInput);
        assert sum_of_indices == 13 : "Expected sum of indices to be 13, but got " + sum_of_indices;
    }

    static void integers_in_right_order() {
        ComparisonResult result = PacketChecker.comparePackets(new PacketValueInt(0), new PacketValueInt(1));
        assert result == ComparisonResult.RightBigger;
    }

    static void integers_out_of_order() {
        ComparisonResult result = PacketChecker.comparePackets(new PacketValueInt(2), new PacketValueInt(1));
        assert result == ComparisonResult.LeftBigger;
    }

    static void lists_in_right_order() {
        ComparisonResult result = PacketChecker.comparePackets(
                new PacketValueList(new PacketValueInt(1), new PacketValueInt(1), new PacketValueInt(1)),
                new PacketValueList(new PacketValueInt(1), new PacketValueInt(1), new PacketValueInt(2))
        );
        assert result == ComparisonResult.RightBigger;
    }

    static void lists_in_right_order_2() {
        ComparisonResult result = PacketChecker.comparePackets(
                new PacketValueList(new PacketValueInt(1), new PacketValueInt(1), new PacketValueInt(1)),
                new PacketValueList(new PacketValueInt(1), new PacketValueInt(2), new PacketValueInt(1))
        );
        assert result == ComparisonResult.RightBigger;
    }

    static void lists_out_of_order() {
        ComparisonResult result = PacketChecker.comparePackets(
                new PacketValueList(new PacketValueInt(1), new PacketValueInt(1), new PacketValueInt(1)),
                new PacketValueList(new PacketValueInt(1), new PacketValueInt(1))
        );
        assert result == ComparisonResult.LeftBigger;
    }

    static void lists_out_of_order_2() {
        ComparisonResult result = PacketChecker.comparePackets(
                new PacketValueList(new PacketValueInt(1), new PacketValueInt(2)),
                new PacketValueList(new PacketValueInt(1), new PacketValueInt(1))
        );
        assert result == ComparisonResult.LeftBigger;
    }

    static void mixed_in_order() {
        ComparisonResult result = PacketChecker.comparePackets(
                new PacketValueList(
                        new PacketValueList(
                                new PacketValueInt(1),
                                new PacketValueInt(1)
                        ), new PacketValueInt(1)
                ),
                new PacketValueList(
                        new PacketValueList(
                                new PacketValueInt(1),
                                new PacketValueInt(1)
                        ), new PacketValueInt(1), new PacketValueInt(1)
                )
        );
        assert result == ComparisonResult.RightBigger;
    }

    static void mixed_in_order_2() {
        ComparisonResult result = PacketChecker.comparePackets(
                new PacketValueList(
                        new PacketValueList(
                                new PacketValueInt(1)
                        ), new PacketValueList(
                        new PacketValueInt(2),
                        new PacketValueInt(3),
                        new PacketValueInt(4)
                )
                ),
                new PacketValueList(
                        new PacketValueList(
                                new PacketValueInt(1)
                        ), new PacketValueInt(4)
                )
        );
        assert result == ComparisonResult.RightBigger;
    }

    static void mixed_in_order_3() {
        ComparisonResult result = PacketChecker.comparePackets(
                new PacketValueList(
                        new PacketValueList(),
                        new PacketValueList(
                                new PacketValueList(
                                        new PacketValueInt(2),
                                        new PacketValueInt(3),
                                        new PacketValueInt(4)
                                )
                        )
                ),
                new PacketValueList(
                        new PacketValueList(
                                new PacketValueList(
                                        new PacketValueList(
                                                new PacketValueInt(9),
                                                new PacketValueInt(3),
                                                new PacketValueInt(4)
                                        )
                                )
                        )
                )
        );
        assert result == ComparisonResult.RightBigger;
    }

    static void mixed_out_of_order() {
        ComparisonResult result = PacketChecker.comparePackets(
                new PacketValueList(
                        new PacketValueList(
                                new PacketValueInt(1),
                                new PacketValueInt(1)
                        ), new PacketValueInt(1), new PacketValueInt(1)
                ),
                new PacketValueList(
                        new PacketValueList(
                                new PacketValueInt(1),
                                new PacketValueInt(1)
                        ), new PacketValueInt(1)
                )
        );
        assert result == ComparisonResult.LeftBigger;
    }

    static void mixed_out_of_order_2() {
        ComparisonResult result = PacketChecker.comparePackets(
                new PacketValueList(new PacketValueInt(9)),
                new PacketValueList(
                        new PacketValueList(
                                new PacketValueInt(8),
                                new PacketValueInt(7),
                                new PacketValueInt(6)
                        )
                )
        );
        assert result == ComparisonResult.LeftBigger;
    }


    static void packet_sorting_dividers_product_is_140() {
        int dividersProduct = new PacketChecker().productOfDividers(puzzleInput);
        assert dividersProduct == 140 : "Expected dividers product of 140, but got " + dividersProduct;
    }
}
