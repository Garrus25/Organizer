package com.example.organizerclients.Model;

import com.example.organizerclients.Controller.TestModelSingleUserView;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CreateSingleUserTable extends CreateTable {
    private final TestModelSingleUserView testModelSingleUserView = new TestModelSingleUserView();

    @Override
    public  LinkedHashMap<TableColumnKey, TableColumn<Map<String, Event>, String>> createColumns(LocalDate localDate) {
        LocalDate date = localDate;
        LinkedHashMap<TableColumnKey, TableColumn<Map<String, Event>, String>> tableColumns = new LinkedHashMap<>();

        for (int i = 0; i < 7; i++) {
            TableColumn<Map<String, Event>, String> tableColumn
                    = new TableColumn<>(date.toString());
            setColumns(tableColumn, date.toString(), new TableColumnKey("", date).toString());
            tableColumns.put(new TableColumnKey("", date), tableColumn);
            date = date.plusDays(1);
        }

        return tableColumns;
    }

    @Override
    public ObservableList<Map<String, Event>> createModel(Set<TableColumnKey> columnKeys) {
        return setObservableList(testModelSingleUserView.testContent, columnKeys);
    }

    @Override
    public void insertData(Event event) {
        TreeMap <LocalTime, Event> temp = new TreeMap<LocalTime, Event>();
        temp.put(event.getDate().toLocalTime() ,event);
        testModelSingleUserView.addData(new TableColumnKey("", event.getDate().toLocalDate()),temp);
    }
}
