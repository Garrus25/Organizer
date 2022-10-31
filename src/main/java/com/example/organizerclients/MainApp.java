package com.example.organizerclients;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage){
        SceneController sceneController = SceneController.getInstance();
        sceneController.setStage(stage);
        sceneController.setLoginScene();
        stage.setTitle(OrganizerProperties.MAIN_VIEW_TITLE);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
