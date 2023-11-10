package com.example.trails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trails.activity.AddHike;
import com.example.trails.adapter.HikeAdapter;
import com.example.trails.database.DBHelper;
import com.example.trails.database.Hike;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainHike extends AppCompatActivity {

    private FloatingActionButton btnPlus;
    private RecyclerView rcvHike;
    private HikeAdapter hikeAdapter;
    private DBHelper db;
    private ArrayList<Hike> hikeList = new ArrayList<>();
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_hike);

        actionBar = getSupportActionBar();
        btnPlus = findViewById(R.id.btnPlus);
        rcvHike = findViewById(R.id.rcvHike);
        //
        db = new DBHelper(this);
        //
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainHike.this, AddHike.class);
                startActivityForResult(i, 0);
            }
        });
        refreshHikeList();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void refreshHikeList() {
        hikeList.clear();
        hikeList.addAll(db.getAllHike());
        hikeAdapter = new HikeAdapter(this, db.getAllHike(), MainHike.this);
        rcvHike.setAdapter(hikeAdapter);
        hikeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshHikeList();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            refreshHikeList();
        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int deletedHikeId = data.getIntExtra("deletedHikeId", -1);
            if (deletedHikeId != -1) {
                // Xóa mục tương ứng từ danh sách hikeList
                for (Hike hike : hikeList) {
                    if (hike.getId() == deletedHikeId) {
                        hikeList.remove(hike);
                        break;
                    }
                }
                hikeAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            refreshHikeList();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_top_menu, menu);
        //get search item from menu
        MenuItem item = menu.findItem(R.id.searchHike);
        //search area
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchHike(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchHike(query);
                return true;
            }
        });
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.deleteAllHike) {
            db.deleteAllHike();
            Toast.makeText(this, "Delete All!!", Toast.LENGTH_SHORT).show();
            onResume();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void searchHike(String query) {
        ArrayList<Hike> searchResults = db.getSearchHike(query);
        hikeAdapter = new HikeAdapter(this, searchResults, MainHike.this);
        rcvHike.setAdapter(hikeAdapter);
        hikeAdapter.notifyDataSetChanged();
    }

}