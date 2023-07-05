package com.example.rekhachitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class BusRouteScreen extends AppCompatActivity {

    ArrayList<BusStop> busStopArrayList;
    RecyclerView parentRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_route_screen);
        busStopArrayList = new ArrayList<>();
        busStopArrayList.add(new BusStop("sinnar",0));
        busStopArrayList.add(new BusStop("vavi ves ",5));
        busStopArrayList.add(new BusStop("bus stand sinnar",12));
        busStopArrayList.add(new BusStop("adva fata",15));
        busStopArrayList.add(new BusStop("sinnar mahavidyalay",20));
        busStopArrayList.add(new BusStop("s. t. colony",25));
        busStopArrayList.add(new BusStop("udyog bhavan",30));
        busStopArrayList.add(new BusStop("midc fata sarda",35));
        busStopArrayList.add(new BusStop("jindal fata",40));
        busStopArrayList.add(new BusStop("lnt fata",45));
        busStopArrayList.add(new BusStop("mohdari",50));
        busStopArrayList.add(new BusStop("pravara",52));
        busStopArrayList.add(new BusStop("toll naka",54));
        busStopArrayList.add(new BusStop("chincholi",56));
        busStopArrayList.add(new BusStop("shinde goan",58));
        busStopArrayList.add(new BusStop("palse",60));
        busStopArrayList.add(new BusStop("nashik road",62));



        AdapterBusStop adapterBusStop = new AdapterBusStop(this,busStopArrayList,9,15);
        parentRv = findViewById(R.id.parentRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        parentRv.setLayoutManager(linearLayoutManager);
        parentRv.setAdapter(adapterBusStop);
    }
}