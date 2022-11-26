package com.example.organizerclients.Model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Model {
    private SimpleStringProperty time;
    private final SimpleObjectProperty<Event> event1 = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> event2 = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> event3 = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> event4 = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> event5 = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> event6 = new SimpleObjectProperty<>(new Event("","",null));
    private final SimpleObjectProperty<Event> event7 = new SimpleObjectProperty<>(new Event("","",null));

    public Model(String time){
        this.time = new SimpleStringProperty(time + " : 00" );
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public Event getEvent1() {
        return event1.get();
    }

    public SimpleObjectProperty<Event> event1Property() {
        return event1;
    }

    public void setEvent1(Event event1) {
        this.event1.set(event1);
    }

    public Event getEvent2() {
        return event2.get();
    }

    public SimpleObjectProperty<Event> event2Property() {
        return event2;
    }

    public void setEvent2(Event event2) {
        this.event2.set(event2);
    }

    public Event getEvent3() {
        return event3.get();
    }

    public SimpleObjectProperty<Event> event3Property() {
        return event3;
    }

    public void setEvent3(Event event3) {
        this.event3.set(event3);
    }

    public Event getEvent4() {
        return event4.get();
    }

    public SimpleObjectProperty<Event> event4Property() {
        return event4;
    }

    public void setEvent4(Event event4) {
        this.event4.set(event4);
    }

    public Event getEvent5() {
        return event5.get();
    }

    public SimpleObjectProperty<Event> event5Property() {
        return event5;
    }

    public void setEvent5(Event event5) {
        this.event5.set(event5);
    }

    public Event getEvent6() {
        return event6.get();
    }

    public SimpleObjectProperty<Event> event6Property() {
        return event6;
    }

    public void setEvent6(Event event6) {
        this.event6.set(event6);
    }

    public Event getEvent7() {
        return event7.get();
    }

    public SimpleObjectProperty<Event> event7Property() {
        return event7;
    }

    public void setEvent7(Event event7) {
        this.event7.set(event7);
    }

    @Override
    public String toString() {
        return "Model{" +
                "time=" + time +
                ", event1=" + event1 +
                ", event2=" + event2 +
                ", event3=" + event3 +
                ", event4=" + event4 +
                ", event5=" + event5 +
                ", event6=" + event6 +
                ", event7=" + event7 +
                '}';
    }
}
