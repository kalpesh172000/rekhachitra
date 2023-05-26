package com.example.rekhachitra;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.icu.util.Calendar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBusStop extends RecyclerView.Adapter<AdapterBusStop.ViewHolder> {

    Activity activity;

    ArrayList<BusStop> busStopArrayList;

    int startHour,startMinute , hour, minute , currentHour, currentMinute;

    android.icu.util.Calendar calendar = Calendar.getInstance();

    public AdapterBusStop(Activity activity, ArrayList<BusStop> busStopArrayList, int startHour, int startMinute) {
        this.activity = activity;
        this.busStopArrayList = busStopArrayList;
        this.startHour = startHour;
        this.startMinute = startMinute;
        currentHour = calendar.get(android.icu.util.Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(android.icu.util.Calendar.MINUTE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_stop,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusStop busStop = busStopArrayList.get(position);
        minute = busStop.offset;
        minute = startMinute + minute;
        hour = startHour;
        if(minute>59)
        {
            hour++;
            minute=minute-60;
        }
        holder.textViewOffset.setText(Integer.toString(hour) + ":" + Integer.toString(minute));
        holder.textViewName.setText(busStop.name);
        if(hour>currentHour || (hour==currentHour && minute>currentMinute))
        {
            Log.d("kalpeshtime",currentHour+ " " + currentMinute + " blue");
            holder.imageViewCheck.setImageResource(R.drawable.baseline_check_green);
        }
        else
        {
            Log.d("kalpeshtime",currentHour+ " " + currentMinute+ " green");
            holder.imageViewCheck.setImageResource(R.drawable.baseline_check_purple);
        }
    }

    @Override
    public int getItemCount() {
        return busStopArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageViewCheck;
        TextView textViewName,textViewOffset;

        ConstraintLayout containerParent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCheck=itemView.findViewById(R.id.imageViewCheck);
            textViewName=itemView.findViewById(R.id.textViewName);
            textViewOffset=itemView.findViewById(R.id.textViewOffset);
            containerParent=itemView.findViewById(R.id.containerParent);
        }
    }
}
