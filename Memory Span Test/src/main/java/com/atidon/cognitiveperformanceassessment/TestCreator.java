package com.atidon.cognitiveperformanceassessment;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SidonKK onFinishTask 14/02/2017.
 */

public class TestCreator {

    //Alphabet string used to create the string array of random letters
    private String alphabet = "abcdefghijklmnopqrstuvxyz";

    //minimum amount of letters used
    private int minLetters = 6;

    //Maximum amount of letters used
    private int maxLetters = 9;

    public ArrayList createRandomStringArray(int length_Of_Array){

        //Temporary string array list
        ArrayList <String> tempArray = new ArrayList<>();

        //Creating new Random object
        Random random = new Random();

        int letterLength = 3;
        //For loop for creating random letter combination array.
        for (int i = 0; i < length_Of_Array; i++){

            //Temporary string
            String tempString = "";

            //Inner for loop
            for(int j = 0; j < letterLength; j++){
                String randomLetter = String.valueOf(alphabet.charAt(random.nextInt(alphabet.length())));
                tempString = tempString + randomLetter + " ";
            }

            tempArray.add(tempString);
            letterLength++;

        }
        return tempArray;

    }

    public void setMaxLetters(int maxLetters) {
        this.maxLetters = maxLetters;
    }

    public void setMinLetters(int minLetters) {
        this.minLetters = minLetters;
    }

}
