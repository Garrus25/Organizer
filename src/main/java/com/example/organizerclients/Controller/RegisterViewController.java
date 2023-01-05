package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.MailerServices;
import com.example.organizerclients.Model.OrganizerProperties;
import com.example.organizerclients.Model.TokenAuthorizeGeneratorService;
import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.RegisterData;
import com.example.organizerclients.Requests.RequestObjects.UserID;
import com.example.organizerclients.Requests.RequestObjects.UserLogin;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class RegisterViewController{
    private final SceneController sceneController = SceneController.getInstance();

    @FXML
    public TextField loginTextField;

    @FXML
    private Label registerText;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label informationText;

    @FXML
    private Button registerButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    public void initialize(){
        setFieldParameters();
        setButtonParameters();
    }

    private Integer token;

    private void tempRegisterRequest(String login, String password, String email, String name, String surname){
        this.token = Integer.parseInt(TokenAuthorizeGeneratorService.createTokenAuthorizeUser());
        RegisterData registerData = new RegisterData(1, login, password, email, name, surname, "", token , false);
        Request request = null;

        try {
            request = new Request(RequestType.REGISTER_USER_TEMPORARY.getNameRequest(),
                    SaveDataAsJson.saveDataAsJson(registerData));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Optional<Response> response= RequestTool.sendRequest(request);

    }

    public String getInsertedId(String login){
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
    public void onRegisterButtonClick(){
        String emailAddress = emailTextField.getText();
        String password = passwordTextField.getText();
        String login = loginTextField.getText();
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();

        Map<String, String> confirmationViewData = new HashMap<>();


        if (emailAddress.length() > 0 && password.length() > 0){
            Boolean result = checkEmailAddress(emailAddress);
            if (result){
                tempRegisterRequest(login, password, emailAddress, name, surname);

                confirmationViewData.put("emailAddress", emailAddress);
                confirmationViewData.put("id", getInsertedId(login));
                confirmationViewData.put("token", token.toString());

                sendEmailMessage(emailAddress);
                sceneController.setUserData(confirmationViewData);
                sceneController.setConfirmationScene();
            }else {
                informationText.setText(OrganizerProperties.REGISTER_WRONG_EMAIL_FORMAT_TEXT);
                informationText.setTextFill(Color.RED);
            }
        }else {
            informationText.setText(OrganizerProperties.LOGIN_EMPTY_FIELD_TEXT);
            informationText.setTextFill(Color.RED);
        }
    }

    @FXML
    public void onReturnButtonClick(){
        sceneController.setLoginScene();
    }

    protected void setFieldParameters(){
        registerText.setText(OrganizerProperties.REGISTER_BUTTON_TEXT);
        informationText.setText("");
        emailTextField.setPromptText(OrganizerProperties.EMAIL_TEXTFIELD_PROMPT_TEXT);
        passwordTextField.setPromptText(OrganizerProperties.PASSWORD_TEXTFIELD_PROMPT_TEXT);
    }

    protected void setButtonParameters(){
        registerButton.setText(OrganizerProperties.REGISTER_BUTTON_TEXT);
        returnButton.setText(OrganizerProperties.REGISTER_RETURN_BUTTON_TEXT);
    }

    private Boolean checkEmailAddress(String address){
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        return Pattern.compile(regexPattern)
                .matcher(address)
                .matches();
    }

    //TODO Do sprawdzenia czy emaile będą przychodziły gdy serwer wstanie
    private void sendEmailMessage(String address){
        //MailerServices.sendMail(address);
    }
}
