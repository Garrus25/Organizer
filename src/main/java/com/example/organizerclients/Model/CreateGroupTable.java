package com.example.organizerclients.Model;

import com.example.organizerclients.Controller.TestModelGroupView;
import com.example.organizerclients.Controller.TestModelSingleUserView;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CreateGroupTable extends CreateTable{
    private final TestModelGroupView testModelGroupView = new TestModelGroupView();

    @Override
    public LinkedHashMap<GroupTableColumnKey,TableColumn<Map<String, Event>, String>> createColumns(LocalDate localDate) {
        List<GroupTableColumnKey> groupTableColumnKeys = new ArrayList<>();
        groupTableColumnKeys.add(new GroupTableColumnKey("Marek", localDate));
        groupTableColumnKeys.add(new GroupTableColumnKey("Iwona", localDate));
        groupTableColumnKeys.add(new GroupTableColumnKey("Renata", localDate));
        groupTableColumnKeys.add(new GroupTableColumnKey("Szymon", localDate));
        LinkedHashMap<GroupTableColumnKey, TableColumn<Map<String, Event>, String>> tableColumns = new LinkedHashMap<>();

        groupTableColumnKeys.forEach(key -> {
            TableColumn<Map<String, Event>, String> tableColumn
                    = new TableColumn<>(key.getName());
            setColumns(tableColumn, key.getName(), key.toString());
            tableColumns.put(key, tableColumn);
        });

        return tableColumns;
    }

    @Override
    public ObservableList<Map<String, Event>> createModel(Set<GroupTableColumnKey> columnKeys) {
        return setObservableList(testModelGroupView.testContent, columnKeys);
    }

    @Override
    public void insertData(Event event) {
        TreeMap <LocalTime, Event> temp = new TreeMap<LocalTime, Event>();
        temp.put(event.getDate().toLocalTime(), event);
        System.out.println(event);
        testModelGroupView.addData(new GroupTableColumnKey(event.getPersonName(), event.getDate().toLocalDate() ),temp);
        System.out.println(testModelGroupView.testContent);
    }
}
