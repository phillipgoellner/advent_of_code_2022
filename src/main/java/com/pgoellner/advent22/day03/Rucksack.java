package com.pgoellner.advent22.day03;

public class Rucksack {
    private final String firstCompartmentItems;
    private final String secondCompartmentItems;

    public Rucksack(String itemsContained) {
        firstCompartmentItems = itemsContained.substring(0, itemsContained.length() / 2);
        secondCompartmentItems = itemsContained.substring(itemsContained.length() / 2);
    }

    public String duplicateItemType() {
        for (char character : firstCompartmentItems.toCharArray()) {
            String characterString = character + "";
            if (secondCompartmentItems.contains(characterString)) {
                return characterString;
            }
        }
        return "";
    }
}
