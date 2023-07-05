package com.example.rekhachitra;

import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MultipathGraph
{
    int vertices = 0;
    ArrayList<String> busStop=new ArrayList<>();

    Map<String, Map<String, Integer>> createGraphFromCSV(Map<String, Map<String, Integer>> graph,InputStream inputStream)
    {

        String  string;
        try {
            string = IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CSVReader reader = new CSVReader(new StringReader(string));
        String[] nodeData;
        while (true)
        {
            try
            {
                if ((nodeData = reader.readNext()) == null)
                {
                    break;
                }
                vertices++;
                String source = nodeData[0];
                busStop.add(source);
                Map<String, Integer> neighbors = new HashMap<>();
                for (int i = 1; i < nodeData.length - 1; i += 2) {
                    String destination = nodeData[i];
                    if(destination.equals(""))
                        break;
                    int cost = Integer.parseInt(nodeData[i + 1]);
                    neighbors.put(destination, cost);
                }
                graph.put(source, neighbors);
            } catch (IOException | CsvValidationException | NumberFormatException e)
            {
                throw new RuntimeException(e);
            }
        }
        return graph;
    }


    Map<List<String>,Integer> findAllPaths(String source,
                                           String destination,
                                           Map<String, Map<String, Integer>> graph)
    {
        Map<List<String>,Integer> result = new HashMap<>();
        boolean[] isVisited = new boolean[vertices];
        int distance=0;
        ArrayList<String> pathList= new ArrayList<>();
        pathList.add(source);

        return findAllPathsUtil(source,destination,isVisited,pathList,distance,result,graph);
    }


    Map<List<String>,Integer> findAllPathsUtil(String source, String destination,
                                               boolean[] isVisited,
                                               List<String> localpathList,
                                               int distance,
                                               Map<List<String>,Integer> result,
                                               Map<String, Map<String, Integer>> graph)
    {
        if(source.equals(destination))
        {
            System.out.print(localpathList + " : " + distance + "\n");
            result.put(new ArrayList<>(localpathList),distance);
            return result;
        }

        isVisited[busStop.indexOf(source)]=true;

        Map<String, Integer> currentNode;
        currentNode=graph.get(source);
        for(Map.Entry<String, Integer> loc: currentNode.entrySet())
        {
            if(!isVisited[busStop.indexOf(loc.getKey())])
            {
                String key = loc.getKey();
                boolean isBranch = graph.get(key).size()>2;
                if(isBranch || Objects.equals(key, destination)
                        || Objects.equals(key, "Nimani")
                        || Objects.equals(key, "Tapovan")
                        || Objects.equals(key, "Nashik Road")
                        || Objects.equals(key, "New CBS")
                        || Objects.equals(key, "Sinnar Phata")
                        || Objects.equals(key, "Ozar")
                        || Objects.equals(key, "MET"))
                {
                    localpathList.add(key);
                    distance=distance+loc.getValue();
                    findAllPathsUtil(key,destination,isVisited,localpathList,distance,result,graph);
                    distance=distance-loc.getValue();
                    localpathList.remove(key);
                }
                else
                {
                    distance=distance+loc.getValue();
                    findAllPathsUtil(key,destination,isVisited,localpathList,distance,result,graph);
                    distance=distance-loc.getValue();
                }
            }
        }
        isVisited[busStop.indexOf(source)]=false;
        return result;
    }


    void printAllPaths(Map<List<String>,Integer> result)
    {
        for(Map.Entry<List<String>,Integer> entry : result.entrySet())
        {
            Log.d("kalpeshAllPossiblePaths", "\n" + entry.getKey() + " : " + entry.getValue());
        }
    }


    void getAllCombination(String source, String destination,Map<List<String>,Integer> result)
    {
        byte currentMinute;
        byte currentHour;
    }


    public void getPossibleRoutes(String source, String destination, InputStream inputStream)
    {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph=createGraphFromCSV(graph,inputStream);
        Map<List<String>,Integer> result= findAllPaths(source,destination,graph);
        printAllPaths(result);
    }
}
