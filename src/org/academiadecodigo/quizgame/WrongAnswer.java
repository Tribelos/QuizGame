package org.academiadecodigo.quizgame;

public enum WrongAnswer {

    WRONG1("Sorry.. Wrong answer"),
    WRONG2("Pfff... FAIL! Sorry, not sorry");

    private String text;

    WrongAnswer(String text) {
        this.text = text;
    }


    public String getText() {
        return this.text;
    }


}
