package com.example.trails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trails.activity.AddObservation;
import com.example.trails.adapter.ObservationAdapter;
import com.example.trails.database.DBHelper;
import com.example.trails.database.Observation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainObservation extends AppCompatActivity {
    private FloatingActionButton  btnPlus, btnBack;
    private RecyclerView rcvObservation;
    private ObservationAdapter observationAdapter;
    private DBHelper db;
    private ArrayList<Observation> obList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_observation);
        //
        btnPlus = findViewById(R.id.btnPlus);
        btnBack = findViewById(R.id.back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rcvObservation = findViewById(R.id.rcvObservation);
        //
        db = new DBHelper(this);
        //
        Intent intent = getIntent();
        int hikeID = intent.getIntExtra("hikeID", -1);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(MainObservation.this, AddObservation.class);
                i.putExtra("hikeID", hikeID); // Chuyển hikeID khi thêm quan sát
                startActivityForResult(i, 0);
            }
        });
        refreshObservationList();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refreshObservationList() {
        obList.clear();
        Intent intent = getIntent();
        int hikeID = intent.getIntExtra("hikeID", -1);
        if (hikeID != -1) {
            // Sử dụng hikeID để truy vấn các quan sát liên quan đến chuyến dã ngoại này
            ArrayList<Observation> observationList = db.getObservationsForHike(hikeID);
            observationAdapter = new ObservationAdapter(this, observationList, this);
            rcvObservation.setAdapter(observationAdapter);
            observationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK){
            refreshObservationList();
        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            refreshObservationList();
        }
        if (requestCode == 2 && resultCode == RESULT_OK){
            int deleteObservationId = data.getIntExtra("deleteById", -1);
            if (deleteObservationId != -1){
                for (Observation observation : obList){
                    if (observation.getId() == deleteObservationId){
                        obList.remove(observation);
                        break;
                    }
                }
                refreshObservationList();
            }
        }
    }
}