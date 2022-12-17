package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.*;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import jfxtras.scene.control.CalendarPicker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

public class ChartController {

    private final SceneController sceneController = SceneController.getInstance();
    private final CreateTable singleUserModel = new CreateSingleUserTable();
    private final CreateTable groupModel = new CreateGroupTable();
    private CreateTable createTable = singleUserModel;
    private LocalDate currentSelectedDate = LocalDate.now();

    boolean groupModelSet = false;

    @FXML
    public HBox mainContentBox;

    @FXML
    TableView<Map<String, Event>> mainTable;

    @FXML
    Button addGroupButton;

    @FXML
    Button showGroupsButton;

    @FXML
    Button switchViewButton;

    @FXML
    Button userPanelButton;

    @FXML
    CalendarPicker calendarPicker;

    @FXML
    public void initialize(){
        setTableView();
        createTable(currentSelectedDate);
        addCellClickListener();
        setBasicButtonParameters();
        setButtonListeners();
        setCalendarListener();
    }

    private void changeUIElements(){
        setChangeViewButtonName();
    }

    private void setChangeViewButtonName(){
        if (!groupModelSet){
            switchViewButton.setText(OrganizerProperties.MAINVIEW_SWITCHTOSINGLE_TEXT);
        }else {
            switchViewButton.setText(OrganizerProperties.MAINVIEW_SWITCHGROUP_TEXT);
        }
    }

    public void setTableView(){
        mainTable.setFixedCellSize(35.75);
        mainTable.getSelectionModel().setCellSelectionEnabled(true);
    }

    private void changeViewType(){
        setModel();
        changeUIElements();
        groupModelSet = !groupModelSet;
    }

    private void changeModel(CreateTable model){
        createTable = model;
        createTable(currentSelectedDate);
    }

    private void setModel(){
        if (!groupModelSet){
            changeModel(groupModel);
        }else {
            changeModel(singleUserModel);
        }
        mainTable.refresh();
    }

    private void createTable(LocalDate localDate) {
        mainTable.getColumns().clear();
        mainTable.getColumns().add(createTable.createTimeColumn());
        List<TableColumn<Map<String, Event>, String>> columns = createTable.createColumns(localDate);
        CustomCell.numberOfColumns = columns.size();
        mainTable.getColumns().addAll(columns);
        mainTable.setItems(createTable.createModel());
    }

    private void addCellClickListener(){
        mainTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getTarget() instanceof CustomCell){
                    sceneController.showMeetingStage();
                    int y = ((CustomCell<?, ?>) event.getTarget()).getY();
                    int x = ((CustomCell<?, ?>) event.getTarget()).getX();
                    System.out.println(x);
                    System.out.println(y);
                }
            }
        });
    }

    private void setBasicButtonParameters(){
        addGroupButton.getStyleClass().add("menuButton");
        showGroupsButton.getStyleClass().add("menuButton");
        switchViewButton.getStyleClass().add("menuButton");
        userPanelButton.getStyleClass().add("menuButton");

        addGroupButton.setText(OrganizerProperties.MAINVIEW_ADDGROUP_TEXT);
        showGroupsButton.setText(OrganizerProperties.MAINVIEW_SHOWGROUP_TEXT);
        switchViewButton.setText(OrganizerProperties.MAINVIEW_SWITCHGROUP_TEXT);
        userPanelButton.setText(OrganizerProperties.MAINVIEW_USER_PANEL_BUTTON_TEXT);
    }

    private void setButtonListeners(){
        addGroupButton.setOnAction(actionEvent -> {
            sceneController.setAddGroupStage();
        });

        showGroupsButton.setOnAction(actionEvent -> {
            sceneController.setShowGroupListStage();
        });

        switchViewButton.setOnAction(actionEvent -> {
            changeViewType();
        });

        userPanelButton.setOnAction(actionEvent -> {
            sceneController.showUserPanelStage();
        });
    }

    private void setCalendarListener(){
        calendarPicker.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentSelectedDate = calendarPicker.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                createTable(currentSelectedDate);
                mainTable.refresh();
            }
        });
    }
}
