package com.example.organizerclients.Model;

import java.time.LocalDateTime;

public class Event {
    private String eventName;
    private String group;
    private LocalDateTime date;

    public Event(String eventName, String group, LocalDateTime date) {
        this.eventName = eventName;
        this.group = group;
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return eventName;
    }
}
