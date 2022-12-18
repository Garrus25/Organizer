package com.example.organizerclients.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.MapValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public abstract class CreateTable {
    protected final String TIME_COLUMN = "timeColumn";
    protected List<Map<String, Event>> model = new ArrayList<>();

    public abstract  List<TableColumn<Map<String, Event>, String>> createColumns(LocalDate localDate);

    public abstract ObservableList <Map<String, Event>> createModel();

    public abstract void insertData(Event event, LocalDate selectedDate, LocalTime selectedTime);

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

    protected <T> ObservableList<Map<String, Event>> setObservableList(HashMap<T, TreeMap<LocalTime, Event>> hashMapTasks) {
        model.clear();
        for (int i = 0; i < 24; i++) {
            LocalTime time = LocalTime.of(i, 0);
            HashMap<String, Event> hashMap = new HashMap<>();
            hashMap.put(TIME_COLUMN, new Event(time.toString(), "", null));

            hashMapTasks.forEach((key, value) -> {
                if (value.containsKey(time)) {
                    hashMap.put(key.toString(), value.get(time));
                } else {
                    hashMap.put(key.toString(), new Event("", "", null));
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
