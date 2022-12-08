package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.CustomCell;
import com.example.organizerclients.Model.Event;
import com.example.organizerclients.Model.Model;
import com.example.organizerclients.Model.OrganizerProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class SingleUserViewController{

    private final SceneController sceneController = SceneController.getInstance();
    private ObservableList<Model> tableContent;
    private final ArrayList<Model> model = new ArrayList<>();

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
    Button switchToGroupViewButton;

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
        mainTable.setFixedCellSize(55.0);
        mainTable.getSelectionModel().setCellSelectionEnabled(true);
    }

    private void setObservableList() {
        for (int i = 0; i < 24; i++) {
            model.add(new Model(Integer.toString(i)));
        }

        tableContent = FXCollections.observableArrayList(
                model
            );
    }

    private void initialColumnSetup() {
        setColumns(timeColumn, "time", "");
        setColumns(mondayColumn, "event1", "Monday");
        setColumns(tuesdayColumn, "event2", "Tuesday");
        setColumns(wednesdayColumn, "event3", "Wednesday");
        setColumns(thursdayColumn, "event4", "Thursday");
        setColumns(fridayColumn, "event5", "Friday");
        setColumns(saturdayColumn, "event6", "Saturday");
        setColumns(sundayColumn, "event7", "Sunday");

        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        mainTable.getItems().get(4).setEvent1(new Event("1", "dsa", new Date()));
        mainTable.getItems().get(23).setEvent3(new Event("2", "dsa", new Date()));
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
                if (event.getTarget() instanceof TextFieldTableCell){
                    ((TextFieldTableCell<?, ?>) event.getTarget()).setId("test");
                }
            }
        });
    }

    private void setBasicButtonParameters(){
        addGroupButton.getStyleClass().add("menuButton");
        showGroupsButton.getStyleClass().add("menuButton");
        switchToGroupViewButton.getStyleClass().add("menuButton");

        addGroupButton.setText(OrganizerProperties.MAINVIEW_ADDGROUP_TEXT);
        showGroupsButton.setId(OrganizerProperties.MAINVIEW_SHOWGROUP_TEXT);
        switchToGroupViewButton.setId(OrganizerProperties.MAINVIEW_SWITCHGROUP_TEXT);
    }

    private void setButtonListeners(){
        addGroupButton.setOnAction(actionEvent -> {

        });

        showGroupsButton.setOnAction(actionEvent -> {

        });

        switchToGroupViewButton.setOnAction(actionEvent -> {
            sceneController.setGroupScene();
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
