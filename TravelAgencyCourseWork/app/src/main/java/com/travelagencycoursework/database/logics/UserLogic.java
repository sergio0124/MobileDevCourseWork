package com.travelagencycoursework.database.logics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.travelagencycoursework.database.DatabaseHelper;
import com.travelagencycoursework.database.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserLogic {

    Context context;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "user";
    final String COLUMN_ID = "id";
    final String COLUMN_LOGIN = "login";
    final String COLUMN_PASSWORD = "password";

    public UserLogic(Context context) {
        this.context = context;
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public UserLogic open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<UserModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<UserModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            UserModel obj = new UserModel();

            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setLogin(cursor.getString((int) cursor.getColumnIndex(COLUMN_LOGIN)));
            obj.setPassword(cursor.getString((int) cursor.getColumnIndex(COLUMN_PASSWORD)));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public UserModel getElement(UserModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_LOGIN + " = '" + model.getLogin()+"'", null);
        if(cursor == null){
            return null;
        }
        UserModel obj = new UserModel();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
        obj.setLogin(cursor.getString((int) cursor.getColumnIndex(COLUMN_LOGIN)));
        obj.setPassword(cursor.getString((int) cursor.getColumnIndex(COLUMN_PASSWORD)));
        return obj;
    }

    public void insert(UserModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_LOGIN, model.getLogin());
        content.put(COLUMN_PASSWORD, model.getPassword());
        db.insert(TABLE, null, content);
    }

    public void update(UserModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_LOGIN, model.getLogin());
        content.put(COLUMN_PASSWORD, model.getPassword());
        String where = COLUMN_ID + " = " + model.getId();
        db.update(TABLE, content, where, null);
    }

    public void delete(UserModel model) {
        String where = COLUMN_ID + " = " + model.getId();
        db.delete(TABLE, where, null);
        TripLogic customerLogic = new TripLogic(context);
        customerLogic.deleteByUserId(model.getId());
    }

    public void deleteDatabase(Context context){
        sqlHelper.deleteDatabase(context);
    }
}
