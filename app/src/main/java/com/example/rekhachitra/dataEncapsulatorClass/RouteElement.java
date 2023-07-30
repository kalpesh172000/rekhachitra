package com.example.rekhachitra.dataEncapsulatorClass;

import java.io.Serializable;

public class RouteElement implements Serializable
{
    public String location;
    public Byte offset;

    public RouteElement(String location, Byte offset) {
        this.location = location;
        this.offset = offset;
    }
    public RouteElement()
    {
         byte i;
    }
    public String getKey()
    {
        return location;
    }
    public Byte getValue()
    {
        return offset;
    }
}
