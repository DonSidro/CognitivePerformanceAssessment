package com.atidon.cognitiveperformanceassessment;

/**
 * Created by Atila on 15-Feb-17.
 */

public class Test {

    private int test_id;
    private int length;
    private boolean result;

    public Test(int length, boolean result){
        this.length = length;
        this.result = result;
    }

    //Getters
    public int getTest_id(){
        return this.test_id;
    }

    public int getLength(){
        return this.length;
    }

    public boolean getResult(){
        return this.result;
    }
}
