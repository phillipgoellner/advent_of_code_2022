package com.pgoellner.advent22.day08;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

import java.util.ArrayList;
import java.util.List;

public class TreeSurveyor {

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_08.txt");
        System.out.println("Part 1: " + new TreeSurveyor().countVisibleTrees(puzzleInput));
    }

    public int countVisibleTrees(PuzzleInput puzzleInput) {
        List<String> lines = puzzleInput.lines();
        List<String> columns = transpose(lines);

        int width = lines.get(0).length();
        int height = lines.size();

        List<String> visibleTrees = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (treeIsVisible(i, j, lines.get(j), columns.get(i))) {
                    visibleTrees.add(i + "/" + j);
                }
            }
        }

        return visibleTrees.size();
    }

    private List<String> transpose(List<String> lines) {
        final List<String> columns = new ArrayList<>();

        for (int i = 0; i < lines.get(0).length(); i++) {
            String column = "";
            for (String line : lines) {
                column += line.charAt(i);
            }
            columns.add(column);
        }

        return columns;
    }

    private boolean treeIsVisible(int x, int y, String row, String column) {
        return treeIsVisibleFromLeft(x, row.toCharArray()) || treeIsVisibleFromRight(x, row.toCharArray()) ||
                treeIsVisibleFromLeft(y, column.toCharArray()) || treeIsVisibleFromRight(y, column.toCharArray());
    }

    private boolean treeIsVisibleFromRight(int position, char[] row) {
        for (int i = row.length - 1; i > position; i--) {
            if (row[i] >= row[position]) {
                return false;
            }
        }
        return true;
    }

    private boolean treeIsVisibleFromLeft(int position, char[] row) {
        for (int i = 0; i < position; i++) {
            if (row[i] >= row[position]) {
                return false;
            }
        }
        return true;
    }
}
