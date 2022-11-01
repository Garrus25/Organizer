package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.EmailSender;
import com.example.organizerclients.Model.OrganizerProperties;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
    }

    @FXML
    public void onConfirmButtonClick(){
        int test = 3333;
        if (codeTextField.getText().length() > 0){
            if (Integer.parseInt(codeTextField.getText()) == test) {
                System.out.println("GIT");
                informationText.setText(OrganizerProperties.REGISTER_CORRECT_CODE_TEXT);
                informationText.setTextFill(Color.GREEN);
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

    //TODO add sending messages here
    @FXML
    public void onResendButtonClick(){
        String emailAddress = emailTextField.getText();

        if (emailAddress.length() > 0){
            Boolean result = emailSender.checkEmailAddress(emailAddress);
            if (result){
                emailSender.sendMessage();
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
        emailTextField.setText(sceneController.getUserData().toString());
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

    /*
    TODO
    We need to remember generated codes and check them here.
     */
    public Boolean checkConfirmationCode(){
        return true;
    }
}
