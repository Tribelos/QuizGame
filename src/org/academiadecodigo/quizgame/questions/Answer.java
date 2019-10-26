package org.academiadecodigo.quizgame.questions;

public class Answer {

    private String text;
    private AnswerToken token;

    public Answer (AnswerToken token, String text) {
        this.token = token;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AnswerToken getToken() {
        return token;
    }

    public void setToken(AnswerToken token) {
        this.token = token;
    }
}