package org.academiadecodigo.quizgame;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Server {

    private int port;
    private static final int MAX_NUM_PLAYERS = 20;
    private final List<ClientDispatcher> players = Collections.synchronizedList(new ArrayList<>());
    private ArrayList<CurrentPlayer> currentPlayers = new ArrayList<CurrentPlayer>();
    private Prompt serverPrompt;

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


    private void serverMenu(BufferedReader in, BufferedWriter out, Socket clientSocket) throws IOException {
        out.write("\n" +
                "     ██████╗ ██╗   ██╗██╗███████╗██╗███████╗██╗███╗   ██╗██╗  ██╗ ██████╗ \n" +
                "    ██╔═══██╗██║   ██║██║╚══███╔╝██║╚══███╔╝██║████╗  ██║██║  ██║██╔═══██╗\n" +
                "    ██║   ██║██║   ██║██║  ███╔╝ ██║  ███╔╝ ██║██╔██╗ ██║███████║██║   ██║\n" +
                "    ██║▄▄ ██║██║   ██║██║ ███╔╝  ██║ ███╔╝  ██║██║╚██╗██║██╔══██║██║   ██║\n" +
                "    ╚██████╔╝╚██████╔╝██║███████╗██║███████╗██║██║ ╚████║██║  ██║╚██████╔╝\n" +
                "     ╚══▀▀═╝  ╚═════╝ ╚═╝╚══════╝╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ " +
                "\n");
        out.newLine();
        out.flush();

        String[] options = {"Join the Quizizinho", "Leave"};
        MenuInputScanner mainMenu = new MenuInputScanner(options);
        mainMenu.setMessage("Do you want to play?");

        int answerIndex = serverPrompt.getUserInput(mainMenu);

        if (answerIndex == 2){
            out.write("K then bye");
            out.newLine();
            out.flush();
            out.close();
            in.close();
            clientSocket.close();
        }

        if(answerIndex == 1){
            out.write("Let's play !! Good luck !!");
            out.newLine();
            out.flush();
            StringInputScanner askName = new StringInputScanner();
            askName.setMessage("What's your name?  ");
            out.newLine();
            String playerName = serverPrompt.getUserInput(askName);
            CurrentPlayer player = new CurrentPlayer(playerName, clientSocket, in, out);
            currentPlayers.add(player);

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

                serverMenu(in,out,clientSocket);

                if(currentPlayers.size() == 2) {
                    GameLogic gameLogic = new GameLogic(currentPlayers);
                    gameLogic.menuInit();
                }


                while (!clientSocket.isClosed()) {

                    String line = in.readLine();

                    if (line == null) {
                        in.close();
                        clientSocket.close();
                        continue;
                    }

                }

                players.remove(this);

            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
    
}
