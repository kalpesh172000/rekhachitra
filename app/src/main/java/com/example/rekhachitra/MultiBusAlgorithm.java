package com.example.rekhachitra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MultiBusAlgorithm
{
    Map<Short, Map<Byte, Byte>> busSchedule; //bus schedule like 146-6,0-6,30-7,0
    Map<Short, List<routeElement>> busRoute; //bus route like 146-sinnar 0-nashik 4
    ArrayList<String> locations; //array of all locations
    Map<List<String>,Integer> allPath; //all  possible paths from user source to user destination calculated with graph

    public MultiBusAlgorithm(Map<Short, Map<Byte, Byte>> busSchedule, Map<Short, List<routeElement>> busRoute, ArrayList<String> locations /*, Map<List<String>, Integer> allPath*/)
    {
        this.busSchedule = busSchedule;
        this.busRoute = busRoute;
        this.locations = locations;
        //this.allPath = allPath;
    }

    /*public void  getResult(byte hour,byte minute,String source,String destination)
    {
        int i,j,k;


        for(Map.Entry<List<String>,Integer> path : allPath.entrySet())
        {
            for(i=0;i<path.getKey().size();i++)
            {
                String currentSource = path.getKey().get(i);
                String currentDestination = path.getKey().get(i+1);
                for(Map.Entry<Short, List<routeElement>> route : busRoute.entrySet())
                {
                    //check if bus route is correct route
                    for(j = 0 ;j<route.getValue().size();j++)
                    {
                        //Log.d("kalpehsDisplayRouteGraph", route.getValue().get(i).location +
                        // " : " + route.getValue().get(i).offset);
                    }
                }
            }
        }
    }*/

    //this will take input from user as listed in the arguments and return the result that will be passed to the ui
    public void  getResult(byte hour,byte minute,String source,String destination, Map<List<String>, Integer> allPath)
    {
        this.allPath=allPath;
        for(Map.Entry<List<String>,Integer> path : allPath.entrySet())
        {
        }

    }
}
