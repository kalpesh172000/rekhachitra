package com.example.rekhachitra;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterResultBusNode extends RecyclerView.Adapter<AdapterResultBusNode.ViewHolder>
{

    ArrayList<ResultBusNode> resultBusNodeArrayList;

    public AdapterResultBusNode(ArrayList<ResultBusNode> resultBusNodeArrayList) {
        this.resultBusNodeArrayList = resultBusNodeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_card_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultBusNode resultBusNode = resultBusNodeArrayList.get(position);
        holder.textViewBusId.setText(Integer.toString(resultBusNode.busId));
        holder.textViewSource.setText(resultBusNode.source);
        holder.textViewDestination.setText(resultBusNode.destination);
        holder.textViewSourceTime.setText(Integer.toString(resultBusNode.sourceHour) + ":" + Integer.toString(resultBusNode.sourceMinute));
        holder.textViewDestinationTime.setText(Integer.toString(resultBusNode.destinationHour) + ":" + Integer.toString(resultBusNode.destinationMinute));
    }

    @Override
    public int getItemCount() {
        return resultBusNodeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewBusId,textViewSource,textViewDestination;
        TextView textViewSourceTime,textViewDestinationTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBusId = itemView.findViewById(R.id.textViewBusId);
            textViewSource = itemView.findViewById(R.id.textViewSource);
            textViewDestination = itemView.findViewById(R.id.textViewDestination);
            textViewSourceTime = itemView.findViewById(R.id.textViewSourceTime);
            textViewDestinationTime = itemView.findViewById(R.id.textViewDestinationTime);

        }
    }

}
