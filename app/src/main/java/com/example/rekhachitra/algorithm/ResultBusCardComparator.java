package com.example.rekhachitra.algorithm;

import com.example.rekhachitra.dataEncapsulatorClass.ResultBusCard;

import java.util.Comparator;

public class ResultBusCardComparator implements Comparator<ResultBusCard>
{

    @Override
    public int compare(ResultBusCard resultBusCard, ResultBusCard t1)
    {
        byte r1Hour = resultBusCard.totalHour;
        byte r1Minute = resultBusCard.totalMinute;
        byte r2Hour = t1.totalHour;
        byte r2Minute = t1.totalMinute;
        if(r1Hour > r2Hour)
            return -1;
        else if (r1Hour < r2Hour)
            return 1;
        else if (r1Minute > r2Minute)
            return -1;
        else
            return 1;
    }
}
