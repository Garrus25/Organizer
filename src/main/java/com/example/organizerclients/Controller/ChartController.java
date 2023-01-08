package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import jfxtras.scene.control.CalendarPicker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ChartController {

    private final SceneController sceneController = SceneController.getInstance();
    private final CreateSingleUserTable singleUserModel = new CreateSingleUserTable(sceneController.getId());
    private final CreateGroupTable groupModel = new CreateGroupTable();
    private CreateTable currentModel = singleUserModel;
    private LocalDate currentSelectedDate = LocalDate.now();
    private Integer currentGroupId = 10;
    private Button currentGroupButton = null;
    private final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    private final int REFRESH_TIME = 5;

    private boolean groupModelSet = false;

    @FXML
    public HBox mainContentBox;

    @FXML
    private TableView<Map<String, Event>> mainTable;

    @FXML
    private Button addGroupButton;

    @FXML
    private Button showGroupsButton;

    @FXML
    private Button switchViewButton;

    @FXML
    private Button userPanelButton;

    @FXML
    private CalendarPicker calendarPicker;

    @FXML
    public void initialize(){
        setTableView();
        createTable(currentSelectedDate);
        addCellClickListener();
        setBasicButtonParameters();
        setButtonListeners();
        setCalendarListener();
        refreshTableContent();
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
        currentModel = model;
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

    public void updateModel(Event event){
        mainTable.refresh();
        currentModel.insertData(event);
    }

    private void createTable(LocalDate localDate) {
        mainTable.getColumns().clear();
        mainTable.getColumns().add(currentModel.createTimeColumn());
        LinkedHashMap<TableColumnKey, TableColumn<Map<String, Event>, String>> columns
                = currentModel.createColumns(localDate);
        columns.forEach((key, value) -> {
            mainTable.getColumns().add(value);
        });

        mainTable.setItems(currentModel.createModel(columns.keySet()));
        mainTable.refresh();
    }

    private void addCellClickListener(){
        final ChartController chartController = this;
        mainTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getTarget() instanceof CustomCell){
                    CustomCell<?, ?> target = (CustomCell<?, ?>) event.getTarget();
                    Event item = ((Event) target.getItem());
                    sceneController.showMeetingStage(chartController, item);
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

        final ChartController chartController = this;
        showGroupsButton.setOnAction(actionEvent -> {
            sceneController.setShowGroupListStage(chartController);
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
                if (null != calendarPicker.getCalendar()){
                    currentSelectedDate = calendarPicker.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    createTable(currentSelectedDate);
                    mainTable.refresh();
                }
            }
        });
    }

    public void changeGroup(int groupId, Button group) {
        groupModel.changeGroup(groupId);
        currentGroupId = groupId;
        if (currentGroupButton != null) {
            currentGroupButton.setVisible(true);
        }
        currentGroupButton = group;
        currentGroupButton.setVisible(false);
        if (groupModelSet) {
            createTable(currentSelectedDate);
            mainTable.refresh();
        }
    }

    public Integer getCurrentGroupId() {
        return currentGroupId;
    }

    public void setCurrentGroupButton(Button currentGroupButton) {
        this.currentGroupButton = currentGroupButton;
    }

    public void refreshTableContent() {
        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                while (true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("resrewf");
                            createTable(currentSelectedDate);
                        }
                    });
                    Thread.sleep(REFRESH_TIME * 1000);
                }
            }
        };
        scheduledExecutorService.schedule(task, REFRESH_TIME, TimeUnit.SECONDS);



    }
}
