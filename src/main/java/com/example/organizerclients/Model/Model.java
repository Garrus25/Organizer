package com.example.organizerclients.Model;

import javafx.beans.property.SimpleStringProperty;

public class Model {
    private SimpleStringProperty time;
    private final Event event1 = new Event("","",null);
    private final Event event2 = new Event("","",null);
    private final Event event3 = new Event("","",null);
    private final Event event4 = new Event("","",null);
    private final Event event5 = new Event("","",null);
    private final Event event6 = new Event("","",null);
    private final Event event7 = new Event("","",null);

    public Model(String time){
        this.time = new SimpleStringProperty(time + " : 00" );
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public Event getEvent1() {
        return event1;
    }

    public Event getEvent2() {
        return event2;
    }

    public Event getEvent3() {
        return event3;
    }

    public Event getEvent4() {
        return event4;
    }

    public Event getEvent5() {
        return event5;
    }

    public Event getEvent6() {
        return event6;
    }

    public Event getEvent7() {
        return event7;
    }
}
