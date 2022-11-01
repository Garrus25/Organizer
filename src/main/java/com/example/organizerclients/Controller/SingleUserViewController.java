package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Event;
import com.example.organizerclients.Model.EventStringConverter;
import com.example.organizerclients.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.w3c.dom.events.MouseEvent;

import java.time.LocalDate;
import java.util.ArrayList;

@SuppressWarnings({"unchecked", "rawtypes"})
public class SingleUserViewController{

    private final SceneController sceneController = SceneController.getInstance();

    @FXML
    public HBox mainContentBox;

    private ObservableList<Model> tableContent;

    @FXML
    TableView<Model> mainTable;

    @FXML
    TableColumn<String,String> time;

    @FXML
    TableColumn<Event,String> first;

    @FXML
    public void clickItem(MouseEvent event){
        System.out.println("XD");
    }
    @FXML
    public void initialize(){
        setObservableList();
        setTableView();
        setColumns(time,"time","Time");
        setColumns(first,"event","Time");

        time.setCellFactory(TextFieldTableCell.forTableColumn());
        first.setCellFactory(TextFieldTableCell.forTableColumn(new EventStringConverter<>()));

        setColumnListeners();
        setCalendar();
    }

    public void setColumns(TableColumn tableColumn, String propertyValue, String tableName){
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyValue));
        tableColumn.setText(tableName);
        tableColumn.setResizable(false);
        tableColumn.setReorderable(false);
        tableColumn.setSortable(false);
        tableColumn.setMinWidth(150.0);
    }

    public void setTableView(){
        mainTable.setItems(tableContent);
        mainTable.setFixedCellSize(40.0);
        mainTable.getSelectionModel().setCellSelectionEnabled(true);
    }

    public void setCellAfterCommit(Integer col, Integer row){

    }

    public void setColumnListeners(){
        for (int i = 1; i <= 1; i ++){
            mainTable.getColumns().get(i).setOnEditStart(event -> {
                System.out.println("XD");
            });

        }
    }

    private void setObservableList(){
        ArrayList<Model> model = new ArrayList<>();
        for (int i = 0; i < 24; i++){
            model.add(new Model(Integer.toString(i)));
        }
        tableContent = FXCollections.observableArrayList(
                model
            );
    }

    public void setCalendar(){
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
        Node popupContent = datePickerSkin.getPopupContent();
        VBox leftPane = new VBox();

        leftPane.getChildren().add(popupContent);
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setPadding(new Insets(0,8,8,8));

        mainContentBox.getChildren().clear();
        mainContentBox.getChildren().addAll(leftPane,mainTable);
    }
}
