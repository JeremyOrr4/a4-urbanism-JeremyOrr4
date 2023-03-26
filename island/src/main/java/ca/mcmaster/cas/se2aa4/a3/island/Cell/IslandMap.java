package ca.mcmaster.cas.se2aa4.a3.island.Cell;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.BeachGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShaper;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.ExampleElevationProfile;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.BoundedShape;
import ca.mcmaster.cas.se2aa4.a3.island.Water.LakesFactory;

public class IslandMap {
 
    
Mesh mesh; 
List<Cell> cells = new ArrayList<Cell>(); 


List<Double> vertexElevations = new ArrayList<Double>(); 




boolean terrainGenerated=false;
boolean elevationAssigned = false;  



public IslandMap(Mesh mesh){

    this.mesh =mesh; 
    for(Polygon p : mesh.getPolygonsList()){       
        cells.add(new Cell(p, mesh));
    }

    for(int i=0; i<mesh.getVerticesCount(); i++){
        vertexElevations.add(0.0); 
    }

}


public void generateTerrain(BoundedShape shape){

    IslandShaper shaper = new IslandShaper(shape); 

    for(Cell cell : cells){
        if(shaper.isTerrain(cell)) cell.setToTerrain();
        else cell.setToOcean(); 
    }

    terrainGenerated = true; 
}


public void generateBeaches(){
    BeachGenerator.generateBeachCells(cells);
}


public void generateElevation(){


    List<Point> points = MeshOperations.vertexToPoint(mesh.getVerticesList()); 

    vertexElevations   =  Elevation.setVertexElevation(points, new ExampleElevationProfile()); 

    for(Cell cell : cells){
        cell.setCellElevation(vertexElevations);
    }

}

public void createLakes(){
    //LakesFactory.lol(cells, 120);

    LakesFactory.lol(cells, 7);
}




public Mesh toMesh(){

    List<Polygon> polygons = new ArrayList<Polygon>(); 
    for(Cell c : cells){
        polygons.add(c.toPolygon()); 
    }
    return Mesh.newBuilder().addAllVertices(mesh.getVerticesList()).addAllSegments(mesh.getSegmentsList()).addAllPolygons(polygons).build(); 


}








}
