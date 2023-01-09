package com.example.organizerclients.Model;

import java.time.LocalTime;
import java.util.TreeMap;

public class TableModel {
    private TableColumnKey key;
    private TreeMap<LocalTime, Event> treeMap;

    public TableModel(TableColumnKey key, TreeMap<LocalTime, Event> treeMap) {
        this.key = key;
        this.treeMap = treeMap;
    }

    public TableColumnKey getKey() {
        return key;
    }

    public TreeMap<LocalTime, Event> getTreeMap() {
        return treeMap;
    }

    @Override
    public String toString() {
        return "TableModel{" +
                "key=" + key +
                ", treeMap=" + treeMap +
                '}';
    }
}
