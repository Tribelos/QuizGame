package org.academiadecodigo.quizgame.Questions;

import java.util.*;

public class Question {

    private String text;
    private AnswerToken correctAnswer;
    private List<Answer> answers;

    public Question(String text, AnswerToken rightAnswer, Answer ... possibleAnswers) {
        this.text = text;
        this.correctAnswer = rightAnswer;
        this.answers = new ArrayList<>();
        this.answers.addAll(Arrays.asList(possibleAnswers));
    }

    public Set<AnswerToken> getAnswerToken() {
        Set<AnswerToken> tokens = new HashSet<>();
        for (Answer answer : answers) {
            tokens.add(answer.getToken());
        }
        return tokens;
    }


    public boolean isCorrectAnswer (AnswerToken ans) {
        return correctAnswer == null ? ans == null : correctAnswer.equals(ans);
    }

    public void setAnswers(List<Answer> choices) {
        this.answers = choices;
    }

    public void getAnswers() {
        for(int i = 0; i < answers.size(); i++){
            System.out.println(answers.get(i).getText());

        }

    }

    // Get text from questions
    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AnswerToken getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(AnswerToken answer) {
        this.correctAnswer = answer;
    }


}