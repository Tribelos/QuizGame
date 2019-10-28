package org.academiadecodigo.quizgame.game;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.quizgame.game.gametypes.FourPlayers;
import org.academiadecodigo.quizgame.game.gametypes.SinglePlayer;
import org.academiadecodigo.quizgame.game.gametypes.TwoPlayers;

import java.io.*;
import java.net.*;

public class WaitingRoom implements Runnable {

    private Player player;
    private PrintWriter printWriter;

    public WaitingRoom(Socket clientSocket) throws IOException {
        this.player = new Player(clientSocket);
        this.printWriter = new PrintWriter(clientSocket.getOutputStream());
    }


    private void mainMenu() throws IOException {

        printWriter.write("\n" +
                "     ██████╗ ██╗   ██╗██╗███████╗██╗███████╗██╗███╗   ██╗██╗  ██╗ ██████╗ \n" +
                "    ██╔═══██╗██║   ██║██║╚══███╔╝██║╚══███╔╝██║████╗  ██║██║  ██║██╔═══██╗\n" +
                "    ██║   ██║██║   ██║██║  ███╔╝ ██║  ███╔╝ ██║██╔██╗ ██║███████║██║   ██║\n" +
                "    ██║▄▄ ██║██║   ██║██║ ███╔╝  ██║ ███╔╝  ██║██║╚██╗██║██╔══██║██║   ██║\n" +
                "    ╚██████╔╝╚██████╔╝██║███████╗██║███████╗██║██║ ╚████║██║  ██║╚██████╔╝\n" +
                "     ╚══▀▀═╝  ╚═════╝ ╚═╝╚══════╝╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ " +
                "\n");
        printWriter.write("/n");
        printWriter.flush();

        String[] options = {"Single Player", "2 Players", "4 Players", "Leave"};
        MenuInputScanner startMenu = new MenuInputScanner(options);
        startMenu.setMessage("Do you want to play?");

        int answerIndex = player.getPrompt().getUserInput(startMenu);

        if (answerIndex == 4) {
            printWriter.write("K then bye");
            printWriter.write("\n");
            printWriter.flush();
            player.getClientSocket().close();
        }

        if(answerIndex == 1) {

            printWriter.write("SINGLE PLAYER");
            printWriter.write("\n");
            printWriter.flush();

            StringInputScanner askName = new StringInputScanner();
            askName.setMessage("What's your name?  ");
            printWriter.write("\n");
            String playerName = player.getPrompt().getUserInput(askName);
            player.setName(playerName);
            SinglePlayer singlePlayer = new SinglePlayer(player);

        }

        if(answerIndex == 2) {

            printWriter.write("2 PLAYERS");
            printWriter.write("\n");
            printWriter.flush();

            StringInputScanner askName = new StringInputScanner();
            askName.setMessage("What's your name?  ");
            printWriter.write("\n");
            String playerName = player.getPrompt().getUserInput(askName);
            player.setName(playerName);
            TwoPlayers twoPlayers = new TwoPlayers(player);

        }

        if(answerIndex == 3) {

            printWriter.write("4 PLAYERS");
            printWriter.write("\n");
            printWriter.flush();

            StringInputScanner askName = new StringInputScanner();
            askName.setMessage("What's your name?  ");
            printWriter.write("\n");
            String playerName = player.getPrompt().getUserInput(askName);
            player.setName(playerName);
            FourPlayers fourPlayers = new FourPlayers(player);

        }

    }

    @Override
    public void run() {
        try {
            mainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
