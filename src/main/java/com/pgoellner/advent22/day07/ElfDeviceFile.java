package com.pgoellner.advent22.day07;

import java.util.List;

public class ElfDeviceFile implements ElfDeviceFsElement {
    private final String name;
    private final int size;

    public ElfDeviceFile(String filename, int fileSize) {
        this.name = filename;
        this.size = fileSize;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isDir() {
        return false;
    }

    @Override
    public List<ElfDeviceFsElement> collectSubDirs() {
        return List.of();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ElfDeviceFile && this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return (this.name + this.size).hashCode();
    }
}
