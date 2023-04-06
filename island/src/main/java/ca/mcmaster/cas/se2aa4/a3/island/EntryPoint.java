package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.BiomeFactory;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.BiomeTest;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.Whittaker;
import ca.mcmaster.cas.se2aa4.a3.island.City.CityTest;
import ca.mcmaster.cas.se2aa4.a3.island.City.PathFinderEntry;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.ElevationTest;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.ExampleElevationProfile;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.VolcanoProfile;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.*;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.BoundTest;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Irregular;
import ca.mcmaster.cas.se2aa4.a3.island.Visualization.ElevationVisualizer;
import ca.mcmaster.cas.se2aa4.a3.island.Visualization.HumidityView;
import ca.mcmaster.cas.se2aa4.a3.island.Water.LakesFactory;
import ca.mcmaster.cas.se2aa4.a3.island.Water.riverFactory;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**Class which incrementally adds elements of a complete island**/
public class EntryPoint {

    public static Mesh meshTest(Mesh aMesh,int lakes,int river, String vis,boolean lagoon,String Profile, int seed, String Shape,int height,int width,int cities){

        TileTest Tiletest = new TileTest();
        Tiletest.testSuite();

        ExtractionTest ExtractTest = new ExtractionTest();
        ExtractTest.testSuite();

        ElevationTest ElevTest = new ElevationTest();
        ElevTest.TestSuite();

        BiomeTest biomeTest = new BiomeTest();
        biomeTest.BiomeTest();

        BoundTest boundTest = new BoundTest();
        boundTest.TestSuite();

        HumidityTest humidTest = new HumidityTest();
        humidTest.TestSuite();

        CityTest CityTest = new CityTest();
        CityTest.TestSuite();

        Extractor.MeshHeight=height;
        Extractor.MeshWidth=width;
        if (height>width){
            Extractor.MinDimension=width;
        }else{
            Extractor.MinDimension=height;
        }

        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh,lagoon,Shape,seed);

        LakesFactory lf = new LakesFactory();
        lagoonMesh = lf.RandomLakes(lakes,lagoonMesh,seed);

        if (Profile.equals("Volcano")){
            lagoonMesh = Elevation.SetVertexElevation(lagoonMesh, new VolcanoProfile());
        }else{
            lagoonMesh = Elevation.SetVertexElevation(lagoonMesh, new ExampleElevationProfile());
        }

        Elevation Elev = new Elevation();
        lagoonMesh = Elev.addPolyElevation(lagoonMesh);

        riverFactory rf = new riverFactory();
        lagoonMesh = rf.riverGenerator(lagoonMesh,river, seed);

        Humidity Humid = new Humidity();
        lagoonMesh = Humid.GenerateHumidities(lagoonMesh);

        BiomeFactory biome = new BiomeFactory();
        lagoonMesh = biome.BiomeSetter(lagoonMesh);

        if (vis.equals("Humidity")){
            lagoonMesh = HumidityView.HumidityView(lagoonMesh);
        }else if (vis.equals("Elevation")){
            lagoonMesh = ElevationVisualizer.elevationView(lagoonMesh);
        }
        

        for(int j = 0; j < cities; j++){
            Random random = new Random();
            int StartNodeID = random.nextInt(700);

            for(int i = 0; i < 3; i++){ // 3 = paths
                PathFinderEntry PathFinder = new PathFinderEntry();
                lagoonMesh = PathFinder.CreateCity(lagoonMesh,StartNodeID);
            }
        }

        return  lagoonMesh;
    }

}

