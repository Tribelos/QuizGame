package org.academiadecodigo.quizgame.Questions;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {

    private static final Random random = new Random();
    private static List<Question> loadQuestions() {


        // TODO: 25/10/2019 Talvez arranjar uma maneira de ter todas as questoes
        // noutro sitio ou num ficheiro txt ou numa classe de questoes


        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is my name?", AnswerToken.A,
                new Answer(AnswerToken.A, "Basilio"),
                new Answer(AnswerToken.B, "Torrinha"),
                new Answer(AnswerToken.C, "Cunha"),
                new Answer(AnswerToken.D, "Vieira")));

        questions.add(new Question("Quantas horas paço aqui?", AnswerToken.B,
                new Answer(AnswerToken.A, "poucas"),
                new Answer(AnswerToken.B, "Chop chop!"),
                new Answer(AnswerToken.C, "Demasiadas"),
                new Answer(AnswerToken.D, "nao sei o que é isso")));

        return questions;
    }

    public static void main(String[] args) {

        List<Question> questions = loadQuestions();
        StringBuffer sb = new StringBuffer();

        for (Question s : questions) {

            //System.out.println();
            //System.out.println(s.getText());
            //System.out.println(s.getCorrectAnswer());
            s.getText();
            s.getCorrectAnswer();
            s.getAnswers();
        }

    }

}