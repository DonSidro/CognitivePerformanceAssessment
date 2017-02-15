package com.atidon.cognitiveperformanceassessment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;


/**
 * Created by SidonKK onFinishTask 14/02/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDATA.db";
    public static final String TABLE_DATA = "userdata";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_INPUT = "_input";
    public static final String COLUMN_OUPUT = "_output";
    public static final String COLUMN_DATAPROCENT = "_dataProcent";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_DATA + "("+
                COLUMN_ID + " INGEGER PRIMARY KEY AUTOINCREMENT" +
                COLUMN_INPUT + " BLOB " +
                COLUMN_OUPUT + " BLOB " +
                COLUMN_DATAPROCENT + " REAL " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_DATA);
        onCreate(db);
    }


//    public void addData(UserData userData){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_INPUT, userData.get_input().toString());
//        contentValues.put(COLUMN_OUPUT, userData.get_output().toString());
//        contentValues.put(COLUMN_DATAPROCENT, userData.get_dataProcent());
//        SQLiteDatabase db = getWritableDatabase();
//        db.insert(TABLE_DATA,null, contentValues);
//        db.close();
//    }



}
