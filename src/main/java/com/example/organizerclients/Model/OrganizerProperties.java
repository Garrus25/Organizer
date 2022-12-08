package com.example.organizerclients.Model;

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

    public static final String CODE_CONFIRMATION_TEXT = properties.getProperty("codeConfirmation.confirm.text");
    public static final String CODE_CONFIRMATION_PROMPT_TEXT = properties.getProperty("codeConfirmation.prompt.text");
    public static final String CODE_CONFIRMATION_CONFIRM_BUTTON_TEXT = properties.getProperty("codeConfirmation.button.confirm.text");
    public static final String CODE_CONFIRMATION_RETURN_BUTTON_TEXT = properties.getProperty("codeConfirmation.return.button.text");
    public static final String CODE_CONFIRMATION_EMAIL_INFO_TEXT = properties.getProperty("codeConfirmation.email.info.text");
    public static final String CODE_CONFIRMATION_RESEND_TEXT = properties.getProperty("codeConfirmation.resend.text");
    public static final String CODE_CONFIRMATION_MESSAGE_RESENT_TEXT = properties.getProperty("codeConfirmation.messageResent.text");

    public static final String MAINVIEW_ADDGROUP_TEXT = properties.getProperty("mainview.addgroup.text");
    public static final String MAINVIEW_SHOWGROUP_TEXT = properties.getProperty("mainview.showgroups.text");
    public static final String MAINVIEW_SWITCHGROUP_TEXT = properties.getProperty("mainview.switchtogroup.text");
    public static final String MAINVIEW_SWITCHTOSINGLE_TEXT = properties.getProperty("mainview.switchtosingle.text");


    public static final String ADD_GROUP_VIEW_GROUP_CREATE_BUTTON_TEXT = properties.getProperty("addGroupView.groupCreateButton.text");
    public static final String ADD_GROUP_VIEW_VIEW_TITLE_TEXT = properties.getProperty("addGroupView.viewTitle.text");
    public static final String ADD_GROUP_VIEW_GROUP_NAME_TEXT_FIELD_PROMPT_TEXT = properties.getProperty("addGroupView.groupNameTextField.prompt.text");

    public static final String GROUP_INFORMATION_VIEW_GROUP_NAME_TEXT = properties.getProperty("groupInformationView.groupName.text");
    public static final String GROUP_INFORMATION_VIEW_GROUP_CODE_TEXT = properties.getProperty("groupInformationView.groupCode.text");
    public static final String GROUP_INFORMATION_VIEW_BUTTON_ADD_TO_GROUP_TEXT = properties.getProperty("groupInformationView.buttonAddToGroup.text");

    public static final String SHOW_GROUP_LIST_VIEW_TITLE_TEXT = properties.getProperty("showGroupListView.title.text");

    public static final String USER_PANEL_VIEW_NAME_TEXT = properties.getProperty("userPanelView.name.text");
    public static final String USER_PANEL_VIEW_SURNAME_TEXT = properties.getProperty("userPanelView.surname.text");
    public static final String USER_PANEL_VIEW_EMAIL_TEXT = properties.getProperty("userPanelView.email.text");
    public static final String USER_PANEL_VIEW_LOGIN_TEXT = properties.getProperty("userPanelView.login.text");


}