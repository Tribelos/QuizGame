package org.academiadecodigo.quizgame;

import org.academiadecodigo.quizgame.players.PlayerConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int port;
    private static final int MAX_NUM_PLAYERS = 20;
    private final List<PlayerConnection> players = Collections.synchronizedList(new ArrayList<>());

    public Server(int port) throws IOException {

        this.port = port;

    }

    public void init() throws IOException {

        ExecutorService fixedPool = Executors.newFixedThreadPool(MAX_NUM_PLAYERS);

        System.out.println("Bind to port " + port + "");

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server starting " + serverSocket + "");

        GameLogic gameLogic = new GameLogic(this );

        while(true){

            Socket clientSocket = serverSocket.accept();

            try {

                PlayerConnection playerConnection = new PlayerConnection(clientSocket);
                //playerConnection.getInput().
                players.add(playerConnection);
                fixedPool.submit(playerConnection);

            } catch (IOException ex) {
                System.out.println("Client Error" + ex);
            }
        }
    }

    private void broadcast(String message) {
        synchronized (players) {
            for (PlayerConnection player : players) {
                player.send(message);
            }
        }
    }

}
