package com.atidon.cognitiveperformanceassessment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SidonKK on 15/02/2017.
 */

public class ResultAdapter extends ArrayAdapter {

    private Context context;
    private List<Items> results;

    public ResultAdapter(Context context, List<Items> results) {
        super(context, R.layout.customlistview, results);

        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.customlistview, parent, false);

        TextView idtext = (TextView) view.findViewById(R.id.textView9);
        idtext.setText(""+results.get(position).getId());
        TextView lettertext = (TextView) view.findViewById(R.id.textView8);
        lettertext.setText(""+results.get(position).getLetters());
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.order_table_row_present_CheckBox);
        if(results.get(position).getResult() == 1){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        return  view;
    }
}
