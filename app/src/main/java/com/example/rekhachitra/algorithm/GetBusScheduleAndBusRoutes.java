package com.example.rekhachitra.algorithm;

import android.util.Log;

import com.example.rekhachitra.dataEncapsulatorClass.RouteElement;
import com.example.rekhachitra.dataEncapsulatorClass.Time;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GetBusScheduleAndBusRoutes
{
    public Map<Short, List<Time>> getTime(InputStream inputStream)
    {
        Map<Short, List<Time>> timeGraph=new HashMap<>();
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
                List<Time> departure = new LinkedList<>();
                for (int i = 1; i < timeData.length - 1; i += 2) {
                    if(timeData[i].equals(""))
                        break;
                    hour = Byte.parseByte(timeData[i]);
                    minute = Byte.parseByte(timeData[i+1]);
                    departure.add(new Time(hour,minute));
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
    public Map<Short, List<RouteElement>> getRoute(InputStream inputStream)
    {
        Map<Short, List<RouteElement>> routeGraph = new HashMap<>();
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
                List<RouteElement> routeElement = new LinkedList<>();
                for (int i = 1; i < routeData.length - 1; i += 2)
                {

                    RouteElement element = new RouteElement();
                    element.location = routeData[i];
                    element.offset = Byte.parseByte(routeData[i+1]);
                    routeElement.add(element);
                }
                routeGraph.put(routeID, routeElement);



                short routeID1=(short) (Short.parseShort(routeData[0]) + 1000);
                List<RouteElement> routeElement1 = new LinkedList<>();
                offset = Byte.parseByte(routeData[routeData.length-1]);
                current=0;
                for(int i = routeData.length-1;i>1;i-=2)
                {

                    RouteElement element1 = new RouteElement();
                    element1.offset=(byte) (offset-Byte.parseByte(routeData[i])+current);
                    element1.location=routeData[i-1];
                    routeElement1.add(element1);
                }
                routeGraph.put(routeID1,routeElement1);
                Log.d("kalpeshRouteGraph", String.valueOf(routeID) + " = " + routeElement);
                Log.d("kalpeshRouteGraph", String.valueOf(routeID1) + " = " + routeElement1);


            }
            catch (IOException | CsvValidationException | NumberFormatException e)
            {
                throw new RuntimeException(e);
            }
        }
        //Log.d("kalpeshRouteGraph", routeGraph.toString());
        return routeGraph;
    }

    public Map<String,List<Short>> getReverseRoute (InputStream inputStream)
    {
        Map<String, List<Short>> reverse = new HashMap<>();
        String  string;
        try {
            string = IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CSVReader reader = new CSVReader(new StringReader(string));
        String[] routeData;
        while (true)
        {
            try
            {
                if ((routeData = reader.readNext()) == null)
                {
                    break;
                }
                String loc = routeData[0];
                List<Short> buses = new LinkedList<>();
                for (int i = 1;i<routeData.length;i++)
                {
                    buses.add(Short.parseShort(routeData[i]));
                }
                reverse.put(loc,buses);
                Log.d("kalpeshreverse",loc + buses);
            }
            catch (IOException | CsvValidationException | NumberFormatException e)
            {
                throw new RuntimeException(e);
            }
        }
        return reverse;
    }


    public void displayTimeAndRoute(Map<Short, Map<Byte, Byte>> timeGraph, Map<Short, List<RouteElement>> routeGraph)
    {
//        for(Map.Entry<Short, Map<Byte, Byte>> time : timeGraph.entrySet())
//        {
//            Log.d
//        }

        for(Map.Entry<Short, List<RouteElement>> route : routeGraph.entrySet())
        {
            Log.d("kalpehsDisplayRouteGraph", route.getKey() + "_______________________________________________________________" + route.getValue().size());
            for(int i = 0 ;i<route.getValue().size();i++)
            {
                Log.d("kalpehsDisplayRouteGraph", route.getValue().get(i).location + " : " + route.getValue().get(i).offset);
            }
        }
    }
}
