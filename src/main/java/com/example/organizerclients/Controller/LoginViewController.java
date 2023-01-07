package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.OrganizerProperties;
import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Optional;

public class LoginViewController{
    private final SceneController sceneController = SceneController.getInstance();

    @FXML
    private Label loginText;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label informationText;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    public void initialize(){
        setFieldParameters();
        setButtonParameters();
    }

    public String getUserId(String login){
        UserLogin userLogin = new UserLogin(login);
        UserID responseData = null;

        try {
            Request request=new Request(RequestType.GET_USER_ID_FROM_LOGIN.getNameRequest(), SaveDataAsJson.saveDataAsJson(userLogin));
            Optional<Response> response= RequestTool.sendRequest(request);
            responseData= ReadObjectFromJson.read( response.get().getData(),UserID.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return responseData.getUserID();
    }

    @FXML
    public void onLoginButtonClick() {
        String emailAddress = emailTextField.getText();
        String password = passwordTextField.getText();
        sceneController.setId(Integer.parseInt(getUserId(emailAddress)));

        if (emailAddress.length() > 0 && password.length() > 0){
            if (checkCredentials(emailAddress,password) && isAccountActive()){
                showCredentialsMessage(true);
                sceneController.setId(Integer.parseInt(getUserId(emailAddress)));
                sceneController.setSingleUserScene();
            }else {
                showCredentialsMessage(false);
            }
        }else {
            informationText.setText(OrganizerProperties.LOGIN_EMPTY_FIELD_TEXT);
            informationText.setTextFill(Color.RED);
        }
    }

    private Boolean isAccountActive(){
        UserID userId = new UserID(sceneController.getId().toString());
        Request request= null;

        try {
            request = new Request(RequestType.GET_USER_DATA.getNameRequest(), SaveDataAsJson.saveDataAsJson(userId));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Optional<Response> response = RequestTool.sendRequest(request);

        try {
            UserData userData = ReadObjectFromJson.read(response.get().getData(),UserData.class);
            return userData.getIsActive() != 0;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;

    }

    @FXML
    public void onRegisterButtonClick() {
        sceneController.setRegisterScene();
    }

    protected void setFieldParameters(){
        loginText.setText(OrganizerProperties.LOGIN_BUTTON_TEXT);
        informationText.setText("");
        emailTextField.setPromptText(OrganizerProperties.LOGIN_TEXTFIELD_PROMPT_TEXT);
        passwordTextField.setPromptText(OrganizerProperties.PASSWORD_TEXTFIELD_PROMPT_TEXT);
    }

    protected void setButtonParameters(){
        loginButton.setText(OrganizerProperties.LOGIN_BUTTON_TEXT);
        registerButton.setText(OrganizerProperties.REGISTER_BUTTON_TEXT);
    }

    private void showCredentialsMessage(Boolean correct){
        if (correct){
            informationText.setText(OrganizerProperties.LOGIN_CORRECT_CREDENTIALS_TEXT);
            informationText.setTextFill(Color.GREEN);
        }else{
            informationText.setText(OrganizerProperties.LOGIN_WRONG_CREDENTIALS_TEXT);
            informationText.setTextFill(Color.RED);
        }
    }

    private Boolean checkCredentials(String email, String password){
        System.out.println("email: " + emailTextField.getText());
        System.out.println("password: " + passwordTextField.getText());

        LoginAndPassword loginAndPassword = new LoginAndPassword(email, password);

        try {
            Request request = new Request(RequestType.USER_LOGIN_DATA_VALID.getNameRequest(), SaveDataAsJson.saveDataAsJson(loginAndPassword));
            Optional<Response> response = RequestTool.sendRequest(request);
            ValidLoginData responseData = ReadObjectFromJson.read(response.get().getData(),ValidLoginData.class);
            return responseData.isLoginDataValid();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return false;
    }
}