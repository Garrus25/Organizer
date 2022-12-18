package com.example.organizerclients.Model;

import com.example.organizerclients.Controller.TestModelSingleUserView;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CreateSingleUserTable extends CreateTable {
    private final TestModelSingleUserView testModelSingleUserView = new TestModelSingleUserView();

    @Override
    public List<TableColumn<Map<String, Event>, String>> createColumns(LocalDate localDate) {
        LocalDate date = localDate;
        List<TableColumn<Map<String, Event>, String>> tableColumns = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            TableColumn<Map<String, Event>, String> tableColumn
                    = new TableColumn<>(date.toString());
            setColumns(tableColumn, date.toString(), date.toString());
            tableColumns.add(tableColumn);
            date = date.plusDays(1);
        }

        return tableColumns;
    }

    @Override
    public ObservableList<Map<String, Event>> createModel() {
        return setObservableList(testModelSingleUserView.testContent);
    }

    @Override
    public void insertData(Event event, LocalDate selectedDate, LocalTime selectedTime) {
        TreeMap <LocalTime, Event> temp = new TreeMap<LocalTime, Event>();
        temp.put(selectedTime,event);

        testModelSingleUserView.addData(selectedDate,temp);
    }
}
