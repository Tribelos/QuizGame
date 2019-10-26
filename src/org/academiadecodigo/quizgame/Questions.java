package org.academiadecodigo.quizgame;

import java.util.ArrayList;

public class Questions {

    private ArrayList<String[]> arraylist;

    public Questions() {
        this.arraylist = new ArrayList<>();
        allQuestion();
    }


    private void allQuestion() {

        String[] question1 = {"This is the question?1", "asnwer1", "answer2", "answer3", "answer4", "answer2"};
        String[] question2 = {"This is the question?2", "asnwer1", "answer2", "answer3", "answer4", "answer3"};
        String[] question3 = {"This is the question?3", "asnwer1", "answer2", "answer3", "answer4", "answer4"};
        String[] question4 = {"This is the question?4", "asnwer1", "answer2", "answer3", "answer4", "answer2"};


        arraylist.add(question1);
        arraylist.add(question2);
        arraylist.add(question3);
        arraylist.add(question4);


    }

    public String[] getRandom(){

        int randomQuestion = (int) Math.round(Math.random() * arraylist.size());
        return arraylist.get(randomQuestion);

    }
}
