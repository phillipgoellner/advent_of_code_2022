package com.pgoellner.advent22.day13;

import java.util.List;

public class PacketValueList extends PacketValue {
    private final List<PacketValue> values;

    public PacketValueList(List<PacketValue> values) {
        this.values = values;
    }

    public PacketValueList(PacketValue... values) {
        this.values = List.of(values);
    }

    public List<PacketValue> values() {
        return values.stream().toList();
    }

    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return values.toString();
    }
}
