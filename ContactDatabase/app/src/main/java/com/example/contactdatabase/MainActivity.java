package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText name,email,date;
    Button save, view;
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
        date.setOnClickListener(view -> {
            MyDatePicker dlg = new MyDatePicker();
            dlg.setDateField(date);
            dlg.show(getSupportFragmentManager(), " Date!");
        });
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
    public static class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        private EditText dateField;
        public void setDateField(EditText dateField) {
            this.dateField = dateField;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            if (dateField.getText().length() != 0) {
                String date = dateField.getText().toString();
                String[] separated = date.split("/");
                int year = Integer.parseInt(separated[2]);
                int month = Integer.parseInt(separated[1]);
                int day = Integer.parseInt(separated[0]);
                return new DatePickerDialog(getActivity(), this, year, month - 1, day);
            } else {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(getActivity(), this, year, month, day);
            }
            // Create a new instance of DatePickerDialog and return it
        }
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String dateReturn = selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear;
            dateField.setText(dateReturn);
        }
    }
}