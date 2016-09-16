package com.example.albertlee.weathertalk.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.albertlee.weathertalk.utils.DbHelper;

/**
 * Created by AlbertLee on 2016/9/3 17:32
 */
public class Dao_Today {
    private DbHelper helper;
    private String data;

    public Dao_Today(Context context) {
        helper = new DbHelper(context);
    }

    public String query(String table,String name, int raw) {
        SQLiteDatabase sqldb = helper.getReadableDatabase();
        Cursor cursor = sqldb.query(table, new String[]{name}, "_id=?", new String[]{String.valueOf(raw)}, null, null, null);
        if (cursor.moveToNext()) {
            data = cursor.getString(cursor.getColumnIndex(name));
        }
        cursor.close();
        sqldb.close();
        return data;
    }
}
