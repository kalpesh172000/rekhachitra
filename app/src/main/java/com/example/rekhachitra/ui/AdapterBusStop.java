package com.example.rekhachitra.ui;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.rekhachitra.R;
import com.example.rekhachitra.dataEncapsulatorClass.RouteElement;

import java.util.ArrayList;

public class AdapterBusStop extends RecyclerView.Adapter<AdapterBusStop.ViewHolder> {

    Activity activity;

    ArrayList<RouteElement> routeElementArrayList;

    byte startHour,startMinute , hour, minute , currentHour, currentMinute;

    android.icu.util.Calendar calendar = Calendar.getInstance();

    public AdapterBusStop(Activity activity, ArrayList<RouteElement> routeElementArrayList, byte  startHour, byte startMinute) {
        this.activity = activity;
        this.routeElementArrayList = routeElementArrayList;
        this.startHour = startHour;
        this.startMinute = startMinute;
        currentHour = (byte)calendar.get(android.icu.util.Calendar.HOUR_OF_DAY);
        currentMinute = (byte)calendar.get(android.icu.util.Calendar.MINUTE);
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
        RouteElement routeElement = routeElementArrayList.get(position);
        minute = routeElement.offset;
        minute = (byte) ((byte)startMinute + minute);
        hour = startHour;
        if(minute>59)
        {
            hour++;
            minute= (byte) (minute-60);
        }
        holder.textViewOffset.setText(Integer.toString(hour) + ":" + Integer.toString(minute));
        holder.textViewName.setText(routeElement.location);
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
    public int getItemCount()
    {
        return routeElementArrayList.size();
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
