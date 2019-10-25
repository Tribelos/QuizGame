package org.academiadecodigo.quizgame;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {

    private int port;
    private final List<ClientDispatcher> players = Collections.synchronizedList(new ArrayList<>());

    public Server(int port) {
        this.port = port;
        init();
    }

    private void init() {
        int playerNum = 0;
        ExecutorService fixedPool = Executors.newFixedThreadPool(4);

        try {
            System.out.println("Bind to port " + port + "");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server starting " + serverSocket + "");

            while (true) {

                Socket clientSocket = serverSocket.accept();

                try {

                    playerNum++;
                    String name = "Player" + playerNum;
                    ClientDispatcher clDispatcher = new ClientDispatcher(name, clientSocket);
                    players.add(clDispatcher);
                    fixedPool.submit(clDispatcher);

                } catch (IOException ex) {
                    System.out.println("Client Error" + ex);
                }
            }

        } catch (IOException e) {
            System.out.println("Server error");
        }

    }

    private void broadcast(String originClient, String message) {
        synchronized (players) {
            for (ClientDispatcher player : players) {
                player.send(originClient, message);
            }
        }
    }

    private class ClientDispatcher implements Runnable {

        private final String name;
        private final Socket clientSocket;
        private final BufferedReader in;
        private final BufferedWriter out;

        private ClientDispatcher(String name, Socket clientSocket) throws IOException {
            this.name = name;
            this.clientSocket = clientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {

            try {

                while (!clientSocket.isClosed()) {
                    String line = in.readLine();
                    if (line == null) {
                        System.out.println("Player " + name + " left the room");
                        in.close();
                        clientSocket.close();
                        continue;
                    }

                    broadcast(name, line);
                }

                players.remove(this);

            } catch (IOException e) {
                System.out.println(e);
            }

        }

        public void send(String originClient, String message) {

            try {
                out.write(originClient + ": " + message);
                out.newLine();
                out.flush();

            } catch (IOException e){
                System.out.println(e);
            }

        }

    }


}
