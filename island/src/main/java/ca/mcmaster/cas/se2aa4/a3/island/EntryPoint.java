package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.Whittaker;
import ca.mcmaster.cas.se2aa4.a3.island.Cell.IslandMap;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.ElevationTest;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.*;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Irregular;
import ca.mcmaster.cas.se2aa4.a3.island.Visualization.ElevationVisualizer;
import ca.mcmaster.cas.se2aa4.a3.island.Visualization.HumidityView;
import ca.mcmaster.cas.se2aa4.a3.island.Water.LakesFactory;
import ca.mcmaster.cas.se2aa4.a3.island.Water.riverFactory;

import java.util.ArrayList;
import java.util.List;
public class EntryPoint {
    public static Mesh meshTest(Mesh aMesh,int lakes,int river, String vis,boolean lagoon,String Profile,String Shape){


        IslandMap islandMap = new IslandMap(aMesh); 

        islandMap.generateTerrain(new Irregular(1920/2, 1080/2, 1400, 650));

     
         islandMap.generateElevation();

        islandMap.generateBeaches();

        islandMap.createLakes();

        System.out.println("test");
        Whittaker w = new Whittaker();

        System.out.println(w.evaluateBiome(629, 204));

        TileTest Tiletest = new TileTest();
        Tiletest.testSuite();

        ExtractionTest ExtractTest = new ExtractionTest();
        ExtractTest.testSuite();

        ElevationTest ElevTest = new ElevationTest();
        ElevTest.TestSuite();

        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh,lagoon,Shape);

        LakesFactory lf = new LakesFactory();
        lagoonMesh = lf.RandomLakes(lakes,lagoonMesh);

        lagoonMesh = Tiles.MasterPropertyFactory(lagoonMesh,river,Profile);

        if (vis.equals("Humidity")){
            lagoonMesh = HumidityView.HumidityView(lagoonMesh);
        }else if (vis.equals("Elevation")){
            lagoonMesh = ElevationVisualizer.elevationView(lagoonMesh);
        }

        return  lagoonMesh;
    }

}

