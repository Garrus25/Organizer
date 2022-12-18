package com.example.organizerclients.Model;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.Map;

public class TimeCellFactory implements Callback<TableColumn<Map<String, Event>, String>, TableCell<Map<String, Event>, String>> {

    @Override
    public TableCell<Map<String, Event>, String> call(TableColumn<Map<String, Event>, String> mapStringTableColumn) {
        return new TimeCell<>();
    }
}
