package com.example.organizerclients.Model;

import com.example.organizerclients.Controller.TestModelGroupView;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CreateGroupTable extends CreateTable{
    private final TestModelGroupView testModelGroupView = new TestModelGroupView();
    private  List<String> personListInGroup = new ArrayList<>();

    public CreateGroupTable() {
        setPersonListInGroup();
    }

    @Override
    public LinkedHashMap<TableColumnKey,TableColumn<Map<String, Event>, String>> createColumns(LocalDate localDate) {
        LinkedHashMap<TableColumnKey, TableColumn<Map<String, Event>, String>> tableColumns = new LinkedHashMap<>();

        personListInGroup.forEach(key -> {
            TableColumnKey tableColumnKey = new TableColumnKey(key, localDate);
            TableColumn<Map<String, Event>, String> tableColumn
                    = new TableColumn<>(key);
            setColumns(tableColumn, key, tableColumnKey.toString());
            tableColumns.put(tableColumnKey, tableColumn);
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

    private void setPersonListInGroup() {
        personListInGroup.add("Marek");
        personListInGroup.add("Iwona");
        personListInGroup.add("Renata");
        personListInGroup.add("Szymon");
    }

    public void changeGroup(List<String> persons) {
        personListInGroup = persons;
    }
}
