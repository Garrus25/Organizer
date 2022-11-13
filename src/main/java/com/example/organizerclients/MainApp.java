package com.example.organizerclients;

import com.example.organizerclients.Model.OrganizerProperties;
import com.example.organizerclients.Controller.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {
    private final SceneController sceneController = SceneController.getInstance();

    @Override
    public void init() throws Exception {
        super.init();
        sceneController.prepareScenes();
    }

    @Override
    public void start(Stage stage){
        sceneController.setStage(stage);
        //sceneController.setLoginScene();
        //sceneController.setSingleUserScene();
        sceneController.setGroupScene();
        stage.setTitle(OrganizerProperties.MAIN_VIEW_TITLE);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
