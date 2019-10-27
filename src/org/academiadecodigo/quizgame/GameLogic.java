package org.academiadecodigo.quizgame;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.*;

public class GameLogic {

    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private Prompt prompt;
    private Questions questions;
    private String playerName;
    private int playerScore;
    private String questionText;
    private String[] questionAnswers;
    private String correctAnswer;
    public static final int NUMBER_OF_ROUNDS = 15;


    public GameLogic(Socket clientSocket, BufferedReader in, BufferedWriter out) throws IOException {
        this.clientSocket = clientSocket;
        this.in = in;
        this.out = out;
        this.questions = new Questions();
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

        playerName = prompt.getUserInput(askName);

        out.write("Welcome to the QUIZIZINHO, " + playerName + "! Good luck!");
        out.newLine();
        out.flush();

        gameStart();
    }


    private void question(){

        String[] question = questions.getRandom();
        questions.getArrayList().remove(question);

        questionText = question[0];
        questionAnswers = new String[]{question[1], question[2], question[3], question[4]};
        correctAnswer = question[5];
    }



    private void gameStart() throws IOException {

        playerScore = 0;
        int questionCounter = 0;

        while (questionCounter < NUMBER_OF_ROUNDS){

            question();
            questionCounter++;

            MenuInputScanner mainMenu = new MenuInputScanner(questionAnswers);

            mainMenu.setMessage(questionText);

            int answerIndex = prompt.getUserInput(mainMenu);

            String answer = questionAnswers[answerIndex - 1];

            if(!answer.equals(correctAnswer)){
                clear();
                out.write((WrongAnswer.values()[(int) (Math.random() * WrongAnswer.values().length)].getText()));
                newLineAndFlush();

                continue;
            }
            clear();
            out.write("*** CORRECT! Great Success ***");
            newLineAndFlush();

            playerScore+= 10;

        }

        gameOver();
    }


    public void gameOver() throws IOException {

        out.write(" === GAME OVER! ===\nCongratulations " + playerName + "!\nYour score is " + playerScore +"." );
        newLineAndFlush();
    }

    private void newLineAndFlush() throws IOException {
        out.newLine();
        out.flush();
    }

    private void clear() throws IOException {
        out.write("\033[2J");
        out.flush();
    }
}
