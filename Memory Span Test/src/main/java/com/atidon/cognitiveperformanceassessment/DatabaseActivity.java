package com.atidon.cognitiveperformanceassessment;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    DataProvider dataProvider;
    Button btn_Server;
    ListView listView;
    ArrayList<Items> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        setTitle("Database View");

        dataProvider = new DataProvider(this);
        updatecheck();


        btn_Server = (Button) findViewById(R.id.button);

        btn_Server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataProvider.dropTable(v);
                updatecheck();

            }
        });


    }

    void updatecheck(){
        listView = (ListView) findViewById(R.id.newListview);
        values = new ArrayList<>();
        try {
            Cursor cursor = dataProvider.selectAllTests();

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(0);
                        int length = cursor.getInt(1);
                        int resu = cursor.getInt(2);
                        System.out.println(id + " " + length + " " + resu);

                        values.add(new Items(id,length,resu));
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            listView.setAdapter(new ResultAdapter(this,values));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, MainMemoryActivity.class);
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
