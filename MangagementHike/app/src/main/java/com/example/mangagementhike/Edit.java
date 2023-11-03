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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class Edit extends AppCompatActivity {

    private EditText edtName, edtLocation,edtDate, edtLength,edtLevel,edtDescription,edtVehicle;
    private RadioGroup rgParking;
    private RadioButton rbYes, rbNo;
    private Button btnSave, btnCancel,btnDelete ;
    private AccessData accessData;
    private String parking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        accessData = new AccessData(Edit.this);
        //Lay du lieu tu main
        Intent intent = getIntent();
        Hike hike = (Hike) intent.getSerializableExtra("DATA"); //chuoi nhan lai gia tri tu main gui sang
        //anh xa du lieu
        AnhXa();

        //lay du lieu day len view
        edtName.setText(hike.getName());
        edtLocation.setText(hike.getLocation());
        edtDate.setText(hike.getDate_of_hike());
        edtLength.setText(hike.getLength_the_hike());
        edtLevel.setText(hike.getLevel_of_difficulty());
        edtDescription.setText(hike.getDescription());
        edtVehicle.setText(hike.getVehicle());


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hike.setName(edtName.getText().toString());
                hike.setLocation(edtLocation.getText().toString());
                hike.setDate_of_hike(edtDate.getText().toString());
                hike.setLength_the_hike(edtLength.getText().toString());
                hike.setLevel_of_difficulty(edtLevel.getText().toString());
                hike.setDescription(edtDescription.getText().toString());
                hike.setVehicle(edtVehicle.getText().toString());
                hike.setParking_available(parking);
                accessData.EditHike(hike);
                Toast.makeText(getApplicationContext(),"Save Successfull",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Edit.this, MainActivity.class);
                startActivity(intent);

            }
        });

        rgParking.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rb_yes){
                    parking = "Yes";
                }else {
                    parking = "No";
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accessData.DeleteHike(hike);
                Toast.makeText(getApplicationContext(),"Delete Successfull",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Edit.this, MainActivity.class);
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
        btnSave = (Button) findViewById(R.id.btn_save);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnDelete =(Button) findViewById(R.id.btn_delete);
        edtDate.setOnClickListener(view -> {
            Add.MyDatePicker dlg = new Add.MyDatePicker();
            dlg.setDateField(edtDate);
            dlg.show(getSupportFragmentManager(), "Hike date!");
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