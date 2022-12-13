package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class TestModel {

    HashMap<LocalDate, HashMap<Integer, Event>> testContent = new HashMap<>();

    HashMap<Integer, Event> test1 = new HashMap<>();
    HashMap<Integer, Event> test2 = new HashMap<>();
    HashMap<Integer, Event> test3 = new HashMap<>();
    HashMap<Integer, Event> test4 = new HashMap<>();

    LocalDate date1 = LocalDate.of(2022,12,11);
    LocalDate date2 = LocalDate.of(2022,12,12);
    LocalDate date3 = LocalDate.of(2022,12,13);
    LocalDate date4 = LocalDate.of(2022,12,14);

    Event event1 = new Event("test1","GRUPA1", LocalDateTime.now());
    Event event2 = new Event("test2","GRUPA1", LocalDateTime.now());
    Event event3 = new Event("test3","GRUPA1", LocalDateTime.now());
    Event event4 = new Event("test4","GRUPA1", LocalDateTime.now());

    public TestModel(){
        test1.put(13,event1);
        test2.put(5,event2);
        test3.put(6,event3);
        test4.put(22,event4);

        testContent.put(date1,test1);
        testContent.put(date2,test2);
        testContent.put(date3,test3);
        testContent.put(date4,test4);
    }
}
