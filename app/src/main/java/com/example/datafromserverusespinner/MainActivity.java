package com.example.datafromserverusespinner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.DnsResolver;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity  implements  View.OnClickListener {

    Spinner spinner;
    List<String> mylist = new ArrayList<String>();
    List<ServiceData> my_services_list;
    ArrayAdapter<String> spinnerAdapter;
    Button btnDatePicker, btnTimePicker,booked1;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDatePicker=findViewById(R.id.btn_date);
        btnTimePicker=findViewById(R.id.btn_time);
        txtDate=findViewById(R.id.in_date);
        txtTime=findViewById(R.id.in_time);
        booked1 = findViewById(R.id.booked);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        spinner =findViewById(R.id.servicesname);

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mylist);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyInterface.JSONURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyInterface api = retrofit.create(MyInterface.class);
        Call<List<ServiceData>> call = api.get_services();

        call.enqueue(new Callback<List<ServiceData>>() {
            @Override
            public void onResponse(Call<List<ServiceData>> call, Response<List<ServiceData>> response) {
                Toast.makeText(MainActivity.this, "spinnner", Toast.LENGTH_SHORT).show();
                my_services_list = new ArrayList<>(response.body());
                for (int i=0;i<my_services_list.size();i++){
                    mylist.add(my_services_list.get(i).getServicesName());
                }
                spinner.setAdapter(spinnerAdapter);
            }

            @Override
            public void onFailure(Call<List<ServiceData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public void onClickBooked(View view) {
        Intent intent =new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }
}


