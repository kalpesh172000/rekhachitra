package com.example.rekhachitra.dataEncapsulatorClass;

import java.io.Serializable;

public class BusStop implements Serializable {
    String name;
    int offset;

    public BusStop(String name, int offset) {
        this.name = name;
        this.offset = offset;
    }
}
