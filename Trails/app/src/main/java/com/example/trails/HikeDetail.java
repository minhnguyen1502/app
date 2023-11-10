package com.example.trails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trails.database.DBHelper;
import com.example.trails.database.Hike;

public class HikeDetail extends AppCompatActivity {

    public TextView h_id, h_name, h_location, h_date, h_length, h_level, h_description, h_parking,h_vehicle;
    public DBHelper db;
    public int id;
    public Button btnDelete, btnBack;
    public Hike hike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hike_detail);
        //
        h_id = findViewById(R.id.tv_id);
        h_name = findViewById(R.id.tv_name);
        h_location = findViewById(R.id.tv_location);
        h_date = findViewById(R.id.tv_date_of_hike);
        h_length = findViewById(R.id.tv_length_the_hike);
        h_level = findViewById(R.id.tv_level_of_difficulty);
        h_vehicle = findViewById(R.id.tv_vehicle);
        h_description = findViewById(R.id.tv_description);
        h_parking = findViewById(R.id.tv_parking_available);
        btnDelete = findViewById(R.id.btn_delete);
        btnBack = findViewById(R.id.btn_back);


        //
        db = new DBHelper(this);
        //
        Intent i = getIntent();
        id = i.getIntExtra("hikeID", 0);
        LoadDataByID();


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                db.deleteHike(id);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("deletedHikeId", id);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void LoadDataByID(){
        String query = "SELECT * FROM " + Hike.TABLE_NAME + " WHERE " +
                Hike.COLUMN_ID + " = " + id;
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                String id = ""+cursor.getString(cursor.getColumnIndexOrThrow(Hike.COLUMN_ID));
                String name = ""+cursor.getString(cursor.getColumnIndexOrThrow(Hike.COLUMN_NAME));
                String location = ""+cursor.getString(cursor.getColumnIndexOrThrow(Hike.COLUMN_LOCATION));
                String date = ""+cursor.getString(cursor.getColumnIndexOrThrow(Hike.COLUMN_DATE));
                String description = ""+cursor.getString(cursor.getColumnIndexOrThrow(Hike.COLUMN_DESCRIPTION));
                String level = ""+cursor.getString(cursor.getColumnIndexOrThrow(Hike.COLUMN_LEVEL));
                String vehicle = ""+cursor.getString(cursor.getColumnIndexOrThrow(Hike.COLUMN_VEHICLE));
                double length = Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(Hike.COLUMN_LENGTH)));
                int parking = cursor.getInt(cursor.getColumnIndexOrThrow(Hike.COLUMN_PARKING));
                h_id.setText(id);
                h_name.setText(name);
                h_location.setText(location);
                h_date.setText(date);
                h_description.setText(description);
                h_level.setText(level);
                h_vehicle.setText(vehicle);
                h_length.setText(String.valueOf(length));
                h_parking.setText(parking == 1 ? "Yes" : "No");
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}