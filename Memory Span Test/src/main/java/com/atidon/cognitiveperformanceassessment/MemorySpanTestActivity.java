package com.atidon.cognitiveperformanceassessment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SidonKK onFinishTask 12/02/2017.
 */

public class MemorySpanTestActivity extends Fragment {

    View MemorySpanTestView;

    TextView txt_characters;
    TextView txt_in;
    EditText edit_text;
    Button btn_check;

    UserDataHandler userDataHandler;

    TestCreator newText;
    ArrayList<String> c;
    boolean stringCheck = false;
    int checked = 0;
    int num_Of_Length = 8;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set title of new fragment
        getActivity().setTitle("Memory Span Test");
        userDataHandler = new UserDataHandler();

        //Making refernce to UI objects
        txt_characters = (TextView) view.findViewById(R.id.Text_Label_Output);
        //txt_in = (TextView) view.findViewById(R.id.textView3);
        edit_text = (EditText) view.findViewById(R.id.Edit_Text_Input);
        btn_check = (Button) view.findViewById(R.id.Button_Submit);

        //Adding OnClickListener to button
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChars(v);
            }
        });

        newText = new TestCreator();


        c = newText.createRandomStringArray(8);
    }

    public void showChars(View v){

        //Disabling  Button and EditText field

        btn_check.setEnabled(false);
        edit_text.setEnabled(false);

        if(checked <= num_Of_Length - 1 && !stringCheck) {
            userDataHandler.showShowText(c,checked,new Listener<String, Boolean>(){
                @Override
                public void onFinishTask(String arg, Boolean arg1) {
                    txt_characters.setText(arg);

                    if(arg1){
                        btn_check.setEnabled(true);
                        edit_text.setEnabled(true);
                        stringCheck = true;
                    }
                }


            });

        }else if(stringCheck && edit_text.getText() != null && checked <= num_Of_Length - 1){

        }



    }


}
