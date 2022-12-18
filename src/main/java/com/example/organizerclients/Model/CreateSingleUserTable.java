package com.example.organizerclients.Model;

import com.example.organizerclients.Controller.TestModelSingleUserView;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateSingleUserTable extends CreateTable {

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
        TestModelSingleUserView testModelSingleUserView = new TestModelSingleUserView();
        return setObservableList(testModelSingleUserView.testContent);
    }
}
