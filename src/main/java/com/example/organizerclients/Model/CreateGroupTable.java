package com.example.organizerclients.Model;

import com.example.organizerclients.Controller.TestModelGroupView;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateGroupTable extends CreateTable{

    @Override
    public List<TableColumn<Map<String, Event>, String>> createColumns(LocalDate localDate) {
        TestModelGroupView userTasks = new TestModelGroupView();
        List<TableColumn<Map<String, Event>, String>> tableColumns = new ArrayList<>();
        userTasks.testContent.forEach( (key, value) -> {
            if (key.getLocalDate().equals(localDate)) {
                TableColumn<Map<String, Event>, String> tableColumn
                        = new TableColumn<>(key.getName());
                setColumns(tableColumn, key.getName(), key.toString());
                tableColumns.add(tableColumn);
            }
        });

        return tableColumns;
    }

    @Override
    public ObservableList<Map<String, Event>> createModel() {
        TestModelGroupView testModelGroupView = new TestModelGroupView();
        return setObservableList(testModelGroupView.testContent);
    }

    @Override
    public void insertData(Event event, LocalDate selectedDate, LocalTime selectedTime) {

    }
}
