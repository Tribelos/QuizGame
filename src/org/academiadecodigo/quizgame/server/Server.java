package org.academiadecodigo.quizgame.server;

import org.academiadecodigo.quizgame.game.WaitingRoom;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {

    private int port;
    private static final int THREADS = 100;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public Server(int port) {
        this.port = port;
        this.threadPool = Executors.newFixedThreadPool(THREADS);
        initServer();
    }

    private void initServer() {

        try {
            System.out.println("Bind to port " + port + "");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server starting " + serverSocket + "");

            while (true) {

                Socket clientSocket = serverSocket.accept();
                WaitingRoom waitingRoom = new WaitingRoom(clientSocket);
                threadPool.submit(waitingRoom);

            }

        } catch (IOException e) {
            System.out.println("Server error!");
        }
    }
}