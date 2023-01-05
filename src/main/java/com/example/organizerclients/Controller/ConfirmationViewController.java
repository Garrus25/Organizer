package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.EmailSender;
import com.example.organizerclients.Model.OrganizerProperties;
import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.UserID;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Optional;

public class ConfirmationViewController{
    private final SceneController sceneController = SceneController.getInstance();
    private final EmailSender emailSender = new EmailSender();

    @FXML
    private Label confirmationText;

    @FXML
    private Label emailInformation;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField codeTextField;

    @FXML
    private Label informationText;

    @FXML
    private Button confirmButton;

    @FXML
    private Button resendButton;

    @FXML
    private Button returnButton;

    @FXML
    public void initialize(){
        setFieldParameters();
        setButtonParameters();
        setCodeTextField();
        emailTextField.setDisable(true);
    }

    @FXML
    public void onConfirmButtonClick(){
        int token = Integer.parseInt(((HashMap<String, String>)sceneController.getUserData()).get("token"));
        if (codeTextField.getText().length() > 0){
            if (Integer.parseInt(codeTextField.getText()) == token) {
                informationText.setText(OrganizerProperties.REGISTER_CORRECT_CODE_TEXT);
                informationText.setTextFill(Color.GREEN);
                sceneController.setSingleUserScene();
                registerRequest(((HashMap<String, String>)sceneController.getUserData()).get("id"));
            }else {
                informationText.setText(OrganizerProperties.REGISTER_WRONG_CODE_TEXT);
                informationText.setTextFill(Color.RED);
            }
        }
    }

    @FXML
    public void onReturnButtonClick(){
        sceneController.setRegisterScene();
    }

    @FXML
    public void onResendButtonClick(){
        String emailAddress = emailTextField.getText();

        if (emailAddress.length() > 0){
            Boolean result = emailSender.checkEmailAddress(emailAddress);
            if (result){
                sendEmailMessage(((HashMap<String, String>)sceneController.getUserData()).get(emailAddress));
                informationText.setText(OrganizerProperties.CODE_CONFIRMATION_MESSAGE_RESENT_TEXT);
                informationText.setTextFill(Color.GREEN);
            }else {
                informationText.setText(OrganizerProperties.REGISTER_WRONG_EMAIL_FORMAT_TEXT);
                informationText.setTextFill(Color.RED);
            }
        }else {
            informationText.setText(OrganizerProperties.LOGIN_EMPTY_FIELD_TEXT);
            informationText.setTextFill(Color.RED);
        }
    }

    protected void setFieldParameters(){
        informationText.setText("");
        codeTextField.setPromptText(OrganizerProperties.CODE_CONFIRMATION_PROMPT_TEXT);
        emailTextField.setText(((HashMap<String, String>)sceneController.getUserData()).get("emailAddress"));
        emailInformation.setText(OrganizerProperties.CODE_CONFIRMATION_EMAIL_INFO_TEXT);
    }

    protected void setButtonParameters(){
        confirmButton.setText(OrganizerProperties.CODE_CONFIRMATION_CONFIRM_BUTTON_TEXT);
        returnButton.setText(OrganizerProperties.CODE_CONFIRMATION_RETURN_BUTTON_TEXT);
        resendButton.setText(OrganizerProperties.CODE_CONFIRMATION_RESEND_TEXT);
    }

    private void setCodeTextField(){
        confirmationText.setText(OrganizerProperties.CODE_CONFIRMATION_TEXT);
        int maxLength = 6;

        codeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    codeTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        codeTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (codeTextField.getText().length() > maxLength) {
                    String s = codeTextField.getText().substring(0, maxLength);
                    codeTextField.setText(s);
                }
            }
        });
    }

    private void registerRequest(String userId){
        UserID idUser = new UserID(userId);
        Request request= null;

        try {
            request = new Request(RequestType.REGISTER_USER.getNameRequest(), SaveDataAsJson.saveDataAsJson(idUser));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Optional<Response> response = RequestTool.sendRequest(request);
    }


    //TODO Odkomentować gdy serwer będzie działac
    private void sendEmailMessage(String address){
        //MailerServices.sendMail(address);
    }
}
