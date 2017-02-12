package com.atidon.cognitiveperformanceassessment;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SidonKK on 12/02/2017.
 */

public class Characters {

    String randomLetters = "abcdefghijklmnopqrstuvxyz";

    public ArrayList Characters(int num) {
        ArrayList <String> temp = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < num; i++){
            String c = "";
            int n = rand.nextInt(9 - 6) + 6;
            for(int j = 0; j < n; j++){
                String d = String.valueOf(randomLetters.charAt(rand.nextInt(randomLetters.length())));
                c = c + d;
            }
            temp.add(c);

        }
        return temp;
    }

}
