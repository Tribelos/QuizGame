package org.academiadecodigo.quizgame.players;

import org.academiadecodigo.quizgame.Server;

import java.io.*;
import java.net.Socket;

public class PlayerConnection implements Runnable{

    Server server;
    Socket clientSocket;

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;


    public PlayerConnection(Socket socket){
        clientSocket = socket;

        try {

            bufferedReader = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
            bufferedWriter = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void run() {

    }
}
