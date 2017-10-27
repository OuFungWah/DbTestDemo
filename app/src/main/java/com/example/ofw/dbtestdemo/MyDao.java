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
            //第二个参数用于把没有指定字段的数据插入到第二个参数的列中,可为null
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

    /**
     * 修改用户表
     *
     * @param contentValues 修改的字段和对应值集合
     * @param where         用于确定被修改行的位置的字段，为null时则修改所有行
     * @param whereArgs     用于确定被修改行的位置的对应值
     * @return 返回是否操作成功
     */
    public boolean updateUsers(ContentValues contentValues, String where, String whereArgs[]) {
        return update(MyDbHelper.TABLE_USERS, contentValues, where, whereArgs);
    }

    /**
     * 修改事件表
     *
     * @param contentValues 修改的字段和对应值集合
     * @param where         用于确定被修改行的位置的字段，为null时则修改所有行
     * @param whereArgs     用于确定被修改行的位置的对应值
     * @return 返回是否操作成功
     */
    public boolean updateEvents(ContentValues contentValues, String where, String whereArgs[]) {
        return update(MyDbHelper.TABLE_EVENTS, contentValues, where, whereArgs);
    }

    protected boolean update(String tableName, ContentValues contentValues, String where, String whereArgs[]) {
        boolean flag = false;
        int index = 0;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            /**
             * Convenience method for updating rows in the database.
             *
             * @param table the table to update in 表名
             * @param values a map from column names to new column values. null is a
             *            valid value that will be translated to NULL.
             * @param whereClause the optional WHERE clause to apply when updating.
             *            Passing null will update all rows. 查找参数字段
             * @param whereArgs You may include ?s in the where clause, which
             *            will be replaced by the values from whereArgs. The values
             *            will be bound as Strings. 查找参数值
             * @return the number of rows affected 受影响的行数
             */
            index = database.update(tableName, contentValues, where, whereArgs);
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

    /**
     * 修改用户表
     *
     * @param where     用于确定被修改行的位置的字段，为null时则修改所有行
     * @param whereArgs 用于确定被修改行的位置的对应值
     * @return 返回是否操作成功
     */
    public boolean deleteUsers(String where, String whereArgs[]) {
        return delete(MyDbHelper.TABLE_USERS, where, whereArgs);
    }

    /**
     * 修改事件表
     *
     * @param where     用于确定被删除行的位置的字段，为null时则修改所有行
     * @param whereArgs 用于确定被删除行的位置的对应值
     * @return 返回是否操作成功
     */
    public boolean deleteEvents(String where, String whereArgs[]) {
        return delete(MyDbHelper.TABLE_EVENTS, where, whereArgs);
    }

    private boolean delete(String tableName, String where, String whereArgs[]) {
        boolean flag = false;
        int index = -1;
        SQLiteDatabase database = null;
        try {
            database = helper.getWritableDatabase();
            /**
             * Convenience method for deleting rows in the database.
             *
             * @param table the table to delete from
             * @param whereClause the optional WHERE clause to apply when deleting.
             *            Passing null will delete all rows.
             * @param whereArgs You may include ?s in the where clause, which
             *            will be replaced by the values from whereArgs. The values
             *            will be bound as Strings.
             * @return the number of rows affected if a whereClause is passed in, 0
             *         otherwise. To remove all rows and get a count pass "1" as the
             *         whereClause.
             */
            index = database.delete(tableName, where, whereArgs);
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

    /**
     * 只带一个约束条件和它（或它们）对应的属性值
     *
     * @param selection     字段名 如： " name = ? "、" name = ? AND num = ? "、" name = 'Tom' "
     * @param selectionArgs 对应的值 对应多少个问号就多少个值
     * @return
     */
    public List<Map> selectUser(String selection, String[] selectionArgs) {
        return select(MyDbHelper.TABLE_USERS, null, selection, selectionArgs, null, null, null);
    }

    /**
     * 只带一个约束条件和它（或它们）对应的属性值
     *
     * @param selection     字段名 如： " name = ? "、" name = ? AND num = ? "、" name = 'Tom' "
     * @param selectionArgs 对应的值 对应多少个问号就多少个值
     * @return
     */
    public List<Map> selectEvents(String selection, String[] selectionArgs) {
        return select(MyDbHelper.TABLE_EVENTS, null, selection, selectionArgs, null, null, null);
    }

    /**
     * @param selection     字段名 如： " name = ? "、" name = ? AND num = ? "、" name = 'Tom' "
     * @param selectionArgs 对应的值 对应多少个问号就多少个值
     * @param groupBy       按某字段名分组
     * @param having        按照某字段名分组的情况下，选择该字段的值为XXX值的一组
     * @param orderBy       排序
     * @return
     */
    public List<Map> selectUsers(String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return select(MyDbHelper.TABLE_USERS, null, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * @param selection     字段名 如： " name = ? "、" name = ? AND num = ? "、" name = 'Tom' "
     * @param selectionArgs 对应的值 对应多少个问号就多少个值
     * @param groupBy       按某字段名分组
     * @param having        按照某字段名分组的情况下，选择该字段的值为XXX值的一组
     * @param orderBy       排序
     * @return
     */
    public List<Map> selectEvents(String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return select(MyDbHelper.TABLE_USERS, null, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * 查找全部事件
     *
     * @return
     */
    public List<Map> selectAllEvents() {
        return selectAll(MyDbHelper.TABLE_EVENTS);
    }

    /**
     * 查找全部用户
     *
     * @return
     */
    public List<Map> selectAllUser() {
        return selectAll(MyDbHelper.TABLE_USERS);
    }

    private List<Map> selectAll(String tableName) {
        return select(tableName, null, null, null, null, null, null);
    }

    /**
     * 基础的 Select 方法
     *
     * @param table         表明
     * @param columns
     * @param selection     字段名 如： " name = ? "、" name = ? AND num = ? "、" name = 'Tom' "
     * @param selectionArgs 对应的值 对应多少个问号就多少个值
     * @param groupBy
     * @param having
     * @param orderBy
     * @return
     */
    public List<Map> select(String table, String[] columns, String selection,
                            String[] selectionArgs, String groupBy, String having,
                            String orderBy) {
        List<Map> mapList = new ArrayList<>();
        SQLiteDatabase database = null;
        /**
         * getType()返回的类型与对应的 int 值
         * int FIELD_TYPE_BLOB = 4;
         * int FIELD_TYPE_FLOAT = 2;
         * int FIELD_TYPE_INTEGER = 1;
         * int FIELD_TYPE_NULL = 0;
         * int FIELD_TYPE_STRING = 3;
         */
        Cursor cursor = null;
        try {
            database = helper.getReadableDatabase();
            /**
             * Query the given table, returning a {@link Cursor} over the result set.
             *
             * @param table The table name to compile the query against.
             * @param columns A list of which columns to return. Passing null will
             *            return all columns, which is discouraged to prevent reading
             *            data from storage that isn't going to be used.
             * @param selection A filter declaring which rows to return, formatted as an
             *            SQL WHERE clause (excluding the WHERE itself). Passing null
             *            will return all rows for the given table. 约束条件
             * @param selectionArgs You may include ?s in selection, which will be
             *         replaced by the values from selectionArgs, in order that they
             *         appear in the selection. The values will be bound as Strings. 约束条件参数
             * @param groupBy A filter declaring how to group rows, formatted as an SQL
             *            GROUP BY clause (excluding the GROUP BY itself). Passing null
             *            will cause the rows to not be grouped. 对某属性相等的行进行分组
             * @param having A filter declare which row groups to include in the cursor,
             *            if row grouping is being used, formatted as an SQL HAVING
             *            clause (excluding the HAVING itself). Passing null will cause
             *            all row groups to be included, and is required when row
             *            grouping is not being used.  选择对应属性值的分组
             * @param orderBy How to order the rows, formatted as an SQL ORDER BY clause
             *            (excluding the ORDER BY itself). Passing null will use the
             *            default sort order, which may be unordered. 排序方式
             * @return A {@link Cursor} object, which is positioned before the first entry. Note that
             * {@link Cursor}s are not synchronized, see the documentation for more details.
             * @see Cursor
             */
            cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
        return mapList;
    }

}
