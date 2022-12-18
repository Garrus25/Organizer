package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.OrganizerProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserPanelViewController {

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
        setFieldParameters();
    }

    private void setFieldParameters(){
        surnameProperties.setText(OrganizerProperties.USER_PANEL_VIEW_SURNAME_TEXT);
        nameProperties.setText(OrganizerProperties.USER_PANEL_VIEW_NAME_TEXT);
        emailProperties.setText(OrganizerProperties.USER_PANEL_VIEW_EMAIL_TEXT);
        loginProperties.setText(OrganizerProperties.USER_PANEL_VIEW_LOGIN_TEXT);
    }
}
