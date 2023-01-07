package com.example.organizerclients.Model;

import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.TaskData;
import com.example.organizerclients.Requests.RequestObjects.UserID;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CreateSingleUserTable extends CreateTable {

    public CreateSingleUserTable() {
        setUserTasks();
    }

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
        return setObservableList(userTasks, columnKeys);
    }

    @Override
    public void insertData(Event event) {
        TreeMap <LocalTime, Event> temp = new TreeMap<LocalTime, Event>();
        temp.put(event.getDate().toLocalTime() ,event);
        addData(new TableColumnKey("", event.getDate().toLocalDate()),temp);
    }

    private List<TaskData> getAllUserTask() {
        UserID idUser = new UserID("1");
        List<TaskData> result = null;
        try {
            Request request = new Request(RequestType.GET_ALL_TASK_FOR_USER.getNameRequest(), SaveDataAsJson.saveDataAsJson(idUser));
            Optional<Response> response= RequestTool.sendRequest(request);
            result = ReadObjectFromJson.<TaskData>readListObject(response.orElseThrow().getData(), TaskData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private TableModel convertTaskToTableModel(TaskData taskData) {
        TreeMap<LocalTime, Event> treeMap = new TreeMap<>();
        TableColumnKey tableColumnKey = new TableColumnKey("", taskData.getDateOfNotification().toLocalDateTime().toLocalDate());
        Event event = new Event(taskData.getName(), "",
                taskData.getDateOfNotification().toLocalDateTime(),
                taskData.getDescription(), "", taskData.getIdTask(), null, null);
        treeMap.put(LocalTime.of(taskData.getDateOfNotification().toLocalDateTime().toLocalTime().getHour(), 0), event);
        return new TableModel(tableColumnKey, treeMap);
    }

    private void setUserTasks() {
        List<TaskData> allUserTask = getAllUserTask();
        allUserTask.forEach(taskData -> {
            TableModel tableModel = convertTaskToTableModel(taskData);
            userTasks.put(tableModel.getKey(), tableModel.getTreeMap());
        });

    }
}
