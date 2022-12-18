package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Event;
import com.example.organizerclients.Model.OrganizerProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;

public class AddTaskViewController {
    SceneController sceneController = SceneController.getInstance();

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

    private void setFieldParameters(){
        titleView.setText(OrganizerProperties.ADD_TASK_VIEW_TITLE_VIEW_TEXT);
        taskName.setText(OrganizerProperties.ADD_TASK_VIEW_TASK_NAME_TEXT);
        headerForChoiceBox.setText(OrganizerProperties.ADD_TASK_VIEW_HEADER_FOR_CHOICE_BOX_TEXT);
        description.setText(OrganizerProperties.ADD_TASK_VIEW_DESCRIPTION_TEXT);
        buttonCreateTask.setText(OrganizerProperties.ADD_TASK_VIEW_BUTTON_CREATE_TASK_TEXT);

        chooseTaskType.setItems(FXCollections.observableArrayList(
                OrganizerProperties.ADD_TASK_VIEW_CHOOSE_TASK_VALUE1_TEXT,
                OrganizerProperties.ADD_TASK_VIEW_CHOOSE_TASK_VALUE2_TEXT,
                OrganizerProperties.ADD_TASK_VIEW_CHOOSE_TASK_VALUE3_TEXT
        ));

        chooseTaskType.setValue(OrganizerProperties.ADD_TASK_VIEW_CHOOSE_TASK_VALUE1_TEXT);
    }

    private void setListeners(){
        buttonCreateTask.setOnAction(actionEvent -> {
            updateModel();
            sceneController.getStage().close();
        });
    }

    private Event getMeetingData(){
        String eventName = taskName.getText();
        String description;
        return new Event(eventName,"", LocalDateTime.now());
    }

    private void updateModel(){
        sceneController.getChartController().updateModel(getMeetingData());
    }
}
