package org.academiadecodigo.quizgame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server(8080);
        server.init();

    }
}
