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
import java.util.Map;

public class MultiBusAlgorithm
{

    CSVReader reader;


    public void printGraph(Map<String, Map<String, Integer>> graph) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
            String key = entry.getKey();
            Map<String, Integer> value = entry.getValue();
            sb.append(key + ": " + value.toString() + "\n");
        }
        Log.d("kalpesh1Graph", sb.toString());
    }


    public void printPaths(ArrayList<LinkedList<String>> paths) {
        Log.d("kalpesh2Graph", "here");
        for (LinkedList<String> path : paths) {
                Log.d("kalpesh2Graph", path.toString());
        }
    }

    Map<String, Map<String, Integer>> createGraphFromCSV(Map<String, Map<String, Integer>> graph,InputStream inputStream)
    {

        String  string;
        try {
            string = IOUtils.toString(inputStream);
            Log.d("kalpeshGraph1", string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reader=new CSVReader(new StringReader(string));
        String[] nodeData;
        while (true)
        {
            try
            {
                if ((nodeData = reader.readNext()) == null)
                {
                    Log.d("kalpesh3Graph", "this was end");
                    break;
                }

                String source = nodeData[0];
                Map<String, Integer> neighbors = new HashMap<>();

                for (int i = 1; i < nodeData.length - 1; i += 2) {
                    String destination = nodeData[i];
                    if(destination.equals(""))
                        break;
                    Log.d("kalpesh4Graph", destination);
                    int cost = Integer.parseInt(nodeData[i + 1]);
                    neighbors.put(destination, cost);
                }

                graph.put(source, neighbors);


            } catch (IOException | CsvValidationException | NumberFormatException e)
            {
                throw new RuntimeException(e);
            }
        }


        return graph;
    }

    public ArrayList<LinkedList<String>> findAllPaths(Map<String, Map<String, Integer>> graph, String src, String dest) {
        ArrayList<LinkedList<String>> result = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        path.add(src);
        findAllPathsHelper(graph, src, dest, path, result);
        return result;
    }

    private void findAllPathsHelper(Map<String, Map<String, Integer>> graph, String src, String dest,
                                    LinkedList<String> path, ArrayList<LinkedList<String>> result){
        if (src.equals(dest)) {
            result.add(new LinkedList<>(path));
            return;
        }
        Map<String, Integer> neighbors = graph.get(src);
        if (neighbors==null)
            Log.d("kalpesh3Graph", "this graph was end" + src);
        for (String neighbor : neighbors.keySet()) {
            if (!path.contains(neighbor)) {
                path.add(neighbor);
                findAllPathsHelper(graph, neighbor, dest, path, result);
                path.removeLast();
            }
        }
    }



//    public void getPossibleRoutes(String source , String destination , int hour , int minute , InputStream inputStream)
    public void getPossibleRoutes(InputStream inputStream)
    {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph=createGraphFromCSV(graph,inputStream);
        printGraph(graph);
        ArrayList<LinkedList<String>> paths= findAllPaths(graph,"Sinnar Phata","K K Wagh");
        printPaths(paths);
    }
}
