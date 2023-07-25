package com.example.rekhachitra;

import java.io.Serializable;
import java.util.List;

public class PossiblePaths implements Serializable
{
    List<String> path;
    int totalTime;
    public PossiblePaths(List<String> path, int totalTime)
    {
        this.path = path;
        this.totalTime = totalTime;
    }
}
