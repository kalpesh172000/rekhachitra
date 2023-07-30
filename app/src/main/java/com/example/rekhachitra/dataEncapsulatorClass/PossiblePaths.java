package com.example.rekhachitra.dataEncapsulatorClass;

import java.io.Serializable;
import java.util.List;

public class PossiblePaths implements Serializable
{
    public List<String> path;
    public int totalTime;
    public PossiblePaths(List<String> path, int totalTime)
    {
        this.path = path;
        this.totalTime = totalTime;
    }
}
