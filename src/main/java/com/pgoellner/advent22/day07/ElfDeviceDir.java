package com.pgoellner.advent22.day07;

import java.util.ArrayList;
import java.util.List;

public class ElfDeviceDir implements ElfDeviceFsElement {
    private final String name;
    private final List<ElfDeviceFsElement> children;

    private ElfDeviceDir(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public ElfDeviceDir(String name, ElfDeviceFsElement... childElements) {
        this(name);

        for (ElfDeviceFsElement child : childElements) {
            addChild(child);
        }
    }

    public void addChild(ElfDeviceFsElement newChild) {
        children.add(newChild);
    }

    public ElfDeviceDir childWithName(String name) {
        return (ElfDeviceDir) children.stream().filter(child -> child.name().equals(name)).findFirst().orElseThrow();
    }

    @Override
    public int size() {
        return children.stream().map(ElfDeviceFsElement::size).reduce(0, Integer::sum);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isDir() {
        return true;
    }

    @Override
    public List<ElfDeviceFsElement> collectSubDirs() {
        List<ElfDeviceFsElement> foundDirs = new ArrayList<>();

        children.stream().filter(ElfDeviceFsElement::isDir).forEach(
                child -> {
                    foundDirs.add(child);
                    foundDirs.addAll(child.collectSubDirs());
                }
        );

        return foundDirs;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ElfDeviceDir && this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return (this.name + children).hashCode();
    }
}
