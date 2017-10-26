package com.example.ofw.dbtestdemo;

import android.content.ContentValues;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

//    private String createUserTable = "CREATE TABLE " + TABLE_USERS + "(" +
//            " num char(64) primary not null," +
//            " name char(20) not null," +
//            " password char(20) not null," +
//            " avatar char(20)," +
//            " sex char(20) not null," +
//            " birthday char(20) not null," +
//            " collage char(20) not null," +
//            " subject char(20) not null," +
//            " grade int," +
//            " class int," +
//            ")";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        MyDao dao = new MyDao(appContext);
        ContentValues contentValues = new ContentValues();
        contentValues.put("num","0000000001");
        contentValues.put("name","区枫华");
        contentValues.put("password","chicken520");
        contentValues.put("avatar","http://www.baidu.com");
        contentValues.put("sex","男");
        contentValues.put("birthday","19970119");
        contentValues.put("collage","计算机学院");
        contentValues.put("subject","软件工程");
        contentValues.put("grade",2015);
        contentValues.put("class",3);
        dao.insertUser(contentValues);
        List<Map> list;
        list = dao.selectAllUser();
        Log.d(TAG, "list的大小" + list.isEmpty());

    }
}
