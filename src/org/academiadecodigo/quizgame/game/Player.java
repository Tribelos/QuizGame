package org.academiadecodigo.quizgame.game;

import org.academiadecodigo.bootcamp.Prompt;

import java.io.*;
import java.net.*;

public class Player {
    private Socket clientSocket;
    private String name;
    private BufferedReader in;
    private Prompt prompt;

    public Player(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.name = "Player";
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.prompt = new Prompt(clientSocket.getInputStream(), new PrintStream(clientSocket.getOutputStream()));
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Prompt getPrompt() {
        return prompt;
    }

}
