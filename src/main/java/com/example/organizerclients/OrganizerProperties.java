package com.example.organizerclients;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OrganizerProperties {
    private static Properties properties;

    static {
        load();
    }

    public static void load(){
        InputStream is = null;
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/organizer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String MAIN_VIEW_TITLE = properties.getProperty("main.view.title");
    public static final String LOGIN_BUTTON_TEXT = properties.getProperty("login.button.text");
    public static final String REGISTER_BUTTON_TEXT = properties.getProperty("register.button.text");
}

