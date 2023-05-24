package com.example.rekhachitra;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterResultBusCard extends RecyclerView.Adapter<AdapterResultBusCard.ViewHolder>
{

    private Activity activity;

    ArrayList<ResultBusCard> resultBusCardArrayList;

    public AdapterResultBusCard(Activity activity, ArrayList<ResultBusCard> resultBusCardArrayList) {
        this.activity = activity;
        this.resultBusCardArrayList = resultBusCardArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_card,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultBusCard resultBusCard = resultBusCardArrayList.get(position);

        AdapterResultBusNode adapterResultBusNode = new AdapterResultBusNode(resultBusCard.resultBusNodeArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        holder.nestedRv.setLayoutManager(linearLayoutManager);
        holder.nestedRv.setAdapter(adapterResultBusNode);
        holder.textViewTotalTime.setText(Integer.toString(resultBusCard.totalHour) + ":" + Integer.toString(resultBusCard.totalMinute));
    }

    @Override
    public int getItemCount() {
        return resultBusCardArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewTotalTime;

        RecyclerView nestedRv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTotalTime = itemView.findViewById(R.id.textViewTotalTime);
            nestedRv=itemView.findViewById(R.id.nestedRv);
        }
    }
}
