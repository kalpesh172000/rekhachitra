package com.example.rekhachitra;

public class ResultBusNode {
    short busId;
    String source,destination;
    byte sourceHour,sourceMinute;
    byte destinationHour,destinationMinute;
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
