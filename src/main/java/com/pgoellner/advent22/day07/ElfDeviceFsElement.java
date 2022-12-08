package com.pgoellner.advent22.day07;

import java.util.ArrayList;
import java.util.List;

public interface ElfDeviceFsElement {
    int size();
    String name();
    boolean isDir();
    List<ElfDeviceFsElement> collectSubDirs();
}

