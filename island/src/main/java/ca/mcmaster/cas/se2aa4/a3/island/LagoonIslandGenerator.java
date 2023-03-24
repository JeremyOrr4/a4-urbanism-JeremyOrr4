package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.IslandWater.LakesFactory;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Humidity;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Irregular;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles.TileType;

import java.util.ArrayList;
import java.util.List;

public class LagoonIslandGenerator {


    public static Mesh LagoonMesh(Mesh aMesh){

        //  Circle circle = new Circle(1920/2, 1080/2, 400); 
        Irregular irr = new Irregular(1920/2, 1080/2,1380,750); 
     

        //You can choose whatever shape class you want here: Circle,Square,Irregular 
        IslandShaper shaper = new IslandShaper(irr);


        Mesh shapedIsland = shaper.generateShape(aMesh); 

     //  shapedIsland = IslandShaper.fillRegion(shapedIsland, new Circle(1920/2,1080/2, 150), TileType.LAGOON); 
        
    //   shapedIsland = IslandShaper.fillRegion(shapedIsland, irr.scale(0.4, 0.4), TileType.LAGOON);

        

       BeachGenerator bg = new BeachGenerator(shapedIsland);
       Mesh lagoonIsland = bg.beachMesh();

     
       return lagoonIsland;



    }


    
}
