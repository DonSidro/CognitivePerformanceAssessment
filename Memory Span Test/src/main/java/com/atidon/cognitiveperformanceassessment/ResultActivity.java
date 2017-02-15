package com.atidon.cognitiveperformanceassessment;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity{

    DataProvider dataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Result View");
        dataProvider = new DataProvider(this);

        BarChart barChart = (BarChart) findViewById(R.id.barchart);

        List<BarEntry> entries = new ArrayList<>();

        YAxis leftAxis = barChart.getAxisLeft();
        XAxis xAxis = barChart.getXAxis();

        leftAxis.setDrawLabels(true); // no axis labels
        leftAxis.setDrawAxisLine(true); // no axis line
        leftAxis.setDrawGridLines(true); // no grid lines
        leftAxis.setDrawZeroLine(true); // draw a zero line
        barChart.getAxisRight().setEnabled(false); // no right axis
        barChart.getDescription().setEnabled(false);

        leftAxis.setAxisMinimum(0f); // start at zero

        leftAxis.setTextSize(12f);

        xAxis.setTextSize(12f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

        entries.add(new BarEntry(3,dataProvider.getProfilesCount(3)));
        entries.add(new BarEntry(4,dataProvider.getProfilesCount(4)));
        entries.add(new BarEntry(5,dataProvider.getProfilesCount(5)));
        entries.add(new BarEntry(6,dataProvider.getProfilesCount(6)));
        entries.add(new BarEntry(7,dataProvider.getProfilesCount(7)));
        entries.add(new BarEntry(8,dataProvider.getProfilesCount(8)));
        entries.add(new BarEntry(9,dataProvider.getProfilesCount(9)));
        entries.add(new BarEntry(10,dataProvider.getProfilesCount(10)));

        BarDataSet barDataSet = new BarDataSet(entries, "About of people that remembered");

        BarData data = new BarData(barDataSet);
        data.setBarWidth(.8f); // set custom bar width
        data.setValueTextSize(10f);
        data.setValueFormatter(new MyCustomFormatter());
        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh


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
