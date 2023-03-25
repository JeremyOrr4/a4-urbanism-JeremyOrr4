package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.Whittaker;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.*;
import ca.mcmaster.cas.se2aa4.a3.island.Visualization.ElevationVisualizer;
import ca.mcmaster.cas.se2aa4.a3.island.Visualization.HumidityView;
import ca.mcmaster.cas.se2aa4.a3.island.Water.LakesFactory;
import ca.mcmaster.cas.se2aa4.a3.island.Water.riverFactory;

import java.util.ArrayList;
import java.util.List;
public class EntryPoint {
    public static Mesh meshTest(Mesh aMesh,int lakes,int river, String vis,boolean lagoon,String Profile){


        System.out.println("test");
        Whittaker w = new Whittaker();

        //this thing calculate the biome given a temp and humidity
        System.out.println(w.evaluateBiome(629, 204));

        TileTest Tiletest = new TileTest();
        Tiletest.testSuite();
        ExtractionTest ExtractTest = new ExtractionTest();
        ExtractTest.testSuite();

        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh,lagoon);

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

