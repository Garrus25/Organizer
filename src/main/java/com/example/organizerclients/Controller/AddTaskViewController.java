package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Event;
import com.example.organizerclients.Model.OrganizerProperties;
import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.AddTaskToGroupData;
import com.example.organizerclients.Requests.RequestObjects.AddTaskToUser;
import com.example.organizerclients.Requests.RequestObjects.IdTask;
import com.example.organizerclients.Requests.RequestObjects.TaskData;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class AddTaskViewController {
    private SceneController sceneController = SceneController.getInstance();
    private final ChartController chartController;
    private final Event event;

    @FXML
    private Label titleView;

    @FXML
    private TextField taskName;

    @FXML
    private Label headerForChoiceBox;

    @FXML
    private TextArea description;

    @FXML
    private Button buttonCreateTask;

    @FXML
    private ChoiceBox<String> chooseTaskType;

    @FXML
    public void initialize() {
        setFieldParameters();
        setListeners();
    }

    public AddTaskViewController(ChartController chartController, Event event) {
        this.chartController = chartController;
        this.event = event;
    }

    private void setFieldParameters(){
        titleView.setText(OrganizerProperties.ADD_TASK_VIEW_TITLE_VIEW_TEXT);
        taskName.setPromptText(OrganizerProperties.ADD_TASK_VIEW_TASK_NAME_TEXT);
        headerForChoiceBox.setText(OrganizerProperties.ADD_TASK_VIEW_HEADER_FOR_CHOICE_BOX_TEXT);
        description.setPromptText(OrganizerProperties.ADD_TASK_VIEW_DESCRIPTION_TEXT);
        buttonCreateTask.setText(OrganizerProperties.ADD_TASK_VIEW_BUTTON_CREATE_TASK_TEXT);

        chooseTaskType.setItems(FXCollections.observableArrayList(
                OrganizerProperties.ADD_TASK_VIEW_CHOOSE_TASK_VALUE1_TEXT,
                OrganizerProperties.ADD_TASK_VIEW_CHOOSE_TASK_VALUE2_TEXT,
                OrganizerProperties.ADD_TASK_VIEW_CHOOSE_TASK_VALUE3_TEXT
        ));

        chooseTaskType.setValue(OrganizerProperties.ADD_TASK_VIEW_CHOOSE_TASK_VALUE1_TEXT);

        taskName.setText(event.getEventName());
        description.setText(event.getDescription());
        chooseTaskType.setValue(event.getType());

        int maxLength = 20;

        taskName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (taskName.getText().length() > maxLength) {
                    String s = taskName.getText().substring(0, maxLength);
                    taskName.setText(s);
                }
            }
        });

    }

    private void setListeners(){
        buttonCreateTask.setOnAction(actionEvent -> {
            updateModel();
            sceneController.getStage().close();
        });
    }

    private Event getMeetingData(){
        String eventName = taskName.getText();
        String description = this.description.getText();
        String type = chooseTaskType.getValue();
        return new Event(eventName,event.getGroup(), event.getDate(), description, type, event.getLogin(), event.getTaskId(), event.getIdGroup(), event.getIdUser());
    }

    private void updateModel(){
        Event meetingData = getMeetingData();
        event.replaceData(meetingData);
        chartController.updateModel(event);
    }

    private Integer addNewTask(Event event) {
        TaskData taskData=new TaskData(event.getTaskId(), event.getEventName(), event.getDescription(), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(event.getDate()));
        Request request= null;
        IdTask idNewTask = new IdTask();
        try {
            request = new Request(RequestType.ADD_NEW_TASK.getNameRequest(), SaveDataAsJson.saveDataAsJson(taskData));
            Optional<Response> response= RequestTool.sendRequest(request);
            idNewTask= ReadObjectFromJson.read(response.orElseThrow().getData(),IdTask.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return idNewTask.getId_task();
    }


    private void addTaskToUser(Event event) {
        AddTaskToUser addTaskToUser=new AddTaskToUser(event.getIdUser(),event.getTaskId(),0);
        Request request= null;
        try {
            request = new Request(RequestType.ADD_TASK_TO_USER.getNameRequest(), SaveDataAsJson.saveDataAsJson(addTaskToUser));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Optional<Response> response= RequestTool.sendRequest(request);
    }

    private void addTaskToGroup(Event event) {
        AddTaskToGroupData addTaskToUser = new AddTaskToGroupData(event.getTaskId(),event.getIdGroup(),event.getIdUser());
        Request request= null;
        try {
            request = new Request(RequestType.ADD_TASK_TO_GROUP.getNameRequest(), SaveDataAsJson.saveDataAsJson(addTaskToUser));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Optional<Response> response= RequestTool.sendRequest(request);

    }

}
