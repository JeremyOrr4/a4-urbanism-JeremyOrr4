package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.Whittaker;

public class EntryPoint {
    public static Mesh meshTest(Mesh aMesh){


//        System.out.println("hello");
//        Whittaker w = new Whittaker();


       //w.evaluateBiome(0, 0);


        Mesh lagoonMesh = LagoonIslandGenerator.LagoonMesh(aMesh);
        return   lagoonMesh; 
    }

}

