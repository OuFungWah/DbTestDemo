package com.example.ofw.dbtestdemo;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private MyDao dao;
    private List<Map> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new MyDao(this);
        testEvent();

    }

    protected void testUser() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("num", "0000000002");
        contentValues.put("name", "区枫华");
        contentValues.put("password", "chicken520");
        contentValues.put("avatar", "http://www.baidu.com");
        contentValues.put("sex", "男");
        contentValues.put("birthday", "19970119");
        contentValues.put("collage", "计算机学院");
        contentValues.put("subject", "软件工程");
        contentValues.put("grade", 2015);
        contentValues.put("class", 3);
        dao.insertUser(contentValues);

        list = dao.selectAllUser();
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            Log.d(TAG, "num :" + map.get("num"));
            Log.d(TAG, "name :" + map.get("name"));
            Log.d(TAG, "password :" + map.get("password"));
            Log.d(TAG, "avatar :" + map.get("avatar"));
            Log.d(TAG, "sex :" + map.get("sex"));
            Log.d(TAG, "birthday :" + map.get("birthday"));
            Log.d(TAG, "collage :" + map.get("collage"));
            Log.d(TAG, "subject :" + map.get("subject"));
            Log.d(TAG, "grade :" + map.get("grade"));
            Log.d(TAG, "class :" + map.get("class"));
        }
    }

//    private String createEventTable = "CREATE TABLE " + TABLE_EVENTS + "(" +
//            " num CHAR(64) PRIMARY KEY NOT NULL," +
//            " name CHAR(64) NOT NULL," +
//            " type CHAR(20) NOT NULL," +
//            " location char(20) NOT NULL," +
//            " date CHAR(20) NOT NULL," +
//            " start CHAR(20)," +
//            " end CHAR(20)," +
//            " content CHAR(400))";

    protected void testEvent() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("num", "0000000002");
        contentValues.put("name", "C#");
        contentValues.put("type", "课程");
        contentValues.put("location", "A5 508");
        contentValues.put("date", "星期二");
        contentValues.put("start", "第1周");
        contentValues.put("end", "第15周");
        contentValues.put("content", "泛型，或参数化类型，是被C#支持的.NET 2.0特性。不同于C++模版，.NET参数化类型是在运行时被实例化，而不是编译时，因此它可以跨语言，而C++模版却不行。它支持的一些特性并不被C++模版直接支持，比如约束泛型参数实现一个接口。另一方面，C# 不支持无类型的泛型参数。不像Java中的泛型，在CLI虚拟机中，.NET generics使用具化生成泛型参数，它允许优化和保存类型信息");
        dao.insertEvent(contentValues);

        list = dao.selectAllEvents();
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            Log.d(TAG, "num :" + map.get("num"));
            Log.d(TAG, "name :" + map.get("name"));
            Log.d(TAG, "type :" + map.get("type"));
            Log.d(TAG, "location :" + map.get("location"));
            Log.d(TAG, "date :" + map.get("date"));
            Log.d(TAG, "start :" + map.get("start"));
            Log.d(TAG, "end :" + map.get("end"));
            Log.d(TAG, "content :" + map.get("content"));
        }
    }

}
