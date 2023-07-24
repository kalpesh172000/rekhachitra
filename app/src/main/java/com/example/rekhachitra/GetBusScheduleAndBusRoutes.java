package com.example.rekhachitra;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetBusScheduleAndBusRoutes
{
    public Map<Short, Map<Byte, Byte>> getTime(InputStream inputStream)
    {
        Map<Short, Map<Byte, Byte>> timeGraph=new HashMap<>();
        String  string;
        try {
            string = IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CSVReader reader = new CSVReader(new StringReader(string));
        String[] timeData;
        Byte hour,minute;
        while (true)
        {
            try
            {
                if ((timeData = reader.readNext()) == null)
                {
                    break;
                }
                Short routeID=Short.parseShort(timeData[0]);
                Map<Byte, Byte> departure = new HashMap<>();
                for (int i = 1; i < timeData.length - 1; i += 2) {
                    if(timeData[i].equals(""))
                        break;
                    hour = Byte.parseByte(timeData[i]);
                    minute = Byte.parseByte(timeData[i+1]);
                    departure.put(hour, minute);
                }
                timeGraph.put(routeID, departure);
                Log.d("kalpeshTimeGraph", routeID + " = " + departure);
            } catch (IOException | CsvValidationException | NumberFormatException e)
            {
                throw new RuntimeException(e);
            }
        }
        //Log.d("kalpeshTimeGraph", timeGraph.toString());
        return timeGraph;
    }
    public Map<Short, List<routeElement>> getRoute(InputStream inputStream,ArrayList<String> locations)
    {
        Map<Short, List<routeElement>> routeGraph = new HashMap<>();
        String  string;
        try {
            string = IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CSVReader reader = new CSVReader(new StringReader(string));
        String[] routeData;
        byte offset,current;
        while (true)
        {
            try
            {
                if ((routeData = reader.readNext()) == null)
                {
                    break;
                }
                short routeID=Short.parseShort(routeData[0]);
                List<routeElement> routeElement = new LinkedList<>();
                for (int i = 1; i < routeData.length - 1; i += 2)
                {
                    if(!locations.contains(routeData[i]))
                        Log.d("kalpeshRouteError",routeData[i]);
                    routeElement element = new routeElement();
                    element.location = routeData[i];
                    element.offset = Byte.parseByte(routeData[i+1]);
                    routeElement.add(element);
                }
                routeGraph.put(routeID, routeElement);



                short routeID1=(short) (Short.parseShort(routeData[0]) + 1000);
                List<routeElement> routeElement1 = new LinkedList<>();
                offset = Byte.parseByte(routeData[routeData.length-1]);
                current=0;
                for(int i = routeData.length-1;i>1;i-=2)
                {
                    if(!locations.contains(routeData[i-1]))
                        Log.d("kalpeshRouteError",routeData[i-1]);
                    routeElement element1 = new routeElement();
                    element1.offset=(byte) (offset-Byte.parseByte(routeData[i])+current);
                    element1.location=routeData[i-1];
                    routeElement1.add(element1);
                }
                routeGraph.put(routeID1,routeElement1);
                Log.d("kalpeshRouteGraph", String.valueOf(routeID) + " = " + routeElement);
                Log.d("kalpeshRouteGraph", String.valueOf(routeID1) + " = " + routeElement1);


            } catch (IOException | CsvValidationException | NumberFormatException e)
            {
                throw new RuntimeException(e);
            }
        }
        //Log.d("kalpeshRouteGraph", routeGraph.toString());
        return routeGraph;
    }


    public void displayTimeAndRoute(Map<Short, Map<Byte, Byte>> timeGraph, Map<Short, List<routeElement>> routeGraph)
    {
//        for(Map.Entry<Short, Map<Byte, Byte>> time : timeGraph.entrySet())
//        {
//            Log.d
//        }

        for(Map.Entry<Short, List<routeElement>> route : routeGraph.entrySet())
        {
            Log.d("kalpehsDisplayRouteGraph", route.getKey() + "_______________________________________________________________" + route.getValue().size());
            for(int i = 0 ;i<route.getValue().size();i++)
            {
                Log.d("kalpehsDisplayRouteGraph", route.getValue().get(i).location + " : " + route.getValue().get(i).offset);
            }
        }
    }
}
