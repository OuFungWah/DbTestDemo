package com.example.ofw.dbtestdemo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ofw on 2017/10/26.
 */

public class MyDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Campus";
    public static final String TABLE_USERS = "Users";
    public static final String TABLE_EVENTS = "Events";

    private String createUserTable = "CREATE TABLE " + TABLE_USERS + "(" +
            " num char(64) primary not null," +
            " name char(20) not null," +
            " password char(20) not null," +
            " avatar char(20)," +
            " sex char(20) not null," +
            " birthday char(20) not null," +
            " collage char(20) not null," +
            " subject char(20) not null," +
            " grade int," +
            " class int," +
            ")";

    private String createEventTable = "CREATE TABLE " + TABLE_EVENTS + "(" +
            " num char(64) primary not null," +
            " name char(64) not null," +
            " type char(20) not null," +
            " location char(20) not null," +
            " date char(20) not null," +
            " start char(20)," +
            " end char(20)," +
            " content char(400))";

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserTable);
        db.execSQL(createEventTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
