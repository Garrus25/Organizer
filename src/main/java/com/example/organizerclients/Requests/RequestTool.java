package com.example.organizerclients.Requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

public class RequestTool {
    private static final String IP_ADDRESS_SERVER = "127.0.0.1";
    private static final Integer PORT_NUMBER = 2137;

    public static Optional<Response> sendRequest(Request request) {
        try (Socket clientSocket = new Socket(IP_ADDRESS_SERVER, PORT_NUMBER);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {

            out.println(SaveDataAsJson.saveDataAsJson(request));
            String responseRawText = in.readLine();

            Response response = ReadObjectFromJson.read(responseRawText, Response.class);
            return Optional.of(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}