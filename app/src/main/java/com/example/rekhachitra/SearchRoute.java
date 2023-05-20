package com.example.rekhachitra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.graphics.drawable.ColorDrawable;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

public class SearchRoute extends AppCompatActivity {

    EditText editTextTime;

    EditText editTextSource, editTextDestination;
    RadioButton radioButtonCurrent,radioButtonCustom;
    Button buttonFindRoute;

    TimePickerDialog timePickerDialog;

    int hour,minute;

    Dialog dialog;

    Calendar calendar = Calendar.getInstance();

    CSVReader reader = null;

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


        //reading csv file and extracting locations data to put into autocomplete
        InputStream inputStreams = getResources().openRawResource(R.raw.sampletest);
        String  string;
        try {
            string = IOUtils.toString(inputStreams);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader=new CSVReader(new StringReader(string));
        String[] nodeData;
        ArrayList<String> locations = new ArrayList<String>();

        while (true)
        {
            try
            {
                if ((nodeData = reader.readNext()) == null) break;
                locations.add(nodeData[0]);

            } catch (IOException | CsvValidationException e)
            {
                throw new RuntimeException(e);
            }
        }


        //open a searchable spinner dialog
        editTextSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize dialog
                dialog=new Dialog(SearchRoute.this);

                // set custom dialog
                dialog.setContentView(R.layout.searchable_location_spinner);

                // set custom height and width
                dialog.getWindow().setLayout(650,800);

                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<String> adapter=new ArrayAdapter<>(SearchRoute.this, android.R.layout.simple_list_item_1,locations);

                // set adapter
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }

                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        editTextSource.setText(adapter.getItem(position));
                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });


        editTextDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize dialog
                dialog=new Dialog(SearchRoute.this);

                // set custom dialog
                dialog.setContentView(R.layout.searchable_location_spinner);

                // set custom height and width
                dialog.getWindow().setLayout(650,800);

                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<String> adapter=new ArrayAdapter<>(SearchRoute.this, android.R.layout.simple_list_item_1,locations);

                // set adapter
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }

                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        editTextDestination.setText(adapter.getItem(position));
                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });
        //autocomplete code end

        radioButtonCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextTime.setVisibility(View.INVISIBLE);
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
            }
        });


        //default values that will be set to timepicker dialog when it opens first time
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);


        radioButtonCustom.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextTime.setVisibility(View.VISIBLE);
                editTextTime.setText(hour + ":" + minute);
            }
        });

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
                        editTextTime.clearFocus();
                        },
                    hour,
                    minute,
                    true);
                    timePickerDialog.show();
                }
                editTextTime.clearFocus();

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
                            editTextTime.clearFocus();
                        },
                        hour,
                        minute,
                        true);
                timePickerDialog.show();
                editTextTime.clearFocus();
            }
        });




    }
}