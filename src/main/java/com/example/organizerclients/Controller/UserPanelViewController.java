package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.OrganizerProperties;
import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.UserData;
import com.example.organizerclients.Requests.RequestObjects.UserID;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Optional;

public class UserPanelViewController {

    SceneController sceneController = SceneController.getInstance();

    @FXML
    private Label name;

    @FXML
    private Label surname;

    @FXML
    private Label email;

    @FXML
    private Label login;

    @FXML
    private Label surnameProperties;

    @FXML
    private Label nameProperties;

    @FXML
    private Label emailProperties;

    @FXML
    private Label loginProperties;

    @FXML
    public void initialize() {
        setContent();
    }

    private UserData fetchUserData(){
        UserID userId = new UserID(sceneController.getId().toString());
        Request request= null;

        try {
            request = new Request(RequestType.GET_USER_DATA.getNameRequest(), SaveDataAsJson.saveDataAsJson(userId));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Optional<Response> response = RequestTool.sendRequest(request);

        try {
            return ReadObjectFromJson.read(response.get().getData(),UserData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;

    }

    private void setContent(){
        UserData userData = fetchUserData();

        surnameProperties.setText(userData.getSurname());
        nameProperties.setText(userData.getName());
        emailProperties.setText(userData.getEmail());
        loginProperties.setText(userData.getLogin());
    }
}
