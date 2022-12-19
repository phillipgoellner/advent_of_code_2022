package com.pgoellner.advent22.day13;

public class PacketValueInt extends PacketValue {
    public final int value;

    public PacketValueInt(int value) {
        this.value = value;
    }

    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return "" + value;
    }
}
