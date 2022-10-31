package com.example.organizerclients;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginViewController extends ViewController{
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
    @Override
    public void initialize(){
        setFieldParameters();
        setButtonParameters();
    }

    @FXML
    public void onLoginButtonClick() {
        String emailAddress = emailTextField.getText();
        String password = passwordTextField.getText();

        if (emailAddress.length() > 0 && password.length() > 0){
            showCredentialsMessage(checkCredentials(emailAddress,password));
        }else {
            informationText.setText(OrganizerProperties.LOGIN_EMPTY_FIELD_TEXT);
            informationText.setTextFill(Color.RED);
        }
    }

    @FXML
    public void onRegisterButtonClick() {
        sceneController.setRegisterScene();
    }

    @Override
    protected void setFieldParameters(){
        loginText.setText(OrganizerProperties.LOGIN_BUTTON_TEXT);
        informationText.setText("");
        emailTextField.setPromptText(OrganizerProperties.EMAIL_TEXTFIELD_PROMPT_TEXT);
        passwordTextField.setPromptText(OrganizerProperties.PASSWORD_TEXTFIELD_PROMPT_TEXT);
    }

    @Override
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

    /**
     * Tutaj będzie wrzucony call do bazy sprawdzający dane pobrane z textfieldów

     */
    private Boolean checkCredentials(String email, String password){
        System.out.println("email: " + emailTextField.getText());
        System.out.println("password: " + emailTextField.getText());
        return false;
    }
}