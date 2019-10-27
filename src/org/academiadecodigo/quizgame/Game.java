package org.academiadecodigo.quizgame;

import java.io.*;
import java.net.*;
import java.util.*;

public class Game {

    private ArrayList<CurrentPlayer> currentPlayers;

    public Game (ArrayList<CurrentPlayer> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public void start() {

        for (CurrentPlayer currentPlayer : currentPlayers) {
            GameLogic gameLogic = new GameLogic(currentPlayer.getClientSocket(), currentPlayer.getIn(), currentPlayer.getOut(), currentPlayer.getName(), currentPlayer.getScore());
        }

    }

    private class GameLogic {

        private Socket clientSocket;
        private BufferedReader in;
        private BufferedWriter out;
        private String name;
        private int score;

        public GameLogic(Socket clientSocket, BufferedReader in, BufferedWriter out, String name, int score){
            this.clientSocket = clientSocket;
            this.in = in;
            this.out = out;
            this.name = name;
            this.score = score;
        }


    }
}
