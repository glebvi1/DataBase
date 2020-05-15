package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Literature";
    public static final String DATABASE_TABLE = "Books";
    public static final int  DATABASE_VERSIONS = 1;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_AUTHOR = "author";



    public OpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSIONS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE  + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_AUTHOR + "TEXT NOT NULL, " +
                COLUMN_YEAR + " INTEGER NOT NULL); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("");
    }
}
