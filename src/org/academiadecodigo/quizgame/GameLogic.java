package org.academiadecodigo.quizgame;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.*;
import java.net.*;


public class GameLogic {

    private Socket clientSocket;
    private int playerCount;

    public GameLogic(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.playerCount = 0;

    }



    public void startMenu(BufferedReader in, BufferedWriter out) throws IOException {


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
            playerCount++;
            System.out.println("-----" +playerCount+ "-----");

            if(playerCount == 4){
                System.out.println("Starting game");
            }

        }

    }

}
