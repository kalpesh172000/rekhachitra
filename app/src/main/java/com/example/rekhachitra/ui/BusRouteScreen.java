package com.example.rekhachitra.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rekhachitra.R;
import com.example.rekhachitra.dataEncapsulatorClass.RouteElement;

import java.util.ArrayList;

public class BusRouteScreen extends AppCompatActivity {

    ArrayList<RouteElement> routeElementArrayList;
    RecyclerView parentRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_route_screen);
        routeElementArrayList = new ArrayList<>();
        routeElementArrayList.add(new RouteElement("sinnar",(byte)0));
        routeElementArrayList.add(new RouteElement("vavi ves ",(byte)5));
        routeElementArrayList.add(new RouteElement("bus stand sinnar",(byte)12));
        routeElementArrayList.add(new RouteElement("adva fata",(byte)15));
        routeElementArrayList.add(new RouteElement("sinnar mahavidyalay",(byte)20));
        routeElementArrayList.add(new RouteElement("s. t. colony",(byte)25));
        routeElementArrayList.add(new RouteElement("udyog bhavan",(byte)30));
        routeElementArrayList.add(new RouteElement("midc fata sarda",(byte)35));
        routeElementArrayList.add(new RouteElement("jindal fata",(byte)40));
        routeElementArrayList.add(new RouteElement("lnt fata",(byte)45));
        routeElementArrayList.add(new RouteElement("mohdari",(byte)50));
        routeElementArrayList.add(new RouteElement("pravara",(byte)52));
        routeElementArrayList.add(new RouteElement("toll naka",(byte)54));
        routeElementArrayList.add(new RouteElement("chincholi",(byte)56));
        routeElementArrayList.add(new RouteElement("shinde goan",(byte)58));
        routeElementArrayList.add(new RouteElement("palse",(byte)60));
        routeElementArrayList.add(new RouteElement("nashik road",(byte)62));



        AdapterBusStop adapterBusStop = new AdapterBusStop(this,routeElementArrayList,(byte)9,(byte)15);
        parentRv = findViewById(R.id.parentRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        parentRv.setLayoutManager(linearLayoutManager);
        parentRv.setAdapter(adapterBusStop);
    }
}