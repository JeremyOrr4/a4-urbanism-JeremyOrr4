package ca.mcmaster.cas.se2aa4.a3.island.Cell;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public class Edge {


    Mesh parentMesh; 
    Segment segment; 


    public Edge(Segment s, Mesh mesh ){

        this.parentMesh = mesh; 
        this.segment = s; 
        
    }



    public Segment toSegment(){

        return Segment.newBuilder(this.segment).clearProperties().build(); 

    }
    
}
