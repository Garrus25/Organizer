package com.example.organizerclients.Requests.RequestObjects;

public class LoginData {
    private String login;

    public LoginData() {}

    public LoginData(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
