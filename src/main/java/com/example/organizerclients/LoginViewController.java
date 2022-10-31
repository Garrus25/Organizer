package com.example.organizerclients;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginViewController {
    private final SceneController sceneController = SceneController.getInstance();

    @FXML
    private Label loginText;

    @FXML
    private Label informationText;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    public void initialize(){
        setFieldParameters();
        setButtonParameters();
    }

    @FXML
    protected void onLoginButtonClick() {
        showCredentialsMessage(checkCredentials());
    }

    @FXML
    protected void onRegisterButtonClick() {
        sceneController.setRegisterScene();
    }

    @FXML
    private void setFieldParameters(){
        loginText.setText(OrganizerProperties.LOGIN_BUTTON_TEXT);
        informationText.setText("");
        emailTextField.setPromptText(OrganizerProperties.EMAIL_TEXTFIELD_PROMPT_TEXT);
        passwordTextField.setPromptText(OrganizerProperties.PASSWORD_TEXTFIELD_PROMPT_TEXT);
    }

    private void setButtonParameters(){
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

    /**
     * Tutaj będzie wrzucony call do bazy sprawdzający dane pobrane z textfieldów
     */
    private Boolean checkCredentials(){
        System.out.println("email: " + emailTextField.getText());
        System.out.println("password: " + emailTextField.getText());
        return false;
    }
}