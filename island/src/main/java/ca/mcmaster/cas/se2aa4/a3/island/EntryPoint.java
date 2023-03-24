package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.Whittaker;
import ca.mcmaster.cas.se2aa4.a3.island.IslandWater.LakesFactory;
import ca.mcmaster.cas.se2aa4.a3.island.IslandWater.riverFactory;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.*;

import java.util.ArrayList;
import java.util.List;

public class EntryPoint {
    public static Mesh meshTest(Mesh aMesh,int lakes,int river){


        System.out.println("test");
        Whittaker w = new Whittaker();
        for (int i=-300;i<800;i+=50){
            for (int j =-300;j<800;j+=50){
                if (!(w.evaluateBiome(i,j)).equals("Invalid Biome")){
                    System.out.println(w.evaluateBiome(i,j));
                    System.out.println(i+", "+j);
                }
            }
        }


        //this thing calculate the biome given a temp and humidity
        System.out.println(w.evaluateBiome(629, 204));
        TileTest test = new TileTest();
        test.testSuite();

        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh);

        LakesFactory lf = new LakesFactory();
        lagoonMesh = lf.RandomLakes(lakes,lagoonMesh);

        lagoonMesh = Tiles.MasterPropertyFactory(lagoonMesh,river);
        return   lagoonMesh; 
    }

}

