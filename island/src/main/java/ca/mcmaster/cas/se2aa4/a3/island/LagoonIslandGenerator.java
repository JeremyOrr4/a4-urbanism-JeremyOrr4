package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Humidity;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.BoundedShape;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Irregular;
import ca.mcmaster.cas.se2aa4.a3.island.Shapes.Square;
import ca.mcmaster.cas.se2aa4.a3.island.Water.LakesFactory;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles.TileType;

import java.util.ArrayList;
import java.util.List;
/**Produces island shape and size as well as lagoon (optional)**/
public class LagoonIslandGenerator {


    public static Mesh LagoonMesh(Mesh aMesh,boolean lagoon,String Shape){
        Mesh shapedIsland;
        if (Shape.equals("Circle")){
            Circle shape = new Circle(1920/2, 1080/2, 400);
            IslandShaper shaper = new IslandShaper(shape);
            shapedIsland = shaper.generateShape(aMesh);
            if (lagoon) shapedIsland = IslandShaper.fillRegion(shapedIsland, shape.scale(0.4), TileType.LAGOON);
        }else if (Shape.equals("Square")){
            Square shape = new Square(1920/2,1080/2,800,800);
            IslandShaper shaper = new IslandShaper(shape);
            shapedIsland = shaper.generateShape(aMesh);
            if (lagoon) shapedIsland = IslandShaper.fillRegion(shapedIsland, shape.scale(0.3), TileType.LAGOON);
        }else {
            Irregular shape = new Irregular(1920/2, 1080/2,1380,750);
            IslandShaper shaper = new IslandShaper(shape);
            shapedIsland = shaper.generateShape(aMesh);
            if (lagoon) shapedIsland = IslandShaper.fillRegion(shapedIsland, shape.scale(0.4, 0.4), TileType.LAGOON);
        }


        

       BeachGenerator bg = new BeachGenerator(shapedIsland);
       Mesh lagoonIsland = bg.beachMesh();

     
       return lagoonIsland;



    }


    
}
