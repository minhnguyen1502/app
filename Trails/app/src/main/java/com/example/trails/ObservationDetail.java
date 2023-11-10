package com.example.trails;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trails.database.DBHelper;
import com.example.trails.database.Observation;

import java.util.ArrayList;

public class ObservationDetail extends AppCompatActivity {

    private TextView o_observation, o_date, o_comment,o_id;
    private Button btnDelete, btnCancel;
    private DBHelper db;
    private ArrayList<Observation> obList = new ArrayList<>();
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observation_detail);
        o_observation = findViewById(R.id.tv_observation);
        o_id = findViewById(R.id.tv_id);
        o_date = findViewById(R.id.tv_date_of_time);
        o_comment = findViewById(R.id.tv_comment);
        btnDelete = findViewById(R.id.btn_delete);
        btnCancel = findViewById(R.id.btn_back);

        db = new DBHelper(this);
        Intent i = getIntent();
        id = i.getIntExtra("observationID", 0);
        loadById();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteObservation(id);
                Intent i = new Intent();
                i.putExtra("deleteById", id);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadById() {
        String query = "SELECT * FROM " + Observation.TABLE_NAME + " WHERE " +
                Observation.COLUMN_ID + " = " + id;
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                String observation = ""+cursor.getString(cursor.getColumnIndexOrThrow(Observation.COLUMN_NAME));
                String id = ""+cursor.getString(cursor.getColumnIndexOrThrow(Observation.COLUMN_ID));
                String date = ""+cursor.getString(cursor.getColumnIndexOrThrow(Observation.COLUMN_DATE));
                String comment = ""+cursor.getString(cursor.getColumnIndexOrThrow(Observation.COLUMN_COMMENT));
                o_observation.setText(observation);
                o_id.setText(id);
                o_date.setText(date);
                o_comment.setText(comment);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}