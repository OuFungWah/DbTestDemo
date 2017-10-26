package com.example.ofw.dbtestdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ofw on 2017/10/26.
 */

public class MyDao {

    private MyDbHelper helper;

    public MyDao(Context context) {
        helper = new MyDbHelper(context, MyDbHelper.DB_NAME, null, 1);
    }

    public boolean insertUser(ContentValues contentValues) {
        boolean flag = false;
        long index = -1L;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            //第二个参数用于把没有指定列名的数据插入到第二个参数的列中,可为null
            index = database.insert(MyDbHelper.TABLE_USERS, null, contentValues);
            if (index != -1) {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }finally {
            database.close();
        }

        return flag;
    }

    public List<Map<String,String>> selectAllUser(){
        boolean flag = false;
        List<Map<String,String>> mapList = new ArrayList<>();
        Cursor cursor = null;
        SQLiteDatabase database;
        try{
            database = helper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM "+MyDbHelper.TABLE_USERS,null);
            while(cursor.moveToNext()){
                Map<String,String> map = new HashMap<>();
                for(int i = 0;i<cursor.getColumnCount();i++){
                    map.put(cursor.getColumnName(i),cursor.getString(i));
                }
            }
        }catch (Exception e){

        }finally {

        }
        return null;
    }

    public boolean insertEvent(ContentValues contentValues) {
        boolean flag = false;
        long index = -1L;
        try {
            SQLiteDatabase database = helper.getWritableDatabase();
            //第二个参数用于把没有指定列名的数据插入到第二个参数的列中,可为null
            index = database.insert(MyDbHelper.TABLE_EVENTS, null, contentValues);
            if (index != -1) {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

}
