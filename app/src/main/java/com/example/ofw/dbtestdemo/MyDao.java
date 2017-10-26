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
        return insert(MyDbHelper.TABLE_USERS, contentValues);
    }

    public boolean insertEvent(ContentValues contentValues) {
        return insert(MyDbHelper.TABLE_EVENTS, contentValues);
    }

    protected boolean insert(String tableName, ContentValues contentValues) {
        boolean flag = false;
        long index = -1L;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            //第二个参数用于把没有指定列名的数据插入到第二个参数的列中,可为null
            index = database.insert(tableName, null, contentValues);
            if (index != -1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            database.close();
        }

        return flag;
    }

    protected boolean update(){
        boolean flag = false;
        return flag;
    }

    public List<Map> selectAllEvents() {
        return selectAll(MyDbHelper.TABLE_EVENTS);
    }


    public List<Map> selectAllUser() {
        return selectAll(MyDbHelper.TABLE_USERS);
    }

    private List<Map> selectAll(String tableName) {
        List<Map> mapList = new ArrayList<>();
        /**
         * getType()返回的类型与对应的 int 值
         * int FIELD_TYPE_BLOB = 4;
         * int FIELD_TYPE_FLOAT = 2;
         * int FIELD_TYPE_INTEGER = 1;
         * int FIELD_TYPE_NULL = 0;
         * int FIELD_TYPE_STRING = 3;
         */
        Cursor cursor = null;
        SQLiteDatabase database = null;
        try {
            database = helper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM " + tableName, null);
            while (cursor.moveToNext()) {
                Map map = new HashMap<>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    //判断数据类型再按相应的类型进行存储
                    switch (cursor.getType(i)) {
                        case 0:
                            map.put(cursor.getColumnName(i), null);
                            break;
                        case 1:
                            map.put(cursor.getColumnName(i), cursor.getInt(i));
                            break;
                        case 2:
                            map.put(cursor.getColumnName(i), cursor.getFloat(i));
                            break;
                        case 3:
                            map.put(cursor.getColumnName(i), cursor.getString(i));
                            break;
                        case 4:
                            map.put(cursor.getColumnName(i), cursor.getBlob(i));
                            break;
                    }
                }
                mapList.add(map);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            database.close();
        }
        return mapList;
    }

}
