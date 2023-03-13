package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.TileType;

public class LagoonIslandGenerator {


    public static Mesh LagoonMesh(Mesh aMesh){

        Circle circle = new Circle(1920/2, 1080/2, 400); 
        IslandShaper shaper = new IslandShaper(circle);


        Mesh shapedIsland = shaper.generateShape(aMesh); 

        shapedIsland = IslandShaper.fillRegion(shapedIsland, new Circle(1920/2,1080/2, 200), TileType.LAGOON); 


       BeachGenerator bg = new BeachGenerator(shapedIsland);
       Mesh lagoonIsland = bg.beachMesh(); 

        return lagoonIsland; 



    }

    
}
