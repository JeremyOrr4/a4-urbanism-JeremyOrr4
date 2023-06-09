package ca.mcmaster.cas.se2aa4.a3.island.Biomes;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Humidity;
import ca.mcmaster.cas.se2aa4.a3.island.MeshAttributes.Tiles;
import java.util.ArrayList;
import java.util.List;
/**Produces a biome given already produced island mesh by using humidity and elevation as a coordinate value which is plotted on a whittaker svg**/
public class BiomeFactory {
    public Structs.Mesh BiomeSetter(Structs.Mesh aMesh){
        Whittaker w = new Whittaker();
        List<Structs.Polygon> newPolygons = new ArrayList<>();
        for (Structs.Polygon p: aMesh.getPolygonsList()){
            Structs.Polygon newTile = p;
            String biome;
            try{
                biome = w.evaluateBiome(Extractor.getPolyHumidity(p),Extractor.getPolyElevation(p));
            }catch (Exception e){
                biome = "Land";
            }
            if (Tiles.getTileType(p).equals("Land")){
                if (biome.equals("tropical")){
                    newTile = Tiles.setBiome(newTile, Tiles.TileType.TROPICAL);
                }else if (biome.equals("subtropicalDesert")){
                    newTile = Tiles.setBiome(newTile, Tiles.TileType.TROPICALDESERT);
                }else if (biome.equals("taiga")){
                    newTile = Tiles.setBiome(newTile,Tiles.TileType.TAIGA);
                }else if (biome.equals("deciduous")){
                    newTile=Tiles.setBiome(newTile,Tiles.TileType.DECIDUOUS);
                }else if (biome.equals("savanna")){
                    newTile=Tiles.setBiome(newTile, Tiles.TileType.SAVANNA);
                }else if (biome.equals("seasonalForest")){
                    newTile=Tiles.setBiome(newTile,Tiles.TileType.SEASONALFOREST);
                }
            }
            newPolygons.add(newTile);
        }
        return Structs.Mesh.newBuilder().addAllVertices(aMesh.getVerticesList()).addAllSegments(aMesh.getSegmentsList()).addAllPolygons(newPolygons).build();
    }

    public  boolean isTropTile(Structs.Mesh aMesh,Structs.Polygon p){

        if(Tiles.getTileType(p).equals("Water")) return false;
        if(Tiles.getTileType(p).equals("Lagoon")) return false;
        if(Tiles.getTileType(p).equals("Beach")) return false;
        for (int i: p.getNeighborIdxsList()){


            String type = Tiles.getTileType(aMesh.getPolygonsList().get(i));


            if (type.equals("Lake")){
                return true;
            }
        }
        return false;
    }
}

