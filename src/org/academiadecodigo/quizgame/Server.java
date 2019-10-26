package org.academiadecodigo.quizgame;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {

    private int port;
    private static final int MAX_NUM_PLAYERS = 20;
    private final List<ClientDispatcher> players = Collections.synchronizedList(new ArrayList<>());
    private CustomMessages msg;
    private int playerNum;

    public Server(int port) {
        this.port = port;
        init();
    }

    private void init() {

        playerNum = 0;
        ExecutorService fixedPool = Executors.newFixedThreadPool(MAX_NUM_PLAYERS);

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


    public int getPlayerNum() {
        return playerNum;
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

        /*

        public void startMenu() throws IOException {


            InputStream input = clientSocket.getInputStream();
            PrintStream output = new PrintStream(clientSocket.getOutputStream());
            String[] options = {"Leave", "Join the quizizinho"};
            MenuInputScanner scanner = new MenuInputScanner(options);

            scanner.setMessage("Do you want to play?");
            Prompt prompt = new Prompt(input, output);

            int answerIndex = prompt.getUserInput(scanner);
            System.out.println(answerIndex);

            if (answerIndex == 1){
                out.write("K then bye");
                out.newLine();
                out.flush();
                clientSocket.close();
                in.close();
            }

            if(answerIndex == 2){
                out.write("Let's play !! Good luck !!");
                out.newLine();
                out.flush();
            }




        }

         */

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            try {

                GameLogic gameLogic = new GameLogic(clientSocket);
                gameLogic.startMenu(in, out);

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