package com.example.organizerclients.Model;

import javafx.scene.control.TableCell;

public class CustomCell<String, Event> extends TableCell<String, Event>{
    private int x;
    private int y;

    private static int xIterator = 0;
    private static int yIterator = 0;

    public CustomCell() {
        setValues();
    }

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void setValues(){
        checkIfIndexesAreInRange();
        this.x = xIterator;
        this.y = yIterator;

        xIterator++;

        if (xIterator >=7){
            yIterator++;
            xIterator = 0;
        }
    }

    private void checkIfIndexesAreInRange(){
        if (yIterator >= 23 && xIterator >= 7){
            yIterator = 0;
            xIterator = 0;
        }
    }

}
