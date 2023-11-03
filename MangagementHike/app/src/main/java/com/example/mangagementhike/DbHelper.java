package com.example.mangagementhike;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "HikeManagement.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlHikeCreate = "CREATE TABLE IF NOT EXISTS " +
                "Hike(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name VARCHAR(200), " +
                "Location VARCHAR(200)," +
                "DateOfHike VARCHAR(200),"+
                "ParkingAvailable VARCHAR(200),"+
                "LengthTheHike VARCHAR(200),"+
                "LevelOfDifficulty VARCHAR(200),"+
                "Description VARCHAR(200)," +
                "Vehicle VARCHAR(200))" ;
        sqLiteDatabase.execSQL(sqlHikeCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
