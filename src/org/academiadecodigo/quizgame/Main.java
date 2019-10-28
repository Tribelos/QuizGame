package org.academiadecodigo.quizgame;

import org.academiadecodigo.quizgame.server.Server;

public class Main {

    private static final int DEFAULT_PORT = 50000;

    public static void main(String[] args) {

        try {
            int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
            Server server = new Server(port);
        } catch (NumberFormatException e) {
            System.err.println("Usage: WebServer [PORT]");
            System.exit(1);
        }

    }
}
