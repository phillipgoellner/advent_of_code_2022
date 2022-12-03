package com.pgoellner.advent22.day03;

import java.util.List;

public class ElfGroup {
    private final List<String> groupRucksacks;

    public ElfGroup(List<String> groupRucksacks) {
this.groupRucksacks = groupRucksacks;
    }

    public String badgeItemType() {
        String firstRucksack = groupRucksacks.get(0);
        String secondRucksack = groupRucksacks.get(1);
        String thirdRucksack = groupRucksacks.get(2);

        String commonItemTypes = "";
        for (char character : firstRucksack.toCharArray()) {
            String characterString = character + "";
            if (secondRucksack.contains(characterString)) {
                commonItemTypes += characterString;
            }
        }

        for (char character : commonItemTypes.toCharArray()) {
            String characterString = character + "";
            if (thirdRucksack.contains(characterString)) {
                return characterString;
            }
        }

        return "";
    }
}
