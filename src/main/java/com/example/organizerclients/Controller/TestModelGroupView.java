package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Event;
import com.example.organizerclients.Model.TableColumnKey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.TreeMap;

public class TestModelGroupView {

    public HashMap<TableColumnKey, TreeMap<LocalTime, Event>> testContent = new HashMap<>();

    TreeMap<LocalTime, Event> test1 = new TreeMap<>();
    TreeMap<LocalTime, Event> test2 = new TreeMap<>();
    TreeMap<LocalTime, Event> test3 = new TreeMap<>();
    TreeMap<LocalTime, Event> test4 = new TreeMap<>();

    LocalDate date1 = LocalDate.of(2022,12,11);
    LocalDate date2 = LocalDate.of(2022,12,12);
    LocalDate date3 = LocalDate.of(2022,12,13);
    LocalDate date4 = LocalDate.of(2022,12,14);

    Event event1 = new Event("test1","GRUPA1", LocalDateTime.now(), "", "", null, null, null);
    Event event2 = new Event("test2","GRUPA1", LocalDateTime.now(), "", "", null, null, null);
    Event event3 = new Event("test3","GRUPA1", LocalDateTime.now(), "", "", null, null, null);
    Event event4 = new Event("test4","GRUPA1", LocalDateTime.now(), "", "", null, null, null);

    public TestModelGroupView(){
        test1.put(LocalTime.of(14, 0), event1);
        test2.put(LocalTime.of(14, 0), event2);
        test3.put(LocalTime.of(14, 0), event3);
        test4.put(LocalTime.of(14, 0), event4);

        addData(new TableColumnKey( 1, date1, "Marek") ,test1);
        addData(new TableColumnKey( 2, date4, "Marek1"),test2);
        addData(new TableColumnKey( 3, date4, "Marek2"),test2);
        addData(new TableColumnKey( 4, date1, "Marek3"),test3);
        addData(new TableColumnKey( 5, date2, "Marek4"),test1);
        addData(new TableColumnKey( 6, date4 ,"Marek5"),test2);
        addData(new TableColumnKey( 7, date3, "Marek6"),test3);

    }

    public void addData(TableColumnKey key, TreeMap<LocalTime, Event> eventTreeMap) {
        if (testContent.containsKey(key)) {
            testContent.get(key).put(eventTreeMap.firstKey(), eventTreeMap.get(eventTreeMap.firstKey()));
        } else {
            TreeMap<LocalTime, Event> temp = new TreeMap<>();
            temp.put(eventTreeMap.firstKey(), eventTreeMap.get(eventTreeMap.firstKey()));
            testContent.put(key, temp);
        }
    }
}
