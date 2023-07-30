package com.example.rekhachitra.algorithm;

import com.example.rekhachitra.dataEncapsulatorClass.PossiblePaths;

import java.io.Serializable;
import java.util.Comparator;

public class PossiblePathsComparator implements Comparator<PossiblePaths>
{


    @Override
    public int compare(PossiblePaths possiblePaths, PossiblePaths t1)
    {
        return (possiblePaths.totalTime > t1.totalTime)?1:-1;
    }
}
