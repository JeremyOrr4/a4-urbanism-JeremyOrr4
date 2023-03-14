package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.Whittaker;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.BoundedShape;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Irregular;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileType;

import java.util.ArrayList;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.White;

public class EntryPoint {
    public static Mesh meshTest(Mesh aMesh){


        System.out.println("hello");
        Whittaker w = new Whittaker(); 


       w.evaluateBiome(0, 0); 


        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh);
        return   lagoonMesh; 
    }

}

