package org.academiadecodigo.quizgame.timer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class CountDown {

    private final int DELAY = 1000;
    private final int PERIOD = 1000;

    private Timer timer;
    private int count;

    private boolean isActive;

    public BufferedWriter out;

    public CountDown(int countFrom, BufferedWriter out){

        timer = new Timer();
        count = countFrom;
        isActive = true;

        this.out = out;

        timer.scheduleAtFixedRate( new TimerTask(){

            public void run(){
                try {
                    setCount();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }, DELAY, PERIOD);
    }

    private void setCount() throws IOException {
        System.out.println(count);
        if(count == 1){
            isActive = false;
            timer.cancel();

        }
        out.write(String.valueOf(count) + "... ");
        //out.newLine();
        out.flush();

        count--;
    }

    public boolean isActive(){
        return isActive;
    }

}
