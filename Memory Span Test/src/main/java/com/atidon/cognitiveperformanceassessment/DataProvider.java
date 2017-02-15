package com.atidon.cognitiveperformanceassessment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Atila on 15-Feb-17.
 */

public class DataProvider {

    private DbHelper dbHelper;
    private SQLiteDatabase database;

    public DataProvider(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //Inserts a task in sqlite db
    public long insertTest(Test test){
        ContentValues values = new ContentValues();
        values.put(DbHelper.LENGTH, test.getLength());
        values.put(DbHelper.RESULT, test.getResult());
        return database.insert(DbHelper.TABLE_TEST, null, values);
    }
    public Cursor selectAllTests(){
        String[] cols = new String[] {DbHelper.TEST_ID, DbHelper.LENGTH, DbHelper.RESULT};
        Cursor cursor = database.query(true, DbHelper.TABLE_TEST, cols, null, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void dropTable(View v){

            database.execSQL("DROP TABLE IF EXISTS " + dbHelper.TABLE_TEST);
            dbHelper.onCreate(database);
            Toast.makeText(v.getContext(),"All Data deleted", LENGTH_LONG).show();

    }

    public int getProfilesCount(int num) {
        String countQuery = "SELECT * FROM " + dbHelper.TABLE_TEST;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int n = 0;
        cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if(cursor.getInt(1) == num && cursor.getInt(2) == 1){
                    n++;
                }
                cursor.moveToNext();
            }
        cursor.close();
        return n;
    }

    public int getCount() {
        String countQuery = "SELECT * FROM " + dbHelper.TABLE_TEST;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int n = cursor.getCount();
        cursor.close();
        return n;
    }

}
