package com.example.organizerclients.Model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Model {
    private SimpleStringProperty timeColumn;
    private final SimpleObjectProperty<Event> mondayColumn = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> tuesdayColumn = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> wednesdayColumn = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> thursdayColumn = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> fridayColumn = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> saturdayColumn = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> sundayColumn = new SimpleObjectProperty<>(new Event("","",null));

    public Model(String time){
        this.timeColumn = new SimpleStringProperty(time + " : 00" );
    }

    public String getTimeColumn() {
        return timeColumn.get();
    }

    public SimpleStringProperty timeColumnProperty() {
        return timeColumn;
    }

    public void setTimeColumn(String timeColumn) {
        this.timeColumn.set(timeColumn);
    }

    public Event getMondayColumn() {
        return mondayColumn.get();
    }

    public SimpleObjectProperty<Event> mondayColumnProperty() {
        return mondayColumn;
    }

    public void setMondayColumn(Event mondayColumn) {
        this.mondayColumn.set(mondayColumn);
    }

    public Event getTuesdayColumn() {
        return tuesdayColumn.get();
    }

    public SimpleObjectProperty<Event> tuesdayColumnProperty() {
        return tuesdayColumn;
    }

    public void setTuesdayColumn(Event tuesdayColumn) {
        this.tuesdayColumn.set(tuesdayColumn);
    }

    public Event getWednesdayColumn() {
        return wednesdayColumn.get();
    }

    public SimpleObjectProperty<Event> wednesdayColumnProperty() {
        return wednesdayColumn;
    }

    public void setWednesdayColumn(Event wednesdayColumn) {
        this.wednesdayColumn.set(wednesdayColumn);
    }

    public Event getThursdayColumn() {
        return thursdayColumn.get();
    }

    public SimpleObjectProperty<Event> thursdayColumnProperty() {
        return thursdayColumn;
    }

    public void setThursdayColumn(Event thursdayColumn) {
        this.thursdayColumn.set(thursdayColumn);
    }

    public Event getFridayColumn() {
        return fridayColumn.get();
    }

    public SimpleObjectProperty<Event> fridayColumnProperty() {
        return fridayColumn;
    }

    public void setFridayColumn(Event fridayColumn) {
        this.fridayColumn.set(fridayColumn);
    }

    public Event getSaturdayColumn() {
        return saturdayColumn.get();
    }

    public SimpleObjectProperty<Event> saturdayColumnProperty() {
        return saturdayColumn;
    }

    public void setSaturdayColumn(Event saturdayColumn) {
        this.saturdayColumn.set(saturdayColumn);
    }

    public Event getSundayColumn() {
        return sundayColumn.get();
    }

    public SimpleObjectProperty<Event> sundayColumnProperty() {
        return sundayColumn;
    }

    public void setSundayColumn(Event sundayColumn) {
        this.sundayColumn.set(sundayColumn);
    }
}
