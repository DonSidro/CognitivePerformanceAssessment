package com.atidon.cognitiveperformanceassessment;

/**
 * Created by SidonKK on 15/02/2017.
 */

public class Items {

    private int id;
    private int letters;
    private int result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLetters() {
        return letters;
    }

    public void setLetters(int letters) {
        this.letters = letters;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Items(int id, int letters, int result) {
        this.id = id;
        this.letters = letters;
        this.result = result;
    }
}
