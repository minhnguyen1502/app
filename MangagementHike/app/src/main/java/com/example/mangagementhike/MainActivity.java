package com.example.mangagementhike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvHike;
    FloatingActionButton btnPlus;
    private List<Hike> hikeList;
    private HikeAdapter adapter;
    private AccessData accessData;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home");

        btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Add.class);
                startActivity(intent);
            }
        });


        // Ánh xạ
        lvHike = (ListView) findViewById(R.id.lv_hikes);
        hikeList = new ArrayList<Hike>();
        accessData = new AccessData(MainActivity.this);
        hikeList = accessData.AllHikes();

        adapter = new HikeAdapter(getApplicationContext(),hikeList);
        lvHike.setAdapter(adapter);
        CRUD_Listview();


    }

    private void CRUD_Listview() {
        lvHike.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hike hike = hikeList.get(i);
                Intent intent = new Intent(MainActivity.this, Edit.class); // main la man hinh 1 edit la man hinh 2
                intent.putExtra("DATA",hike);   //putExtra dung de truyen du lieu sang
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_add){
            Intent intent = new Intent(MainActivity.this,Add.class);
            startActivity(intent);
        }
        if (id == R.id.menu_exit){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onResume() {
        super.onResume();
        hikeList.clear();
        hikeList.addAll(accessData.AllHikes());
        adapter.notifyDataSetChanged();

    }
}