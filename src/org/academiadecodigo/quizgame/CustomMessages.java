package org.academiadecodigo.quizgame;

import org.academiadecodigo.bootcamp.*;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class CustomMessages {

    public void welcome() {
        System.out.println("Welcome to the Quizizinho!");
    }

    public void startMenu(){

        Prompt prompt = new Prompt(System.in, System.out);
        String[] options = {"Joing Game", "Leave"};

        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Do you want to play?");
        int answerIndex = prompt.getUserInput(scanner);

    }

    public void gameTitle() {

        System.out.println("" +
                "(  __  \\ (  ___  )  (  ___  )|\\     /|\\__   __// ___   )/ ___   )|\\     /|(  ____ \\( )\n" +
                "| (  \\  )| (   ) |  | (   ) || )   ( |   ) (   \\/   )  |\\/   )  |( \\   / )| (    \\/| |\n" +
                "| |   ) || (___) |  | |   | || |   | |   | |       /   )    /   ) \\ (_) / | (__    | |\n" +
                "| |   | ||  ___  |  | |   | || |   | |   | |      /   /    /   /   \\   /  |  __)   | |\n" +
                "| |   ) || (   ) |  | | /\\| || |   | |   | |     /   /    /   /     ) (   | (      (_)\n" +
                "| (__/  )| )   ( |  | (_\\ \\ || (___) |___) (___ /   (_/\\ /   (_/\\   | |   | (____/\\ _ \n" +
                "(______/ |/     \\|  (____\\/_)(_______)\\_______/(_______/(_______/   \\_/   (_______/(_)");
    }




}
