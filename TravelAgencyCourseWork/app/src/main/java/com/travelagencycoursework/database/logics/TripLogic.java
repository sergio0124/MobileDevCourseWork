package com.travelagencycoursework.database.logics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.travelagencycoursework.database.DatabaseHelper;
import com.travelagencycoursework.database.models.TripModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TripLogic {

    Context context;
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final int MILI = 1000;
    final String TABLE = "trip";
    final String COLUMN_ID = "id";
    final String COLUMN_NAME = "name";
    final String GUIDE_NAME = "guidename";
    final String GUIDE_ID = "guideid";
    final String PLACE_NAME = "placename";
    final String PLACE_ID = "placeid";
    final String USER_ID = "userid";
    final String USER_NAME = "username";
    final String COLUMN_DATE = "date";

    public TripLogic(Context context) {
        this.context = context;
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public TripLogic open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<TripModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<TripModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            TripModel obj = new TripModel();

            Calendar date = new GregorianCalendar();
            date.setTimeInMillis(cursor.getLong((int) cursor.getColumnIndex(COLUMN_DATE))* MILI);

            obj.setDate(date);
            obj.setGuideId(cursor.getInt((int) cursor.getColumnIndex(GUIDE_ID)));
            obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
            obj.setGuideName(cursor.getString((int) cursor.getColumnIndex(GUIDE_NAME)));
            obj.setPlaceId(cursor.getInt((int) cursor.getColumnIndex(PLACE_ID)));
            obj.setPlaceName(cursor.getString((int) cursor.getColumnIndex(PLACE_NAME)));
            obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
            obj.setUserId(cursor.getInt((int) cursor.getColumnIndex(GUIDE_ID)));
            obj.setUserLogin(cursor.getString((int) cursor.getColumnIndex(USER_NAME)));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public TripModel getElement(TripModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.getId(), null);
        TripModel obj = new TripModel();
        if (!cursor.moveToFirst()) {
            return null;
        }
        Calendar date = new GregorianCalendar();
        date.setTimeInMillis(cursor.getLong((int) cursor.getColumnIndex(COLUMN_DATE)));

        obj.setDate(date);
        obj.setGuideId(cursor.getInt((int) cursor.getColumnIndex(GUIDE_ID)));
        obj.setId(cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID)));
        obj.setGuideName(cursor.getString((int) cursor.getColumnIndex(GUIDE_NAME)));
        obj.setPlaceId(cursor.getInt((int) cursor.getColumnIndex(PLACE_ID)));
        obj.setPlaceName(cursor.getString((int) cursor.getColumnIndex(PLACE_NAME)));
        obj.setName(cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME)));
        obj.setUserId(cursor.getInt((int) cursor.getColumnIndex(GUIDE_ID)));
        obj.setUserLogin(cursor.getString((int) cursor.getColumnIndex(USER_NAME)));
        return obj;
    }

    public void insert(TripModel model) {
        ContentValues content = new ContentValues();

        long date = model.getDate().getTimeInMillis() / MILI;
        content.put(COLUMN_DATE,date);
        content.put(COLUMN_NAME,model.getName());
        content.put(COLUMN_ID,model.getId());
        content.put(GUIDE_ID,model.getGuideId());
        content.put(GUIDE_NAME,model.getGuideName());
        content.put(PLACE_ID,model.getPlaceId());
        content.put(PLACE_NAME,model.getPlaceName());
        content.put(USER_ID,model.getUserId());
        content.put(USER_NAME,model.getUserName());

        db.insert(TABLE,null,content);
    }

    public void update(TripModel model) {
        ContentValues content=new ContentValues();

        long date = model.getDate().getTimeInMillis() / MILI;
        content.put(COLUMN_DATE,date);
        content.put(COLUMN_NAME,model.getName());
        content.put(COLUMN_ID,model.getId());
        content.put(GUIDE_ID,model.getGuideId());
        content.put(GUIDE_NAME,model.getGuideName());
        content.put(PLACE_ID,model.getPlaceId());
        content.put(PLACE_NAME,model.getPlaceName());
        content.put(USER_ID,model.getUserId());
        content.put(USER_NAME,model.getUserName());

        String where = COLUMN_ID + " = " + model.getId();
        db.update(TABLE,content,where,null);
    }

    public void delete(TripModel model) {
        String where = COLUMN_ID+" = "+model.getId();
        db.delete(TABLE,where,null);
    }

    public void deleteByUserId(int userId){
        String where = USER_ID+" = "+userId;
        db.delete(TABLE,where,null);
    }
}
