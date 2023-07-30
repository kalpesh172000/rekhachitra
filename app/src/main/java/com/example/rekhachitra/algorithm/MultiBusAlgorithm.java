package com.example.rekhachitra.algorithm;


import android.util.Log;

import com.example.rekhachitra.dataEncapsulatorClass.PossiblePaths;
import com.example.rekhachitra.dataEncapsulatorClass.ResultBusCard;
import com.example.rekhachitra.dataEncapsulatorClass.ResultBusNode;
import com.example.rekhachitra.dataEncapsulatorClass.RouteElement;
import com.example.rekhachitra.dataEncapsulatorClass.Time;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class MultiBusAlgorithm
{
    Map<Short, List<Time>> busSchedule; //bus schedule like 146-6,0-6,30-7,0
    Map<Short, List<RouteElement>> busRoute; //bus route like 146-sinnar 0-nashik 4
    ArrayList<PossiblePaths> allPath; //all  possible paths from user source to user destination calculated with graph
    Map<String,List<Short>> reverse; // string,145,156,151
    String source;
    String destination;
    PossiblePaths currentPath;

    byte veryStartHour,veryStartMinute;

    public MultiBusAlgorithm(Map<Short, List<Time>> busSchedule, Map<Short, List<RouteElement>> busRoute, Map<String,List<Short>> reverse)
    {
        this.busSchedule = busSchedule;
        this.busRoute = busRoute;
        this.reverse=reverse;
    }

    /*public void  getResult(byte hour,byte minute,String source,String destination)
    {
        int i,j,k;


        for(Map.Entry<List<String>,Integer> path : allPath.entrySet())
        {
            for(i=0;i<path.getKey().size();i++)
            {
                String currentSource = path.getKey().get(i);
                String currentDestination = path.getKey().get(i+1);
                for(Map.Entry<Short, List<routeElement>> route : busRoute.entrySet())
                {
                    //check if bus route is correct route
                    for(j = 0 ;j<route.getValue().size();j++)
                    {
                        //Log.d("kalpehsDisplayRouteGraph", route.getValue().get(i).location +
                        // " : " + route.getValue().get(i).offset);
                    }
                }
            }
        }
    }*/

    //this will take input from user as listed in the arguments and return the result that will be passed to the ui
    public ArrayList<ResultBusCard> getResult(byte hour,byte minute,String source,String destination, ArrayList<PossiblePaths> allPath)
    {
        this.allPath=allPath;
        this.source=source;
        this.destination=destination;
        this.veryStartHour=hour;
        this.veryStartMinute=minute;
        PriorityQueue<ResultBusCard> resultBusCardArrayList = new PriorityQueue<>(5,new ResultBusCardComparator());
        for(int i = 0 ; i < allPath.size();i++)
        {
            Log.d("kalpeshresultbuscardcheck","size a errror");
            this.currentPath=allPath.get(i);
            resultBusCardArrayList = getResultBusCard(hour,minute,currentPath.path.get(0),currentPath.path.get(1),0,1,resultBusCardArrayList,new ArrayList<ResultBusNode>());
        }
        Log.d("kalpeshresultbuscardcheck","size1 errror");
        return new ArrayList<>(resultBusCardArrayList);

    }

    public PriorityQueue<ResultBusCard> getResultBusCard(byte cHour, byte cMinute,
                                                     String cSource, String cDestination,
                                                     int sourceIndex, int destinationIndex,
                                                     PriorityQueue<ResultBusCard> getResultBusCardArrayList,
                                                     ArrayList<ResultBusNode> resultBusNodeArrayList)
    {
        Log.d("kalpeshresultbuscardcheck","size getresult errror " + cSource + " : " + cDestination);
        int tempDestIndex=destinationIndex;
        int count;
        byte eHour,eMinute,edHour,edMinute,totalHour,totalMinute;
        byte cSourceOffset=-1,cDestinationOffset=-1;
        String dummyDestination=cDestination;
        byte dummyDestionationOffset=-1;
        int dummyDestionationIndex=destinationIndex;
        for (short cBusId : reverse.get(cSource))
        {
            Log.d("kalpeshresultbuscardcheck","size busid errror " + reverse.size() + " " + cBusId);
            cSourceOffset=-1;
            cDestinationOffset=-1;
            dummyDestionationOffset=-1;
            destinationIndex=tempDestIndex;
            for(RouteElement cRouteElement : Objects.requireNonNull(busRoute.get(cBusId)))
            {
                Log.d("kalpeshresultbuscardcheck","size RouteElement errror" + cRouteElement.location);
                if(Objects.equals(cRouteElement.location, cSource))
                    cSourceOffset=cRouteElement.offset;
                else if(Objects.equals(cRouteElement.location, cDestination))
                {
                    Log.d("kalpeshresultbuscardcheck","size destionation exits errror " + cRouteElement.location + " : " + cDestination);
                    cDestinationOffset = cRouteElement.offset;
                    dummyDestination=cDestination;
                    dummyDestionationOffset=cDestinationOffset;
                    dummyDestionationIndex=destinationIndex;
                    destinationIndex++;
                    if(cDestination != this.destination && currentPath.path.size()!=destinationIndex)
                    {
                        cDestination=currentPath.path.get(destinationIndex);
                    }
                    else
                    {
                        destinationIndex--;
                        break;
                    }
                }
                if(cSourceOffset == -1 && dummyDestionationOffset > -1)
                    break;
            }
            if(cSourceOffset != -1 && dummyDestionationOffset != -1)
            {
                count = 0;
                short tempBusId = (short) ((short) cBusId%1000);
                List<Time> timeArrayList = (List<Time>) busSchedule.get(tempBusId);
                if(timeArrayList == null)
                    Log.d("kalpeshresultbuscardcheck","size Busschedule null errror" );
                if(timeArrayList.size() == 0)
                    Log.d("kalpeshresultbuscardcheck","size Busschedule size 0 errror" );
                for(Time time : timeArrayList)
                {
                    Log.d("kalpeshresultbuscardcheck","size time errror " + time.hour + " : " + time.minute);
                    eMinute = (byte) (time.minute + cSourceOffset);
                    if(eMinute > 59)
                    {
                        eMinute = (byte) (eMinute - 60);
                        eHour = (byte) (time.hour + 1);
                    }
                    else
                    {
                        eHour = time.hour;
                    }
                    if(eHour-cHour < 0)
                        continue;
                    if(eHour == cHour && (eMinute - cMinute) < 3) // i want 3 minute uncertainty
                        continue;
                    if(eHour > cHour && (eMinute + (60 - cMinute)) < 3) // checks same condition in different form
                        continue;
                    if(count < 5)
                    {
                        Log.d("kalpeshresultbuscardcheck","size recursion errror " + time.hour + " : " + time.minute);
                        count++;
                        edMinute = (byte) (time.minute + dummyDestionationOffset);
                        if(edMinute > 59)
                        {
                            edMinute = (byte) (edMinute - 60);
                            edHour = (byte) (time.hour + 1);
                        }
                        else
                            edHour = time.hour;
                        resultBusNodeArrayList.add(new ResultBusNode(cBusId,cSource,dummyDestination,eHour,eMinute,edHour,edMinute,time.hour,time.minute));
                        if(Objects.equals(cDestination, this.destination))
                        {
                            Log.d("kalpeshresultbuscardcheck","size same destionation errror");
                            totalHour = (byte) (edHour-veryStartHour);
                            totalMinute = (byte) (edMinute-veryStartMinute);
                            if(totalMinute<0)
                            {
                                totalMinute = (byte) (60 + totalMinute);
                                totalHour--;
                            }
                            getResultBusCardArrayList.add(new ResultBusCard(totalHour, totalMinute,resultBusNodeArrayList));
                            if(getResultBusCardArrayList.size() == 0)
                                Log.d("kalpeshresultbuscardcheck","size 3 errror");
                            else
                                Log.d("kalpeshresultbuscardcheck","size 4 errror");

                            displayResult(new ArrayList<>(getResultBusCardArrayList));
                        }
                        else
                        {
                            getResultBusCard(edHour,edMinute,dummyDestination,cDestination,dummyDestionationIndex,dummyDestionationIndex+1,getResultBusCardArrayList,resultBusNodeArrayList);
                        }
                        resultBusNodeArrayList.remove(resultBusNodeArrayList.size()-1);
                        //this will contain the recursive call for utmost* 5 times ;
                        //also will add and remove bus config from result.
                    }
                    if(count == 5)
                        break;
                }

            }

        }
        return getResultBusCardArrayList;
    }
    public void displayResult(ArrayList<ResultBusCard> resultBusCards)
    {
        if(resultBusCards.size()==0)
            Log.d("kalpeshresultbuscardcheck","size 2 errror");
        if(resultBusCards == null)
            Log.d("kalpeshresultbuscardcheck","null errror");

        for(ResultBusCard resultBusCard : resultBusCards)
        {
            Log.d("kalpeshresultbuscardcheck",resultBusCard.totalHour + " : " + resultBusCard.totalMinute);
            for (ResultBusNode resultBusNode : resultBusCard.resultBusNodeArrayList)
            {
                Log.d("kalpeshresultbuscardcheck",resultBusNode.busId + " : " + resultBusNode.source + " : " + resultBusNode.destination + " : " + resultBusNode.sourceHour + " : " + resultBusNode.sourceMinute + " : " + resultBusNode.destinationHour + " : " + resultBusNode.destinationMinute + " : " + resultBusNode.busStartHour + " : " + resultBusNode.busStartMinute);
            }
            Log.d("kalpeshresultbuscardcheck","------------------------------------------------------");
        }

    }
}
