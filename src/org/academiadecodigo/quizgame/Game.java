package org.academiadecodigo.quizgame;

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


}
