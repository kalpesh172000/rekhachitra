package com.example.rekhachitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectedOutputScreen extends AppCompatActivity {

    AdapterSelectedCard adapterSelectedCard;
    ArrayList<ResultBusNode> resultBusNodeArrayList;
    RecyclerView parentRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_output_screen);
        ResultBusCard resultBusCard =  (ResultBusCard)getIntent().getSerializableExtra("resultBusCard");

        resultBusNodeArrayList=resultBusCard.resultBusNodeArrayList;


        adapterSelectedCard = new AdapterSelectedCard(this,resultBusNodeArrayList , new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(int position) {
                Toast.makeText(SelectedOutputScreen.this,
                        resultBusNodeArrayList.get(position).destination,
                        Toast.LENGTH_SHORT).show();


//                ResultBusNode resultBusNode = resultBusCard.resultBusNodeArrayList.get(position);
//                Intent intent = new Intent(SelectedOutputScreen.this,BusRoute.class);
//                intent.putExtra("resultBusCard" , resultBusCard);
//                startActivity(intent);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        parentRv = findViewById(R.id.parentRv);
        parentRv.setLayoutManager(linearLayoutManager);
        parentRv.setAdapter(adapterSelectedCard);
    }
}