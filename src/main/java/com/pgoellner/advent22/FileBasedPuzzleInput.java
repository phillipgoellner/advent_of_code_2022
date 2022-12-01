package com.pgoellner.advent22;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileBasedPuzzleInput implements PuzzleInput {
    private final String classPathFilePath;

    public FileBasedPuzzleInput(String classPathFilePath) {
        this.classPathFilePath = classPathFilePath;
    }

    @Override
    public List<String> lines() {
        try (Stream<String> lines = Files.lines(Path.of(ClassLoader.getSystemResource(classPathFilePath).toURI()))) {
            return lines.toList();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
