package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Group;
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
    private Button buttonSelectGroup;

    private int groupId;

    private Boolean isSet = false;

    private ChartController chartController;

    @FXML
    private void initialize(){
        setFieldParameters();
        setAddToGroupButtonListener();
        setChooseGroupButtonListener();
        isActiveGroup();
    }

    public GroupInformationViewController(ChartController chartController, int groupId) {
        this.chartController = chartController;
        this.groupId = groupId;
    }

    private void setFieldParameters(){
        groupCodeHeader.setText(OrganizerProperties.GROUP_INFORMATION_VIEW_GROUP_CODE_TEXT);
        groupNameHeader.setText(OrganizerProperties.GROUP_INFORMATION_VIEW_GROUP_NAME_TEXT);
        buttonSelectGroup.setText(OrganizerProperties.GROUP_INFORMATION_VIEW_CHOOSE_GROUP_BUTTON_TEST);
    }

    private void isActiveGroup() {
        if (chartController.getCurrentGroupId() != null && chartController.getCurrentGroupId() == groupId) {
            chartController.setCurrentGroupButton(buttonSelectGroup);
            setSelectButtonState(false);
        }
    }

    public void setData(Group group) {
        this.groupCode.setText(group.getCode());
        this.groupName.setText(group.getName());
        this.isSet = group.getIsSetFlag();
        switchMode();
    }

    private void switchMode(){
        if (isSet){
            setLeaveGroupMode();
            setSelectButtonState(true);
        }else {
            setJoinGroupMode();
            setSelectButtonState(false);
        }
    }

    private void setSelectButtonState(Boolean disabled){
        buttonSelectGroup.setVisible(disabled);
    }

    private void setJoinGroupMode(){
        buttonAddToGroup.setStyle("-fx-background-color: #5559C9");
        buttonAddToGroup.setText(OrganizerProperties.GROUP_INFORMATION_VIEW_JOIN_GROUP_BUTTON_TEXT);
    }

    private void setLeaveGroupMode(){
        buttonAddToGroup.setStyle("-fx-background-color: red");
        buttonAddToGroup.setText(OrganizerProperties.GROUP_INFORMATION_VIEW_LEAVE_GROUP_BUTTON_TEXT);
    }

    private void setAddToGroupButtonListener(){
        buttonAddToGroup.setOnAction(actionEvent -> {
            isSet = !isSet;
            switchMode();
        });
    }

    private void setChooseGroupButtonListener(){
        buttonSelectGroup.setOnAction(actionEvent -> {
            chartController.changeGroup(groupId, buttonSelectGroup);
        });
    }
}
