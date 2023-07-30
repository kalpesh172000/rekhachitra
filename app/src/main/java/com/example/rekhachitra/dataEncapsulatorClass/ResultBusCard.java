package com.example.rekhachitra.dataEncapsulatorClass;

import com.example.rekhachitra.dataEncapsulatorClass.ResultBusNode;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultBusCard implements Serializable {
    byte totalHour,totalMinute;
    public ArrayList<ResultBusNode> resultBusNodeArrayList;

    public ResultBusCard(byte totalHour, byte totalMinute, ArrayList<ResultBusNode> resultBusNodes) {
        this.totalHour = totalHour;
        this.totalMinute = totalMinute;
        this.resultBusNodeArrayList = resultBusNodes;
    }
}
