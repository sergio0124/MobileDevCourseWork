package com.travelagencycoursework.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TravelAgency.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS user (\n" +
                "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "    login character(100) NOT NULL,\n" +
                "    password character(100) NOT NULL);\n");

        db.execSQL("CREATE TABLE trip (\n" +
                "    id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "    name character(100) NOT NULL,\n" +
                "    guidename character(100) NOT NULL,\n" +
                "    placename character(100) NOT NULL,\n" +
                "    price real NOT NULL,\n" +
                "    date long NOT NULL,\n" +
                "    username character(100) NOT NULL,\n" +
                "    userid integer NOT NULL,\n" +
                "    CONSTRAINT userfk FOREIGN KEY (userid)\n" +
                "    REFERENCES user(id) ON DELETE CASCADE);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public void deleteDatabase(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }
}
