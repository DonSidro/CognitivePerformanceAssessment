package com.atidon.cognitiveperformanceassessment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputFilter;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView txt_characters;
    EditText edit_text;
    Button btn_check;
    Characters characters;
    ArrayList<String> c;
    boolean started = false;
    int checked = 0;
    int num_Of_Length = 8;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txt_characters = (TextView) findViewById(R.id.Text_Label);
        edit_text = (EditText) findViewById(R.id.editText);
        btn_check = (Button) findViewById(R.id.button);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                for(int i = 0; i < s_out.length(); i ++){
                    if(String.valueOf(s_out.charAt(i)).equals(String.valueOf(s_in.charAt(i)))){
                        bb.add(true);
                    }else{
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
            Toast.makeText(getApplicationContext(),"The Test Is Done", Toast.LENGTH_LONG).show();

        }


    }

}
