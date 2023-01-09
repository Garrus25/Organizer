package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Group;
import com.example.organizerclients.Model.OrganizerProperties;
import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.GroupId;
import com.example.organizerclients.Requests.RequestObjects.UserData;
import com.example.organizerclients.Requests.RequestObjects.UserGroupData;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class GroupInformationViewController {
    SceneController sceneController = SceneController.getInstance();

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

    public void setMembership(){
        System.out.println("Membership called");
        GroupId groupData = new GroupId(groupId);
        Request request = null;

        try {
            request = new Request(RequestType.GET_MEMBERSHIP_GROUP_ABOUT_UD.getNameRequest(), SaveDataAsJson.saveDataAsJson(groupData));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Optional<Response> response= RequestTool.sendRequest(request);

        try {
            List<UserData> results = ReadObjectFromJson.<UserData>readListObject(response.get().getData(),UserData.class);
            results.forEach(result -> {
                if (result.getIdUser() == sceneController.getId()){
                    isSet = true;
                    switchMode();
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void setData(Group group) {
        this.groupCode.setText(group.getCode());
        this.groupName.setText(group.getName());
        this.isSet = group.getIsSetFlag();
        switchMode();
        setMembership();
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
            if (isSet){
                leaveGroup(groupId, sceneController.getId());
            }else {
                joinGroup(groupId, sceneController.getId());
            }

            switchMode();
        });
    }

    private void setChooseGroupButtonListener(){
        buttonSelectGroup.setOnAction(actionEvent -> {
            chartController.changeGroup(groupId, buttonSelectGroup);
        });
    }

    private void joinGroup(int userId, int groupId){

        UserGroupData groupData=new UserGroupData(groupId, userId);
        Request request= null;
        try {
            request = new Request(RequestType.ADD_USER_TO_GROUP.getNameRequest(), SaveDataAsJson.saveDataAsJson(groupData));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Optional<Response> response = RequestTool.sendRequest(request);

        isSet = true;

    }

    private void leaveGroup(int userId, int groupId){
        UserGroupData groupData=new UserGroupData(groupId,userId);
        Request request= null;
        try {
            request = new Request(RequestType.REMOVE_USER_FROM_GROUP.getNameRequest(), SaveDataAsJson.saveDataAsJson(groupData));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Optional<Response> response= RequestTool.sendRequest(request);

        isSet = false;
    }

}
