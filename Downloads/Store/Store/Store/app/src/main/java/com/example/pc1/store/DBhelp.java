package com.example.pc1.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelp extends SQLiteOpenHelper {
    String sql;

    public DBhelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int Version) {
        super(context, name, factory, Version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql = "CREATE TABLE chat (_id INTEGER PRIMARY KEY AUTOINCREMENT,  " + " 'NAME' TEXT, " + " 'content' TEXT, " + " 'img' TEXT, " + " 'room' TEXT, "+"'profile' TEXT,"+" 'read' TEXT,"+" 'time' TEXT);";
        db.execSQL(sql);
//        db.execSQL("INSERT INTO TEST VALUES(NULL, 'KIDS');");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS chat");
        db.execSQL(sql);

        onCreate(db);
    }
}