package com.pgoellner.advent22.day07;

import java.util.List;
import java.util.Stack;

public final class FsFactory {
    private FsFactory() {
    }

    public static ElfDeviceFsElement createFromCommands(List<String> commands) {
        ElfDeviceDir root = new ElfDeviceDir("/");

        Stack<ElfDeviceDir> workingDirectories = new Stack<>();
        workingDirectories.push(root);

        for (String line : commands) {
            if (line.startsWith("$ cd")) {
                String cd = line.replace("$ cd ", "");
                if (cd.equals("..")) {
                    workingDirectories.pop();
                } else if (!cd.equals("/")) {
                    workingDirectories.push(workingDirectories.peek().childWithName(cd));
                }
            } else if (!line.startsWith("$")) {
                if (line.startsWith("dir")) {
                    String dirName = line.replace("dir ", "");
                    ElfDeviceDir newDirectory = new ElfDeviceDir(dirName);
                    workingDirectories.peek().addChild(newDirectory);
                } else {
                    String[] fileLineSplit = line.split(" ");
                    ElfDeviceFile newFile = new ElfDeviceFile(fileLineSplit[1], Integer.parseInt(fileLineSplit[0]));
                    workingDirectories.peek().addChild(newFile);
                }
            }
        }

        return root;
    }
}
