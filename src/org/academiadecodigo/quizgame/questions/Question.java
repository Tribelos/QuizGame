package org.academiadecodigo.quizgame.questions;

public class Question {

    private String question;
    private String[] answers;


    public Question(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }


    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }



}