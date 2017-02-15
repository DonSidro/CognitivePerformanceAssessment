package com.atidon.cognitiveperformanceassessment;

import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;

import java.util.ArrayList;

/**
 * Created by SidonKK onFinishTask 14/02/2017.
 */

public class UserDataHandler {


    Handler handler;
    DbHelper dbHelper;
    DataProvider dataProvider;


    public String showShowText(ArrayList<String> output, int checked, final Listener<String, Boolean> onCompleteListener) {

        final String _temp = output.get(checked);
        handler = new Handler();

        for (int i = 0; i < _temp.length(); i++) {
            final int finalI = i;
            final String[] t = {""};

            final  Runnable r = new Runnable() {
                @Override
                public void run() {
                    if(finalI == _temp.length()-1){
                        onCompleteListener.onFinishTask(String.valueOf(_temp.charAt(finalI)).toString(),true);

                    }else{
                        onCompleteListener.onFinishTask(String.valueOf(_temp.charAt(finalI)).toString(),false);

                    }
                }
            };
            handler.postDelayed(r, 500 * i);
        }

        return "";
    }


    public Test testText(ArrayList<String> output, String userIpput, int checked){

        Test test;

        final String _temp = output.get(checked);
        String s_in = userIpput;
        String temp = _temp.replace(" ", "");

        if(temp.equals(s_in)){
            test = new Test((_temp.length()/2), true);
        }else{
            test = new Test((_temp.length()/2), false);
        }

        return test;
    }
}
