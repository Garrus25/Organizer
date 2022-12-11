package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.CustomCell;
import com.example.organizerclients.Model.Event;
import com.example.organizerclients.Model.Model;
import com.example.organizerclients.Model.OrganizerProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import jfxtras.scene.control.CalendarPicker;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ChartController {

    private final SceneController sceneController = SceneController.getInstance();
    private ObservableList<Model> tableContent;
    private final ArrayList<Model> singleUserModel = new ArrayList<>();
    private final ArrayList<Model> groupModel = new ArrayList<>();

    boolean groupModelSet = false;

    @FXML
    public HBox mainContentBox;

    @FXML
    TableView<Model> mainTable;

    @FXML
    TableColumn<String,String> timeColumn;

    @FXML
    TableColumn<Event,String> mondayColumn;

    @FXML
    TableColumn<Event,String> tuesdayColumn;

    @FXML
    TableColumn<Event,String> wednesdayColumn;

    @FXML
    TableColumn<Event,String> thursdayColumn;

    @FXML
    TableColumn<Event,String> fridayColumn;

    @FXML
    TableColumn<Event,String> saturdayColumn;

    @FXML
    TableColumn<Event,String> sundayColumn;

    @FXML
    Button addGroupButton;

    @FXML
    Button showGroupsButton;

    @FXML
    Button switchViewButton;

    @FXML
    CalendarPicker calendarPicker;

    @FXML
    public void initialize(){
        setObservableList();
        setTableView();
        initialColumnSetup();
        disableTimeColumn();
        addCellClickListener();
        setBasicButtonParameters();
        setButtonListeners();
        setCalendarListener();
    }

    private void changeUIElements(){
        setChangeViewButtonName();
        setTableColumnNames();
    }

    private void setChangeViewButtonName(){
        if (!groupModelSet){
            switchViewButton.setText(OrganizerProperties.MAINVIEW_SWITCHGROUP_TEXT);
        }else {
            switchViewButton.setText(OrganizerProperties.MAINVIEW_SWITCHTOSINGLE_TEXT);
        }
    }

    private void setTableColumnNames(){
        if (!groupModelSet){
            setGroupColumns();
        }else {
            setSingleUserColumns();
        }
    }

    private void changeViewType(){
        setModel();
        changeUIElements();
        groupModelSet = !groupModelSet;
    }

    private void changeModel(ArrayList<Model> model){
        tableContent = FXCollections.observableArrayList(
                model
        );
        mainTable.getItems().clear();
        mainTable.setItems(tableContent);
    }

    private void setModel(){
        if (!groupModelSet){
            changeModel(groupModel);
        }else {
            changeModel(singleUserModel);
        }
        mainTable.refresh();
    }

    public <T> void setColumns(TableColumn<T, String> tableColumn, String propertyValue, String tableName){
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyValue));
        tableColumn.setCellFactory(cell -> new CustomCell<>());
        tableColumn.setText(tableName);
        tableColumn.setResizable(false);
        tableColumn.setReorderable(false);
        tableColumn.setSortable(false);
        tableColumn.setMinWidth(163.5);
    }

    public void setTableView(){
        mainTable.setItems(tableContent);
        mainTable.setFixedCellSize(35.75);
        mainTable.getSelectionModel().setCellSelectionEnabled(true);
    }

    private void setObservableList() {
        for (int i = 0; i < 24; i++) {
            singleUserModel.add(new Model(Integer.toString(i)));
            groupModel.add(new Model(Integer.toString(i)));
        }

        tableContent = FXCollections.observableArrayList(
                singleUserModel
            );

    }

    private void setData(int x, int y, String eventName, String eventDesc, Date date){
        switch (x){
            case 1 :
                mainTable.getItems().get(y).setMondayColumn(new Event(eventName, eventDesc,date));
                break;
            case 2 :
                mainTable.getItems().get(y).setTuesdayColumn(new Event(eventName, eventDesc,date));
                break;
            case 3 :
                mainTable.getItems().get(y).setWednesdayColumn(new Event(eventName, eventDesc,date));
                break;
            case 4 :
                mainTable.getItems().get(y).setThursdayColumn(new Event(eventName, eventDesc,date));
                break;
            case 5 :
                mainTable.getItems().get(y).setFridayColumn(new Event(eventName, eventDesc,date));
                break;
            case 6 :
                mainTable.getItems().get(y).setSaturdayColumn(new Event(eventName, eventDesc,date));
                break;
            case 7 :
                mainTable.getItems().get(y).setSundayColumn(new Event(eventName, eventDesc,date));
                break;

        }
    }

    private void initialColumnSetup() {
        setColumns(timeColumn, "timeColumn", "");
        setColumns(mondayColumn, "mondayColumn", "Monday");
        setColumns(tuesdayColumn, "tuesdayColumn", "Tuesday");
        setColumns(wednesdayColumn, "wednesdayColumn", "Wednesday");
        setColumns(thursdayColumn, "thursdayColumn", "Thursday");
        setColumns(fridayColumn, "fridayColumn", "Friday");
        setColumns(saturdayColumn, "saturdayColumn", "Saturday");
        setColumns(sundayColumn, "sundayColumn", "Sunday");
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void setSingleUserColumns(){
        mainTable.getColumns().get(1).setText("Monday");
        mainTable.getColumns().get(2).setText("Tuesday");
        mainTable.getColumns().get(3).setText("Wednesday");
        mainTable.getColumns().get(4).setText("Thursday");
        mainTable.getColumns().get(5).setText("Friday");
        mainTable.getColumns().get(6).setText("Saturday");
        mainTable.getColumns().get(7).setText("Sunday");
    }

    private void setGroupColumns(){
        mainTable.getColumns().get(1).setText("Test1");
        mainTable.getColumns().get(2).setText("TEST");
        mainTable.getColumns().get(3).setText("f");
        mainTable.getColumns().get(4).setText("g");
        mainTable.getColumns().get(5).setText("h");
        mainTable.getColumns().get(6).setText("j");
        mainTable.getColumns().get(7).setText("j");
    }

    private void disableTimeColumn(){
        timeColumn.setCellFactory(
                new Callback<TableColumn<String, String>, TableCell<String, String>>() {
                    @Override
                    public TableCell<String, String> call(TableColumn<String, String> paramTableColumn) {
                        return new TextFieldTableCell<String, String>(new DefaultStringConverter()) {
                            @Override
                            public void updateItem(String s, boolean b) {
                                super.updateItem(s, b);
                                setDisable(true);
                                setEditable(false);
                            }
                        };
                    }
                });
    }

    private void addCellClickListener(){
        mainTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getTarget() instanceof CustomCell){
                    //sceneController.showMeetingStage();
                    int y = ((CustomCell<?, ?>) event.getTarget()).getY();
                    int x = ((CustomCell<?, ?>) event.getTarget()).getX();
                    System.out.println(x);
                    System.out.println(y);
                    setData(x + 1,y,"sa","test",new Date());
                }
            }
        });
    }

    private void setBasicButtonParameters(){
        addGroupButton.getStyleClass().add("menuButton");
        showGroupsButton.getStyleClass().add("menuButton");
        switchViewButton.getStyleClass().add("menuButton");

        addGroupButton.setText(OrganizerProperties.MAINVIEW_ADDGROUP_TEXT);
        showGroupsButton.setText(OrganizerProperties.MAINVIEW_SHOWGROUP_TEXT);
        switchViewButton.setText(OrganizerProperties.MAINVIEW_SWITCHGROUP_TEXT);
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
    }

    private void setCalendarListener(){
        calendarPicker.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(calendarPicker.getCalendar().getTime());
                LocalDateTime ldt = calendarPicker.getCalendar().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                System.out.println(ldt.getDayOfYear());
            }
        });
    }
}
