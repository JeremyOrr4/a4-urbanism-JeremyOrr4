package ca.mcmaster.cas.se2aa4.a3.island.Visualization;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;
/**BONUS CLASS - produces island as a gradient which visualizes humidities**/
public class HumidityView {


    public static Structs.Mesh HumidityView(Structs.Mesh aMesh){


        List<Polygon> newPolygons = new ArrayList<Polygon>();

        for (Polygon p : aMesh.getPolygonsList()){

            int elevation = Extractor.getPolyHumidity(p)/3;


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