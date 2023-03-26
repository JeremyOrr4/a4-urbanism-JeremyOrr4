package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.BiomeFactory;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.BiomeTest;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.Whittaker;
import ca.mcmaster.cas.se2aa4.a3.island.Cell.IslandMap;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.ElevationTest;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.ExampleElevationProfile;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.VolcanoProfile;
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

        BiomeTest biomeTest = new BiomeTest();
        biomeTest.BiomeTest();

        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh,lagoon,Shape);

        LakesFactory lf = new LakesFactory();
        lagoonMesh = lf.RandomLakes(lakes,lagoonMesh);

        if (Profile.equals("Volcano")){
            lagoonMesh = Elevation.SetVertexElevation(lagoonMesh, new VolcanoProfile());
        }else{
            lagoonMesh = Elevation.SetVertexElevation(lagoonMesh, new ExampleElevationProfile());
        }

        Elevation Elev = new Elevation();
        lagoonMesh = Elev.addPolyElevation(lagoonMesh);

        riverFactory rf = new riverFactory();
        lagoonMesh = rf.riverGenerator(lagoonMesh,river);

        Humidity Humid = new Humidity();
        lagoonMesh = Humid.GenerateHumidities(lagoonMesh);

        BiomeFactory biome = new BiomeFactory();
        lagoonMesh = biome.BiomeSetter(lagoonMesh);

        if (vis.equals("Humidity")){
            lagoonMesh = HumidityView.HumidityView(lagoonMesh);
        }else if (vis.equals("Elevation")){
            lagoonMesh = ElevationVisualizer.elevationView(lagoonMesh);
        }

        return  lagoonMesh;
    }

}

