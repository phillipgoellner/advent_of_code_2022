package com.pgoellner.advent22.day07;

import com.pgoellner.advent22.FileBasedPuzzleInput;
import com.pgoellner.advent22.PuzzleInput;

import java.util.List;

public class FsExplorer {

    public static void main(String[] args) {
        FileBasedPuzzleInput puzzleInput = new FileBasedPuzzleInput("puzzle_input_day_07.txt");
        System.out.println("Part 1: " + new FsExplorer().processCommands(puzzleInput));
        System.out.println("Part 2: " + new FsExplorer().findMinMaxDir(puzzleInput, 70_000_000, 30_000_000));
    }
    public int processCommands(PuzzleInput puzzleInput) {
        final List<ElfDeviceDir> sizeCandidates = findDirectoryCandidates(
                createFsFromCommands(puzzleInput),
                100_000);

        return sizeCandidates.stream().map(ElfDeviceDir::size).reduce(0, Integer::sum);
    }

    List<ElfDeviceDir> findDirectoryCandidates(ElfDeviceFsElement root, int maximumSize) {
        return root.collectSubDirs()
                .stream()
                .filter(file -> file.size() <= maximumSize)
                .map(file -> (ElfDeviceDir) file)
                .toList();
    }

    public ElfDeviceFsElement createFsFromCommands(PuzzleInput puzzleInput) {
        return FsFactory.createFromCommands(puzzleInput.lines());
    }

    public int findMinMaxDir(PuzzleInput puzzleInput, int deviceSpace, int spaceRequiredForUpdate) {
        ElfDeviceFsElement root = createFsFromCommands(puzzleInput);

        int totalFsSize = root.size();
        int totalRequiredSpace = spaceRequiredForUpdate - (deviceSpace - totalFsSize);

        return root.collectSubDirs().stream().map(ElfDeviceFsElement::size).filter(dirSize -> dirSize > totalRequiredSpace).sorted().findFirst().orElseThrow();
    }
}
