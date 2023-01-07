package com.example.organizerclients.Model;

import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.GroupId;
import com.example.organizerclients.Requests.RequestObjects.UserData;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CreateGroupTable extends CreateTable{
    private  List<UserData> personListInGroup = new ArrayList<>();
    private Integer groupId;

    public CreateGroupTable(Integer groupId) {
        this.groupId = groupId;
        changeGroup(groupId);
        setUserTasks();
    }

    @Override
    public LinkedHashMap<TableColumnKey,TableColumn<Map<String, Event>, String>> createColumns(LocalDate localDate) {
        LinkedHashMap<TableColumnKey, TableColumn<Map<String, Event>, String>> tableColumns = new LinkedHashMap<>();

        personListInGroup.forEach(key -> {
            TableColumnKey tableColumnKey = new TableColumnKey(key.getIdUser(), localDate, key.getLogin());
            TableColumn<Map<String, Event>, String> tableColumn
                    = new TableColumn<>(key.getLogin());
            setColumns(tableColumn, key.getLogin(), tableColumnKey.toString());
            tableColumns.put(tableColumnKey, tableColumn);
        });
        return tableColumns;
    }

    @Override
    public ObservableList<Map<String, Event>> createModel(Set<TableColumnKey> columnKeys) {
        return setObservableList(userTasks, columnKeys, groupId);
    }

    @Override
    public void insertData(Event event) {
        TreeMap <LocalTime, Event> temp = new TreeMap<LocalTime, Event>();
        temp.put(event.getDate().toLocalTime(), event);
        System.out.println(event);
        addData(new TableColumnKey(event.getIdUser(), event.getDate().toLocalDate(), event.getLogin()), temp);
    }


    public void changeGroup(int groupId) {
        personListInGroup = getGroupMembers(groupId);
    }

    private List<UserData> getGroupMembers(int groupId) {
        GroupId groupData = new GroupId(groupId);
        List<UserData> result = null;
        try {
            Request request = new Request(RequestType.GET_MEMBERSHIP_GROUP_ABOUT_UD.getNameRequest(), SaveDataAsJson.saveDataAsJson(groupData));
            Optional<Response> response= RequestTool.sendRequest(request);
            result = ReadObjectFromJson.<UserData>readListObject(response.orElseThrow().getData(), UserData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<Event> getAllGroupTask() {
        GroupId idUser = new GroupId(groupId);
        List<Event> result = null;
        try {
            Request request=new Request(RequestType.GET_ALL_TASK_FOR_GROUP.getNameRequest(), SaveDataAsJson.saveDataAsJson(idUser));
            Optional<Response> response= RequestTool.sendRequest(request);
            result = ReadObjectFromJson.<Event>readListObject(response.orElseThrow().getData(), Event.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    private TableModel convertTaskToGroupTableModel(Event event) {
        TreeMap<LocalTime, Event> treeMap = new TreeMap<>();
        TableColumnKey tableColumnKey = new TableColumnKey(event.getIdUser(), event.getDate().toLocalDate(), event.getLogin());

        treeMap.put(LocalTime.of(event.getDate().toLocalTime().getHour(), 0), event);
        return new TableModel(tableColumnKey, treeMap);
    }

    private void setUserTasks() {
        List<Event> allUserTask = getAllGroupTask();

        allUserTask.forEach(taskData -> {
            TableModel tableModel = convertTaskToGroupTableModel(taskData);
            userTasks.put(tableModel.getKey(), tableModel.getTreeMap());
            System.out.println(tableModel);
        });

    }
}
