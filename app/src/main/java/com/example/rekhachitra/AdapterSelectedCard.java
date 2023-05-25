package com.example.rekhachitra;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSelectedCard extends RecyclerView.Adapter<AdapterSelectedCard.ViewHolder>
{
    private Activity activity;
    ArrayList<ResultBusNode> resultBusNodeArrayList;

    RecyclerViewClickListener recyclerViewClickListener;

    public AdapterSelectedCard(Activity activity, ArrayList<ResultBusNode> resultBusNodeArrayList, RecyclerViewClickListener recyclerViewClickListener) {
        this.activity = activity;
        this.resultBusNodeArrayList = resultBusNodeArrayList;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_card,parent,false);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewBusId,textViewSource,textViewDestination;
        TextView textViewSourceTime,textViewDestinationTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBusId = itemView.findViewById(R.id.textViewBusId);
            textViewSource = itemView.findViewById(R.id.textViewSource);
            textViewDestination = itemView.findViewById(R.id.textViewDestination);
            textViewSourceTime = itemView.findViewById(R.id.textViewSourceTime);
            textViewDestinationTime = itemView.findViewById(R.id.textViewDestinationTime);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Log.d("kalpeshselected","working");
            recyclerViewClickListener.recyclerViewListClicked(getAdapterPosition());
        }
    }
}
