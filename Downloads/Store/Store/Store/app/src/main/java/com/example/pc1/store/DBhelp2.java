package com.example.pc1.store;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBhelp2 extends SQLiteOpenHelper {
    String sql2;

    public DBhelp2(Context context, String name, SQLiteDatabase.CursorFactory factory, int Version) {
        super(context, name, factory, Version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql2 = "CREATE TABLE chat2 (_id INTEGER PRIMARY KEY AUTOINCREMENT,  " + " 'ID' TEXT,"+" 'ROOM' TEXT,"+" 'entrance' TEXT);";
        db.execSQL(sql2);
//        db.execSQL("INSERT INTO TEST VALUES(NULL, 'KIDS');");


    }

    //예를들어서 내가입장할경우 존 1050505 입장 을 sqlite에 저장
    //존이 입장해있는동안에는 계속 읽음표시처리
    //만약 존이 나가면 존 105505 나감을 sqlite에 저장
    //그럼 그이후로는 계속 안읽음 처리
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS chat");
        db.execSQL(sql2);

        onCreate(db);
    }

}
