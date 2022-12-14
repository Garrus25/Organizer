package com.example.organizerclients.Controller;

import com.example.organizerclients.MainApp;
import com.example.organizerclients.Model.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static final Integer MAIN_STAGE_WIDTH = 1600;
    private static final Integer MAIN_STAGE_HEIGTH = 900;
    private static SceneController sceneController;
    private Stage stage;

    private Scene loginScene;
    private Scene registerScene;
    private Scene confirmationScene;
    private Scene singleUserScene;

    private Integer id;

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
        setStylesheet(singleUserScene);
    }

    public void setAddGroupStage(){
        Scene addGroupScene;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("add-group-view.fxml"));
        try {
            addGroupScene = new Scene(fxmlLoader.load());
            setCustomStage(addGroupScene, "Add group");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setShowGroupListStage(ChartController chartController){
        Scene showGroupListScene;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("show-group-list-view.fxml"));
        try {
            ShowGroupListViewController showGroupListViewController = new ShowGroupListViewController(chartController);
            fxmlLoader.setController(showGroupListViewController);
            showGroupListScene = new Scene(fxmlLoader.load());
            setCustomStage(showGroupListScene, "Group list");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showUserPanelStage(){
        Scene userPanelScene;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("user-panel-view.fxml"));
        try {
            userPanelScene = new Scene(fxmlLoader.load());
            setCustomStage(userPanelScene, "User Panel");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showMeetingStage(ChartController chartController, Event event){
        Scene meetingScene;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("add-task-view.fxml"));
        AddTaskViewController addTaskViewController = new AddTaskViewController(chartController, event);
        fxmlLoader.setController(addTaskViewController);
        try {
            meetingScene = new Scene(fxmlLoader.load());
            fxmlLoader.getController();
            setCustomStage(meetingScene, "Task editor");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setCustomStage(Scene scene, String title) {
        setStylesheet(scene);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
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
        createSingleUserScene();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
