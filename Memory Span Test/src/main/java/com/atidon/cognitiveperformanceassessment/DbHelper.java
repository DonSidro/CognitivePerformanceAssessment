package com.atidon.cognitiveperformanceassessment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Atila on 15-Feb-17.
 */

public class DbHelper extends SQLiteOpenHelper {

    //DB name
    private static final String DB_NAME = "memory_db";
    //DB version
    private static final int DB_VERSION = 1;
    //Table name
    public static final String TABLE_TEST = "tbltest";
    //column names
    public static final String TEST_ID = "test_id";
    public static final String LENGTH = "length";
    public static final String RESULT = "result";

    //create table statement
    private static final String CREATE_TABLE_TEST = "CREATE TABLE "
            + TABLE_TEST + "(" + TEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + LENGTH + " INTEGER,"
            + RESULT + " BOOLEAN)";

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create tables
        db.execSQL(CREATE_TABLE_TEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
        //create the new tables
        onCreate(db);

    }
}
