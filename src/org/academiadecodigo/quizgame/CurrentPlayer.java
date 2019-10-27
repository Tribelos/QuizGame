package org.academiadecodigo.quizgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.*;

public class CurrentPlayer {

    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private String name;
    private int score;

    public CurrentPlayer(String name, Socket clientSocket, BufferedReader in, BufferedWriter out) {
        this.clientSocket = clientSocket;
        this.in = in;
        this.out = out;
        this.name = name;
        this.score = 0;
    }

}
