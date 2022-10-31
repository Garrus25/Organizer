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

    public static final String PASSWORD_TEXTFIELD_PROMPT_TEXT = properties.getProperty("password.textField.prompt.text");
    public static final String EMAIL_TEXTFIELD_PROMPT_TEXT = properties.getProperty("email.textField.prompt.text");

    public static final String REGISTER_WRONG_CODE_TEXT = properties.getProperty("register.wrongCode.text");
    public static final String REGISTER_CORRECT_CODE_TEXT = properties.getProperty("register.correctCode.text");
    public static final String REGISTER_WRONG_EMAIL_FORMAT_TEXT = properties.getProperty("register.wrongEmailFormat.text");
    public static final String REGISTER_RETURN_BUTTON_TEXT = properties.getProperty("register.returnButton.text");
    public static final String LOGIN_WRONG_CREDENTIALS_TEXT = properties.getProperty("login.wrongCredentials.text");
    public static final String LOGIN_CORRECT_CREDENTIALS_TEXT = properties.getProperty("login.correctCredentials.text");
    public static final String LOGIN_EMPTY_FIELD_TEXT = properties.getProperty("login.emptyField.text");
}