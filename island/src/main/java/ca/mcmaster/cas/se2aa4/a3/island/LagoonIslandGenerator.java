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
            Circle shape = new Circle(Extractor.MeshWidth/2, Extractor.MeshHeight/2, (int)Extractor.MinDimension*0.4);
            IslandShaper shaper = new IslandShaper(shape);
            shapedIsland = shaper.generateShape(aMesh);
            if (lagoon) shapedIsland = IslandShaper.fillRegion(shapedIsland, shape.scale(0.4), TileType.LAGOON);
        }else if (Shape.equals("Square")){
            Square shape = new Square(Extractor.MeshWidth/2,Extractor.MeshHeight/2,Extractor.MinDimension*0.6,Extractor.MinDimension*0.6);
            IslandShaper shaper = new IslandShaper(shape);
            shapedIsland = shaper.generateShape(aMesh);
            if (lagoon) shapedIsland = IslandShaper.fillRegion(shapedIsland, shape.scale(0.3), TileType.LAGOON);
        }else {
            Irregular shape = new Irregular(Extractor.MeshWidth/2, Extractor.MeshHeight/2,0.75*Extractor.MeshWidth,0.75*Extractor.MeshHeight);
            IslandShaper shaper = new IslandShaper(shape);
            shapedIsland = shaper.generateShape(aMesh);
            if (lagoon) shapedIsland = IslandShaper.fillRegion(shapedIsland, shape.scale(0.4, 0.4), TileType.LAGOON);
        }


        

       BeachGenerator bg = new BeachGenerator(shapedIsland);
       Mesh lagoonIsland = bg.beachMesh();

     
       return lagoonIsland;



    }


    
}
