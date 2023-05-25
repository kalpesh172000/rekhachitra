package com.example.rekhachitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class PossibleRoutesOutput extends AppCompatActivity{

    AdapterResultBusCard adapterResultBusCard;

    ArrayList<ResultBusCard> resultBusCardArrayList;

    ArrayList<ResultBusNode> resultBusNodeArrayList;

    RecyclerView parentRv;


    //--------------------------
    short busId;
    String source,destination;
    byte sourceHour,sourceMinute;
    byte destinationHour,destinationMinute;
    byte busStartHour,busStartMinute;

    byte veryStartHour,veryStartMinute,veryEndHour,veryEndMinute,totalHour,totalMinute;
    //--------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.possible_routes_output);


        resultBusCardArrayList=new ArrayList<>();

        resultBusNodeArrayList = new ArrayList<>();
        //---one bus
        busId=146;
        source = "Udyog Bhavan";
        destination = "NashikRoad";
        sourceHour=8;
        sourceMinute=54;
        destinationHour=9;
        destinationMinute=25;
        busStartHour=8;
        busStartMinute=45;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryStartHour = sourceHour;
        veryStartMinute = sourceMinute;

        //---one bus end
        //---one bus
        busId=245;
        source = "NashikRoad";
        destination = "Aurangabad Naka";
        sourceHour=9;
        sourceMinute=35;
        destinationHour=10;
        destinationMinute=5;
        busStartHour=9;
        busStartMinute=30;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));
        //---one bus end
        //---one bus
        busId=152;
        source = "Aurangabad Naka";
        destination = "K. K. Wagh College";
        sourceHour=10;
        sourceMinute=13;
        destinationHour=10;
        destinationMinute=17;
        busStartHour=10;
        busStartMinute=0;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryEndHour = destinationHour;
        veryEndMinute = destinationMinute;
        //---one bus end

        totalHour= (byte) (veryEndHour-veryStartHour);
        totalMinute= (byte) (veryEndMinute-veryStartMinute);
        if(totalMinute<0)
        {
            totalMinute= (byte) (60+totalMinute);
            totalHour--;
        }
        resultBusCardArrayList.add(new ResultBusCard(totalHour,totalMinute,resultBusNodeArrayList));


        resultBusNodeArrayList = new ArrayList<>();
        //---one bus
        busId=146;
        source = "Udyog Bhavan";
        destination = "Nimani";
        sourceHour=8;
        sourceMinute=54;
        destinationHour=10;
        destinationMinute=15;
        busStartHour=8;
        busStartMinute=45;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryStartHour = sourceHour;
        veryStartMinute = sourceMinute;

        //---one bus end
        //---one bus
        busId=152;
        source = "Nimani";
        destination = "K. K. Wagh College";
        sourceHour=10;
        sourceMinute=20;
        destinationHour=10;
        destinationMinute=27;
        busStartHour=10;
        busStartMinute=10;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryEndHour = destinationHour;
        veryEndMinute = destinationMinute;
        //---one bus end

        totalHour= (byte) (veryEndHour-veryStartHour);
        totalMinute= (byte) (veryEndMinute-veryStartMinute);
        if(totalMinute<0)
        {
            totalMinute= (byte) (60+totalMinute);
            totalHour--;
        }
        resultBusCardArrayList.add(new ResultBusCard(totalHour,totalMinute,resultBusNodeArrayList));


        resultBusNodeArrayList = new ArrayList<>();
        //---one bus
        busId=146;
        source = "Udyog Bhavan";
        destination = "Nimani";
        sourceHour=8;
        sourceMinute=54;
        destinationHour=10;
        destinationMinute=15;
        busStartHour=8;
        busStartMinute=45;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryStartHour = sourceHour;
        veryStartMinute = sourceMinute;

        //---one bus end
        //---one bus
        busId=152;
        source = "Nimani";
        destination = "K. K. Wagh College";
        sourceHour=10;
        sourceMinute=20;
        destinationHour=10;
        destinationMinute=27;
        busStartHour=10;
        busStartMinute=10;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryEndHour = destinationHour;
        veryEndMinute = destinationMinute;
        //---one bus end

        totalHour= (byte) (veryEndHour-veryStartHour);
        totalMinute= (byte) (veryEndMinute-veryStartMinute);
        if(totalMinute<0)
        {
            totalMinute= (byte) (60+totalMinute);
            totalHour--;
        }
        resultBusCardArrayList.add(new ResultBusCard(totalHour,totalMinute,resultBusNodeArrayList));


        resultBusNodeArrayList = new ArrayList<>();
        //---one bus
        busId=146;
        source = "Udyog Bhavan";
        destination = "Nimani";
        sourceHour=8;
        sourceMinute=54;
        destinationHour=10;
        destinationMinute=15;
        busStartHour=8;
        busStartMinute=45;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryStartHour = sourceHour;
        veryStartMinute = sourceMinute;

        //---one bus end
        //---one bus
        busId=152;
        source = "Nimani";
        destination = "K. K. Wagh College";
        sourceHour=10;
        sourceMinute=20;
        destinationHour=10;
        destinationMinute=27;
        busStartHour=10;
        busStartMinute=10;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryEndHour = destinationHour;
        veryEndMinute = destinationMinute;
        //---one bus end

        totalHour= (byte) (veryEndHour-veryStartHour);
        totalMinute= (byte) (veryEndMinute-veryStartMinute);
        if(totalMinute<0)
        {
            totalMinute= (byte) (60+totalMinute);
            totalHour--;
        }
        resultBusCardArrayList.add(new ResultBusCard(totalHour,totalMinute,resultBusNodeArrayList));


        resultBusNodeArrayList = new ArrayList<>();
        //---one bus
        busId=146;
        source = "Udyog Bhavan";
        destination = "Nimani";
        sourceHour=8;
        sourceMinute=54;
        destinationHour=10;
        destinationMinute=15;
        busStartHour=8;
        busStartMinute=45;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryStartHour = sourceHour;
        veryStartMinute = sourceMinute;

        //---one bus end
        //---one bus
        busId=152;
        source = "Nimani";
        destination = "K. K. Wagh College";
        sourceHour=10;
        sourceMinute=20;
        destinationHour=10;
        destinationMinute=27;
        busStartHour=10;
        busStartMinute=10;
        resultBusNodeArrayList.add(new ResultBusNode(busId, source, destination, sourceHour, sourceMinute, destinationHour, destinationMinute, busStartHour, busStartMinute));

        veryEndHour = destinationHour;
        veryEndMinute = destinationMinute;
        //---one bus end

        totalHour= (byte) (veryEndHour-veryStartHour);
        totalMinute= (byte) (veryEndMinute-veryStartMinute);
        if(totalMinute<0)
        {
            totalMinute= (byte) (60+totalMinute);
            totalHour--;
        }
        resultBusCardArrayList.add(new ResultBusCard(totalHour,totalMinute,resultBusNodeArrayList));


        adapterResultBusCard = new AdapterResultBusCard(this, resultBusCardArrayList, new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(int position) {
//                Toast.makeText(PossibleRoutesOutput.this,
//                        resultBusCardArrayList.get(position).resultBusNodeArrayList.get(position).source,
//                        Toast.LENGTH_SHORT).show();
                ResultBusCard resultBusCard = resultBusCardArrayList.get(position);
                Intent intent = new Intent(PossibleRoutesOutput.this,SelectedOutputScreen.class);
                intent.putExtra("resultBusCard" , resultBusCard);
                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        parentRv = findViewById(R.id.parentRv);
        parentRv.setLayoutManager(linearLayoutManager);
        parentRv.setAdapter(adapterResultBusCard);
    }
}