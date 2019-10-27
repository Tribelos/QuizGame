package org.academiadecodigo.quizgame;

import java.util.ArrayList;

public class Questions {

    private ArrayList<String[]> arrayList;

    public Questions() {
        this.arrayList = new ArrayList<>();
        allQuestion();
    }


    private void allQuestion() {


        String[] question1 = {"The Founder of the most famous gaming platform steam is ?", "Bill Gates", "Peter Gabriel", "Gabe Newell", "Bill Cosby", "Gabe Newell"};

        String[] question2 = {"When Michael Jorden played for the Chicago Bulls, how many NBA championships did he win?", "4", "5", "6", "7", "6"};

        String[] question3 = {"Which racer holds the record for the most Grand Prix wins?", "Lewis Hamilton", "Michael Shumacher", "Ayrton Senna", "Alan Prost", "Michael Shumacher"};

        String[] question4 = {"Which of these events is NOT part of a decathlon?", "Javelin throw", "High jump", "110 metres hurdles", "Hammer throw", "Hammer throw"};

        String[] question5 = {"In which year was the first ever Wimbledon Championship held?", "1902", "1873", "1877", "1884", "1877"};



        String[] question6 = {"What year was the first model of the iPhone released?", "2005", "2006", "2007", "2008", "2007"};

        String[] question7 = {"Galileo was an Italian astronomer who developed?", "Telescope", "Aeroplane", "Electricity", "Train", "Telescope"};

        String[] question8 = {"What was the first website name of Amazon?", "amazonia.com", "relentless.com", "buy-online.com", "sharkbuys.com", "relentless.com"};

        String[] question9 = {"What's the shortcut for the 'copy' function on most computers?", "Ctrl + c", "Ctrl + x", "Ctrl + v", "Ctrl + p", "Ctrl + c"};

        String[] question10 = {"Which of these is NOT a web browser?", "Google Chrome", "Epic", "Opera", "Finder", "Finder"};



        String[] question11 = {"Which is the hottest planet in the solar system?", "Mercury", "Venus", "Earth", "Mars", "Venus"};

        String[] question12 = {"What is the symbol for potassium?", "K", "Au", "Cu", "P", "K"};

        String[] question13 = {"Who discovered penicillin?", "Alexander Bell", "Marie Curie", "Charles Babbage", "Alexander Fleming", "Alexander Fleming"};

        String[] question14 = {"How many molecules of oxygen does ozone have?", "0", "1", "2", "3", "3"};

        String[] question15 = {"Which natural disaster is measured with the Richter scale?", "Earthquakes", "Wildfires", "Tornadoes", "Floods", "Earthquakes"};



        String[] question16 = {"How many hearts does an octopus have?", "1", "2", "3", "4", "3"};

        String[] question17 = {"What is the scientific name of a wolf?", "Canidae lobus", "Canis vulgaris", "Canis lupus", "Canidae latrans", "Canis lupus"};

        String[] question18 = {"How many eye does a bee have?", "2", "3", "4", "5", "5"};

        String[] question19 = {"Which mammal has no vocal cords?", "Elephant", "Whale", "Giraffe", "Wolf", "Giraffe"};

        String[] question20 = {"How long is the gestation period of an African elephant?", "12 months", "18 months", "22 months", "24 months", "22 months"};



        String[] question21 = {"Which popular TV show featured house Targaryen and Stark?", "Iron Man", "Breaking Bad", "Friends", "Game of Thrones", "Game of Thrones"};

        String[] question22 = {"How many Lord of the Rings films are there?", "3", "4", "5", "6", "3"};

        String[] question23 = {"In what year was the first episode of South Park aired?", "1995", "1996", "1997", "1998", "1997"};

        String[] question24 = {"Who was the director of the film FIGHT CLUB?", "David Fincher", "Guy Ritchie", "Danny Boyle", "Matthew Vaughn", "David Fincher"};

        String[] question25 = {"How many films did Sean Connery play James Bond in?", "4", "5", "6", "7", "7"};



        String[] question26 = {"Which continent is the largest?", "Africa", "Asia", "Antarctic", "Europe", "Asia"};

        String[] question27 = {"What is the capital of Australia?", "Sydney", "Melbourne", "Brisbane", "Canberra", "Canberra"};

        String[] question28 = {"What is the smallest country in the world?", "San Marino", "Vatican City", "Nauro", "Liechtenstein", "Vatican City"};

        String[] question29 = {"Which two countries share the longest international borders?", "Canada & USA", "China & Mongolia", "Russia & Kazakhstan", "Argentina & Chile", "Canada & USA"};

        String[] question30 = {"Which city in India would you find the Taj Mahal in?", "New Delhi", "Agra", "Jaipur", "Mumbai", "Agra"};


        arrayList.add(question1);
        arrayList.add(question2);
        arrayList.add(question3);
        arrayList.add(question4);
        arrayList.add(question5);
        arrayList.add(question6);
        arrayList.add(question7);
        arrayList.add(question8);
        arrayList.add(question9);
        arrayList.add(question10);
        arrayList.add(question11);
        arrayList.add(question12);
        arrayList.add(question13);
        arrayList.add(question14);
        arrayList.add(question15);
        arrayList.add(question16);
        arrayList.add(question17);
        arrayList.add(question18);
        arrayList.add(question19);
        arrayList.add(question20);
        arrayList.add(question21);
        arrayList.add(question22);
        arrayList.add(question23);
        arrayList.add(question24);
        arrayList.add(question25);
        arrayList.add(question26);
        arrayList.add(question27);
        arrayList.add(question28);
        arrayList.add(question29);
        arrayList.add(question30);
    }

    public String[] getRandom(){

        int randomQuestion = (int) Math.round(Math.random() * arrayList.size());

        return arrayList.get(randomQuestion);

    }

    public ArrayList<String[]> getArrayList(){

        return arrayList;
    }
}
