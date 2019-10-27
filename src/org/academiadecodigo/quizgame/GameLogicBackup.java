package org.academiadecodigo.quizgame;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.quizgame.timer.CountDown;

import java.io.*;
import java.net.*;
import java.util.*;

public class GameLogicBackup {


    private Prompt prompt;

    private String questionText;
    private String[] questionAnswers;
    private String correctAnswer;
    public static final int NUMBER_OF_ROUNDS = 15;
    private ArrayList<CurrentPlayer> currentPlayers;



    private void init() throws IOException {

        InputStream input = clientSocket.getInputStream();
        PrintStream output = new PrintStream(clientSocket.getOutputStream());
        this.prompt = new Prompt(input, output);
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
        int printQuestion = questionCounter + 1;

        while (questionCounter < NUMBER_OF_ROUNDS){
            question();
            questionCounter++;

            MenuInputScanner mainMenu = new MenuInputScanner(questionAnswers);
            mainMenu.setMessage(questionText);

            int answerIndex = prompt.getUserInput(mainMenu);

            String answer = questionAnswers[answerIndex - 1];

            if(!answer.equals(correctAnswer)){
                clear();

                printQuestion++;
                String wrongMsg = WrongAnswer.values()[(int) (Math.random() * WrongAnswer.values().length)].getText();

                nextScreen(Logos.WRONG.getText() + "\n" + wrongMsg + "\nQuestion #" + printQuestion + "\n" + Logos.NEW_ROUND.getText());
                newLineAndFlush();

                continue;
            }

            printQuestion++;
            nextScreen(Logos.CORRECT.getText() + "\n*** CORRECT! Great Success ***\nQuestion #" + printQuestion + "\n" + Logos.NEW_ROUND.getText());
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

    private void nextScreen(String message) throws IOException {

        clear();
        out.write(message);
        newLineAndFlush();

        CountDown countDown = new CountDown(3, out);

        while(countDown.isActive()){
            System.out.println("while in...");
        }

    }

    private void clear() throws IOException {
        out.write("\033[2J");
        newLineAndFlush();
    }
}
