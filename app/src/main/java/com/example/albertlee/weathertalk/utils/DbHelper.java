package com.example.albertlee.weathertalk.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by AlbertLee on 2016/9/2 0:05
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String CREATE_TALBE_FUTURE ="CREATE TABLE future(_id INTEGER PRIMARY KEY ,temperature TEXT,weather TEXT,fa TEXT,fb TEXT,wind TEXT,week TEXT,date TEXT)";
    private static final String CREATE_TALBE_TODAY = "CREATE TABLE today(_id INTEGER PRIMARY KEY,city TEXT,weather TEXT,fa TEXT,fb TEXT,temp INTEGER,wind_direction TEXT,wind_strength TEXT,humidity TEXT,temperature TEXT,uv_index TEXT,exercise_index TEXT,dressing_advice TEXT,date_y TEXT,time TEXT)";

    public DbHelper(Context context) {
        super(context, "weatherStorage.db",null, 1);
    }

    public void deleteTable(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS today");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS future");
        Log.i("数据表清空：","完成");
        onCreate(sqLiteDatabase);
        Log.i("数据表重建：","完成");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TALBE_TODAY);
        sqLiteDatabase.execSQL(CREATE_TALBE_FUTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
