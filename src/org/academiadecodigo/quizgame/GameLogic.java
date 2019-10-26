package org.academiadecodigo.quizgame;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class GameLogic {

    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private Prompt prompt;
    //private List<String> currentPlayers = Collections.synchronizedList(new ArrayList<>());
    //private ConcurrentHashMap<String, Integer> gamePlayers;

    public GameLogic(Socket clientSocket, BufferedReader in, BufferedWriter out) throws IOException {
        this.clientSocket = clientSocket;
        this.in = in;
        this.out = out;
        init();
    }

    private void init() throws IOException {
        InputStream input = clientSocket.getInputStream();
        PrintStream output = new PrintStream(clientSocket.getOutputStream());
        this.prompt = new Prompt(input, output);
    }

    public void gameStart() throws IOException {
        startMenu();
    }
    
    private void startMenu() throws IOException {

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

        int answerIndex = prompt.getUserInput(mainMenu);
        
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
            chooseName();
        }

    }

    private void chooseName() throws IOException {

        StringInputScanner askName = new StringInputScanner();
        askName.setMessage("What's your name?  ");
        out.newLine();

        String playerName = prompt.getUserInput(askName);

        out.write("Welcome to the QUIZIZINHO, " + playerName + "! Good luck!");
        out.newLine();
        out.flush();

        question();
    }


    private void question() throws IOException {

        String[] firstQuestionOptions = {"basilio", "trigo", "lando", "bessa"};

        MenuInputScanner mainMenu = new MenuInputScanner(firstQuestionOptions);

        mainMenu.setMessage("...?");

        int answerIndex = prompt.getUserInput(mainMenu);

        if(answerIndex != 4){
            out.write((WrongAnswer.values()[(int) (Math.random() * WrongAnswer.values().length)].getText()));
            newLineAndFlush();
            return;
        }

        out.write("Correct! Great Success");
        newLineAndFlush();
    }


    private void newLineAndFlush() throws IOException {
        out.newLine();
        out.flush();
    }

 private class CurrentPlayers {
        private String name;

        public CurrentPlayers(String name) {
            this.name = name;
        }

    }

}
