package org.academiadecodigo.quizgame;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {

    private int port;
    private static final int MAX_NUM_PLAYERS = 20;
    private final List<ClientDispatcher> players = Collections.synchronizedList(new ArrayList<>());

    public Server(int port) {
        this.port = port;
        init();
    }

    private void init() {

        ExecutorService fixedPool = Executors.newFixedThreadPool(MAX_NUM_PLAYERS);

        try {
            System.out.println("Bind to port " + port + "");
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server starting " + serverSocket + "");

            while (true) {

                Socket clientSocket = serverSocket.accept();

                try {

                    ClientDispatcher clDispatcher = new ClientDispatcher(clientSocket);
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

    private void broadcast(String message) {
        synchronized (players) {
            for (ClientDispatcher player : players) {
                player.send(message);
            }
        }
    }

    private class ClientDispatcher implements Runnable {

        private final Socket clientSocket;
        private final BufferedReader in;
        private final BufferedWriter out;

        private ClientDispatcher(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        }

        @Override
        public void run() {
            try {

                GameLogic gameLogic = new GameLogic(clientSocket, in, out);
                gameLogic.gameStart();
                while (!clientSocket.isClosed()) {

                    String line = in.readLine();

                    if (line == null) {
                        in.close();
                        clientSocket.close();
                        continue;
                    }


                    broadcast(line);
                }

                players.remove(this);

            } catch (IOException e) {
                System.out.println(e);
            }

        }

        public void send(String message) {

            try {

                out.write(message);
                out.newLine();
                out.flush();


            } catch (IOException e){
                System.out.println(e);
            }

        }

    }


}
