package com.example.organizerclients.Controller;

import com.example.organizerclients.MainApp;
import com.example.organizerclients.Model.Group;
import com.example.organizerclients.Model.OrganizerProperties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowGroupListViewController {

    @FXML
    private Label title;

    @FXML
    private VBox listGroups;

    private ChartController chartController;

    @FXML
    private void initialize(){
        System.out.println("Called12345");
        addGroupInformationView(chartController);
        setFieldParameters();
        setModel();
    }

    public ShowGroupListViewController(ChartController chartController) {
        this.chartController = chartController;

    }

    private final List<Group> groupList = new ArrayList<>();


    private void setFieldParameters(){
        title.setText(OrganizerProperties.SHOW_GROUP_LIST_VIEW_TITLE_TEXT);
    }


    private void addGroupInformationView(ChartController chartController) {
        groupList.forEach(group -> {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("group-information-view.fxml"));

            try {
                GroupInformationViewController groupInformationViewController = new GroupInformationViewController(chartController);

                fxmlLoader.setController(group);
                AnchorPane anchorPane = fxmlLoader.load();

                groupInformationViewController.setData(group);

                listGroups.getChildren().add(anchorPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

    }

    private void setModel(){
        groupList.add(new Group("test1","test",false));
        groupList.add(new Group("test1","test",true));
        groupList.add(new Group("test1","test",false));
        groupList.add(new Group("test1","test",true));
    }
}
