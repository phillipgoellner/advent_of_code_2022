package com.pgoellner.advent22.day07;

import com.pgoellner.advent22.PuzzleInput;
import com.pgoellner.advent22.TestInput;

import java.util.List;

public class FsExplorerTest {
    private static final List<String> dataStreamBuffer = List.of(
            "$ cd /",
            "$ ls",
            "dir a",
            "14848514 b.txt",
            "8504156 c.dat",
            "dir d",
            "$ cd a",
            "$ ls",
            "dir e",
            "29116 f",
            "2557 g",
            "62596 h.lst",
            "$ cd e",
            "$ ls",
            "584 i",
            "$ cd ..",
            "$ cd ..",
            "$ cd d",
            "$ ls",
            "4060174 j",
            "8033020 d.log",
            "5626152 d.ext",
            "7214296 k");

    private static final ElfDeviceFsElement fs = new ElfDeviceDir(
            "/", new ElfDeviceDir(
            "a", new ElfDeviceDir(
            "e", new ElfDeviceFile("i", 584)
    ),
            new ElfDeviceFile("f", 29116),
            new ElfDeviceFile("g", 2557),
            new ElfDeviceFile("h.lst", 62596)
    ),
            new ElfDeviceFile("b.txt", 14848514),
            new ElfDeviceFile("c.dat", 8504156),
            new ElfDeviceDir(
                    "d", new ElfDeviceFile("j", 4060174),
                    new ElfDeviceFile("d.log", 8033020),
                    new ElfDeviceFile("d.ext", 5626152),
                    new ElfDeviceFile("k", 7214296)
            )
    );

    private final static PuzzleInput puzzleInput = new TestInput(dataStreamBuffer);

    public static void main(String[] args) {
        returns_95437_as_total_directory_size();
        creates_fs_representation();
        find_directory_size_24933642_to_be_best_fit_deletion_candidate();

        System.out.println("All tests passed");
    }

    static void returns_95437_as_total_directory_size() {
        int totalSize = new FsExplorer().processCommands(puzzleInput);
        assert totalSize == 95437 : "Expected total directory size of 95437, but got " + totalSize;
    }

    static void creates_fs_representation() {
        ElfDeviceFsElement resultingFs = new FsExplorer().createFsFromCommands(puzzleInput);
        assert resultingFs.equals(fs);
    }

    static void find_directory_size_24933642_to_be_best_fit_deletion_candidate() {
        int totalSize = new FsExplorer().findMinMaxDir(puzzleInput, 70_000_000, 30_000_000);
        assert totalSize == 24933642 : "Expected total directory size of 24933642, but got " + totalSize;
    }
}
