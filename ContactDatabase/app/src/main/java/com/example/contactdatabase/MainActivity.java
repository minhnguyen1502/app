package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,email,date;
    Button save, view, picture;
    ImageView img;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    name = findViewById(R.id.edtName);
    email = findViewById(R.id.edtEmail);
    date = findViewById(R.id.edtDateofBirth);
    save = findViewById(R.id.btnSave);
    view = findViewById(R.id.btnView);

    DB = new DBHelper(this);

    view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, UserList.class));
        }
    });

    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String txtName = name.getText().toString();
            String txtEmail = email.getText().toString();
            String txtDate = date.getText().toString();

            Boolean isSaveData = DB.insertuserdata(txtName,txtEmail,txtDate);

            if (isSaveData==true){
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }
}