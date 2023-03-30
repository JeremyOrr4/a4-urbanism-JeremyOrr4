package ca.mcmaster.cas.se2aa4.a3.pathfinder.GraphADT;

import ca.mcmaster.cas.se2aa4.a2.io.*;

public class Point {
    
    String Point;

    Point(Structs.Segment Segment){
        this.Point = Segment.getV1Idx() + "" + Segment.getV1Idx();
    }
    
}
