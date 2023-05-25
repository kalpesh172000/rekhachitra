package com.example.rekhachitra;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultBusCard implements Serializable {
    byte totalHour,totalMinute;
    ArrayList<ResultBusNode> resultBusNodeArrayList;

    public ResultBusCard(byte totalHour, byte totalMinute, ArrayList<ResultBusNode> resultBusNodes) {
        this.totalHour = totalHour;
        this.totalMinute = totalMinute;
        this.resultBusNodeArrayList = resultBusNodes;
    }
}
