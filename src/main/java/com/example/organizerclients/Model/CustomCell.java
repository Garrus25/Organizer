package com.example.organizerclients.Model;

import javafx.scene.control.TableCell;

public class CustomCell<String, Event> extends TableCell<String, Event>{
    @Override
    protected void updateItem(Event event, boolean b) {
        super.updateItem(event, b);
        if(null!=event){
            setText(event.toString());
            if (!event.toString().equals("")) {
                setStyle("-fx-background-color: red");
            }else {
                setStyle("");
            }
        }
    }


}
