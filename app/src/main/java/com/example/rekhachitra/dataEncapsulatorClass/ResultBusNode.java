package com.example.rekhachitra.dataEncapsulatorClass;

import java.io.Serializable;

public class ResultBusNode implements Serializable {
    public short busId;
    public String source;
    public String destination;
    public byte sourceHour;
    public byte sourceMinute;
    public byte destinationHour;
    public byte destinationMinute;
    byte busStartHour,busStartMinute;

    public ResultBusNode(short busId, String source, String destination, byte sourceHour, byte sourceMinute, byte destinationHour, byte destinationMinute, byte busStartHour, byte busStartMinute) {
        this.busId = busId;
        this.source = source;
        this.destination = destination;
        this.sourceHour = sourceHour;
        this.sourceMinute = sourceMinute;
        this.destinationHour = destinationHour;
        this.destinationMinute = destinationMinute;
        this.busStartHour = busStartHour;
        this.busStartMinute = busStartMinute;
    }
}
