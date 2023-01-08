package com.example.organizerclients.Controller;

import com.example.organizerclients.MainApp;
import com.example.organizerclients.Model.Group;
import com.example.organizerclients.Model.OrganizerProperties;
import com.example.organizerclients.Requests.*;
import com.example.organizerclients.Requests.RequestObjects.GroupData;
import com.example.organizerclients.Requests.RequestObjects.GroupId;
import com.example.organizerclients.Requests.RequestObjects.UserData;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ShowGroupListViewController {

    @FXML
    private Label title;

    @FXML
    private VBox listGroups;

    private ChartController chartController;

    @FXML
    private void initialize(){
        setFieldParameters();
        fetchAllGroupsData();
        addGroupInformationView();
    }

    public ShowGroupListViewController(ChartController chartController) {
        this.chartController = chartController;

    }

    private List<Group> groupList = new ArrayList<>();


    private void setFieldParameters(){
        title.setText(OrganizerProperties.SHOW_GROUP_LIST_VIEW_TITLE_TEXT);
    }


    private void addGroupInformationView() {
        groupList.forEach(group -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("group-information-view.fxml"));

                GroupInformationViewController groupInformationViewController = new GroupInformationViewController(chartController, group.getGroupId());
                fxmlLoader.setController(groupInformationViewController);

                AnchorPane anchorPane = fxmlLoader.load();
                groupInformationViewController.setData(group);
                listGroups.getChildren().add(anchorPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

    }

    private void fetchAllGroupsData(){

        Request request= null;
        try {
            request = new Request(RequestType.GET_ALL_GROUP_DATA.getNameRequest(), SaveDataAsJson.saveDataAsJson(""));
            Optional<Response> response= RequestTool.sendRequest(request);
            List<GroupData> result = ReadObjectFromJson.<GroupData>readListObject(response.get().getData(),GroupData.class);

            for (GroupData groupData : result) {
                groupList.add(new Group(groupData.getIdGroup(), groupData.getNameGroup(), groupData.getGroupCode(), false, false));
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private void addUserToGrup(){

    }

    private void removeUserFromGroup(){

    }
}