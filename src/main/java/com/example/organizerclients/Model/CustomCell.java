package com.example.organizerclients.Model;

import javafx.scene.control.TableCell;

public class CustomCell<String, Event> extends TableCell<String, Event>{
    private int x;
    private int y;

    private static int xIterator = 0;
    private static int yIterator = 0;

    public static int numberOfColumns = 7;

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
        if (yIterator>23){
            int a = yIterator/24;
            this.y = yIterator-(a*24);
        }else {
            this.y = yIterator;
        }

        this.x = xIterator;

        xIterator++;

        if (xIterator >= numberOfColumns){
            yIterator++;
            xIterator = 0;
        }
    }

    private void checkIfIndexesAreInRange(){
        if (yIterator >= 23 && xIterator >= numberOfColumns){
            yIterator = 0;
            xIterator = 0;
        }
    }
}
