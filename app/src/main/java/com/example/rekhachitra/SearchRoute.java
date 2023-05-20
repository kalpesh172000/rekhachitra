package com.example.rekhachitra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class SearchRoute extends AppCompatActivity {

    EditText editTextSource,editTextDestination,editTextTime;
    RadioButton radioButtonCurrent,radioButtonCustom;
    Button buttonFindRoute;

    TimePickerDialog timePickerDialog;

    int hour,minute;

    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_route);

        editTextSource=findViewById(R.id.editTextSource);
        editTextDestination=findViewById(R.id.editTextDestination);
        editTextTime=findViewById(R.id.editTextTime);
        radioButtonCurrent=findViewById(R.id.radioButtonCurrent);
        radioButtonCustom=findViewById(R.id.radioButtonCustom);
        buttonFindRoute=findViewById(R.id.buttonFindRoute);


        radioButtonCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextTime.setVisibility(View.INVISIBLE);
            }
        });


        radioButtonCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextTime.setVisibility(View.VISIBLE);
            }
        });


        //default values that will be set to timepickerdialog when it opens first time
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        //when editTime gets focus when user clicks on editText
        editTextTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus)
                {
                    timePickerDialog = new TimePickerDialog(SearchRoute.this,
                    (tp, sHour, sMinute) -> {
                        editTextTime.setText(sHour + ":" + sMinute);
                        //saves the time for later use
                        hour=sHour;
                        minute=sMinute;
                        },
                    hour,
                    minute,
                    true);
                    timePickerDialog.show();
                }

            }
        });


        //this activates when user clicks on the editText when edittext already has focus
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(SearchRoute.this,
                        (tp, sHour, sMinute) -> {
                            editTextTime.setText(sHour + ":" + sMinute);
                            //saves the time for later use
                            hour=sHour;
                            minute=sMinute;
                        },
                        hour,
                        minute,
                        true);
                timePickerDialog.show();
            }
        });




    }
}