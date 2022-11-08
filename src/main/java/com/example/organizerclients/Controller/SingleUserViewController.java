package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Event;
import com.example.organizerclients.Model.EventStringConverter;
import com.example.organizerclients.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.time.LocalDate;
import java.util.ArrayList;

@SuppressWarnings({"unchecked", "rawtypes"})
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
    Button addGroup;

    @FXML
    Button showGroups;

    @FXML
    Button switchToGroupView;

    @FXML
    public void initialize(){
        setObservableList();
        setTableView();
        initialColumnSetup();
        setCalendar();
        disableTimeColumn();
        //setCellColor("Test2");
        addCellClickListener();
    }

    public void setColumns(TableColumn tableColumn, String propertyValue, String tableName){
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyValue));
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

        model.get(4).getEvent1().setEventName("Test2");
        model.get(22).getEvent5().setEventName("Test1");

        tableContent = FXCollections.observableArrayList(
                model
            );
    }

    public void setCalendar(){
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
        Node popupContent = datePickerSkin.getPopupContent();
        VBox leftPane = new VBox();

        leftPane.getChildren().addAll(popupContent,addGroup,showGroups,switchToGroupView);
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setPadding(new Insets(0,8,8,8));

        mainContentBox.getChildren().clear();
        mainContentBox.getChildren().addAll(leftPane,mainTable);
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

        mainTable.getColumns().forEach(item -> {
            if (!item.getText().equals("")) {

            }
        });
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

    private void setCellColor(String name){
        mondayColumn.setCellFactory(new Callback<TableColumn<Event, String>, TableCell<Event, String>>() {
            @Override
            public TableCell<Event, String> call(TableColumn<Event, String> eventStringTableColumn) {
                return new TextFieldTableCell<Event,String>(new EventStringConverter<String>()){
                    @Override
                    public void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (s.equals(name)){
                        }
                    }
                };
            }
        });
    }

    public class EditCell<S, T> extends TableCell<S, T> {

        private final StringConverter<T> converter;

        public EditCell(StringConverter<T> converter) {
            this.converter = converter;

            itemProperty().addListener((obx, oldItem, newItem) -> {
                if (newItem == null) {
                    setText(null);
                } else {
                    setText(converter.toString(newItem));
                }
            });
        }

        @Override
        protected void updateItem(T t, boolean b) {
            super.updateItem(t, b);
            System.out.println("A: " + t);
            if (t.equals("Test2")){
                setId("test");
            }
        }
    }
}
