package com.example.organizerclients.Controller;

import com.example.organizerclients.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static final Integer MAIN_STAGE_WIDTH = 1600;
    private static final Integer MAIN_STAGE_HEIGTH = 900;
    private static final Integer CUSTOM_STAGE_WIDTH = 300;
    private static final Integer CUSTOM_STAGE_HEIGTH = 400;
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
            loginScene = new Scene(fxmlLoader.load(), MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createRegisterScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("register-view.fxml"));
        try {
            registerScene = new Scene(fxmlLoader.load(), MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createConfirmationScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("code-confirmation-view.fxml"));
        try {
            confirmationScene = new Scene(fxmlLoader.load(), MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSingleUserScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("single-user-view.fxml"));
        try {
            singleUserScene = new Scene(fxmlLoader.load(), MAIN_STAGE_WIDTH, MAIN_STAGE_HEIGTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAddGroupStage(){
        Scene addGroupScene;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
        try {
            addGroupScene = new Scene(fxmlLoader.load(), CUSTOM_STAGE_WIDTH, CUSTOM_STAGE_HEIGTH);
            setCustomStage(addGroupScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setShowGroupListStage(){
        Scene showGroupListScene;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
        try {
            showGroupListScene = new Scene(fxmlLoader.load(), CUSTOM_STAGE_HEIGTH, CUSTOM_STAGE_HEIGTH);
            setCustomStage(showGroupListScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showUserPanelStage(){
        Scene userPanelScene;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
        try {
            userPanelScene = new Scene(fxmlLoader.load(), CUSTOM_STAGE_HEIGTH, CUSTOM_STAGE_HEIGTH);
            setCustomStage(userPanelScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showMeetingStage(){
        Scene meetingScene;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));
        try {
            meetingScene = new Scene(fxmlLoader.load(), CUSTOM_STAGE_HEIGTH, CUSTOM_STAGE_HEIGTH);
            setCustomStage(meetingScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setCustomStage(Scene scene) {
        setStylesheet(scene);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
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

    public Stage getStage() {
        return stage;
    }
}
