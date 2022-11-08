package com.example.organizerclients.Controller;

import com.example.organizerclients.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static final Integer STAGE_WIDTH = 1600;
    private static final Integer STAGE_HEIGHT = 900;
    private static SceneController sceneController;
    private Stage stage;

    private Scene loginScene;
    private Scene registerScene;
    private Scene confirmationScene;
    private Scene singleUserScene;

    public static SceneController getInstance() {
        if (sceneController == null) {
            sceneController = new SceneController();
        }
        return sceneController;
    }

    public void prepareScenes(){
        createRegisterScene();
        createLoginScene();
        createSingleUserScene();
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

    private void createConfirmationScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("code-confirmation-view.fxml"));
        try {
            confirmationScene = new Scene(fxmlLoader.load(), STAGE_WIDTH, STAGE_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSingleUserScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("single-user-view.fxml"));
        try {
            singleUserScene = new Scene(fxmlLoader.load(), STAGE_WIDTH, STAGE_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setStylesheet(singleUserScene);
    }

    public void setLoginScene(){
        stage.setScene(loginScene);
    }

    public void setRegisterScene(){
        stage.setScene(registerScene);
    }

    public void setConfirmationScene(){
        createConfirmationScene();
        stage.setScene(confirmationScene);
    }

    public void setSingleUserScene(){
        stage.setScene(singleUserScene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUserData(Object object){
        stage.setUserData(object);
    }

    public Object getUserData(){
        return stage.getUserData();
    }

    public void setStylesheet(Scene scene) {
        scene.getStylesheets().add("styles.css");
    }
}
