package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.Whittaker;
import ca.mcmaster.cas.se2aa4.a3.island.IslandWater.LakesFactory;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Humidity;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;

import java.util.ArrayList;
import java.util.List;

public class EntryPoint {
    public static Mesh meshTest(Mesh aMesh,int lakes){


        System.out.println("hello");
        Whittaker w = new Whittaker();


        //this thing calculate the biome given a temp and humidity
       System.out.println(w.evaluateBiome(629, 204));

        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh);
        LakesFactory lf = new LakesFactory();
        lagoonMesh = lf.RandomLakes(lakes,lagoonMesh);
        lagoonMesh = Tiles.MasterPropertyFactory(lagoonMesh);
        return   lagoonMesh; 
    }

}

