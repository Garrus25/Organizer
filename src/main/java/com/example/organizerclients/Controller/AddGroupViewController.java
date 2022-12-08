package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.OrganizerProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddGroupViewController {

    @FXML
    private Label titleView;

    @FXML
    private TextField groupName;

    @FXML
    private Button buttonGroupCreate;

    @FXML
    public void initialize() {
        setFieldParameters();
    }

    private void setFieldParameters(){
        titleView.setText(OrganizerProperties.ADD_GROUP_VIEW_VIEW_TITLE_TEXT);
        groupName.setPromptText(OrganizerProperties.ADD_GROUP_VIEW_GROUP_NAME_TEXT_FIELD_PROMPT_TEXT);
        buttonGroupCreate.setText(OrganizerProperties.ADD_GROUP_VIEW_GROUP_CREATE_BUTTON_TEXT);
    }
}
