package com.example.organizerclients.Model;

import javafx.beans.property.SimpleStringProperty;

public class Model {
    private SimpleStringProperty time;
    private Event event = new Event("XD","XD",null);

    public Model(String time){
        this.time = new SimpleStringProperty(time + " : 00" );
    }

    public String getTime() {
        return time.get();
    }

    public Event getEvent() {
        return event;
    }
}
