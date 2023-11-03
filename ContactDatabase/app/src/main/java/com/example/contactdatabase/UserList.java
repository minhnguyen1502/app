package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name, email, date;
    DBHelper DB;
    userAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        DB = new DBHelper(this);
        name = new ArrayList<>();
        email = new ArrayList<>();
        date = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new userAdapter(this, name, email, date);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount()==0){
            Toast.makeText(UserList.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                name.add(cursor.getString(0));
                email.add(cursor.getString(1));
                date.add(cursor.getString(2));
            }
        }
    }
}