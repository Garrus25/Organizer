package com.example.organizerclients.Requests;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Request {

    private String header;
    private String data;

    public Request(String header, String data) {
        this.header = header;

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("xD");
        String encrypted = encryptor.encrypt(data);
        this.data = encrypted;
    }

    public Request() {}

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getData() {

        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("xD");

        return decryptor.decrypt(data);
    }

    public void setData(String data) {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("xD");
        String encrypted = encryptor.encrypt(data);
        this.data = encrypted;
    }

    @Override
    public String toString() {
        return "Request{" +
                "header='" + header + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
