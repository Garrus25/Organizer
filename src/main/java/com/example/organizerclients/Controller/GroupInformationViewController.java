package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.OrganizerProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GroupInformationViewController {

    @FXML
    private Label groupName;

    @FXML
    private Label groupCode;

    @FXML
    private Label groupCodeHeader;

    @FXML
    private Label groupNameHeader;

    @FXML
    private Button buttonAddToGroup;

    @FXML
    public void initialize() {
        setFieldParameters();
    }

    private void setFieldParameters(){
        groupCodeHeader.setText(OrganizerProperties.GROUP_INFORMATION_VIEW_GROUP_CODE_TEXT);
        groupNameHeader.setText(OrganizerProperties.GROUP_INFORMATION_VIEW_GROUP_NAME_TEXT);
        buttonAddToGroup.setText(OrganizerProperties.GROUP_INFORMATION_VIEW_BUTTON_ADD_TO_GROUP_TEXT);
        groupName.setText("name");
        groupCode.setText("code");
    }

    public void setData(String groupName, String groupCode) {
        this.groupCode.setText(groupCode);
        this.groupName.setText(groupName);
    }

}
