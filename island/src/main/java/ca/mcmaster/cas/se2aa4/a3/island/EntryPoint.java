package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.Whittaker;

public class EntryPoint {
    public static Mesh meshTest(Mesh aMesh){


        System.out.println("hello");
        Whittaker w = new Whittaker();


        //this thing calculate the biome given a temp and humidity
      System.out.println(w.evaluateBiome(629, 204));


        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh);
        return   lagoonMesh; 
    }

}

