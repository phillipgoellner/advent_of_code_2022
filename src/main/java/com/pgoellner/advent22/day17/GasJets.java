package com.pgoellner.advent22.day17;

public class GasJets {
    private String pattern;

    public GasJets(String pattern) {
        this.pattern = pattern;
    }

    public PushDirection dispense() {
        char next = pattern.charAt(0);
        pattern = pattern.substring(1) + next;

        if (next == '<') {
            return PushDirection.Left;
        } else {
            return PushDirection.Right;
        }
    }
}
