package com.example.mangagementhike;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AccessData {

    //DAO (DATA ACCESS OBJECT) Co nhiem vu truy van du lieu
    private DbHelper db;
    Context context;
    public AccessData(Context context){
        db = new DbHelper(context);
    }
    //get all hikes
    public List<Hike> AllHikes(){
        String sql ="SELECT * FROM Hike";
        List<Hike> hikeList = new ArrayList<Hike>();
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String location = cursor.getString(2);
            String date_of_hike = cursor.getString(3);
            String parking_available = cursor.getString(4);
            String length_the_hike = cursor.getString(5);
            String level_of_difficulty = cursor.getString(6);
            String description = cursor.getString(7);
            String vehicle = cursor.getString(8);
            Hike hike = new Hike(id, name, location, date_of_hike, parking_available, length_the_hike,
                    level_of_difficulty,description,vehicle);
            hikeList.add(hike);
            cursor.moveToNext();
        }
        return hikeList;
    }
    //get 1 hike
    //add new hike
    public void AddHike(Hike hike){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", hike.getName());
        values.put("Location", hike.getLocation());
        values.put("DateOfHike", hike.getDate_of_hike());
        values.put("ParkingAvailable", hike.getParking_available());
        values.put("LengthTheHike", hike.getLength_the_hike());
        values.put("LevelOfDifficulty", hike.getLevel_of_difficulty());
        values.put("Description", hike.getDescription());
        values.put("Vehicle", hike.getVehicle());
        database.insert("Hike","",values);
        String sql = "INSERT INTO Hike() VALUES ";

    }
    //Edit hike

    public void EditHike(Hike hike){
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", hike.getName());
        values.put("Location", hike.getLocation());
        values.put("DateOfHike", hike.getDate_of_hike());
        values.put("ParkingAvailable", hike.getParking_available());
        values.put("LengthTheHike", hike.getLength_the_hike());
        values.put("LevelOfDifficulty", hike.getLevel_of_difficulty());
        values.put("Description", hike.getDescription());
        values.put("Vehicle", hike.getVehicle());
        String idHike = String.valueOf(hike.getId());
        String idHikes[] = {idHike};
        database.update("Hike",values,"id=?",idHikes);

    }
    //Delete hike
    public void DeleteHike(Hike hike){
        SQLiteDatabase database = db.getWritableDatabase();
        String idHike = String.valueOf(hike.getId());
        database.delete("Hike","id=?", new String[]{String.valueOf(idHike)});
    }

}
