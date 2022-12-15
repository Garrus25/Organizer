package com.example.organizerclients.Model;

import javafx.scene.control.TableCell;

public class TimeCell<String, Event> extends TableCell<String, Event> {
    @Override
    protected void updateItem(Event event, boolean b) {
        super.updateItem(event, b);
        if (!(event == null)) {
            setText(event.toString());
        }
        setStyle("-fx-alignment: center");
        setDisable(true);
        setEditable(false);
    }
}
