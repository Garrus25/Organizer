package com.example.organizerclients.Model;

import com.example.organizerclients.Controller.TestModelGroupView;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CreateGroupTable extends CreateTable{
    private final TestModelGroupView testModelGroupView = new TestModelGroupView();

    @Override
    public LinkedHashMap<TableColumnKey,TableColumn<Map<String, Event>, String>> createColumns(LocalDate localDate) {
        List<TableColumnKey> tableColumnKeys = new ArrayList<>();
        tableColumnKeys.add(new TableColumnKey("Marek", localDate));
        tableColumnKeys.add(new TableColumnKey("Iwona", localDate));
        tableColumnKeys.add(new TableColumnKey("Renata", localDate));
        tableColumnKeys.add(new TableColumnKey("Szymon", localDate));
        LinkedHashMap<TableColumnKey, TableColumn<Map<String, Event>, String>> tableColumns = new LinkedHashMap<>();

        tableColumnKeys.forEach(key -> {
            TableColumn<Map<String, Event>, String> tableColumn
                    = new TableColumn<>(key.getName());
            setColumns(tableColumn, key.getName(), key.toString());
            tableColumns.put(key, tableColumn);
        });

        return tableColumns;
    }

    @Override
    public ObservableList<Map<String, Event>> createModel(Set<TableColumnKey> columnKeys) {
        return setObservableList(testModelGroupView.testContent, columnKeys);
    }

    @Override
    public void insertData(Event event) {
        TreeMap <LocalTime, Event> temp = new TreeMap<LocalTime, Event>();
        temp.put(event.getDate().toLocalTime(), event);
        System.out.println(event);
        testModelGroupView.addData(new TableColumnKey(event.getPersonName(), event.getDate().toLocalDate() ),temp);
        System.out.println(testModelGroupView.testContent);
    }
}
