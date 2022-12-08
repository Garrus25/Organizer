package com.example.organizerclients.Controller;

import com.example.organizerclients.MainApp;
import com.example.organizerclients.Model.OrganizerProperties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ShowGroupListViewController {

    @FXML
    private Label title;

    @FXML
    private VBox listGroups;

    @FXML
    public void initialize() {
        setFieldParameters();
        addGroupInformationView();
    }

    private void setFieldParameters(){
        title.setText(OrganizerProperties.SHOW_GROUP_LIST_VIEW_TITLE_TEXT);
    }


    private void addGroupInformationView() {
        for (int i = 0; i < 5; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("group-information-view.fxml"));
            try {
                AnchorPane anchorPane = fxmlLoader.load();

                GroupInformationViewController groupInformationViewController = fxmlLoader.getController();
                groupInformationViewController.setData("Marek", "Heniek");

                listGroups.getChildren().add(anchorPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
