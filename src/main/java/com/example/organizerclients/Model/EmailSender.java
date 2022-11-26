package com.example.organizerclients.Model;

import java.util.regex.Pattern;

public class EmailSender {
    public void sendMessage(){

    }

    public void checkMessage(){

    }

    public Boolean checkEmailAddress(String address){
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        return Pattern.compile(regexPattern)
                .matcher(address)
                .matches();
    }
}
