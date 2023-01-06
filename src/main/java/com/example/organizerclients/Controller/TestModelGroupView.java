package com.example.organizerclients.Controller;

import com.example.organizerclients.Model.Event;
import com.example.organizerclients.Model.TableColumnKey;

import java.sql.Timestamp;
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

    Event event1 = new Event("test1","GRUPA1", LocalDateTime.now(), "", "", null);
    Event event2 = new Event("test2","GRUPA1", LocalDateTime.now(), "", "", null);
    Event event3 = new Event("test3","GRUPA1", LocalDateTime.now(), "", "", null);
    Event event4 = new Event("test4","GRUPA1", LocalDateTime.now(), "", "", null);

    public TestModelGroupView(){
        test1.put(LocalTime.of(14, 0), event1);
        test2.put(LocalTime.of(14, 0), event2);
        test3.put(LocalTime.of(14, 0), event3);
        test4.put(LocalTime.of(14, 0), event4);

        addData(new TableColumnKey( "Marek", date1) ,test1);
        addData(new TableColumnKey( "Marek", date4),test2);
        addData(new TableColumnKey( "Iwona", date4),test2);
        addData(new TableColumnKey( "Iwona", date1),test3);
        addData(new TableColumnKey( "Iwona", date2),test1);
        addData(new TableColumnKey( "Iwona", date4),test2);
        addData(new TableColumnKey( "Iwona", date3),test3);

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
