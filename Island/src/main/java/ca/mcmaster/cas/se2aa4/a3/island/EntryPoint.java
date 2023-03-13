package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileType;

import java.util.ArrayList;

public class EntryPoint {
    public static Mesh meshTest(Mesh aMesh){
     
        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh);
        return   lagoonMesh; 

    

    }

}

