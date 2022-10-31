package com.example.organizerclients;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static final Integer STAGE_WIDTH = 1280;
    private static final Integer STAGE_HEIGHT = 768;
    private static SceneController sceneController;
    private Stage stage;

    private Scene loginScene;
    private Scene registerScene;

    public static SceneController getInstance() {
        if (sceneController == null) {
            sceneController = new SceneController();
        }
        return sceneController;
    }

    public void prepareScenes(){
        createRegisterScene();
        createLoginScene();
    }

    private void createLoginScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
        try {
            loginScene = new Scene(fxmlLoader.load(), STAGE_WIDTH, STAGE_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createRegisterScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("register-view.fxml"));
        try {
            registerScene = new Scene(fxmlLoader.load(), STAGE_WIDTH, STAGE_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLoginScene(){
        stage.setScene(loginScene);
    }

    public void setRegisterScene(){
        stage.setScene(registerScene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
