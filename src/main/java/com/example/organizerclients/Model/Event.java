package com.example.organizerclients.Model;

import java.time.LocalDateTime;

public class Event {
    private Integer taskId;
    private String eventName;
    private String group;
    private LocalDateTime date;
    private String description;
    private String personName;
    private String type;

    public Event(String eventName, String group, LocalDateTime date, String description, String type, Integer taskId) {
        this.eventName = eventName;
        this.group = group;
        this.date = date;
        this.description = description;
        this.type = type;
        this.personName = "";
        this.taskId = taskId;
    }

    public Event(String eventName, String group, LocalDateTime date, String description, String type, String personName,  Integer taskId) {
        this.eventName = eventName;
        this.group = group;
        this.date = date;
        this.description = description;
        this.type = type;
        this.personName = personName;
        this.taskId = taskId;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return eventName;
    }

    public void replaceData(Event event) {
        this.eventName = event.eventName;
        this.group = event.group;
        this.date = event.date;
        this.type = event.type;
        this.description = event.description;
        this.personName = event.personName;
        this.taskId = event.taskId;
    }
}
