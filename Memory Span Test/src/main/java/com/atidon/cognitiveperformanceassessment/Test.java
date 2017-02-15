package com.atidon.cognitiveperformanceassessment;

/**
 * Created by Atila on 15-Feb-17.
 */

public class Test {
    private int length;
    private boolean result;

    public Test(int length, boolean result){
        this.length = length;
        this.result = result;
    }

    //Getters
    public int getLength(){
        return this.length;
    }

    public boolean getResult(){
        return this.result;
    }
}
