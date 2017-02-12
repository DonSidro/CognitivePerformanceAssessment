package com.atidon.cognitiveperformanceassessment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SidonKK on 12/02/2017.
 */

public class MemorySpanTestActivity extends Fragment {

    TextView txt_characters;
    EditText edit_text;
    Button btn_check;
    Characters characters;
    ArrayList<String> c;
    int checked = 0;
    int num_Of_Length = 8;
    Handler handler = new Handler();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Memory Span Test");
        txt_characters = (TextView) view.findViewById(R.id.Text_Label);
        edit_text = (EditText) view.findViewById(R.id.editText);
        btn_check = (Button) view.findViewById(R.id.button);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChars(v);
            }
        });

        characters = new Characters();
        c = characters.Characters(num_Of_Length);
        System.out.println(c);
    }

    View MemorySpanTestView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MemorySpanTestView = inflater.inflate(R.layout.memory_span_layout, container, false);
        return MemorySpanTestView;
    }


    boolean check = false;
    public void showChars(View v){
        btn_check.setEnabled(false);
        edit_text.setEnabled(false);
        ArrayList<Boolean> bb = new ArrayList<>();

        if(checked <= num_Of_Length - 1 && !check) {
            final String s = c.get(checked);
            System.out.println(s);
            System.out.println(checked);
            for (int i = 0; i < s.length(); i++) {
                final int finalI = i;
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        txt_characters.setText(String.valueOf(s.charAt(finalI)));
                        if(finalI == s.length()-1){
                            btn_check.setEnabled(true);
                            btn_check.setText("Check!");
                            check = true;
                            edit_text.setEnabled(true);
                            edit_text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(s.length())});
                            edit_text.setText("");
                        }

                    }

                }, 1000 * i);
            }
        }else if (check && edit_text.getText() != null && checked <= num_Of_Length - 1){
            String s_out = c.get(checked);
            String s_in = edit_text.getText().toString();
            System.out.println(s_out);
            System.out.println(s_in);
            for(int i = 0; i < s_in.length(); i ++){
                if(String.valueOf(s_out.charAt(i)).equals(String.valueOf(s_in.charAt(i)))){
                    bb.add(true);
                }else{
                    bb.add(false);
                }
            }
            if(s_in.length()< s_out.length()){
                for(int i = s_in.length(); i < s_out.length(); i++){
                    bb.add(false);
                }
            }

            checked++;
            btn_check.setEnabled(true);
            txt_characters.setText("");
            check = false;
            System.out.println(bb);
            showChars(v);

        }else{
            Toast.makeText(getActivity().getApplicationContext(),"The Test Is Done", Toast.LENGTH_LONG).show();

        }


    }


}
