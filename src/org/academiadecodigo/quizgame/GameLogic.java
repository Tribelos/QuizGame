package org.academiadecodigo.quizgame;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.*;
import java.sql.SQLOutput;


public class GameLogic {

    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private Prompt prompt;
    private Questions questions;
    private String questionText;
    private String[] questionAnswers;
    private String correctAnswer;


    public GameLogic(Socket clientSocket, BufferedReader in, BufferedWriter out) throws IOException {
        this.clientSocket = clientSocket;
        this.in = in;
        this.out = out;
        this.questions = new Questions();
        //this.questionAnswers = new String[4];
        init();
    }

    private void init() throws IOException {
        InputStream input = clientSocket.getInputStream();
        PrintStream output = new PrintStream(clientSocket.getOutputStream());
        this.prompt = new Prompt(input, output);
    }

    public void menuInit() throws IOException {
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

        gameStart();
    }


    private void question(){


        System.out.println("aefea");
        String[] question = questions.getRandom();

        System.out.println("aaefeaf");

        questionText = question[0];
        questionAnswers = new String[]{question[1], question[2], question[3], question[4]};
        correctAnswer = question[5];
        System.out.println(questionText);
        System.out.println(questionAnswers[0]);
        System.out.println(correctAnswer);
    }



    private void gameStart() throws IOException {

        question();

        MenuInputScanner mainMenu = new MenuInputScanner(questionAnswers);

        mainMenu.setMessage(questionText);

        int answerIndex = prompt.getUserInput(mainMenu);

        String answer = questionAnswers[answerIndex - 1];

        if(answer != correctAnswer){
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

 /*private class CurrentPlayers {
        private String name;

        public CurrentPlayers(String name) {
            this.name = name;
        }

    }*/

}
