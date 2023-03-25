package ca.mcmaster.cas.se2aa4.a3.island;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

public class ElevationVisualizer {


    public static Mesh elevationView(Mesh aMesh){


        List<Polygon> newPolygons = new ArrayList<Polygon>(); 
        
        for (Polygon p : aMesh.getPolygonsList()){

            int elevation = (int)(Extractor.getPolyElevation(p)*255); 

            elevation = Math.min(255, elevation); 


            Polygon colored = Tiles.setColor(p, String.format("%d,%d,%d", elevation,elevation,elevation)); 
            
            
            newPolygons.add(colored); 
            
        

        }

        return Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(newPolygons).build(); 



        
    }
    
}
