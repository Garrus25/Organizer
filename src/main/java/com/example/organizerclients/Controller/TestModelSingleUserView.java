package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Event;
import com.example.organizerclients.Model.TableColumnKey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.TreeMap;

public class TestModelSingleUserView {

    public HashMap<TableColumnKey, TreeMap<LocalTime, Event>> testContent = new HashMap<>();

    TreeMap<LocalTime, Event> test1 = new TreeMap<>();
    TreeMap<LocalTime, Event> test2 = new TreeMap<>();
    TreeMap<LocalTime, Event> test3 = new TreeMap<>();
    TreeMap<LocalTime, Event> test4 = new TreeMap<>();

    LocalDate date1 = LocalDate.of(2022,12,11);
    LocalDate date2 = LocalDate.of(2022,12,12);
    LocalDate date3 = LocalDate.of(2022,12,13);
    LocalDate date4 = LocalDate.of(2022,12,14);

    Event event1 = new Event("test1","GRUPA1", LocalDateTime.now(), "", "", "", null, null, null);
    Event event2 = new Event("test2","GRUPA1", LocalDateTime.now(), "", "", "", null, null, null);
    Event event3 = new Event("test3","GRUPA1", LocalDateTime.now(), "", "", "", null, null, null);
    Event event4 = new Event("test4","GRUPA1", LocalDateTime.now(), "", "", "", null, null, null);

    public TestModelSingleUserView(){
        test1.put(LocalTime.of(13,0), event1);
        test2.put(LocalTime.of(5,0), event2);
        test3.put(LocalTime.of(6,0), event3);
        test4.put(LocalTime.of(22,0), event4);

        addData(new TableColumnKey(null, date1, "") ,test1);
        addData(new TableColumnKey(null, date1, "") ,test2);
        addData(new TableColumnKey(null, date2, "") ,test2);
        addData(new TableColumnKey(null, date3, "") ,test3);
        addData(new TableColumnKey(null, date4, "") ,test1);
        addData(new TableColumnKey(null, date4, "") ,test2);
        addData(new TableColumnKey(null, date4, "") ,test3);

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
