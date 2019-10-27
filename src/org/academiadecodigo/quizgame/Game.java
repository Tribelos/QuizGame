package org.academiadecodigo.quizgame;

import java.io.*;
import java.util.*;

public class Game {

    private ArrayList<CurrentPlayer> currentPlayers;

    public Game (ArrayList<CurrentPlayer> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public void start() throws IOException {

        for (CurrentPlayer currentPlayer : currentPlayers) {
            currentPlayer.getPlayerGameLogic().gameStart();
        }

    }


}
