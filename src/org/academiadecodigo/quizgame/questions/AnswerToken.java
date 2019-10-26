package org.academiadecodigo.quizgame.questions;

public enum AnswerToken {
    A("A"),B("B"),C("C"),D("D");

    private String string;

    AnswerToken(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
