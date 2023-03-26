package ca.mcmaster.cas.se2aa4.a3.island.Visualization;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

public class ElevationVisualizer {


    public static Mesh elevationView(Mesh aMesh){


        List<Polygon> newPolygons = new ArrayList<Polygon>(); 
        
        for (Polygon p : aMesh.getPolygonsList()){

            int elevation = (int)(Extractor.getPolyElevation(p)*255/1200); 

            elevation = Math.min(255, elevation);


            if (!Tiles.getTileType(p).equals("Water") && !Tiles.getTileType(p).equals("Lake")) {
                Polygon colored = Tiles.setColor(p, String.format("%d,%d,%d", elevation,elevation,elevation));
                newPolygons.add(colored);
            }else{
                newPolygons.add(p);
            }
            
        

        }

        return Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(newPolygons).build(); 



        
    }
    
}
