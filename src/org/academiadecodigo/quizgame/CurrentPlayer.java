package org.academiadecodigo.quizgame;

import java.io.*;
import java.net.*;

public class CurrentPlayer {

    private GameLogic playerGameLogic;

    public CurrentPlayer(String name, Socket clientSocket, BufferedReader in, BufferedWriter out) throws IOException {
        this.playerGameLogic = new GameLogic(clientSocket, in, out, name, 0);
    }

    public GameLogic getPlayerGameLogic() {
        return playerGameLogic;
    }
}
