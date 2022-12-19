package com.example.organizerclients.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.MapValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public abstract class CreateTable {
    protected final String TIME_COLUMN = "timeColumn";
    protected List<Map<String, Event>> model = new ArrayList<>();

    public abstract LinkedHashMap<GroupTableColumnKey, TableColumn<Map<String, Event>, String>> createColumns(LocalDate localDate);

    public abstract ObservableList <Map<String, Event>> createModel(Set<GroupTableColumnKey> columnKeys);

    public abstract void insertData(Event event);

    public TableColumn<Map<String, Event>, String> createTimeColumn() {
        TableColumn<Map<String, Event>, String> timeColumn = new TableColumn<>(TIME_COLUMN);
        setColumns(timeColumn, "TIME", TIME_COLUMN);
        timeColumn.setCellFactory(new TimeCellFactory());
        return timeColumn;
    }

    protected void setColumns(TableColumn<Map<String, Event>, String> tableColumn, String tableName, String tableColumnKey){
        tableColumn.setCellValueFactory(new MapValueFactory(tableColumnKey));
        tableColumn.setCellFactory(new TaskCellFactory());
        tableColumn.setText(tableName);
        tableColumn.setResizable(false);
        tableColumn.setReorderable(false);
        tableColumn.setSortable(false);
        tableColumn.setMinWidth(163.5);
    }

    protected ObservableList<Map<String, Event>> setObservableList(HashMap<GroupTableColumnKey, TreeMap<LocalTime, Event>> hashMapTasks, Set<GroupTableColumnKey> columnKeys) {
        model.clear();
        for (int i = 0; i < 24; i++) {
            LocalTime time = LocalTime.of(i, 0);
            HashMap<String, Event> hashMap = new HashMap<>();
            hashMap.put(TIME_COLUMN, new Event(time.toString(), "", null, "", ""));

            columnKeys.forEach(columnKey -> {
                if (hashMapTasks.containsKey(columnKey) && hashMapTasks.get(columnKey).containsKey(time)) {
                    hashMap.put(columnKey.toString(), hashMapTasks.get(columnKey).get(time));
                } else {
                    hashMap.put(columnKey.toString(), new Event("", "", LocalDateTime.of(columnKey.getLocalDate(), time),  "", "", columnKey.getName()));
                }
            });

            model.add(hashMap);
        }

        return FXCollections.observableArrayList(
                model
        );
    }

    public List<Map<String, Event>> getModel() {
        return model;
    }

    public void setModel(List<Map<String, Event>> model) {
        this.model = model;
    }

}
