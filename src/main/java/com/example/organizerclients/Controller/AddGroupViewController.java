package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.OrganizerProperties;
import com.example.organizerclients.Model.TokenAuthorizeGeneratorService;
import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.GroupCreationData;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Optional;

public class AddGroupViewController {

    SceneController sceneController = SceneController.getInstance();

    @FXML
    private Label titleView;

    @FXML
    private TextField groupName;

    @FXML
    private Button buttonGroupCreate;

    @FXML
    private Label informationText;

    @FXML
    public void initialize() {
        setFieldParameters();
        setButtonGroupCreateListener();
    }

    private void setFieldParameters(){
        titleView.setText(OrganizerProperties.ADD_GROUP_VIEW_VIEW_TITLE_TEXT);
        groupName.setPromptText(OrganizerProperties.ADD_GROUP_VIEW_GROUP_NAME_TEXT_FIELD_PROMPT_TEXT);
        buttonGroupCreate.setText(OrganizerProperties.ADD_GROUP_VIEW_GROUP_CREATE_BUTTON_TEXT);
        informationText.setVisible(false);
    }

    public void setButtonGroupCreateListener(){
        buttonGroupCreate.setOnAction(actionEvent -> {
            if (groupName.getText().length() <= 3){
                informationText.setText(OrganizerProperties.ADD_GROUP_TOO_SHORT_NAME_TEXT);
                informationText.setTextFill(Color.RED);
                informationText.setVisible(true);
            }else{
                addGroupRequest(groupName.getText());
                sceneController.getStage().close();
            }
        });
    }

    private void addGroupRequest(String groupName){
        GroupCreationData groupData = new GroupCreationData(groupName,groupName.substring(0,2) + TokenAuthorizeGeneratorService.createTokenAuthorizeUser());
        try {
            Request request=new Request(RequestType.CREATE_GROUP.getNameRequest(), SaveDataAsJson.saveDataAsJson(groupData));
            Optional<Response> response= RequestTool.sendRequest(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
