package com.example.mangagementhike;

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
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class Add extends AppCompatActivity {

    private EditText edtName, edtLocation, edtDate, edtLength, edtLevel, edtDescription, edtVehicle;
    private RadioGroup rgParking;
    private Button btnAdd, btnCancel;
    private String parking;
    AccessData accessData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //anh xa
        AnhXa();
        accessData = new AccessData(Add.this);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rgParking.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_yes) {
                    parking = "Yes";
                } else {
                    parking = "No";
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String location = edtLocation.getText().toString();
                String date = edtDate.getText().toString();
                String length = edtLength.getText().toString();
                String level = edtLevel.getText().toString();
                String description = edtDescription.getText().toString();
//                String risk = edtRisk.getText().toString();
                String vehicle = edtVehicle.getText().toString();
//                String estiamtedTime = edtEstimatedTime.getText().toString();
                Hike hike = new Hike(name, location, date, parking, length, level, description, vehicle);
                accessData.AddHike(hike);
                Toast.makeText(getApplicationContext(), "Add Hike Successfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        edtName = (EditText) findViewById(R.id.edt_name);
        edtLocation = (EditText) findViewById(R.id.edt_location);
        edtDate = (EditText) findViewById(R.id.edt_date);
        edtLength = (EditText) findViewById(R.id.edt_length);
        edtLevel = (EditText) findViewById(R.id.edt_level);
        edtDescription = (EditText) findViewById(R.id.edt_description);
        edtVehicle = (EditText) findViewById(R.id.edt_vehicle);
        rgParking = (RadioGroup) findViewById(R.id.rg_parking);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        edtDate.setOnClickListener(view -> {
            MyDatePicker dlg = new MyDatePicker();
            dlg.setDateField(edtDate);
            dlg.show(getSupportFragmentManager(), "Hike Date!");
        });
    }

    public static class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
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

        private EditText dateField;

        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String dateReturn = selectedDay + "/" + (selectedMonth + 1) + "/"
                    + selectedYear;
            dateField.setText(dateReturn);

        }
    }
}