package com.atidon.cognitiveperformanceassessment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class MainMemoryActivity extends AppCompatActivity {



    TextView txt_Label_Output;
    TextView txt_hint;
    EditText txt_Edit_Input;
    Button btn_Submit;

    Button btn_Server;
    EditText edit_Server;

    UserDataHandler userDataHandler;

    TestCreator newTest;

    DataProvider dataProvider;

    boolean stringCheck = false;
    int checked = 0;
    int num_Of_Length = 2;

    ArrayList<String> c;

    boolean firstTry = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_memory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataProvider = new DataProvider(this);

        userDataHandler = new UserDataHandler();

        newTest = new TestCreator();

        c = newTest.createRandomStringArray(num_Of_Length);


        txt_Label_Output = (TextView) findViewById(R.id.Text_Label_Output);
        txt_Edit_Input = (EditText) findViewById(R.id.Edit_Text_Input);
        txt_hint = (TextView) findViewById(R.id.Text_View_Hint);

        btn_Server = (Button) findViewById(R.id.button);
        edit_Server = (EditText) findViewById(R.id.editText);


        btn_Submit = (Button) findViewById(R.id.Button_Submit);

        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClick(v);
            }
        });

        btn_Server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataProvider.dropTable(edit_Server.getText().toString(), v);
            }
        });


        System.out.println(dataProvider.getProfilesCount());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, ResultActivity.class);
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void handleOnClick(View v) {

        btn_Submit.setEnabled(false);
        txt_Edit_Input.setEnabled(false);
        txt_hint.setText(R.string.info_text);

        if (checked < num_Of_Length && !stringCheck) {
            userDataHandler.showShowText(c, checked, new Listener<String, Boolean>() {
                @Override
                public void onFinishTask(String arg, Boolean arg1) {
                    txt_Label_Output.setText(arg);

                    if (arg1) {
                        btn_Submit.setEnabled(true);
                        btn_Submit.setText("Next!");
                        txt_Edit_Input.setEnabled(true);
                        txt_Edit_Input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(checked + 3)});
                        txt_Edit_Input.setText("");
                        stringCheck = true;
                    }
                }


            });

        } else if (stringCheck && txt_Edit_Input.getText() != null && checked < num_Of_Length) {
            Test test;
            test = userDataHandler.testText(c, txt_Edit_Input.getText().toString(), checked);

            dataProvider.insertTest(test);
            checked++;
            stringCheck = false;
            handleOnClick(v);

        }else{
            Toast.makeText(this,"The Test Is Done", LENGTH_LONG).show();

            resetTest(v);

        }

    }

    void resetTest(View v){
        btn_Submit.setText("Start!");
        c = newTest.createRandomStringArray(num_Of_Length);
        btn_Submit.setEnabled(true);
        checked = 0;
        stringCheck = false;
    }

}
