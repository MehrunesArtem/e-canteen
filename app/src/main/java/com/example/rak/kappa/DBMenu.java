package com.example.rak.kappa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBMenu {

    private static final String DATABASE_NAME = "menuDishes.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "tableDishes";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PRICE = "Price";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final String COLUMN_CATEGORY = "Category";
    private static final String COLUMN_TIME = "Time";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_PRICE = 2;
    private static final int NUM_COLUMN_DESCRIPTION = 3;
    private static final int NUM_COLUMN_CATEGORY = 4;
    private static final int NUM_COLUMN_TIME = 5;

    private SQLiteDatabase mDataBase;

    public DBMenu(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(int id, String name, double price, String Description, String category, int time) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_DESCRIPTION, Description);
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_TIME, time);

        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Dish d) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, d.getName());
        cv.put(COLUMN_PRICE, d.getPrice());
        cv.put(COLUMN_DESCRIPTION, d.getDescription());
        cv.put(COLUMN_CATEGORY, d.getCategory());
        cv.put(COLUMN_TIME, d.getTime());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(d.getId())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Dish select(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        mCursor.moveToFirst();
        String Name = mCursor.getString(NUM_COLUMN_NAME);
        Double Price = mCursor.getDouble(NUM_COLUMN_PRICE);
        String Description = mCursor.getString(NUM_COLUMN_DESCRIPTION);
        String category = mCursor.getString(NUM_COLUMN_CATEGORY);
        int time = mCursor.getInt(NUM_COLUMN_TIME);
        return new Dish(id, Name, Price, Description, category, time);
    }

    public ArrayList<Dish> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Dish> arr = new ArrayList<Dish>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String Name = mCursor.getString(NUM_COLUMN_NAME);
                Double Price = mCursor.getDouble(NUM_COLUMN_PRICE);
                String Description = mCursor.getString(NUM_COLUMN_DESCRIPTION);
                String category = mCursor.getString(NUM_COLUMN_CATEGORY);
                int time = mCursor.getInt(NUM_COLUMN_TIME);
                arr.add(new Dish(id, Name, Price, Description, category, time));
            } while (mCursor.moveToNext());
        }
        return arr;
    }


    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_PRICE + " FLOAT, " +
                    COLUMN_DESCRIPTION + " TEXT, "+ COLUMN_CATEGORY + " TEXT, " + COLUMN_TIME + " INTEGER)"
                    ;
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}